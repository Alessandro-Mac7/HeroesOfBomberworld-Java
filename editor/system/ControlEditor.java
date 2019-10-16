package system;

import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControlEditor {
	
	//Static Information
	public static final int SIZE_MAP = 20;
	public static final int DISPLAY_WIDTH = 640;
	public static final int DISPLAY_HEIGHT = 640;
	public static final int GAMEOBJECT_SIZE = 32;
	public static final int BUTTON_SIZE = 42;
	
	public static String getName(){
		 Date currentDate = new Date();
	      SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-ddhhmmss");
	     
	     return "resources/custom_level/customlevel" + ft.format(currentDate) + ".txt";
	}
	public static String getScreenName(){
		 Date currentDate = new Date();
	      SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-ddhhmmss");
	     
	     return "resources/custom_level/customlevel" + ft.format(currentDate) + ".png";
	}
		
	//Map Element
	public static final char WALL = 'w';
	public static final char PLAYER = 'p';
	public static final char ENEMY = 'n';
	public static final char BOSS = 'h';
	public static final char EMPTY = 'e';
	public static final char BLOCK = 'b';
	public static final char TRAP = 't';
	public static final char LEVER = 'k';
	public static final char MANHOLE = 'm';
	public static final char pLIFE = 'l';
	public static final char pBOMB = 'u';
	public static final char pSTAR = 's';
	public static final char pBOOST = 'v';

	/*Theme*/
	public final static char NINJA = 'N';
	public final static char TANK = 'T';
	public final static char MAGE = 'M';
	private static char currentTheme = NINJA;
	
	//ControlImage
	private static BufferedImage editorImage;
	private static BufferedImage background = AssetsEditor.background_ninja;
	public static boolean deleteIsActive=false;
	public static char type = WALL;
	
	public static void setBackgroundEditor(BufferedImage bkg){
		background = bkg;
	}
	
	public static BufferedImage getBackgroundEditor(){
		return  background;
	}
	
	public static void paintImage(BufferedImage img){
		editorImage = img;
	}
	
	public static BufferedImage getImageEditor(){
		return editorImage;
	}

	public static char getCurrentTheme() {
		return currentTheme;
	}

	public static void setCurrentTheme(char currentTheme) {
		ControlEditor.currentTheme = currentTheme;
		updateImageEditor();
	}
	
	private static void updateImageEditor(){
		switch(ControlEditor.getCurrentTheme()){
		case ControlEditor.NINJA:
			ControlEditor.paintImage(AssetsEditor.wallImage);
			break;
		case ControlEditor.TANK:
			ControlEditor.paintImage(AssetsEditor.wallTank);
			break;
		case ControlEditor.MAGE:
			ControlEditor.paintImage(AssetsEditor.wallMage);
			break;
		}
	}
}
