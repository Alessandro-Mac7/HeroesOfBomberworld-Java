package system;

import java.awt.image.BufferedImage;
import loader.ImageLoader;

public class AssetsEditor {

	/*Player*/
	public static BufferedImage ninjaPlayer;
	public static BufferedImage magePlayer;
	public static BufferedImage tankPlayer;
	public static BufferedImage ninjaPlayerOn;
	public static BufferedImage magePlayerOn;
	public static BufferedImage tankPlayerOn;
	
	/*Immagini Bottoni*/
	public static BufferedImage separator;
	public static BufferedImage deleteImage;
	public static BufferedImage deleteImageOn;
	public static BufferedImage frecciadx;
	public static BufferedImage frecciadxOn;
	public static BufferedImage frecciasx;
	public static BufferedImage frecciasxOn;
	public static BufferedImage [] powerUpOn;
	public static BufferedImage [] powerUpOff;
	public static BufferedImage[] trap;
	public static BufferedImage[] manhole;
	public static BufferedImage[] key;
	
	public static BufferedImage enemyOn;
	public static BufferedImage enemyOff;
	public static BufferedImage bossOn;
	public static BufferedImage bossOff;
	public static BufferedImage trapOn;
	public static BufferedImage trapOff;
	
	
	/*Theme Ninja*/
	public static BufferedImage ninja_theme;
	public static BufferedImage wallImage;
	public static BufferedImage breakableWallImage;
	public static BufferedImage wallImageOn;
	public static BufferedImage breakableWallImageOn;
		
	/*Theme Mago*/
	public static BufferedImage tank_theme;
	public static BufferedImage mage_theme;
	public static BufferedImage wallMage;
	public static BufferedImage breakableMage;
	public static BufferedImage wallMageOn;
	public static BufferedImage breakableMageOn;
	
	/*Theme Tank*/
	public static BufferedImage wallTank;
	public static BufferedImage breakableTank;
	public static BufferedImage wallTankOn;
	public static BufferedImage breakableTankOn;
	
	/*Titolo e sfondo*/
	public static BufferedImage title;
	public static BufferedImage subTitle;
	public static BufferedImage subTitle1;
	public static BufferedImage subTitle2;
	public static BufferedImage subTitle3;
	public static BufferedImage background;
	public static BufferedImage background_ninja;
	public static BufferedImage background_mage;
	public static BufferedImage background_tank;
	
	
	public static void loadAssets(){
		loadImage();
		loadAudio();
	}
	
	public static void loadImage(){
	
		/*Titoli e background*/
		title = ImageLoader.loadImage("resources/button/title.png");
		subTitle = ImageLoader.loadImage("resources/button/subTitle.png");
		subTitle1 = ImageLoader.loadImage("resources/button/subTitle2.png");
		subTitle2 = ImageLoader.loadImage("resources/button/subTitle3.png");
		subTitle3 = ImageLoader.loadImage("resources/button/subTitle4.png");
		background = ImageLoader.loadImage("resources/button/background.png");
		background_ninja = ImageLoader.loadImage("resources/tile/background_ninja.png");
		background_mage = ImageLoader.loadImage("resources/tile/background_mage.png");
		background_tank = ImageLoader.loadImage("resources/tile/background_tank.png");
		
		/*Player*/
		ninjaPlayer = ImageLoader.loadImage("resources/player/playerNinja.png");
		ninjaPlayerOn = ImageLoader.loadImage("resources/player/playerNinjaOn.png");
		tankPlayer = ImageLoader.loadImage("resources/player/playerTank.png");
		tankPlayerOn = ImageLoader.loadImage("resources/player/playerTankOn.png");
		magePlayer = ImageLoader.loadImage("resources/player/playerMage.png");
		magePlayerOn = ImageLoader.loadImage("resources/player/playerMageOn.png");

		/*Immagini Bottoni*/
		separator = ImageLoader.loadImage("resources/button/separator.png");
		deleteImage = ImageLoader.loadImage("resources/button/delete.png");
		deleteImageOn = ImageLoader.loadImage("resources/button/deleteon.png");
		frecciadx = ImageLoader.loadImage("resources/button/frecciadx.png");
		frecciadxOn = ImageLoader.loadImage("resources/button/frecciadxOn.png");
		frecciasx = ImageLoader.loadImage("resources/button/frecciasx.png");
		frecciasxOn = ImageLoader.loadImage("resources/button/frecciasxOn.png");
		enemyOff = ImageLoader.loadImage("resources/button/enemy.png");
		enemyOn = ImageLoader.loadImage("resources/button/enemyOn.png");
		bossOff = ImageLoader.loadImage("resources/button/boss.png");
		bossOn = ImageLoader.loadImage("resources/button/bossOn.png");
		

		powerUpOff = new BufferedImage[4];
			powerUpOff[0]  = ImageLoader.loadImage("resources/level/powerup/life.png");
			powerUpOff[1]  = ImageLoader.loadImage("resources/level/powerup/bomb.png");
			powerUpOff[2]  = ImageLoader.loadImage("resources/level/powerup/invincible.png");
			powerUpOff[3]  = ImageLoader.loadImage("resources/level/powerup/boost.png");

		powerUpOn = new BufferedImage[4];
			powerUpOn[0]  = ImageLoader.loadImage("resources/level/powerup/lifeOn.png");
			powerUpOn[1]  = ImageLoader.loadImage("resources/level/powerup/bombOn.png");
			powerUpOn[2]  = ImageLoader.loadImage("resources/level/powerup/invincibleOn.png");
			powerUpOn[3]  = ImageLoader.loadImage("resources/level/powerup/boostOn.png");

		manhole = new BufferedImage [2];
			manhole[0]  = ImageLoader.loadImage("resources/level/elements/manholeoff.png");
			manhole[1]  = ImageLoader.loadImage("resources/level/elements/manholeon.png");

		key = new BufferedImage [2];
			key[0]  = ImageLoader.loadImage("resources/level/elements/keyoff.png");
			key[1]  = ImageLoader.loadImage("resources/level/elements/keyon.png");
		
		trap = new BufferedImage [2];
			trap[0] = ImageLoader.loadImage("resources/level/elements/trapdown.png");
			trap[1] = ImageLoader.loadImage("resources/level/elements/trapup.png");
		
		
		/*Theme Ninja*/
		ninja_theme = ImageLoader.loadImage("resources/themeNinja/ninja_theme.png");
		wallImage = ImageLoader.loadImage("resources/themeNinja/wall_ninja.png");
		breakableWallImage = ImageLoader.loadImage("resources/themeNinja/breakable_ninja.png");
		wallImageOn = ImageLoader.loadImage("resources/themeNinja/wall_ninjaOn.png");
		breakableWallImageOn = ImageLoader.loadImage("resources/themeNinja/breakable_ninjaOn.png");
		
		/*Theme Mage*/
		mage_theme = ImageLoader.loadImage("resources/themeMage/mage_theme.png");
		wallMage = ImageLoader.loadImage("resources/themeMage/wall_mage.png");
		breakableMage = ImageLoader.loadImage("resources/themeMage/breakable_mage.png");
		wallMageOn = ImageLoader.loadImage("resources/themeMage/wall_mageOn.png");
		breakableMageOn = ImageLoader.loadImage("resources/themeMage/breakable_mageOn.png");
		
		/*Theme Tank*/
		tank_theme = ImageLoader.loadImage("resources/themeTank/tank_theme.png");
		wallTank = ImageLoader.loadImage("resources/themeTank/wall_tank.png");
		breakableTank = ImageLoader.loadImage("resources/themeTank/breakable_tank.png");
		wallTankOn = ImageLoader.loadImage("resources/themeTank/wall_tankOn.png");
		breakableTankOn = ImageLoader.loadImage("resources/themeTank/breakable_tankOn.png");
		
	}
	
	public static void loadAudio(){
		
	}
}
