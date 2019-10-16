package world.object;

import java.awt.Color;
import java.awt.Graphics;

public class Life extends AbstractObject {

	public Life(int row, int col) {
		super(row, col);
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, dim, dim);
	}

}
