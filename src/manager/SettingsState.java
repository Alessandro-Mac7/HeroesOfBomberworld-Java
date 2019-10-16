package manager;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import graphics.Assets;
import graphics.AnimatedBackground;
import loader.FileManager;
import system.GameConfig;

public class SettingsState extends AbstractGameState{
	
	/*Variabile per la scelta*/
	private int currentChoose;
	private boolean typed;
	
	/*Sfondo*/
	private AnimatedBackground animatedBackground;
	
	public SettingsState(GameStateManager gameStateManager){
		this.gameStateManager=gameStateManager;
		init();
		
	}
	@Override
	public void init() {
		
		currentChoose=0;
		typed = false;
		animatedBackground = new AnimatedBackground();
		animatedBackground.setVelocity(0.3,0);

	}

	public void selectOption(){
		
		if(currentChoose==0){
			if(typed==false){
				Assets.menu_song.stop();
				typed=true;
			}
			else{
				Assets.menu_song.play();
				typed=false;
			}
			
		}
			
		if(currentChoose==1){
			if(FileManager.loadGame())
				gameStateManager.setCurrentState(GameConfig.LEVELCHOOSE_STATE);
		}
		
		if(currentChoose==2){
			}
						
		
	}
	@Override
	public void update() {
		animatedBackground.update();
	}

	@Override
	public void keyPressedEvent(int code) {
		Assets.menu_click_sound.play();
		
		if(code == KeyEvent.VK_ENTER){	
			Assets.menu_click_sound.play();
			selectOption();
		}
		if(code == KeyEvent.VK_UP) {
			Assets.menu_click_sound.play();
			currentChoose--;
			if(currentChoose == -1) {
				currentChoose = Assets.on_off_music0.length - 1;
			}
		}
		if(code == KeyEvent.VK_DOWN) {
			Assets.menu_click_sound.play();
			currentChoose++;
			if(currentChoose == Assets.on_off_music0.length) {
				currentChoose = 0;
			}
		}
		if(code == KeyEvent.VK_ESCAPE){
			Assets.menu_click_sound.play();
			gameStateManager.setCurrentState(GameConfig.MENU_STATE);
		}		
	}

	@Override
	public void draw(Graphics g) {
	
		/*Pulisco lo schermo*/
		g.clearRect(0, 0, GameConfig.DISPLAY_WIDTH, GameConfig.DISPLAY_HEIGHT);
 		
		/*Disegno lo sfondo*/
		g.drawImage(Assets.backgroundSettings, 0, 0, null);
		animatedBackground.draw(g);
			
		/*Stampo i titoli*/
		g.drawImage(Assets.titleSettings, 10, Assets.titleSettings.getHeight()-100, null);
		g.drawImage(Assets.titleMusic, GameConfig.DISPLAY_WIDTH/5, (GameConfig.DISPLAY_HEIGHT/3), null);
		
		/*Stampo le immagini opzioni*/
		
		if(typed==false){
			if(currentChoose==0){
				g.drawImage(Assets.on_off_music0[0], (GameConfig.DISPLAY_WIDTH/4) + 50, (GameConfig.DISPLAY_HEIGHT/3), null);
			}
			else
				g.drawImage(Assets.on_off_music1[0], (GameConfig.DISPLAY_WIDTH/4) + 50 , (GameConfig.DISPLAY_HEIGHT/3) , null);	
		}
		else
		{
			if(currentChoose==0){
				g.drawImage(Assets.on_off_music0[1], (GameConfig.DISPLAY_WIDTH/4) + 50 , (GameConfig.DISPLAY_HEIGHT/3) , null);
			}
			else
				g.drawImage(Assets.on_off_music1[1], (GameConfig.DISPLAY_WIDTH/4) + 50, (GameConfig.DISPLAY_HEIGHT/3) , null);	
		
		}
			if(currentChoose==1){
				g.drawImage(Assets.load_game[1], (GameConfig.DISPLAY_WIDTH/5), (GameConfig.DISPLAY_HEIGHT/2) , null);
			}
			else
				g.drawImage(Assets.load_game[0], (GameConfig.DISPLAY_WIDTH/5), (GameConfig.DISPLAY_HEIGHT/2) , null);	
				
	}

	@Override
	public void keyReleasedEvent(int code) {}

}
