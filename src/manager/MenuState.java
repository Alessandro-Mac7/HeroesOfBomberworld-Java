

package manager;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import graphics.Assets;
import graphics.AnimatedBackground;
import system.GameConfig;

public class MenuState extends AbstractGameState {
	
	/*Variabile per la scelta*/
	private int currentChose;
	
	/*Immagine di sfondo e immagine titolo*/
	private AnimatedBackground animatedBackground;
	
	public MenuState(GameStateManager gameStateManager){
		this.gameStateManager=gameStateManager;
		init();
		
	}
	
	@Override
	public void init() {
		
		/*Variabile che si occupa della scelta corrente e setto il titolo del gioco*/
		currentChose=0;

		/*Setto lo sfondo e imposto la velocità dell'animazione*/
	    animatedBackground = new AnimatedBackground();
	    animatedBackground.setVelocity(0.3, 0);
	    
	}

	public void selectOption(){
	
		if(currentChose==0){
				gameStateManager.setCurrentState(GameConfig.CHOOSE_STATE);
		}
			
		if(currentChose==1){
			gameStateManager.setCurrentState(GameConfig.SETTINGS_STATE);
		}
		
		if(currentChose==2){
			gameStateManager.setCurrentState(GameConfig.SCORE_STATE);
		}
		
		if(currentChose==3){
			System.exit(0);
		}				
		
	}

	@Override
	public void update() {
		animatedBackground.update();
	}

	@Override
	public void keyPressedEvent(int code) {
				
		if(code == KeyEvent.VK_ENTER){	
			Assets.select.play();
			selectOption();
		
		}
		if(code == KeyEvent.VK_UP) {
			Assets.menu_click_sound.play();
			currentChose--;
			if(currentChose == -1) {
				currentChose = Assets.option_MenuOff.length - 1;
			}
		}
		if(code == KeyEvent.VK_DOWN) {
			Assets.menu_click_sound.play();
			currentChose++;
			if(currentChose == Assets.option_MenuOff.length) {
				currentChose = 0;
			}
		}
		
	}
	
	
	@Override
	public void draw(Graphics g) {
			
		/*Pulisco lo schermo*/
		g.clearRect(0, 0, GameConfig.DISPLAY_WIDTH, GameConfig.DISPLAY_HEIGHT);
 		
		
		/*Disegno la parte inferiore dello sfondo*/
		g.drawImage(Assets.backgroundMenu, 0, 0, null);
		

		/*Disegno lo sfondo*/
		animatedBackground.draw(g);
		
		/*Stampo il titolo*/
		g.drawImage(Assets.titleMenu, 10, Assets.titleMenu.getHeight()-70, null);
		
		
		/*Stampo le immagini opzioni*/
		for(int i=0; i<Assets.option_MenuOff.length; i++){
			if(currentChose==i)
				g.drawImage(Assets.option_MenuOn[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.option_MenuOn[i].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/3)-50 + i * 50, null);
			else
				g.drawImage(Assets.option_MenuOff[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.option_MenuOff[i].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/3)-50 + i * 50, null);
			
		}
		
		
	}
	@Override
	public void keyReleasedEvent(int code) {}

}
