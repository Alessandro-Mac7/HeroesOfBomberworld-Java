package world.object.powerUp;

import java.awt.Graphics;

import graphics.Assets;

//POWERUP VITA
public class Life extends PowerUp {

	public Life(int row, int col) {
		super(row, col);
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Assets.powerups[0], x, y, dim, dim, null );
	}

}
