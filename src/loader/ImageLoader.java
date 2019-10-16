package loader;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageLoader {
	/*Classe statica per caricare le immagini*/
	public static BufferedImage loadImage(String s){
		try {
			return ImageIO.read( new File(s));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
