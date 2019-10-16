package world;

import java.awt.Graphics;

import graphics.Assets;
import system.GameConfig;
import world.object.AbstractObject;

//MAPPA CON CUI CREARE IL MONDO
public class Map {
	private char[][] char_map;
	public AbstractObject[][] map;

	public Map() {
		char_map = new char[GameConfig.SIZE_MAP][GameConfig.SIZE_MAP];
		map = new AbstractObject[GameConfig.SIZE_MAP][GameConfig.SIZE_MAP];
	}

	public void update() {

	}

	public void draw(Graphics g) {
		if(GameConfig.theme == GameConfig.THEME_3)
			g.drawImage(Assets.tank_texture, 0,0, GameConfig.DISPLAY_HEIGHT,GameConfig.DISPLAY_WIDTH,null);
		else if(GameConfig.theme == GameConfig.THEME_2)
			g.drawImage(Assets.mage_texture, 0,0, GameConfig.DISPLAY_HEIGHT,GameConfig.DISPLAY_WIDTH,null);
		else
			g.drawImage(Assets.ninja_texture, 0,0, GameConfig.DISPLAY_HEIGHT,GameConfig.DISPLAY_WIDTH,null);

	}

	public char getCharMapPos(int row, int col) {
		return char_map[row][col];
	}

	public char[][] getCharMap() {
		return char_map;
	}

	public void setCharMap(char[][] map) {
		this.char_map = map;
	}

	public AbstractObject[][] getMap() {
		return map;
	}

	public void setMap(AbstractObject[][] map) {
		this.map = map;
	}

	public AbstractObject getMapPos(int row, int col) {
		return map[row][col];
	}

	public void add(int row, int col, AbstractObject obj) {
		map[row][col] = obj;
	}
}
