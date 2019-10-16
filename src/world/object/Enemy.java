package world.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import graphics.Animation;
import graphics.Assets;
import system.GameConfig;

public class Enemy extends AbstractObject implements IMoveable {

	protected boolean alive = true;
	protected final int speed = 1;
	protected int direction;
	protected ArrayList<Wall> walls;
	protected ArrayList<Block> blocks;
	protected ArrayList<Bomb> bombs;
	protected ArrayList<Trap> traps;
	protected ArrayList<Enemy> enemies;
	protected int xOffBound = 0;
	protected int yOffBound = 0;
	protected Player player;
	protected Random random = new Random();
	protected double distance;
	protected final double minDistance = 96;
	protected boolean halted = false;
	protected long time = 0;
	protected long lastTime = 0;
	protected long time2 = 0;
	protected long lastTime2 = 0;
	protected long changeDirection = 3000;
	protected boolean boss = false;
	private final boolean bombDroppable = false;

	// Animation
	protected Animation down;
	protected Animation up;
	protected Animation left;
	protected Animation right;

	public Enemy(int row, int col) {
		super(row, col);
		down = new Animation(250, Assets.enemy_down);
		up = new Animation(250, Assets.enemy_up);
		left = new Animation(250, Assets.enemy_left);
		right = new Animation(250, Assets.enemy_right);

	}

	// aggiornamento dati di movimento e posizione
	@Override
	public void update() {
		// countdown cambia la direzione ogni X secondi
		countdown();
		move(direction);
		updateAnim();
		row = (y + dim / 2) / dim;
		col = (x + dim / 2) / dim;
		rect.setBounds(x + xOffBound, y + yOffBound, dim - xOffBound * 2, dim - yOffBound);

	}

	// AI dei boss che inseguono il giocatore se sono nel raggio di distanza
	// stabilito
	public void directionIA() {

		int dx = player.getX() - this.x;
		int dy = player.getY() - this.y;
		distance = Math.sqrt(Math.pow(x - player.getX() + 16, 2) + (Math.pow(y - player.getY() + 16, 2)));

		if (distance <= minDistance && !halted) {
			if (dy > 0 && dy < dx)
				direction = 1;
			else if (dy < 0 && dy < dx)
				direction = 0;
			else if (dy == 0 && dy < dx) {
				if (dx > 0)
					direction = 3;
				else if (dx < 0)
					direction = 2;
			}

			if (dx > 0 && dx < dy)
				direction = 3;
			else if (dx < 0 && dx < dy)
				direction = 2;
			else if (dx == 0) {
				if (dy > 0)
					direction = 1;
				else if (dy < 0)
					direction = 0;
			}
		}
	}

	protected void updateAnim() {
		up.update();
		left.update();
		right.update();
		down.update();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getCurrentImage(), x, y, null);
	}

	// immagine in base alla direzione
	protected BufferedImage getCurrentImage() {

		if (direction == 0) {
			up.setFirst(true);
			return up.getCurrentFrame();
		} else if (direction == 3) {
			right.setFirst(true);
			return right.getCurrentFrame();
		} else if (direction == 2) {
			left.setFirst(true);
			return left.getCurrentFrame();
		} else {
			down.setFirst(true);
			return down.getCurrentFrame();
		}

	}

	// movimento nelle quattro direzioni e controllo collisioni
	@Override
	public void move(int direction) {
		switch (direction) {
		case 0:// up
			if (y - speed > 0 && collide(new Rectangle(x + xOffBound, y + yOffBound - speed, dim - xOffBound * 2,
					dim - yOffBound)) == false) {
				y = y - speed;

			} else if (y - speed > 0 && collide(new Rectangle(x + xOffBound, y + yOffBound - speed, dim - xOffBound * 2,
					dim - yOffBound)) == true) {
				if (distance < minDistance && !halted) {
					halted = true;
				} else
					this.direction = random.nextInt(4);
			}
			break;

		case 1:// down
			if (y + speed + dim <= GameConfig.DISPLAY_HEIGHT && collide(new Rectangle(x + xOffBound,
					y + yOffBound + speed, dim - xOffBound * 2, dim - yOffBound)) == false) {
				y = y + speed;

			} else if (y + speed + dim <= GameConfig.DISPLAY_HEIGHT && collide(new Rectangle(x + xOffBound,
					y + yOffBound + speed, dim - xOffBound * 2, dim - yOffBound)) == true) {
				if (distance < minDistance && !halted) {
					halted = true;
				} else
					this.direction = random.nextInt(4);
			}
			break;

		case 2:// left
			if (x - speed > 0 && collide(new Rectangle(x + xOffBound - speed, y + yOffBound, dim - xOffBound * 2,
					dim - yOffBound)) == false) {
				x = x - speed;

			} else if (x - speed > 0 && collide(new Rectangle(x + xOffBound - speed, y + yOffBound, dim - xOffBound * 2,
					dim - yOffBound)) == true) {
				if (distance < minDistance && !halted) {
					halted = true;
				} else
					this.direction = random.nextInt(4);
			}
			break;

		case 3:// right
			if (x + speed + dim <= GameConfig.DISPLAY_HEIGHT && collide(new Rectangle(x + xOffBound + speed,
					y + yOffBound, dim - xOffBound * 2, dim - yOffBound)) == false) {
				x = x + speed;

			} else if (x + speed + dim <= GameConfig.DISPLAY_HEIGHT && collide(new Rectangle(x + xOffBound + speed,
					y + yOffBound, dim - xOffBound * 2, dim - yOffBound)) == true) {
				if (distance < minDistance && !halted) {
					halted = true;
				} else
					this.direction = random.nextInt(4);
			}
			break;
		}

	}

	// controllo collisioni con muri,blocchi,bombe, trappole e altri nemici
	@Override
	public boolean collide(Rectangle rect2) {
		for (Wall wall : walls)
			if (wall.getRect().intersects(rect2))
				return true;
		for (Block block : blocks)
			if (block.getRect().intersects(rect2))
				return true;
		for (Bomb bomb : bombs) {
			if (bomb.getRect().intersects(rect))
				return false;
			if (bomb.getRect().intersects(rect2))
				return true;
		}
		for (Trap trap : traps)
			if (trap.getRect().intersects(rect2))
				return true;
		for (Enemy enemy : enemies) {
			if (enemy.getRect().intersects(rect2) && enemy != this)
				return true;
		}
		return false;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void setWalls(ArrayList<Wall> walls) {
		this.walls = walls;
	}

	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
	}

	public void setBombs(ArrayList<Bomb> bombs) {
		this.bombs = bombs;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		distance = Math.sqrt(Math.pow(x - player.getX(), 2) + (Math.pow(y - player.getY(), 2)));
	}

	// ogni time cambia la direzione se non insegue il giocatore
	public void countdown() {
		if (distance > minDistance) {
			time += System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();
			if (time > changeDirection) {
				time = 0;
				direction = random.nextInt(4);
			}
		}
	}

	// ogni secondo il boss bloccato può ripartire all'inseguimento
	public void countdown2() {
		time2 += System.currentTimeMillis() - lastTime2;
		lastTime2 = System.currentTimeMillis();
		if (time2 > 500) {
			time2 = 0;
			halted = false;
		}
	}

	public void setTrap(ArrayList<Trap> traps) {
		this.traps = traps;
	}

	public boolean isBombDroppable() {
		return bombDroppable;
	}

	public void bombIsDropped() {
		return;
	}

	public void setEnemy(ArrayList<Enemy> enemies) {
		this.enemies = enemies;

	}

}
