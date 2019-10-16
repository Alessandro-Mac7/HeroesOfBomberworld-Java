package world.object;

import java.awt.Graphics;

import graphics.Animation;
import graphics.Assets;

//come un nemico ma insegue il player e lancia bombe
public class Boss extends Enemy {

	private long timeBomb = 0;
	private long lastTimeBomb = 0;
	private boolean bombDroppable = true;

	public Boss(int row, int col) {
		super(row, col);
		this.up = new Animation(250, Assets.boss_up);
		this.down = new Animation(250, Assets.boss_down);
		this.left = new Animation(250, Assets.boss_left);
		this.right = new Animation(250, Assets.boss_right);
		bombDroppable = true;
		xOffBound = 10;
		yOffBound = 10;
	}

	// il boss insegue il giocatore nel raggio di ricerca
	@Override
	public void update() {
		directionIA();
		countdown();
		countdown2();
		countdownBomb();
		move(direction);
		this.updateAnim();
		row = (y + dim / 2) / dim;
		col = (x + dim / 2) / dim;
		rect.setBounds(x + xOffBound, y + yOffBound, dim - xOffBound * 2, dim - yOffBound);

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(this.getCurrentImage(), x, y, null);
	}

	// ogni 15 secondi lancia una bomba
	public void countdownBomb() {
		if (!bombDroppable) {
			timeBomb += System.currentTimeMillis() - lastTimeBomb;
			lastTimeBomb = System.currentTimeMillis();
			if (timeBomb > 15000) {
				timeBomb = 0;
				bombDroppable = true;
			}
		}
	}

	@Override
	public boolean isBombDroppable() {
		return bombDroppable;
	}

	@Override
	public void bombIsDropped() {
		bombDroppable = false;
		;
	}
}