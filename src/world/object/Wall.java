package world.object;

import java.awt.Graphics;

import graphics.Assets;
import system.GameConfig;

//MURO NON DISTRUTTIBILE
public class Wall extends AbstractObject {

	public Wall(int row, int col) {
		super(row, col);
	}

	@Override
	public void update() {

	}

	@Override
	public void draw(Graphics g) {
		if(GameConfig.theme == GameConfig.THEME_3)
			g.drawImage(Assets.unbreakableTank, x, y, dim, dim, null);
		else if(GameConfig.theme == GameConfig.THEME_2)
				g.drawImage(Assets.unbreakableMage, x, y, dim, dim, null);
		else
			g.drawImage(Assets.unbreakableNinja, x, y, dim, dim, null);
	}

}
