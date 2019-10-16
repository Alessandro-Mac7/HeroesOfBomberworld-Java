package manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import graphics.AnimatedBackground;
import graphics.Assets;
import loader.FileManager;
import system.GameConfig;

public class NextLevelState extends AbstractGameState {
	

	/*Variabile per la scelta*/
	private int currentChoose;
	/*Font per il drawString*/
	private Font font;
	/*variabile per registrare il punteggio*/
	private String score;
	/*Sfondo animato*/
	private AnimatedBackground bm;
	/*boolean che permette di controllare se è gia stato salvato una volta il livello*/
	private boolean saved;
	

	public NextLevelState(GameStateManager gameStateManager) {
		this.gameStateManager = gameStateManager;
		init();
		
	}

	@Override
	public void init() {
		/*Inizializzo le variabili necessarie per il prossimo livello o non*/
		Assets.game_song[GameConfig.getIndexGameSong()].stop();
		GameConfig.nextMusic();
		/*reimposto il save a false ogni qual volta che si passa in questo stato*/
		saved = false;
		/*setto la velocita dell'animazione e le varie variabili*/
		bm = new AnimatedBackground();
	    bm.setVelocity(0.3, 0);
		currentChoose = 0;
		font = new Font("Goudy Stout", Font.PLAIN, 48);
		/*prendo lo score intero dal game config*/
		score = String.valueOf(GameConfig.score[GameConfig.getCurrentLevel()]);
		
		/*Se ho perso le vite o ho vinto devo inserire il nome*/
		if(GameConfig.life == 0 || GameConfig.getWin())
			openDialog();
		
		/*avvio una canzone a seconda del momento in cui si trova lo stato*/
		if(GameConfig.life == 0)
			Assets.gameOver.play();
		else if(GameConfig.getWin()){
			Assets.winSound.play();
			GameConfig.resetWin();
			
		}
		else
			Assets.nextLevel.play();
		
	}
	
	@Override
	public void update() {
		bm.update();
	}
	
	public void openDialog(){
			
		 JDialog dialog = new JDialog(new JFrame(), "Insert your name!");
		        JTextField textfield = new JTextField(10);
		        textfield.setHorizontalAlignment(JTextField.CENTER);
		        textfield.addKeyListener(new KeyAdapter(){

					@Override
					public void keyPressed(KeyEvent e) {
						int code = e.getKeyCode();
						if(code == KeyEvent.VK_ENTER){
							if(textfield.getText().length()!=0){
								GameConfig.playerName = textfield.getText();
								writeRecord();
								dialog.dispose();
							}
							
						}
					}
		        });
		        dialog.add(textfield);
		        dialog.setSize(200, 80);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
	}

	public void writeRecord(){
		/*scrivo il record*/
		new FileManager().addRecord();
	}
	

	@Override
	public void keyPressedEvent(int code) {
				
		if(code == KeyEvent.VK_ENTER){	
			Assets.select.play();
			selectOption();
		}
		
		if(!saved){
			if(code == KeyEvent.VK_UP) {
				Assets.menu_click_sound.play();
				if(GameConfig.life > 0 && !GameConfig.getWin()){
					currentChoose--;
					if(currentChoose == -1) {
						currentChoose = Assets.options_NextLevelOff.length - 1;
					}
				}
				else{
					currentChoose--;
					if(currentChoose == -1) {
						currentChoose = Assets.options_WinLoseOff.length - 1;
					}
				}
						
			}
			if(code == KeyEvent.VK_DOWN) {
				Assets.menu_click_sound.play();
				if(GameConfig.life > 0 && !GameConfig.getWin()){	
					currentChoose++;
					if(currentChoose == Assets.options_NextLevelOff.length) {
						currentChoose = 0;
					}
				}
				else{
					currentChoose++;
					if(currentChoose == Assets.options_WinLoseOn.length) {
						currentChoose = 0;
					}
		
				}
			}
		}
		else
		{
			if(code == KeyEvent.VK_UP) {
				Assets.menu_click_sound.play();
				if(GameConfig.life > 0 && !GameConfig.getWin()){
					currentChoose--;
					if(currentChoose == -1) {
						currentChoose = Assets.options_NextLevelOff.length - 2;
					}
				}
				else{
					currentChoose--;
					if(currentChoose == -1) {
						currentChoose = Assets.options_WinLoseOff.length - 2;
					}
				}
						
			}
			if(code == KeyEvent.VK_DOWN) {
				Assets.menu_click_sound.play();
				if(GameConfig.life > 0 && !GameConfig.getWin()){	
					currentChoose++;
					if(currentChoose == Assets.options_NextLevelOff.length - 1) {
						currentChoose = 0;
					}
				}
				else{
					currentChoose++;
					if(currentChoose == Assets.options_WinLoseOn.length - 1) {
						currentChoose = 0;
					}
		
				}
			}
		}
		
	}
	
	public void selectOption(){
		
		if(GameConfig.life>0 && !GameConfig.getWin()){
			if(currentChoose==0){
				if(GameConfig.isUnlockAllLevel()){
					GameConfig.nextUnlockLevel();
					gameStateManager.setCurrentState(GameConfig.LEVEL_STATE);
			}
			else{
				GameConfig.nextLevel();
				gameStateManager.setCurrentState(GameConfig.LEVEL_STATE);
		
				}
			}
			if(currentChoose==1){
				if(GameConfig.isCustomLevel())
					GameConfig.scoreCustom = 0;
				else
					GameConfig.score[GameConfig.getCurrentLevel()] = 0;
				gameStateManager.setCurrentState(GameConfig.LEVEL_STATE);
			}
			
			if(currentChoose==2){
				if(GameConfig.isCustomLevel()){
					GameConfig.setCustomLevel(false);
				}
				

				if(GameConfig.isUnlockAllLevel())
					GameConfig.setUnlockAllLevel(false);
				
				
				Assets.menu_song.play();
				gameStateManager.setCurrentState(GameConfig.MENU_STATE);
			}
			
			if(currentChoose==3){
				if(!saved){
					saved = true;
					FileManager.saveGame();
				}

			}				
		}
		else{
			if(currentChoose==0){
				if(GameConfig.isCustomLevel())
					GameConfig.scoreCustom = 0;
				else
					GameConfig.score[GameConfig.getCurrentLevel()] = 0;
				gameStateManager.setCurrentState(GameConfig.LEVEL_STATE);
			}
			if(currentChoose==1){
				if(GameConfig.isCustomLevel())
					GameConfig.setCustomLevel(false);
				
				if(GameConfig.isUnlockAllLevel())
					GameConfig.setUnlockAllLevel(false);
				
				Assets.menu_song.play();
				
				gameStateManager.setCurrentState(GameConfig.MENU_STATE);
			}
			
			if(currentChoose==2){
				if(!saved){
					saved = true;
					FileManager.saveGame();
				}
			}			
		}
		
	}

	@Override
	public void draw(Graphics g) {
		
		g.drawImage(Assets.backgroundNextLevel, 0, 0, null);
		
		bm.draw(g);
		
		if(!GameConfig.isCustomLevel()){
			if(GameConfig.score[GameConfig.getCurrentLevel()] < 300)
				g.drawImage(Assets.stars[0], (GameConfig.DISPLAY_WIDTH/2) - Assets.stars[2].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/3)-100, null);
			else if(GameConfig.score[GameConfig.getCurrentLevel()] < 700)
					g.drawImage(Assets.stars[1], (GameConfig.DISPLAY_WIDTH/2) - Assets.stars[2].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/3)-100, null);
				else
					g.drawImage(Assets.stars[2], (GameConfig.DISPLAY_WIDTH/2) - Assets.stars[2].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/3)-100, null);
		}
		else{
				if(GameConfig.scoreCustom < 300)
					g.drawImage(Assets.stars[0], (GameConfig.DISPLAY_WIDTH/2) - Assets.stars[2].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/3)-100, null);
				else if(GameConfig.scoreCustom < 700)
						g.drawImage(Assets.stars[1], (GameConfig.DISPLAY_WIDTH/2) - Assets.stars[2].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/3)-100, null);
					else
						g.drawImage(Assets.stars[2], (GameConfig.DISPLAY_WIDTH/2) - Assets.stars[2].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/3)-100, null);
			}
			
		FontMetrics fm = g.getFontMetrics();
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(score, (GameConfig.DISPLAY_WIDTH/2) - fm.stringWidth(score)*3 , (GameConfig.DISPLAY_HEIGHT/4)-64);
		
		if(GameConfig.life > 0 && !GameConfig.isCustomLevel() && !GameConfig.getWin()){
			/*Stampo le immagini opzioni*/
			if(!saved){
				for(int i=0; i<Assets.options_NextLevelOff.length; i++){
					if(currentChoose==i)
						g.drawImage(Assets.options_NextLevelOn[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.options_NextLevelOn[i].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/2)-100 + i * 50, null);
					else
						g.drawImage(Assets.options_NextLevelOff[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.options_NextLevelOff[i].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/2)-100 + i * 50, null);
					
					}
				}
			
			else{
				for(int i=0; i<Assets.options_NextLevelOff.length - 1; i++){
					if(currentChoose==i)
						g.drawImage(Assets.options_NextLevelOn[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.options_NextLevelOn[i].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/2)-100 + i * 50, null);
					else
						g.drawImage(Assets.options_NextLevelOff[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.options_NextLevelOff[i].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/2)-100 + i * 50, null);
					
					}

				int tmp = Assets.options_NextLevelOff.length-1;
				
				g.drawImage(Assets.savedBlocked2,  (GameConfig.DISPLAY_WIDTH/2) - Assets.options_NextLevelOff[tmp].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/2)-100 + tmp * 50, null);

			}
		}
		
		else if(GameConfig.getWin())
		{
			if(!saved){
				g.drawImage(Assets.win, 0, (GameConfig.DISPLAY_HEIGHT/3) - Assets.win.getHeight()/2 + 20, null);
				
				for(int i=0; i<Assets.options_WinLoseOn.length; i++){
					if(currentChoose==i)
						g.drawImage(Assets.options_WinLoseOn[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.options_WinLoseOn[i].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/2) + i * 50, null);
					else
						g.drawImage(Assets.options_WinLoseOff[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.options_WinLoseOff[i].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/2) + i * 50, null);
				
				}
				
			}
			else{
		
				g.drawImage(Assets.win, 0, (GameConfig.DISPLAY_HEIGHT/3) - Assets.win.getHeight()/2 + 20, null);
					
					for(int i=0; i<Assets.options_WinLoseOn.length - 1; i++){
						if(currentChoose==i)
							g.drawImage(Assets.options_WinLoseOn[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.options_WinLoseOn[i].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/2) + i * 50, null);
						else
							g.drawImage(Assets.options_WinLoseOff[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.options_WinLoseOff[i].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/2) + i * 50, null);
					
					}
					int tmp = Assets.options_WinLoseOff.length -1;
					
					g.drawImage(Assets.savedBlocked2,  (GameConfig.DISPLAY_WIDTH/2) - Assets.options_WinLoseOff[tmp].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/2) + tmp * 50, null);
		
			}
				
		}
		else{
			if(!saved){
				g.drawImage(Assets.lose, 0, (GameConfig.DISPLAY_HEIGHT/3) - Assets.lose.getHeight()/2 + 20, null);
				
				for(int i=0; i<Assets.options_WinLoseOn.length; i++){
					if(currentChoose==i)
						g.drawImage(Assets.options_WinLoseOn[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.options_WinLoseOn[i].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/2) + i * 50, null);
					else
						g.drawImage(Assets.options_WinLoseOff[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.options_WinLoseOff[i].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/2) + i * 50, null);
				
				}
			}
			else
			{
				g.drawImage(Assets.lose, 0, (GameConfig.DISPLAY_HEIGHT/3) - Assets.lose.getHeight()/2 + 20, null);
				
				for(int i=0; i<Assets.options_WinLoseOn.length - 1; i++){
					if(currentChoose==i)
						g.drawImage(Assets.options_WinLoseOn[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.options_WinLoseOn[i].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/2) + i * 50, null);
					else
						g.drawImage(Assets.options_WinLoseOff[i], (GameConfig.DISPLAY_WIDTH/2) - Assets.options_WinLoseOff[i].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/2) + i * 50, null);
				
				}
				
				int tmp = Assets.options_WinLoseOff.length -1;
				
				g.drawImage(Assets.savedBlocked2,  (GameConfig.DISPLAY_WIDTH/2) - Assets.options_WinLoseOff[tmp].getWidth()/2, (GameConfig.DISPLAY_HEIGHT/2) + tmp * 50, null);

			}
		}
		
	}

	@Override
	public void keyReleasedEvent(int code) {
		// TODO Auto-generated method stub

	}

}
