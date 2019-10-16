package world.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import graphics.Animation;
import graphics.Assets;
import system.GameConfig;

public class Player extends AbstractObject implements IMoveable {

	private String type = GameConfig.player_type;
	private int speed;
	private int maxSpeed;
	private boolean bombDropped = false;
	private int xOffBound = 10;
	private int yOffBound = 13;
	private int direction;
	private int lastDirection;
	private ArrayList<Wall> walls;
	private ArrayList<Enemy> enemies;
	private ArrayList<Block> blocks;
	private ArrayList<Bomb> bombs;
	private ArrayList<Trap> traps;
	private int numBombs;
	private boolean invincible = false;
	private long invincibleTime = 3000;
	private long time = 0;
	private long lastTime = 0;
	private int maxLife;
	private final int maxBomb = 3;

	/* Animation */
	private Animation down;
	private Animation up;
	private Animation left;
	private Animation right;

	// creazione personaggio
	public Player(int row, int col) {
		super(row, col);
		rect = new Rectangle(x + xOffBound, y + yOffBound, dim - xOffBound * 2, dim - yOffBound * 2);
		direction = 0;

		// tipi di personaggi diversi con caratteristiche diverse
		switch (type) {
		case "ninja":
			down = new Animation(300, Assets.ninja_down);
			up = new Animation(300, Assets.ninja_up);
			left = new Animation(300, Assets.ninja_left);
			right = new Animation(300, Assets.ninja_right);
			speed = 3;
			maxSpeed = speed;
			numBombs = 2;
			GameConfig.life = 3;
			setMaxLife(GameConfig.life + 2);
			break;
		case "tank":
			down = new Animation(200, Assets.tank_down);
			up = new Animation(200, Assets.tank_up);
			left = new Animation(200, Assets.tank_left);
			right = new Animation(200, Assets.tank_right);
			speed = 1;
			maxSpeed = speed + 2;
			numBombs = 2;
			GameConfig.life = 5;
			setMaxLife(GameConfig.life);
			break;
		case "mage":
			down = new Animation(200, Assets.mage_down);
			up = new Animation(200, Assets.mage_up);
			left = new Animation(200, Assets.mage_left);
			right = new Animation(200, Assets.mage_right);
			speed = 2;
			maxSpeed = speed + 1;
			numBombs = 1;
			GameConfig.life = 4;
			setMaxLife(GameConfig.life + 1);
			break;
		}
	}

	// aggiornamento dati del player, se tocca trappole o nemici diminuisce la vita
	public void update() {
		updateAnim();
		move(direction);
		row = (y + dim / 2) / dim;
		col = (x + dim / 2) / dim;
		rect.setBounds(x + xOffBound, y + yOffBound, dim - xOffBound * 2, dim - yOffBound);
		checkEnemy();
		checkTrap();
	}

	// danneggiamento se tocca nemico
	private void checkEnemy() {
		for (Enemy enemy : enemies) {
			if (this.rect.intersects(enemy.getRect())) {
				if (invincible == false) {
					Assets.hurt.play();
					setInvincible(true);
					GameConfig.life--;
				}
			}
		}
	}

	// danneggiamento se tocca trappole
	private void checkTrap() {
		for (Trap trap : traps) {
			if (this.rect.intersects(trap.getRect())) {
				if (invincible == false) {
					if (trap.isUp()) {
						Assets.hurt.play();
						setInvincible(true);
						GameConfig.life--;
					}
				}
			}
		}
	}

	// animazione di movimento
	private void updateAnim() {
		up.update();
		left.update();
		right.update();
		down.update();
	}

	public void draw(Graphics g) {
		g.drawImage(getCurrentImage(), x, y, null);
	}

	// immagine in base a dove il personaggio è rivolto
	private BufferedImage getCurrentImage() {

		if (this.direction == 0) {
			if (lastDirection == KeyEvent.VK_UP) {
				up.setFirst(false);
				return up.getCurrentFrame();
			} else if (lastDirection == KeyEvent.VK_RIGHT) {
				right.setFirst(false);
				return right.getCurrentFrame();
			} else if (lastDirection == KeyEvent.VK_LEFT) {
				left.setFirst(false);
				return left.getCurrentFrame();
			} else {
				down.setFirst(false);
				return down.getCurrentFrame();
			}
		}

		if (direction == KeyEvent.VK_UP) {
			up.setFirst(true);
			return up.getCurrentFrame();
		} else if (direction == KeyEvent.VK_RIGHT) {
			right.setFirst(true);
			return right.getCurrentFrame();
		} else if (direction == KeyEvent.VK_LEFT) {
			left.setFirst(true);
			return left.getCurrentFrame();
		} else {
			down.setFirst(true);
			return down.getCurrentFrame();
		}

	}

	// il giocatore si muove se la posizione in cui vuole andare non è occupata da
	// qualcosa di non attraversabile
	public void move(int direction) {
		switch (direction) {
		case KeyEvent.VK_UP:
			if (y - speed > 0 && this.collide(
					new Rectangle(x + xOffBound, y + yOffBound - speed, dim - xOffBound * 2, dim - yOffBound)) == false)
				y = y - speed;
			else if (y - speed > 0 && this.collide(
					new Rectangle(x + xOffBound, y + yOffBound - speed, dim - xOffBound * 2, dim - yOffBound)) == true)
				break;
			else
				y = 0;
			lastDirection = this.direction;
			break;

		case KeyEvent.VK_DOWN:
			if (y + speed + dim <= GameConfig.DISPLAY_HEIGHT && this.collide(
					new Rectangle(x + xOffBound, y + yOffBound + speed, dim - xOffBound * 2, dim - yOffBound)) == false)
				y = y + speed;
			else if (y + speed + dim <= GameConfig.DISPLAY_HEIGHT && this.collide(
					new Rectangle(x + xOffBound, y + yOffBound + speed, dim - xOffBound * 2, dim - yOffBound)) == true)
				break;
			else
				y = GameConfig.DISPLAY_HEIGHT - dim;
			lastDirection = this.direction;
			break;

		case KeyEvent.VK_LEFT:
			if (x - speed > 0 && this.collide(
					new Rectangle(x + xOffBound - speed, y + yOffBound, dim - xOffBound * 2, dim - yOffBound)) == false)
				x = x - speed;
			else if (x - speed > 0 && this.collide(
					new Rectangle(x + xOffBound - speed, y + yOffBound, dim - xOffBound * 2, dim - yOffBound)) == true)
				break;
			else
				x = 0;
			lastDirection = this.direction;
			break;

		case KeyEvent.VK_RIGHT:
			if (x + speed + dim <= GameConfig.DISPLAY_HEIGHT && this.collide(
					new Rectangle(x + xOffBound + speed, y + yOffBound, dim - xOffBound * 2, dim - yOffBound)) == false)
				x = x + speed;
			else if (x + speed + dim <= GameConfig.DISPLAY_HEIGHT && this.collide(
					new Rectangle(x + xOffBound + speed, y + yOffBound, dim - xOffBound * 2, dim - yOffBound)) == true)
				break;
			else
				x = GameConfig.DISPLAY_HEIGHT - dim;
			lastDirection = this.direction;
			break;

		case KeyEvent.VK_SPACE:
			bombDropped = true;
		}
	}

	// controllo delle collisioni con tutti gli oggetti: muri,blocchi e bombe
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
		return false;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	public void setWalls(ArrayList<Wall> walls) {
		this.walls = walls;
	}

	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
	}

	public boolean isBombDropped() {
		return bombDropped;
	}

	public void setBombDropped(boolean bombDropped) {
		this.bombDropped = bombDropped;
	}

	public void setBombs(ArrayList<Bomb> bombs) {
		this.bombs = bombs;
	}

	public int getBombMax() {
		return numBombs;
	}

	public void setTraps(ArrayList<Trap> traps) {
		this.traps = traps;
	}

	// conteggio del tempo dell'invincibilità
	public void countdown() {
		if (invincible) {
			time += System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();
			if (time > invincibleTime) {
				time = 0;
				invincible = false;
			}
		}
	}

	// set dell'invincibilità e aggiornamento del tempo al tempo corrente
	public void setInvincible(boolean val) {
		invincible = val;
		lastTime = System.currentTimeMillis();
	}

	public boolean getInvincible() {
		return invincible;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	// potenziamento bombe
	public void increaseBomb() {
		if (numBombs < maxBomb)
			this.numBombs++;
	}

	// potenziamento velocità
	public void speedUp() {
		if (speed < maxSpeed)
			speed++;
	}

}
