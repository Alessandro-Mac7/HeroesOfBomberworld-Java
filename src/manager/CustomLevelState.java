package manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;

import graphics.Assets;
import loader.ImageLoader;
import graphics.AnimatedBackground;
import system.GameConfig;

public class CustomLevelState extends AbstractGameState {

	/*Rappresenta la scelta corrente*/
	private int currentChoose;
	
	/*Variabile che rappresenta la cartella*/
	private File directory;
	/*Lista dei file nella carte in txt*/
	private String[] listFile;
	/*lista delle immagini nella cartella*/
	private String[] listImage;
	/*Filter di testo e filter di immagini*/
	private FilenameFilter filterText;
	private FilenameFilter filterImage;
	/*Percorso della cartella*/
	private String directoryPath;
	/*Font per il drawString*/
	private Font font;
	/*Sfondo animato e immagini*/
	private AnimatedBackground animatedBackground;
	/*Array di immagini che rappresentano la preview*/
	private BufferedImage[] currentImage;
	
	public CustomLevelState(GameStateManager gameStateManager){
		this.gameStateManager = gameStateManager;
		init();
	}
	
	@Override
	public void init() {
		
		/*Inizializzo le variabili*/
	    directoryPath = "resources/custom_level/";
		directory = new File(directoryPath);
		filterFile();
		listFile  = directory.list(filterText);
		listImage = directory.list(filterImage);
		
		font = new Font("Arial", Font.PLAIN, 18);

		
		/*Setto lo sfondo e imposto la velocità dell'animazione*/
	    animatedBackground = new AnimatedBackground();
	    animatedBackground.setVelocity(0.3, 0);
	    
	    /*Setto la dimensione dell'array di immagini pari alla dimensione dei file*/
	    currentImage = new BufferedImage [listImage.length];
	    	for(int i = 0; i < currentImage.length; i++)
	    		currentImage[i] = ImageLoader.loadImage(directoryPath + listImage[i]);
	}
	
	public void filterFile(){
		 filterText = new FilenameFilter() {
		      public boolean accept(File dir, String name) {
		          return name.endsWith(".txt");
		      }
		  };
		 filterImage = new FilenameFilter() {
		      public boolean accept(File dir, String name) {
		          return name.endsWith(".png");
		      }
		  };
		 
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
			currentChoose--;
			if(currentChoose == -1) {
				currentChoose = listFile.length - 1;
			}
		}
		if(code == KeyEvent.VK_DOWN) {
			Assets.menu_click_sound.play();
			currentChoose++;
			if(currentChoose == listFile.length) {
				currentChoose = 0;
			}
		}
		if(code == KeyEvent.VK_ESCAPE){
			Assets.menu_click_sound.play();
			ChooseState.setFirstChoose(true);
			GameConfig.setCustomLevel(false);
			this.gameStateManager.setCurrentState(GameConfig.CHOOSE_STATE);
		}
		
	}
	
	public void selectOption() {
		
		for(int i = 0; i < listFile.length; i++ )
		{
			if(currentChoose == i){
				GameConfig.setCustomLevel(true);
				GameConfig.setPath(directoryPath + listFile[i]);
				System.out.println(directoryPath + listFile[i]);
				this.gameStateManager.setCurrentState(GameConfig.LEVEL_STATE);
			}
				
		}
	}
	
	@Override
	public void draw(Graphics g) {
		
		/*Pulisco*/
		g.clearRect(0, 0, 640, 640);
		
		/*Disegno sfondo, animazione  e titolo*/
		g.drawImage(Assets.backgroundLevelChoose2, 0, 0, null);
		animatedBackground.draw(g);
		g.drawImage(Assets.customTitle, 0, 0, null);
		
		/*Setto il font*/
		g.setFont(font);
		
		/*Stampo le opzioni*/
		for(int i=0; i<listFile.length; i++){
			if(currentChoose==i){
				g.setColor(Color.RED);
				g.drawString(listFile[i], 0, (GameConfig.DISPLAY_HEIGHT/3 - 40) + 20 * i);
				g.drawImage(currentImage[currentChoose], GameConfig.DISPLAY_WIDTH/2 - 2,(GameConfig.DISPLAY_HEIGHT/2) - ((currentImage[currentChoose].getHeight()/2)/2), 
						GameConfig.DISPLAY_WIDTH/2, GameConfig.DISPLAY_HEIGHT/2, null);
			}
			else{
				g.setColor(Color.ORANGE);
				g.drawString(listFile[i], 0, (GameConfig.DISPLAY_HEIGHT/3 - 40) +20 * i);
				g.drawImage(currentImage[currentChoose], GameConfig.DISPLAY_WIDTH/2 - 2, (GameConfig.DISPLAY_HEIGHT/2) - ((currentImage[currentChoose].getHeight()/2)/2),
						GameConfig.DISPLAY_WIDTH/2, GameConfig.DISPLAY_HEIGHT/2, null);
			}
			
		}		
		/*Stampo il subtitlo*/
		g.drawImage(Assets.customSubTitle,GameConfig.DISPLAY_WIDTH/2, (GameConfig.DISPLAY_HEIGHT/5), null );
		
		
	}

	@Override
	public void keyReleasedEvent(int code) {}

	

}
