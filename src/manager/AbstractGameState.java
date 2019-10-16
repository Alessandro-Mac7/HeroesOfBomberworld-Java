package manager;

import java.awt.Graphics;

public abstract class AbstractGameState {
	
	/*Stati astratti*/
	protected GameStateManager gameStateManager;
	public abstract void init();
	public abstract void update();
	public abstract void keyPressedEvent(int code);
	public abstract void draw(Graphics g);
	public abstract void keyReleasedEvent(int code);
}
