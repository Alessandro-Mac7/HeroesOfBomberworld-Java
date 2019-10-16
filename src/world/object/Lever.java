package world.object;

import java.awt.Graphics;

import graphics.Assets;

//LEVA DI APERTURA PORTA
public class Lever extends AbstractObject {

	private boolean found=false;
	
	public Lever(int row, int col) {
		super(row, col);
	}

	//Suono quando si attiva la leva
	@Override
	public void update() {	
		Assets.keySound.play();
	}

	//disegna la leva verso sinistra o destra
	@Override
	public void draw(Graphics g) {
		if(found)
			g.drawImage(Assets.key[1], x, y, dim, dim, null);
		else
			g.drawImage(Assets.key[0], x, y, dim, dim, null);
	}

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

}
