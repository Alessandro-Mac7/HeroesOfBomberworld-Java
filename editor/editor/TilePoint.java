package editor;

import java.awt.Point;
import java.awt.image.BufferedImage;


public class TilePoint {

	private char typeTile;
	private int x;
	private int y;
	private BufferedImage image;
	
	
	public TilePoint(int x, int y, BufferedImage img, char type){
		this.typeTile = type;
		this.image = img;
		this.x = x;
		this.y = y;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public Point getPoint() {
		return new Point(x,y);
	}

	public void setPoint(Point point) {
		this.x = (int) point.getX();
		this.y = (int) point.getY(); 
	}

	public char getType(){
		return typeTile;
	}
	
}
