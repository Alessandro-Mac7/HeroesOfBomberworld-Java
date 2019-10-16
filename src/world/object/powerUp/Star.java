package world.object.powerUp;

import java.awt.Graphics;

import graphics.Assets;

//POWERUP INVINCIBILITà
public class Star extends PowerUp {

	public Star(int row, int col) {
		super(row, col);
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Assets.powerups[2], x, y, dim, dim, null );

	}
	

}
