package manager;
import java.awt.Graphics;
import graphics.Assets;
import system.GameConfig;

public class GameStateManager {

	private AbstractGameState [] gameState;
	private int currentState;
	
	public GameStateManager(){
		
		gameState = new AbstractGameState[GameConfig.NUMBER_STATE];
		currentState = GameConfig.MENU_STATE;
		Assets.menu_song.play();    
		loadState(currentState);
	}
	
	private void loadState(int state){
		
		if(state==GameConfig.MENU_STATE)
			gameState[state]=new MenuState(this);
		if(state==GameConfig.SCORE_STATE)
			gameState[state]=new ScoreState(this);
		if(state==GameConfig.SETTINGS_STATE)
			gameState[state]=new SettingsState(this);
		if(state==GameConfig.CHOOSE_STATE)
			gameState[state]=new ChooseState(this);
		if(state==GameConfig.LEVELCHOOSE_STATE)
			gameState[state]=new LevelChooseState(this);
		if(state==GameConfig.CUSTOM_STATE)
			gameState[state]=new CustomLevelState(this);
		if(state==GameConfig.LEVEL_STATE){
			gameState[state]=new LevelState(this);
			Assets.menu_song.stop();
			Assets.game_song[GameConfig.getIndexGameSong()].play();
			Assets.game_song[GameConfig.getIndexGameSong()].loop(15);;
		}
		if(state==GameConfig.NEXTLEVEL_STATE)
			gameState[state]=new NextLevelState(this);
	
	}
	
	private void resetState(int state){
		gameState[state]=null;
		
	}
	public void update() {
		try {
		gameState[currentState].update();
		} catch (Exception e){}
	}

	public void keyPressedEvent(int code) {
		try {
			gameState[currentState].keyPressedEvent(code);
		} catch (Exception e){}
	}
	
	public void keyReleasedEvent(int code) {
		gameState[currentState].keyReleasedEvent(code);
	}

	public int getCurrentState() {
		return currentState;
	}

	public void setCurrentState(int currentState) {
		resetState(currentState);
		this.currentState = currentState;
		loadState(currentState);
	}
	
	public void draw(Graphics g){
	try {
		gameState[currentState].draw(g);
	} catch (Exception e){}
	}
	
}
