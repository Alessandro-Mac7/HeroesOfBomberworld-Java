package launcher;

import graphics.Assets;
import graphics.Display;
import system.GameConfig;

public class Launcher {
	
	//Classe che avvia il gioco
	public static void main(String[] args) {
		
		/*Carica e setta le impostazioni iniziali del gioco*/
		GameConfig.initLevels();
		GameConfig.initScore();
		Assets.loadAssets();
		
		/*Crea il frame e lo avvia*/
		Display display= new Display();
		display.start();
	}
}
