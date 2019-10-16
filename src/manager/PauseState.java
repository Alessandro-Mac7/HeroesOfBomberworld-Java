package manager;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import graphics.Assets;
import loader.FileManager;
import system.GameConfig;

public class PauseState {

	private LevelState level;
	private boolean inPause;
	private int currentChoose;
	private boolean saved;
	
	public PauseState(LevelState level){
		this.level = level;
		init();
	}

	public void init() {
		inPause = false;
		saved = false;
	}

	public void setInPause(boolean pause){
		this.inPause = pause;
	}
	
	public boolean getInPause(){
		return inPause;
	}

	public void keyPressedEvent(int code) {	
		
		if(code == KeyEvent.VK_ENTER){	
			Assets.menu_click_sound.play();
			selectOption();
		}
		if(!saved){
			if(code == KeyEvent.VK_UP) {
				Assets.menu_click_sound.play();
				currentChoose--;
				if(currentChoose == -1) {
					currentChoose = Assets.options_pauseOff.length - 1;
				}
			}
			if(code == KeyEvent.VK_DOWN) {
				Assets.menu_click_sound.play();
				currentChoose++;
				if(currentChoose == Assets.options_pauseOff.length) {
					currentChoose = 0;
				}
			}
		}
		else{
			if(code == KeyEvent.VK_UP) {
				Assets.menu_click_sound.play();
				currentChoose--;
				if(currentChoose == -1) {
					currentChoose = Assets.options_pauseOff.length - 2;
				}
			}
			if(code == KeyEvent.VK_DOWN) {
				Assets.menu_click_sound.play();
				currentChoose++;
				if(currentChoose == Assets.options_pauseOff.length -1) {
					currentChoose = 0;
				}
			}
		}
			
		
	}


	public void selectOption(){
	
		if(currentChoose==0){
			inPause = false;
		}
			
		if(currentChoose==1){
			GameConfig.score[GameConfig.getCurrentLevel()] = 0;
			level.gameStateManager.setCurrentState(GameConfig.LEVEL_STATE);
		}
		
		if(currentChoose==2){
			if(GameConfig.isCustomLevel())
				GameConfig.setCustomLevel(false);
			
			if(GameConfig.isUnlockAllLevel())
				GameConfig.setUnlockAllLevel(false);
			
			Assets.game_song[GameConfig.getIndexGameSong()].stop();
			Assets.menu_song.play();
			
			
			level.gameStateManager.setCurrentState(GameConfig.MENU_STATE);
		}
		
		if(currentChoose==3){
			saveGame();
		}
						
		
	}
	private void saveGame() {
		if(!saved){
			saved = true;
			FileManager.saveGame();
		}

	}

	public void draw(Graphics g) {
		
		/*Disegno sfondo*/
		drawBackground(g);
		
				
		/*Disegno le opzioni*/
		if(!saved){
			for(int i=0; i<Assets.options_pauseOff.length; i++){
				if(currentChoose==i)
					g.drawImage(Assets.options_pauseOn[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.options_pauseOn[0].getWidth(), (GameConfig.DISPLAY_HEIGHT/5) + i * 50, null);
				else
					g.drawImage(Assets.options_pauseOff[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.options_pauseOff[0].getWidth(), (GameConfig.DISPLAY_HEIGHT/5) + i * 50, null);
			}	
		}
		else
		{
			for(int i=0; i<Assets.options_pauseOff.length-1; i++){
				if(currentChoose==i)
					g.drawImage(Assets.options_pauseOn[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.options_pauseOn[0].getWidth(), (GameConfig.DISPLAY_HEIGHT/5) + i * 50, null);
				else
					g.drawImage(Assets.options_pauseOff[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.options_pauseOff[0].getWidth(), (GameConfig.DISPLAY_HEIGHT/5) + i * 50, null);
			}
			
			int tmp = Assets.options_pauseOff.length-1;
			
			g.drawImage(Assets.savedBlocked1, (GameConfig.DISPLAY_WIDTH/2) - Assets.options_pauseOn[0].getWidth(), (GameConfig.DISPLAY_HEIGHT/5) + tmp  * 50, null);
		}
	}
	
	private void drawBackground(Graphics g){
		
		g.drawImage(Assets.backgroundPause, 0, 0, null);
		g.drawImage(Assets.pause, GameConfig.DISPLAY_WIDTH - GameConfig.GAMEOBJECT_SIZE, 0, null);
		
	}


}
