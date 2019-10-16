package manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import graphics.Assets;
import loader.FileManager;
import system.GameConfig;
import world.Map;
import world.object.Block;
import world.object.Bomb;
import world.object.Boss;
import world.object.Enemy;
import world.object.Lever;
import world.object.Manhole;
import world.object.Player;
import world.object.Trap;
import world.object.Wall;
import world.object.powerUp.Life;
import world.object.powerUp.MaxBomb;
import world.object.powerUp.PowerUp;
import world.object.powerUp.Star;
import world.object.powerUp.Velocity;

public class LevelState extends AbstractGameState {

	// lista degli oggetti
	private FileManager leveloader;
	private Map map;
	private Player player;
	private PauseState pause;
	private Lever lever;
	private Manhole manhole;
	private ArrayList<Bomb> bombEnemy;
	private ArrayList<Wall> walls;
	private ArrayList<Enemy> enemies;
	private ArrayList<Block> blocks;
	private ArrayList<Bomb> bombs;
	private ArrayList<Trap> traps;
	private ArrayList<PowerUp> powerUps;
	private boolean pressed = false;
	private static boolean viewKey = false;

	// costruttore che alloca la memeria per le liste e invoca l'init per
	// inizializzare la mappa
	public LevelState(GameStateManager gameStateManager) {
		this.gameStateManager = gameStateManager;
		map = new Map();
		walls = new ArrayList<Wall>();
		enemies = new ArrayList<Enemy>();
		blocks = new ArrayList<Block>();
		bombs = new ArrayList<Bomb>();
		bombEnemy = new ArrayList<Bomb>();
		traps = new ArrayList<Trap>();
		powerUps = new ArrayList<PowerUp>();
		leveloader = new FileManager(map.getCharMap());
		init();
	}

	// caricamento mappa dal file di testo e aggiunta degli oggetti alle varie liste
	@Override
	public void init() {
		// livello personalizzato o default
		if (GameConfig.isCustomLevel())
			leveloader.loadCustomMap();
		else{
			if(GameConfig.isUnlockAllLevel()){
				GameConfig.score[GameConfig.getUnlockLevel()] = 0;
				leveloader.loadMap(GameConfig.getUnlockLevel());
			}
			else{
				GameConfig.score[GameConfig.getCurrentLevel()] = 0;
				leveloader.loadMap(GameConfig.getCurrentLevel());
			}
				
		}
			
		pause = new PauseState(this);
		
		// ciclo di creazione e aggiunta degli oggetti alle liste
		for (int i = 0; i < GameConfig.SIZE_MAP; i++) {
			for (int j = 0; j < GameConfig.SIZE_MAP; j++) {
				switch (map.getCharMapPos(i, j)) {
				case GameConfig.PLAYER:
					player = new Player(i, j);
					break;
				case GameConfig.ENEMY:
					enemies.add(new Enemy(i, j));
					break;
				case GameConfig.BOSS:
					enemies.add(new Boss(i, j));
					break;
				case GameConfig.WALL:
					walls.add(new Wall(i, j));
					break;
				case GameConfig.BLOCK:
					blocks.add(new Block(i, j));
					break;
				case GameConfig.TRAP:
					traps.add(new Trap(i, j));
					break;
				case GameConfig.LEVER:
					blocks.add(new Block(i, j));
					lever = new Lever(i, j);
					break;
				case GameConfig.MANHOLE:
					blocks.add(new Block(i, j));
					manhole = new Manhole(i, j);
					break;
				case GameConfig.pLIFE:
					blocks.add(new Block(i, j));
					powerUps.add(new Life(i, j));
					break;
				case GameConfig.pSTAR:
					blocks.add(new Block(i, j));
					powerUps.add(new Star(i, j));
					break;
				case GameConfig.pBOMB:
					blocks.add(new Block(i, j));
					powerUps.add(new MaxBomb(i, j));
					break;
				case GameConfig.pVELOCITY:
					blocks.add(new Block(i, j));
					powerUps.add(new Velocity(i, j));
					break;
				default:
					break;
				}
			}
		}
		// passatto delle liste al player per i vari calcoli
		player.setEnemies(enemies);
		player.setWalls(walls);
		player.setBlocks(blocks);
		player.setBombs(bombs);
		player.setTraps(traps);
		// per ogni nemico inizializzo le liste con gli oggetti realmente creati
		for (Enemy enemy : enemies) {
			enemy.setBlocks(blocks);
			enemy.setWalls(walls);
			enemy.setBombs(bombs);
			enemy.setPlayer(player);
			enemy.setTrap(traps);
			enemy.setEnemy(enemies);
		}
	}

	// aggiornamento ogni 60 volte
	@Override
	public void update() {
		// se il gioco non è in pausa e il giocatore è ancora in vita
		if (!pause.getInPause()) {
			if (GameConfig.life > 0) {
				// countdown calcola il tempo dell'invincibilità
				player.countdown();
				player.update();

				// se il giocatore trova la leva apre la porta
				if (player.getRow() == lever.getRow() && player.getCol() == lever.getCol() && !lever.isFound()) {
					lever.setFound(true);
					manhole.setOpen(true);
					lever.update();
					manhole.update();
				}

				// se il giocatore ha tirato la leva ed ha raggiunto la porta passa al livello
				// successivo
				if (player.getRow() == manhole.getRow() && player.getCol() == manhole.getCol() && lever.isFound())
					gameStateManager.setCurrentState(GameConfig.NEXTLEVEL_STATE);

				// ciclo che scorre ogni powerup e se il player lo tocca allora viene potenziato
				// e rimuove il powerup dalla lista aumentando il punteggio
				for (PowerUp powerUp : powerUps) {
					if (player.getRow() == powerUp.getRow() && player.getCol() == powerUp.getCol()) {
						if (powerUp instanceof Life && GameConfig.life < player.getMaxLife())
							GameConfig.life++;
						if (powerUp instanceof MaxBomb)
							player.increaseBomb();
						if (powerUp instanceof Star)
							player.setInvincible(true);
						if (powerUp instanceof Velocity)
							player.speedUp();
						Assets.powerUp.play();
						powerUps.remove(powerUp);
						if(GameConfig.isCustomLevel())
							GameConfig.scoreCustom += GameConfig.pPup;
						else
							GameConfig.score[GameConfig.getCurrentLevel()] += GameConfig.pPup;
					}
				}

				// permette il movimento in alto o in basso degli spuntoni delle trappole
				for (Trap trap : traps)
					trap.update();

				// per ogni nemico aggiorna la posizione, se il nemico è un boss controlla se è
				// possibile rilasciare una bomba ogni X secondi
				for (Enemy enemy : enemies) {
					enemy.update();
					if (enemy instanceof Boss)
						if (enemy.isBombDroppable()) {
							bombEnemy.add(new Bomb(enemy.getRow(), enemy.getCol(), player, false));
							enemy.bombIsDropped();
						}
				}

				// ciclo che rimuove le bombe esplose e quelle attive gli assegna gli oggetti da
				// poter distruggere
				for (Bomb bomb : bombs) {
					if (bomb.isActive() == false)
						bombs.remove(bomb);
					bomb.setWalls(walls);
					bomb.setBlocks(blocks);
					bomb.setEnemies(enemies);
					bomb.update();
				}

				// come le bombe normali solo che non distruggono i nemici poichè sono lasciate
				// dai boss
				for (Bomb bomb : bombEnemy) {
					if (bomb.isActive() == false)
						bombEnemy.remove(bomb);
					bomb.setWalls(walls);
					bomb.setBlocks(blocks);
					bomb.setEnemies(enemies);
					bomb.update();
				}
				
				if(enemies.size()==0){
					setViewKey(true);
				}

			} else // schermata di passaggio al livello successivo
				this.gameStateManager.setCurrentState(GameConfig.NEXTLEVEL_STATE);
		}
		
	}

	public void draw(Graphics g) {
		
		
		// se il gioco non è in pausa disegna
		if (!pause.getInPause()) {
			// pulisce lo schermo
			g.clearRect(0, 0, GameConfig.DISPLAY_WIDTH, GameConfig.DISPLAY_HEIGHT);

			map.draw(g);

			for (Wall wall : walls)
				wall.draw(g);

			lever.draw(g);

			manhole.draw(g);

			for (PowerUp powerUp : powerUps)
				powerUp.draw(g);

			for (Block block : blocks)
				block.draw(g);

			for (Trap trap : traps)
				trap.draw(g);

			for (Enemy enemy : enemies)
				enemy.draw(g);

			for (Bomb bomb : bombs)
				if (bomb.isActive())
					bomb.draw(g);

			for (Bomb bomb : bombEnemy)
				if (bomb.isActive())
					bomb.draw(g);

			// disegna la sfera di invincibilità del player
			if (player.getInvincible())
				g.drawImage(Assets.shield, player.getX(), player.getY(), GameConfig.GAMEOBJECT_SIZE,
						GameConfig.GAMEOBJECT_SIZE, null);

			player.draw(g);

			// barra della vita in alto
			g.drawImage(Assets.lifeHUD[GameConfig.life - 1], 0, -2, null);
			
			// barra delle bombe
			g.drawImage(Assets.bombHUD[player.getBombMax() - 1], (GameConfig.DISPLAY_WIDTH - Assets.bombHUD[player.getBombMax() - 1].getWidth()) / 2,
					GameConfig.DISPLAY_HEIGHT - Assets.bombHUD[player.getBombMax() - 1].getHeight(), null);

		} else // gioco in pausa
			pause.draw(g);
		
		Font font = new Font("Arial", Font.BOLD, 16);

		g.setColor(Color.ORANGE);
		g.setFont(font);
		
		g.drawString("Best Score: "+ String.valueOf(GameConfig.getBestScore()), GameConfig.DISPLAY_WIDTH - 160, 16);
	}

	@Override
	public void keyPressedEvent(int code) {

		// ESC mette il gioco in pausa
		if (code == KeyEvent.VK_ESCAPE)
			pause.setInPause(true);

		// musica on o off
		if (code == KeyEvent.VK_M && !pressed) {
			pressed = true;
			Assets.game_song[GameConfig.getIndexGameSong()].stop();
		} else if (code == KeyEvent.VK_M && pressed) {
			pressed = false;
			Assets.game_song[GameConfig.getIndexGameSong()].play();
		}

		// N cambia le musiche
		if (code == KeyEvent.VK_N) {
			Assets.game_song[GameConfig.getIndexGameSong()].stop();
			GameConfig.nextMusic();
			Assets.game_song[GameConfig.getIndexGameSong()].play();
			Assets.game_song[GameConfig.getIndexGameSong()].loop(15);
		}

		// se non è in pausa muove il player
		if (!pause.getInPause()) {
			player.setDirection(code);

		} else
			pause.keyPressedEvent(code);

	}

	@Override
	public void keyReleasedEvent(int code) {
		player.setDirection(0);

		// rilascio dello SPAZIO crea una bomba
		if (code == KeyEvent.VK_SPACE) {
			if (bombs.size() < player.getBombMax()) {

				// non può creare la bomba sulle trappole
				for (Trap trap : traps)
					if (trap.getRow() == player.getRow() && trap.getCol() == player.getCol())
						return;

				// non può creare la bomba sulle bombe
				for (Bomb bomb : bombs)
					if (bomb.getRow() == player.getRow() && bomb.getCol() == player.getCol())
						return;

				// non può creare la bomba sulla leva
				if (lever.getRow() == player.getRow() && lever.getCol() == player.getCol())
					return;

				// non può creare la bomba sulla porta
				if (manhole.getRow() == player.getRow() && manhole.getCol() == player.getCol())
					return;

				bombs.add(new Bomb(player.getRow(), player.getCol(), player, true));
				Assets.drop.play();
			}
		}
	}

	public static boolean isViewKey() {
		return viewKey;
	}

	public static void setViewKey(boolean viewKey) {
		LevelState.viewKey = viewKey;
	}
}
