package manager;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import graphics.Assets;
import graphics.AnimatedBackground;
import system.GameConfig;

public class LevelChooseState extends AbstractGameState {

	/*Variabile di sceta corrente*/
	private int currentChoose;
	/*Livelli bloccati*/
	private int lockedLevel;
	/*Animazione background*/
	private AnimatedBackground animatedBackground;

	
	public LevelChooseState(GameStateManager gameStateManager) {
		this.gameStateManager = gameStateManager;
		init();
	}

	@Override
	public void init() {

		currentChoose = 0;
		
		/* Setto lo sfondo e imposto la velocità dell'animazione */
		animatedBackground = new AnimatedBackground();
		animatedBackground.setVelocity(0.3, 0);
	}

	@Override
	public void update() {
		
		animatedBackground.update();
		
		/*Controllo per verificare o tutti i livelli sbloccati o non*/
		if(GameConfig.isUnlockAllLevel())
			lockedLevel = 0;
		else
			lockedLevel = GameConfig.NUM_LEVELS - (GameConfig.getCurrentLevel() + 1);
				
	}

	@Override
	public void keyPressedEvent(int code) {

		if (code == KeyEvent.VK_ENTER) {
			Assets.select.play();
			selectOption();
		}
		if (code == KeyEvent.VK_RIGHT) {
			Assets.menu_click_sound.play();
			currentChoose++;
			if (currentChoose == GameConfig.NUM_LEVELS - lockedLevel) {
				currentChoose = (GameConfig.NUM_LEVELS - lockedLevel) - 1;
				Assets.locked.play();
			}
		}
		if (code == KeyEvent.VK_LEFT) {
			Assets.menu_click_sound.play();
			currentChoose--;
			if (currentChoose == -1) {
				currentChoose = (GameConfig.NUM_LEVELS - lockedLevel) - 1;
			}
		}
		if (code == KeyEvent.VK_DOWN) {
			Assets.menu_click_sound.play();
			currentChoose+=5;
			if (currentChoose >= GameConfig.NUM_LEVELS - lockedLevel) {
				currentChoose = currentChoose - 5;
				Assets.locked.play();
			}
		}
		if (code == KeyEvent.VK_UP) {
			Assets.menu_click_sound.play();
			currentChoose-=5;
			if (currentChoose < 0) {
				currentChoose = currentChoose + 5;
				Assets.locked.play();
			}
		}
		if (code == KeyEvent.VK_ESCAPE) {
			Assets.menu_click_sound.play();
			if(GameConfig.isUnlockAllLevel())
				GameConfig.setUnlockAllLevel(false);
			ChooseState.setFirstChoose(true);
			this.gameStateManager.setCurrentState(GameConfig.CHOOSE_STATE);
		}

	}

	public void selectOption() {

		for (int i = 0; i < GameConfig.NUM_LEVELS - lockedLevel; i++)
			if (currentChoose == i) {
				if(GameConfig.isUnlockAllLevel()){
					GameConfig.setToUnlockLevel(currentChoose);
					this.gameStateManager.setCurrentState(GameConfig.LEVEL_STATE);
				}
				else{
					GameConfig.setToSpecificLevel(currentChoose);
					this.gameStateManager.setCurrentState(GameConfig.LEVEL_STATE);
				}
					
			}

	}

	@Override
	public void draw(Graphics g) {
		/* Pulisco lo schermo */
		g.clearRect(0, 0, GameConfig.DISPLAY_WIDTH, GameConfig.DISPLAY_HEIGHT);

		/* Disegno lo sfondo */
		g.drawImage(Assets.backgroundLevelChoose, 0, 0, null);

		/* Disegno lo sfondo animato */
		animatedBackground.draw(g);

		/* Titolo */
		g.drawImage(Assets.titleLevelChoose, 10, 0, null);

		/* Variabile per suddividere le immagine una volta raggiunte la 5° posizione */

		int tmp = 0;

		for (int i = 0; i < GameConfig.NUM_LEVELS - lockedLevel; i++) {

			if (i < 5) {
				if (currentChoose == i)
					g.drawImage(Assets.numberOn[i], GameConfig.GAMEOBJECT_SIZE + i * 128, (GameConfig.DISPLAY_HEIGHT / 4), GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE * 2, null);
				else
					g.drawImage(Assets.number[i], GameConfig.GAMEOBJECT_SIZE + i * 128, (GameConfig.DISPLAY_HEIGHT / 4), GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE * 2, null);
			} else {
				if (currentChoose == i)
					g.drawImage(Assets.numberOn[i], GameConfig.GAMEOBJECT_SIZE + tmp * 128, (GameConfig.DISPLAY_HEIGHT / 2), GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE * 2, null);
				else
					g.drawImage(Assets.number[i], GameConfig.GAMEOBJECT_SIZE + tmp * 128, (GameConfig.DISPLAY_HEIGHT / 2), GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE * 2, null);

				tmp++;
			}
		}

		if( !GameConfig.isUnlockAllLevel()){
		for (int i = GameConfig.getCurrentLevel() + 1; i < GameConfig.NUM_LEVELS; i++) {
			if (i < 5)
				g.drawImage(Assets.lock, GameConfig.GAMEOBJECT_SIZE + i * 128, (GameConfig.DISPLAY_HEIGHT / 4), GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE * 2, null);
			else {
				g.drawImage(Assets.lock, GameConfig.GAMEOBJECT_SIZE + tmp * 128, (GameConfig.DISPLAY_HEIGHT / 2), GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE * 2, null);
				tmp++;
			}

		}
		}

	}

	@Override
	public void keyReleasedEvent(int code) {
		// TODO Auto-generated method stub

	}

}
