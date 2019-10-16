package world.object;

import java.awt.Graphics;

import graphics.Assets;


//PORTA
public class Manhole extends AbstractObject {
	
	private boolean open;

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Manhole(int row, int col) {
		super(row, col);
		open = false;
	}

	//quando la porta si apre emette il suono
	@Override
	public void update() {
		Assets.manholeSound.play();
	}

	//disegna porta aperta o chiusa
	@Override
	public void draw(Graphics g) {
		if(open)
			g.drawImage(Assets.manhole[1], x, y, dim, dim, null);
		else
			g.drawImage(Assets.manhole[0], x, y, dim, dim, null);
	}
	
	

}
