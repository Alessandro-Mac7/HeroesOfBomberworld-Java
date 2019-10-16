package world.object;

import java.awt.Graphics;
import java.awt.Rectangle;

import system.GameConfig;


//CLASSE DA CUI DERIVARE TUTTI GLI OGGETTI DI GIOCO
public abstract class AbstractObject {

	protected int row;
	protected int col;
	protected int y;
	protected int x;
	protected final int dim = GameConfig.GAMEOBJECT_SIZE;
	protected Rectangle rect;

	public AbstractObject(int row, int col) {
		this.row = row;
		this.col = col;
		y = row * dim;
		x = col * dim;
		rect = new Rectangle(x, y, dim, dim);
	}

	public abstract void update();

	public abstract void draw(Graphics g);

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public int getDim() {
		return dim;
	}

}
