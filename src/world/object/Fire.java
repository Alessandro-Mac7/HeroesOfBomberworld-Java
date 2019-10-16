package world.object;

import java.awt.Graphics;
import java.util.ArrayList;

import graphics.Animation;
import graphics.Assets;

//Fuoco delle esplosioni delle bombe
public class Fire extends AbstractObject {

	private boolean extingued = false;
	private ArrayList<Wall> walls;
	private Animation fireAnim;

	public Fire(int row, int col) {
		super(row, col);
		fireAnim = new Animation(100, Assets.explosion);
		fireAnim.setFirst(true);
	}

	@Override
	public void update() {
		fireAnim.update();
	}

	//disegna su tutto tranne che sui muri indistruttibili
	@Override
	public void draw(Graphics g) {
		for (Wall wall : walls) {
			if (wall.getRow() == this.row && wall.getCol() == this.col)
				return;
		}
		g.drawImage(fireAnim.getCurrentFrame(), x, y, null);
	}

	public boolean isExtingued() {
		return extingued;
	}

	public void setExtingued(boolean extingued) {
		this.extingued = extingued;
	}

	public ArrayList<Wall> getWalls() {
		return walls;
	}

	public void setWalls(ArrayList<Wall> walls) {
		this.walls = walls;
	}

}
