package system;

import java.text.SimpleDateFormat;
import java.util.Date;

import graphics.Assets;

//CONTIENE TUTTE LE INFO E I SETTING GENERALI
public class GameConfig {

	//Static Information
	public static final int SIZE_MAP = 20;
	public static final int DISPLAY_WIDTH = 640;
	public static final int DISPLAY_HEIGHT = 640;
	public static final int GAMEOBJECT_SIZE = 32;
	public static int life = 3;
	public static String playerName = null;
	/*Funzione che ritorna una stringa basasta sul tempo corrente*/
	public static String getName(){
		 Date currentDate = new Date();
	      SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-ddhhmmss");
	   
	     return "saved/savedlevel" + ft.format(currentDate) + ".txt";
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
	public static final char pVELOCITY = 'v';
	public static final char THEME_1 = 'N';
	public static final char THEME_2 = 'M';
	public static final char THEME_3 = 'T';
	public static char theme = THEME_1;
	
	//Point per object
	public static final int pEnemy = 100;
	public static final int pBlock = 10;
	public static final int pPup = 50;
	public static final int pDamage = 20;

	//Time level
	public static long levelTime = 0;
	public static long levelLastTime = 0;

	//GameState
	public static final int MENU_STATE = 0;
	public static final int SCORE_STATE = 1;
	public static final int SETTINGS_STATE = 2;
	public static final int CHOOSE_STATE = 3;
	public static final int LEVELCHOOSE_STATE = 4;
	public static final int CUSTOM_STATE = 5;
	public static final int LEVEL_STATE = 6;
	public static final int NEXTLEVEL_STATE = 7;	
	
	public static final int NUMBER_STATE = 8;
	public static final int NUM_LEVELS = 10;
	

	
	
	//Settings
	public static String player_type;
	public static String aiDifficult;
	private static int indexGameSong;
	public static int [] score = new int [NUM_LEVELS];
	public static int [] bestScore = new int [NUM_LEVELS];
	public static int scoreCustom = 0;
	private static int dimScoreList =0;
	
	public static int getBestScore(){
		if(score[currentLevel] > bestScore[currentLevel])
			bestScore[currentLevel] = score[currentLevel];
		return bestScore[currentLevel];
	}
	
	public static int getDimScoreList(){
		return dimScoreList;
	}
	
	public static void incrementDimScoreList(){
		dimScoreList++;
	}
	
	public static void initScore(){
		for(int i=0; i<score.length; i++){
			score[i] = 0;
			bestScore[i] = 0;
		}
	}
	
	public static int getTotalScore(){
		int tmp = 0;
		for(int i= 0; i< currentLevel+1; i++)
			tmp += score[i];
		return tmp;
	}
	
	public static int getIndexGameSong() {
		return indexGameSong;
	}

	public static void nextMusic() {
		if(indexGameSong < Assets.game_song.length - 1)
			indexGameSong++;
		else 
			indexGameSong=0;
		System.out.println(indexGameSong);
	}

	/* Level */
	private static int currentLevel;
	private static int unlockLevel;
	
	public static void nextUnlockLevel(){
		if (unlockLevel < NUM_LEVELS)
			unlockLevel++;
		
		
	}
	
	public static int getUnlockLevel() {
		return unlockLevel;
	}

	public static void setToUnlockLevel(int level) {
		if (level > NUM_LEVELS)
			return;
		else
			unlockLevel = level;
	}

	private static boolean unlockAllLevel;
	
	public static boolean isUnlockAllLevel() {
		return unlockAllLevel;
	}

	public static void setUnlockAllLevel(boolean unlockAllLevel) {
	    GameConfig.unlockAllLevel = unlockAllLevel;
	}

	private static boolean[] levels = new boolean[GameConfig.NUM_LEVELS];
	private static boolean win = false;
	private static boolean customLevel = false;
	private static String path = null;

	
	
	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		if (customLevel)
			GameConfig.path = path;
	}

	public static boolean isCustomLevel() {
		return customLevel;
	}

	public static void setCustomLevel(boolean customLevel) {
		win = false;
		GameConfig.customLevel = customLevel;
	}

	public static void initLevels() {
		for (int i = 1; i < GameConfig.NUM_LEVELS; i++)
			levels[i] = false;
		levels[0] = true;
		currentLevel = 0;
	}

	public static boolean getLevel(int index) {
		if (index < NUM_LEVELS)
			return false;
		return levels[index];
	}

	public static int getCurrentLevel() {
		return currentLevel;
	}

	public static void nextLevel() {
		if (currentLevel < NUM_LEVELS)
			currentLevel++;
		for (int i = 0; i < currentLevel; i++) {
			levels[i] = true;
		}
	}

	public static boolean getWin() {
		if (currentLevel == NUM_LEVELS - 1)
			win = true;
		else if	(customLevel && life > 0 )
			win = true;
		else if(unlockLevel == NUM_LEVELS -1)
			win = true;
		return win;
	}
	
	public static void resetWin(){
		win = false;
	}

	public static void setToSpecificLevel(int level) {
		if (level > NUM_LEVELS)
			return;
		else
			currentLevel = level;
	}

}
