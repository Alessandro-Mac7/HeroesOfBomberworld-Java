package world.object;

import java.awt.Graphics;

import graphics.Assets;
import system.GameConfig;

//TRAPPOLA
public class Trap extends AbstractObject{
	
	private boolean up;
	private long time, lastTime;
	private int velocity;

	public Trap(int row, int col) {
		super(row, col);
		time = 0;
		velocity = 1000;
		lastTime = System.currentTimeMillis();
	}

	//spuntoni della trappola up e down
	@Override
	public void update() {
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(time > velocity && up){
			time = 0;
			setUp(false);
		}
		
		if(time > velocity && !up){
			time = 0;
			setUp(true);
		}
		
	
		
	}

	//disegna gli spuntoni fuori o dentro
	@Override
	public void draw(Graphics g) {
	
		if(up)
			g.drawImage(Assets.trap[1],	x, y, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, null);
		else
			g.drawImage(Assets.trap[0], x, y, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, null);
	}

	public boolean isUp() {
		return up;
	}

	//quando gli spuntoni sono fuori emette il suono
	public void setUp(boolean up) {
		this.up = up;
			Assets.trapSound.play();
		
	}
	

}
