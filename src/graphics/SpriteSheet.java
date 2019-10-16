package graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	/*immagine contenente lo spritesheet*/
	private BufferedImage sheet;
		
	public SpriteSheet(BufferedImage sheet){
		this.sheet = sheet;
	}
	
	/*metodo che ritorna la porzione di immagine desiderata*/
	public BufferedImage crop(int x, int y, int width, int height){
		return sheet.getSubimage(x, y, width, height);
	}
}
