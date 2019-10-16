package world.object.powerUp;

import java.awt.Graphics;

import graphics.Assets;

//POWERUP DELLA VELOCITà
public class Velocity extends PowerUp {

	public Velocity(int row, int col) {
		super(row, col);
	}

	@Override
	public void update() {

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Assets.powerups[3], x, y, dim, dim,  null);
	}

}
