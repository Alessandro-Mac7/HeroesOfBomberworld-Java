package manager;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import graphics.Assets;
import graphics.AnimatedBackground;
import system.GameConfig;

public class ChooseState extends AbstractGameState{
	
	/*Variabile per riconoscere la scelta corrente*/
	private int currentChoose;
	
	/*Immmagini Titolo1 e titolo2 e sfondo*/
	private AnimatedBackground animatedBackground;
	
	/*Boolean che riconosce in quale dei due stati è la scelta (player/Livelli)*/
	private static boolean firstChoose = false;
	
	/*Per dare l'effetto click*/
	private boolean clickedDx;
	private boolean clickedSx;

	/*Scelte personaggi*/
	private String [] player_type = {
			"ninja",
			"tank",
			"mage"
	};
		
	public ChooseState(GameStateManager gameStateManager){
		this.gameStateManager=gameStateManager;
		init();
	}
	
	@Override
	public void init() {
	
	/*Inizializzo le variabili*/
	currentChoose=0;
	clickedDx = false;
	clickedSx = false;
	
    /*Imposto la velocità dell'animazione dello sfondo animato*/
	animatedBackground = new AnimatedBackground();
	animatedBackground.setVelocity(0.3, 0);
	
	}
	
	public void selectOption(){
		
		/*Controllo se la scelta è stata fatta allora si passa alla scelta dei livelli*/
		if(firstChoose){
			if(currentChoose==0){
				Assets.ulti_ninja.play();
				GameConfig.player_type=(player_type[currentChoose]);
			}
				
			if(currentChoose==1){
				Assets.ulti_tank.play();
				GameConfig.player_type=(player_type[currentChoose]);
			}
			if(currentChoose==2){
				Assets.ulti_mage.play();
				GameConfig.player_type=(player_type[currentChoose]);
			}
			currentChoose = 0;
		}
		
		else{
			
			if(currentChoose==0){
				gameStateManager.setCurrentState(GameConfig.LEVELCHOOSE_STATE);		
			}
			
			if(currentChoose==1){
				GameConfig.setUnlockAllLevel(true);;
				gameStateManager.setCurrentState(GameConfig.LEVELCHOOSE_STATE);		
			}
			
			if(currentChoose==2){
				gameStateManager.setCurrentState(GameConfig.CUSTOM_STATE);		
			}
		}				
	}
	
	@Override
	public void update() {
		animatedBackground.update();
	}

	@Override
	public void keyPressedEvent(int code) {
		
		/*Controllo che la prima scelta sia false per scorrere i personaggi*/
		if(firstChoose == false){
		
		if(code == KeyEvent.VK_ENTER){
			Assets.select.play();
			firstChoose=true;
			selectOption();
		}
		if(code == KeyEvent.VK_LEFT) {
			Assets.menu_click_sound.play();
			clickedSx = true;
			currentChoose--;
			if(currentChoose == -1) {
				currentChoose = player_type.length - 1;
			}
		}
		if(code == KeyEvent.VK_RIGHT) {
			Assets.menu_click_sound.play();
			clickedDx = true;
			currentChoose++;
			if(currentChoose == player_type.length) {
				currentChoose = 0;
			}
		}
		if(code == KeyEvent.VK_ESCAPE){
			Assets.menu_click_sound.play();
			gameStateManager.setCurrentState(GameConfig.MENU_STATE);
		}		
		}
		
		else {
			

			if(code == KeyEvent.VK_ENTER){
				Assets.select.play();
				firstChoose=false;
				selectOption();
			}
			if(code == KeyEvent.VK_LEFT) {
				Assets.menu_click_sound.play();
				currentChoose--;
				if(currentChoose == -1) {
					currentChoose = Assets.option_ChooseOff.length - 1;
				}
			}
			if(code == KeyEvent.VK_RIGHT) {
				Assets.menu_click_sound.play();
				currentChoose++;
				if(currentChoose == Assets.option_ChooseOff.length) {
					currentChoose = 0;
				}
			}
			if(code == KeyEvent.VK_ESCAPE){
				Assets.menu_click_sound.play();
				firstChoose = false;
				gameStateManager.setCurrentState(GameConfig.CHOOSE_STATE);
			}
		}
		
	}

	@Override
	public void draw(Graphics g) {
		/*Pulisco Display*/
		g.clearRect(0, 0, GameConfig.DISPLAY_WIDTH, GameConfig.DISPLAY_HEIGHT);
		
		/*Disegno lo sfondo*/
		g.drawImage(Assets.backgroundChooseScreen, 0, 0, null);
		animatedBackground.draw(g);
	
		
		/*Disegno i personaggi se la prima scelta ancora non è stata fatta*/
		if(firstChoose == false){
			g.drawImage(Assets.title1ChooseScreen, 10, 0, null);

			for(int i=0; i<player_type.length; i++){
				
				if(currentChoose==i ){
					g.drawImage(Assets.player[i] ,GameConfig.DISPLAY_WIDTH/2 - Assets.player[i].getWidth()/2, GameConfig.DISPLAY_HEIGHT/4, null);
					g.drawImage(Assets.infoPlayer[i], GameConfig.DISPLAY_WIDTH/2 - Assets.infoPlayer[i].getWidth()/2, GameConfig.DISPLAY_HEIGHT/2, null);
					
				}
			}
			
			if(clickedDx){
				g.drawImage(Assets.arrowDx[1], 420, GameConfig.DISPLAY_HEIGHT/3, null);
				g.drawImage(Assets.arrowSx[0],  130, GameConfig.DISPLAY_HEIGHT/3, null);
			}
			else if(clickedSx){
				g.drawImage(Assets.arrowDx[0], 420, GameConfig.DISPLAY_HEIGHT/3, null);
				g.drawImage(Assets.arrowSx[1],  130, GameConfig.DISPLAY_HEIGHT/3, null);
			}
			else{
				g.drawImage(Assets.arrowDx[0], 420, GameConfig.DISPLAY_HEIGHT/3, null);
				g.drawImage(Assets.arrowSx[0],  130, GameConfig.DISPLAY_HEIGHT/3, null);
			}
				
		}
			
		/*Disegno le opzioni di difficoltà altrimenti*/
		else {
			g.drawImage(Assets.title2ChooseScreen, 10, 0, null);
			
			for(int i =0; i<Assets.option_ChooseOff.length; i++){
					
					if(currentChoose==i )
						g.drawImage(Assets.option_ChooseOn[i], ((GameConfig.DISPLAY_WIDTH/3) - Assets.option_ChooseOn[i].getWidth()+32) + i*180, (GameConfig.DISPLAY_HEIGHT/2)-64, null);
					else
						g.drawImage(Assets.option_ChooseOff[i], ((GameConfig.DISPLAY_WIDTH/3) - Assets.option_ChooseOff[i].getWidth()+32) + i*180, (GameConfig.DISPLAY_HEIGHT/2)-64, null);
					
					}
		
		}
		
	}

	@Override
	public void keyReleasedEvent(int code) {
		
		if(code == KeyEvent.VK_LEFT) {
			clickedSx = false;
		}
		if(code == KeyEvent.VK_RIGHT) {
			clickedDx = false;
		}
		
	}
	
	
	public static boolean isFirstChoose() {
		return firstChoose;
	}

	public static void setFirstChoose(boolean firstChoose) {
		ChooseState.firstChoose = firstChoose;
	}


}

