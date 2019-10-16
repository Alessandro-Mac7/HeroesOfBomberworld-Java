package world.object;

import java.awt.Graphics;
import graphics.Assets;
import system.GameConfig;

//MURO DISTRUTTIBILE
public class Block extends AbstractObject {
	
	private boolean destroyed = false;

	public Block(int row, int col) {
		super(row, col);
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics g) {
		
		if(GameConfig.theme == GameConfig.THEME_3)
			g.drawImage(Assets.breakableTank, x, y, dim, dim, null);
		else if(GameConfig.theme == GameConfig.THEME_2)
				g.drawImage(Assets.breakableMage, x, y, dim, dim, null);
		else
			g.drawImage(Assets.breakableNinja, x, y, dim, dim, null);

	}
	
	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

}
