package world.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import graphics.Animation;
import graphics.Assets;
import system.GameConfig;

//bomba
public class Bomb extends AbstractObject {

	private Animation bombAnim;
	private boolean active;
	private boolean exploded = false;
	private boolean killEnemy = false;
	private ArrayList<Enemy> enemies;
	private ArrayList<Block> blocks;
	private ArrayList<Fire> fires;
	private Player player;
	private long time = 0;
	private long lastTime = System.currentTimeMillis();

	//costruttore e creazione dei fuochi intorno alla bomba
	public Bomb(int row, int col, Player player, boolean killEnemy) {
		super(row, col);
		this.player = player;
		bombAnim = new Animation(700, Assets.bomb);
		bombAnim.setFirst(true);
		this.killEnemy = killEnemy;
		active = true;
		enemies = new ArrayList<Enemy>();
		blocks = new ArrayList<Block>();
		fires = new ArrayList<Fire>();
		fires.add(new Fire(row, col));
		fires.add(new Fire(row + 1, col));
		fires.add(new Fire(row - 1, col));
		fires.add(new Fire(row, col + 1));
		fires.add(new Fire(row, col - 1));
	}

	//aggiornamento del tempo per l'esplosine e dei fuochi
	@Override
	public void update() {
		countdown();
		bombAnim.update();
		for (Fire fire : fires) {
			fire.update();
			if (fire.isExtingued())
				fires.remove(fire);
		}
	}

	//disegno
	@Override
	public void draw(Graphics g) {
		if (active && !exploded) {
			g.drawImage(bombAnim.getCurrentFrame(), x, y, dim + 5, dim, null);
		}
		if (exploded) {
			for (Fire fire : fires)
				fire.draw(g);
		}
	}

	//distruzione nemici, danno al giocatore e distruzione dei muri dopo 3 secondi
	public void explosion() {
		destroyEnemies();
		checkPlayer();
		if (time > 3000)
			destroyBlocks();
	}

	//distruzione muri in 5 posizioni: SOPRA,SOTTO,SINISTRA,DESTRA,CENTER
	private void destroyBlocks() {
		for (Block block : blocks) {
			//CENTER
			if (block.getRow() == this.row && block.getCol() == this.col) {
				blocks.remove(block);
				GameConfig.score[GameConfig.getCurrentLevel()] += GameConfig.pBlock;
			}
			//SOPRA
			if (block.getRow() == this.row - 1 && block.getCol() == this.col) {
				blocks.remove(block);
				GameConfig.score[GameConfig.getCurrentLevel()] += GameConfig.pBlock;
			}
			//SOTTO
			if (block.getRow() == this.row + 1 && block.getCol() == this.col) {
				blocks.remove(block);
				GameConfig.score[GameConfig.getCurrentLevel()] += GameConfig.pBlock;
			}
			//SINISTRA
			if (block.getRow() == this.row && block.getCol() == this.col - 1) {
				blocks.remove(block);
				GameConfig.score[GameConfig.getCurrentLevel()] += GameConfig.pBlock;
			}
			//DESTRA
			if (block.getRow() == this.row && block.getCol() == this.col + 1) {
				blocks.remove(block);
				GameConfig.score[GameConfig.getCurrentLevel()] += GameConfig.pBlock;
			}
		}
	}

	//distruzione nemici
	private void destroyEnemies() {
		//la bomba del nemico non uccide i nemici
		if (!killEnemy)
			return;
		
		for (Enemy enemy : enemies) {
			//CENTRO
			if (enemy.getRect().intersects(new Rectangle(col * dim, row * dim, dim, dim))) {
				Assets.splash.play();
				enemies.remove(enemy);
				GameConfig.score[GameConfig.getCurrentLevel()] += GameConfig.pEnemy;
			}
			//SINISTRA
			if (enemy.getRect().intersects(new Rectangle(col * dim - dim, row * dim, dim, dim))) {
				Assets.splash.play();
				enemies.remove(enemy);
				GameConfig.score[GameConfig.getCurrentLevel()] += GameConfig.pEnemy;
			}
			//DESTRA
			if (enemy.getRect().intersects(new Rectangle(col * dim + dim, row * dim, dim, dim))) {
				Assets.splash.play();
				enemies.remove(enemy);
				GameConfig.score[GameConfig.getCurrentLevel()] += GameConfig.pEnemy;
			}
			//SOPRA
			if (enemy.getRect().intersects(new Rectangle(col * dim, row * dim - dim, dim, dim))) {
				Assets.splash.play();
				enemies.remove(enemy);
				GameConfig.score[GameConfig.getCurrentLevel()] += GameConfig.pEnemy;
			}
			//SOTTO
			if (enemy.getRect().intersects(new Rectangle(col * dim, row * dim + dim, dim, dim))) {
				Assets.splash.play();
				enemies.remove(enemy);
				GameConfig.score[GameConfig.getCurrentLevel()] += GameConfig.pEnemy;
			}
		}
	}

	//Danneggiamento giocatore in una delle 5 posizioni se il player non è invincibile e riduzione della vita e punteggio
	public void checkPlayer() {
		if (player.getRect().intersects(new Rectangle(col * dim, row * dim, dim, dim)) && !player.getInvincible()) {
			GameConfig.life--;
			player.setInvincible(true);
			GameConfig.score[GameConfig.getCurrentLevel()] -= GameConfig.pDamage;
		} else if (player.getRect().intersects(new Rectangle(col * dim - dim, row * dim, dim, dim))
				&& !player.getInvincible()) {
			GameConfig.life--;
			player.setInvincible(true);
			GameConfig.score[GameConfig.getCurrentLevel()] -= GameConfig.pDamage;
		} else if (player.getRect().intersects(new Rectangle(col * dim + dim, row * dim, dim, dim))
				&& !player.getInvincible()) {
			GameConfig.life--;
			player.setInvincible(true);
			GameConfig.score[GameConfig.getCurrentLevel()] -= GameConfig.pDamage;
		} else if (player.getRect().intersects(new Rectangle(col * dim, row * dim - dim, dim, dim))
				&& !player.getInvincible()) {
			GameConfig.life--;
			player.setInvincible(true);
			GameConfig.score[GameConfig.getCurrentLevel()] -= GameConfig.pDamage;
		} else if (player.getRect().intersects(new Rectangle(col * dim, row * dim + dim, dim, dim))
				&& !player.getInvincible()) {
			GameConfig.life--;
			player.setInvincible(true);
			GameConfig.score[GameConfig.getCurrentLevel()] -= GameConfig.pDamage;
		}

	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	public void setWalls(ArrayList<Wall> walls) {
		for (Fire fire : fires)
			fire.setWalls(walls);
	}

	public boolean isExploded() {
		return exploded;
	}

	public void setExploded(boolean exploded) {
		this.exploded = exploded;
	}
	
	//conteggio per l'esplosione e le fiamme
	private void countdown() {
		if (active) {
			time += System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();
			if (time > 2000) {
				explosion();
				if (!exploded)
					Assets.explosionSound.play();
				exploded = true;
			}
			if (time > 3000) {
				time = 0;
				active = false;
			}
		}
	}
}
