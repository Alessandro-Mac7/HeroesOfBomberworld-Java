package world.object.powerUp;

import java.awt.Graphics;

import graphics.Assets;

//POWERUP BOMBE MASSIME
public class MaxBomb extends PowerUp {

	public MaxBomb(int row, int col) {
		super(row, col);
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Assets.powerups[1], x, y, dim, dim, null );

	}

}
