package graphics;

import java.awt.image.BufferedImage;

import audio.MediaPlayer;
import loader.ImageLoader;
import system.GameConfig;

public class Assets {
	
	/*Animated Background*/
	public static BufferedImage animatedBackground;

	/*Ninja*/
	public static BufferedImage[] ninja_down;
	public static BufferedImage[] ninja_up;
	public static BufferedImage[] ninja_left;
	public static BufferedImage[] ninja_right;
	
	/*Tank*/
	public static BufferedImage[] tank_down;
	public static BufferedImage[] tank_up;
	public static BufferedImage[] tank_left;
	public static BufferedImage[] tank_right;
	
	/*Mage*/
	public static BufferedImage[] mage_down;
	public static BufferedImage[] mage_up;
	public static BufferedImage[] mage_left;
	public static BufferedImage[] mage_right;
	
	/*Enemy*/
	public static BufferedImage[] enemy_down;
	public static BufferedImage[] enemy_up;
	public static BufferedImage[] enemy_left;
	public static BufferedImage[] enemy_right;
	
	/*Boss*/
	public static BufferedImage[] boss_down;
	public static BufferedImage[] boss_up;
	public static BufferedImage[] boss_left;
	public static BufferedImage[] boss_right;
	
	
	/*Texture Maps*/
	public static BufferedImage unbreakableNinja;
	public static BufferedImage breakableNinja;
	public static BufferedImage unbreakableMage;
	public static BufferedImage breakableMage;
	public static BufferedImage unbreakableTank;
	public static BufferedImage breakableTank;
	public static BufferedImage ninja_texture;
	public static BufferedImage mage_texture;
	public static BufferedImage tank_texture;
	public static BufferedImage shadow;
	public static BufferedImage[] bomb;
	public static BufferedImage[] explosion;
	public static BufferedImage[] trap;
	public static BufferedImage[] manhole;
	public static BufferedImage[] key;
	
	/*Game Object*/
	public static BufferedImage[] lifeHUD;
	public static BufferedImage[] bombHUD;
	public static BufferedImage[] number;
	public static BufferedImage[] numberOn;
	public static BufferedImage[] numberPoint;
	public static BufferedImage[] powerups;
	public static BufferedImage lock;
	public static BufferedImage bombLevel;
	public static BufferedImage shield;
	
	
	/*Menu Object*/
	public static BufferedImage backgroundLevelChoose;
	public static BufferedImage backgroundLevelChoose2;
	public static BufferedImage backgroundScore;
	public static BufferedImage backgroundMenu;
	public static BufferedImage titleMenu;
	public static BufferedImage[] option_MenuOn;
	public static BufferedImage[] option_MenuOff;
	public static BufferedImage titleScoreState;
	
	/*Pause Object*/
	public static BufferedImage backgroundPause;
	public static BufferedImage play;
	public static BufferedImage pause;
	public static BufferedImage [] options_pauseOff;
	public static BufferedImage [] options_pauseOn;
	public static BufferedImage savedBlocked1;
		
	/*NextLevel Object*/
	public static BufferedImage[] stars;
	public static BufferedImage [] options_NextLevelOff;
	public static BufferedImage [] options_NextLevelOn;
	public static BufferedImage backgroundNextLevel;
	public static BufferedImage [] options_WinLoseOff;
	public static BufferedImage [] options_WinLoseOn;
	public static BufferedImage lose;
	public static BufferedImage win;
	public static BufferedImage savedBlocked2;
	
	/*ChooseScreen Objects*/
	public static BufferedImage titleLevelChoose;
	public static BufferedImage title1ChooseScreen;
	public static BufferedImage title2ChooseScreen;
	public static BufferedImage backgroundChooseScreen;
	public static BufferedImage [] option_ChooseOn;
	public static BufferedImage [] option_ChooseOff;
	public static BufferedImage [] player;
	public static BufferedImage [] infoPlayer;
	public static BufferedImage [] arrowSx;
	public static BufferedImage [] arrowDx;
	
	/*Custom level*/
	public static BufferedImage customTitle;
	public static BufferedImage customSubTitle;
	
	/*Settings Object*/
	public static BufferedImage backgroundSettings;
	public static BufferedImage titleSettings;
	public static BufferedImage titleMusic;
	public static BufferedImage[] on_off_music0;
	public static BufferedImage[] on_off_music1;
	public static BufferedImage[] load_game;
		
	/*Audio*/
	public static MediaPlayer menu_click_sound;
	public static MediaPlayer menu_song;
	public static MediaPlayer[] game_song;
	public static MediaPlayer drop;
	public static MediaPlayer explosionSound;
	public static MediaPlayer ulti_tank;
	public static MediaPlayer ulti_mage;
	public static MediaPlayer ulti_ninja;
	public static MediaPlayer select;
	public static MediaPlayer locked;
	public static MediaPlayer keySound;
	public static MediaPlayer manholeSound;
	public static MediaPlayer trapSound;
	public static MediaPlayer powerUp;
	public static MediaPlayer powerDown;
	public static MediaPlayer hurtTank;
	public static MediaPlayer hurt;
	public static MediaPlayer winSound;
	public static MediaPlayer gameOver;
	public static MediaPlayer nextLevel;
	public static MediaPlayer splash;
	public static MediaPlayer save;
	
	
	
	public static void loadAssets(){
		loadImage();
		loadAudio();
	}
	
	public static void loadImage(){
	
		/*Animated background*/
		animatedBackground = ImageLoader.loadImage("resources/menu/menubg.png");
		
		/*Sprite Sheet player*/
		SpriteSheet spritesheetNinja = new SpriteSheet(ImageLoader.loadImage("resources/player/ninja.png"));
		SpriteSheet spritesheetMage = new SpriteSheet(ImageLoader.loadImage("resources/player/mage.png"));
		SpriteSheet spritesheetTank = new SpriteSheet(ImageLoader.loadImage("resources/player/tank.png"));
		SpriteSheet spritesheetBomb = new SpriteSheet(ImageLoader.loadImage("resources/level/elements/bomb.png"));
		SpriteSheet spritesheetExplosion = new SpriteSheet(ImageLoader.loadImage("resources/level/elements/explosion.png"));
		SpriteSheet spritesheetEnemy = new SpriteSheet(ImageLoader.loadImage("resources/level/elements/enemy.png"));
		SpriteSheet spritesheetBoss = new SpriteSheet(ImageLoader.loadImage("resources/level/elements/boss.png"));

		/*Ninja*/
		ninja_down = new BufferedImage [4];
			ninja_down[0] = spritesheetNinja.crop(0, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			ninja_down[1] = spritesheetNinja.crop(GameConfig.GAMEOBJECT_SIZE, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			ninja_down[2] = spritesheetNinja.crop(GameConfig.GAMEOBJECT_SIZE * 2, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			ninja_down[3] = spritesheetNinja.crop(GameConfig.GAMEOBJECT_SIZE * 3, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
		
		ninja_left = new BufferedImage [4];
			ninja_left[0] = spritesheetNinja.crop(0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			ninja_left[1] = spritesheetNinja.crop(GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			ninja_left[2] = spritesheetNinja.crop(GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			ninja_left[3] = spritesheetNinja.crop(GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
		
		ninja_right = new BufferedImage [4];
			ninja_right[0] = spritesheetNinja.crop(0, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			ninja_right[1] = spritesheetNinja.crop(GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			ninja_right[2] = spritesheetNinja.crop(GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			ninja_right[3] = spritesheetNinja.crop(GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
		
		ninja_up = new BufferedImage [4];
			ninja_up[0] = spritesheetNinja.crop(0, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			ninja_up[1] = spritesheetNinja.crop(GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			ninja_up[2] = spritesheetNinja.crop(GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			ninja_up[3] = spritesheetNinja.crop(GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
		
		/*Mage*/
		mage_down = new BufferedImage [4];
			mage_down[0] = spritesheetMage.crop(0, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			mage_down[1] = spritesheetMage.crop(GameConfig.GAMEOBJECT_SIZE, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			mage_down[2] = spritesheetMage.crop(GameConfig.GAMEOBJECT_SIZE * 2, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			mage_down[3] = spritesheetMage.crop(GameConfig.GAMEOBJECT_SIZE * 3, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
		
		mage_left = new BufferedImage [4];
			mage_left[0] = spritesheetMage.crop(0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			mage_left[1] = spritesheetMage.crop(GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			mage_left[2] = spritesheetMage.crop(GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			mage_left[3] = spritesheetMage.crop(GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
		
		mage_right = new BufferedImage [4];
			mage_right[0] = spritesheetMage.crop(0, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			mage_right[1] = spritesheetMage.crop(GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			mage_right[2] = spritesheetMage.crop(GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			mage_right[3] = spritesheetMage.crop(GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
		
		mage_up = new BufferedImage [4];
			mage_up[0] = spritesheetMage.crop(0, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			mage_up[1] = spritesheetMage.crop(GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			mage_up[2] = spritesheetMage.crop(GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			mage_up[3] = spritesheetMage.crop(GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
	
		/*Tank*/
		tank_down = new BufferedImage [4];
			tank_down[0] = spritesheetTank.crop(0, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			tank_down[1] = spritesheetTank.crop(GameConfig.GAMEOBJECT_SIZE, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			tank_down[2] = spritesheetTank.crop(GameConfig.GAMEOBJECT_SIZE * 2, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			tank_down[3] = spritesheetTank.crop(GameConfig.GAMEOBJECT_SIZE * 3, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
		
		tank_left = new BufferedImage [4];
			tank_left[0] = spritesheetTank.crop(0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			tank_left[1] = spritesheetTank.crop(GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			tank_left[2] = spritesheetTank.crop(GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			tank_left[3] = spritesheetTank.crop(GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
		
		tank_right = new BufferedImage [4];
			tank_right[0] = spritesheetTank.crop(0, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			tank_right[1] = spritesheetTank.crop(GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			tank_right[2] = spritesheetTank.crop(GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			tank_right[3] = spritesheetTank.crop(GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
		
		tank_up = new BufferedImage [4];
			tank_up[0] = spritesheetTank.crop(0, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			tank_up[1] = spritesheetTank.crop(GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			tank_up[2] = spritesheetTank.crop(GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			tank_up[3] = spritesheetTank.crop(GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
	
		/*Enemy*/
		enemy_down = new BufferedImage [4];
			enemy_down[0] = spritesheetEnemy.crop(0, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			enemy_down[1] = spritesheetEnemy.crop(GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			enemy_down[2] = spritesheetEnemy.crop(GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			enemy_down[3] = spritesheetEnemy.crop(GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
		
		enemy_left = new BufferedImage [4];
			enemy_left[0] = spritesheetEnemy.crop(0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			enemy_left[1] = spritesheetEnemy.crop(GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			enemy_left[2] = spritesheetEnemy.crop(GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			enemy_left[3] = spritesheetEnemy.crop(GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
		
		enemy_right = new BufferedImage [4];
			enemy_right[0] = spritesheetEnemy.crop(0, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			enemy_right[1] = spritesheetEnemy.crop(GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			enemy_right[2] = spritesheetEnemy.crop(GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			enemy_right[3] = spritesheetEnemy.crop(GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
		
		enemy_up = new BufferedImage [4];
			enemy_up[0] = spritesheetEnemy.crop(0, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			enemy_up[1] = spritesheetEnemy.crop(GameConfig.GAMEOBJECT_SIZE, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			enemy_up[2] = spritesheetEnemy.crop(GameConfig.GAMEOBJECT_SIZE * 2, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			enemy_up[3] = spritesheetEnemy.crop(GameConfig.GAMEOBJECT_SIZE * 3, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
		
		boss_down = new BufferedImage [4];
			boss_down[0] = spritesheetBoss.crop(0, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			boss_down[1] = spritesheetBoss.crop(GameConfig.GAMEOBJECT_SIZE, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			boss_down[2] = spritesheetBoss.crop(GameConfig.GAMEOBJECT_SIZE * 2, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			boss_down[3] = spritesheetBoss.crop(GameConfig.GAMEOBJECT_SIZE * 3, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
		
		boss_left = new BufferedImage [4];
			boss_left[0] = spritesheetBoss.crop(0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			boss_left[1] = spritesheetBoss.crop(GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			boss_left[2] = spritesheetBoss.crop(GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			boss_left[3] = spritesheetBoss.crop(GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
		
		boss_right = new BufferedImage [4];
			boss_right[0] = spritesheetBoss.crop(0, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			boss_right[1] = spritesheetBoss.crop(GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			boss_right[2] = spritesheetBoss.crop(GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			boss_right[3] = spritesheetBoss.crop(GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
		
		boss_up = new BufferedImage [4];
			boss_up[0] = spritesheetBoss.crop(0, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			boss_up[1] = spritesheetBoss.crop(GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			boss_up[2] = spritesheetBoss.crop(GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			boss_up[3] = spritesheetBoss.crop(GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE * 3, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);

		
			
		/*Level Elements load*/
		shield = ImageLoader.loadImage("resources/level/elements/shield.png");
		ninja_texture = ImageLoader.loadImage("resources/background/background_ninja.png");
		mage_texture = ImageLoader.loadImage("resources/background/background_mage.png");
		tank_texture = ImageLoader.loadImage("resources/background/background_tank.png");	
		shadow = ImageLoader.loadImage("resources/level/elements/afterBomb.png");	
		
		powerups = new BufferedImage[4];
			powerups[0]  = ImageLoader.loadImage("resources/level/powerup/life.png");
			powerups[1]  = ImageLoader.loadImage("resources/level/powerup/bomb.png");
			powerups[2]  = ImageLoader.loadImage("resources/level/powerup/invincible.png");
			powerups[3]  = ImageLoader.loadImage("resources/level/powerup/boost.png");

		
		manhole = new BufferedImage [2];
			manhole[0]  = ImageLoader.loadImage("resources/level/elements/manholeoff.png");
			manhole[1]  = ImageLoader.loadImage("resources/level/elements/manholeon.png");

		key = new BufferedImage [2];
			key[0]  = ImageLoader.loadImage("resources/level/elements/keyoff.png");
			key[1]  = ImageLoader.loadImage("resources/level/elements/keyon.png");
			
		
		lifeHUD = new BufferedImage[5];
			for(int i = 0; i < lifeHUD.length; i++)
				lifeHUD[i] = ImageLoader.loadImage("resources/level/hud/hud" + (i+1) + ".png");
		
		bombHUD = new BufferedImage[3];
		for(int i = 0; i < bombHUD.length; i++)
			bombHUD[i] = ImageLoader.loadImage("resources/level/hud/bomb" + (i+1) + ".png");
				
		breakableNinja = ImageLoader.loadImage("resources/level/elements/breakable_ninja.png");
		unbreakableNinja = ImageLoader.loadImage("resources/level/elements/wall_ninja.png");
		
		breakableMage = ImageLoader.loadImage("resources/level/elements/breakable_mage.png");
		unbreakableMage = ImageLoader.loadImage("resources/level/elements/wall_mage.png");
		
		breakableTank = ImageLoader.loadImage("resources/level/elements/breakable_tank.png");
		unbreakableTank = ImageLoader.loadImage("resources/level/elements/wall_tank.png");
		
		trap = new BufferedImage [2];
			trap[0] = ImageLoader.loadImage("resources/level/elements/trapdown.png");
			trap[1] = ImageLoader.loadImage("resources/level/elements/trapup.png");
			
		bomb = new BufferedImage[3];
			bomb[0] = spritesheetBomb.crop(0, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			bomb[1] = spritesheetBomb.crop(GameConfig.GAMEOBJECT_SIZE, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			bomb[2] = spritesheetBomb.crop(GameConfig.GAMEOBJECT_SIZE * 2, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			
		explosion = new BufferedImage[9];
			explosion[0] = spritesheetExplosion.crop(0, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			explosion[1] = spritesheetExplosion.crop(GameConfig.GAMEOBJECT_SIZE, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			explosion[2] = spritesheetExplosion.crop(GameConfig.GAMEOBJECT_SIZE * 2, 0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			explosion[3] = spritesheetExplosion.crop(0, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			explosion[4] = spritesheetExplosion.crop(GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			explosion[5] = spritesheetExplosion.crop(GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			explosion[6] = spritesheetExplosion.crop(0, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			explosion[7] = spritesheetExplosion.crop(GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
			explosion[8] = spritesheetExplosion.crop(GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE * 2, GameConfig.GAMEOBJECT_SIZE, GameConfig.GAMEOBJECT_SIZE);
				
		/*ChooseScreen load*/
		customTitle  = ImageLoader.loadImage("resources/level/levelchoose/title2.png");;
		customSubTitle = ImageLoader.loadImage("resources/level/levelchoose/subtitle.png");
		titleLevelChoose = ImageLoader.loadImage("resources/level/levelchoose/title.png");
		backgroundChooseScreen = ImageLoader.loadImage("resources/menu/choosescreen/background.png");
		backgroundLevelChoose = ImageLoader.loadImage("resources/level/levelchoose/background.png");
		backgroundLevelChoose2 = ImageLoader.loadImage("resources/level/levelchoose/background1.png");
		title1ChooseScreen =ImageLoader.loadImage("resources/menu/choosescreen/title1.png");
		title2ChooseScreen =ImageLoader.loadImage("resources/menu/choosescreen/title2.png");
		lock = ImageLoader.loadImage("resources/level/levelchoose/lock.png");
		bombLevel = ImageLoader.loadImage("resources/level/levelchoose/bomba.png");
		
		option_ChooseOn = new BufferedImage [3];
		option_ChooseOff = new BufferedImage [3];
		
		for(int i=0; i<option_ChooseOff.length; i++)
			option_ChooseOff[i] =  ImageLoader.loadImage("resources/menu/choosescreen/"+i+"off.png");
		
		for(int i=0; i<option_ChooseOn.length; i++)
			option_ChooseOn[i] = ImageLoader.loadImage("resources/menu/choosescreen/"+i+"on.png");
		
		number =  new BufferedImage [10];
			for(int i = 0; i < number.length; i++ )
				number[i] = ImageLoader.loadImage("resources/level/levelchoose/"+ i +".png");
		
		numberOn =  new BufferedImage [10];
			for(int i = 0; i < numberOn.length; i++ )
				numberOn[i] = ImageLoader.loadImage("resources/level/levelchoose/"+ i +"on.png");
		
		infoPlayer = new BufferedImage [3];
			infoPlayer[0] = ImageLoader.loadImage("resources/menu/choosescreen/ninjaInfo.png");
			infoPlayer[1] = ImageLoader.loadImage("resources/menu/choosescreen/tankInfo.png");
			infoPlayer[2] = ImageLoader.loadImage("resources/menu/choosescreen/mageInfo.png");
			
		player = new BufferedImage[3];
			player[0] = ImageLoader.loadImage("resources/menu/choosescreen/ninja.png");
			player[1] = ImageLoader.loadImage("resources/menu/choosescreen/tank.png");
			player[2] = ImageLoader.loadImage("resources/menu/choosescreen/mage.png");
			
		arrowDx = new BufferedImage[2];
			arrowDx[0] = ImageLoader.loadImage("resources/menu/choosescreen/frecciadx.png");
			arrowDx[1] = ImageLoader.loadImage("resources/menu/choosescreen/frecciadxOn.png");

		arrowSx = new BufferedImage[2];
			arrowSx[0] = ImageLoader.loadImage("resources/menu/choosescreen/frecciasx.png");
			arrowSx[1] = ImageLoader.loadImage("resources/menu/choosescreen/frecciasxOn.png");
			
		numberPoint = new BufferedImage [11];
			for(int i = 0; i < numberPoint.length; i++ )
				numberPoint[i] = ImageLoader.loadImage("resources/number/"+i+".png");

		/*MenuScreeen load*/
		backgroundScore = ImageLoader.loadImage("resources/menu/backgroundScore.png"); 
		option_MenuOn = new BufferedImage [4];
		option_MenuOff = new BufferedImage [4];

		titleMenu = ImageLoader.loadImage("resources/menu/title.png");
		backgroundMenu = ImageLoader.loadImage("resources/menu/background.png");
			
			for(int i=0; i<option_MenuOff.length; i++)
				option_MenuOff[i] = ImageLoader.loadImage("resources/menu/"+i+"off.png");
			
			for(int i=0; i<option_MenuOn.length; i++)
				option_MenuOn[i] = ImageLoader.loadImage("resources/menu/"+i+"on.png");
		
		titleScoreState  = ImageLoader.loadImage("resources/menu/title1.png");
		
			
		/*Settigs load*/
		backgroundSettings = ImageLoader.loadImage("resources/menu/settingscreen/backgroundSettings.png");
		on_off_music0 = new BufferedImage [2];
		on_off_music1 = new BufferedImage [2];
		load_game = new BufferedImage [2];
		
		titleSettings = ImageLoader.loadImage("resources/menu/settingscreen/title.png");
		titleMusic = ImageLoader.loadImage("resources/menu/settingscreen/title1.png");
		load_game[0] = ImageLoader.loadImage("resources/menu/settingscreen/2off.png");
		load_game[1] = ImageLoader.loadImage("resources/menu/settingscreen/2on.png");
		
		for(int i=0; i<on_off_music0.length; i++)
			on_off_music0 [i] = ImageLoader.loadImage("resources/menu/settingscreen/"+i+"on.png");
		for(int i=0; i<on_off_music1.length; i++)
			on_off_music1 [i] = ImageLoader.loadImage("resources/menu/settingscreen/"+i+"off.png");
		
		/*Pause Load*/
		savedBlocked1 = ImageLoader.loadImage("resources/level/pause/savedBlocked.png");
		backgroundPause = ImageLoader.loadImage("resources/level/pause/pauseBackground.png");
		play = ImageLoader.loadImage("resources/level/pause/play.png");
		pause = ImageLoader.loadImage("resources/level/pause/pause.png");
		
		options_pauseOff = new BufferedImage [4];
		options_pauseOn = new BufferedImage [4];
		
		for(int i=0; i<options_pauseOff.length; i++)
			options_pauseOff[i] = ImageLoader.loadImage("resources/level/pause/"+i+"off.png");
		
		for(int i=0; i<options_pauseOn.length; i++)
			options_pauseOn[i] = ImageLoader.loadImage("resources/level/pause/"+i+"on.png");
	
		
		/*Next Level load*/
		savedBlocked2 = ImageLoader.loadImage("resources/level/nextlevel/savedBlocked.png");
		backgroundNextLevel = ImageLoader.loadImage("resources/level/nextlevel/background.png");
		lose = ImageLoader.loadImage("resources/level/nextlevel/lose.png");
		win = ImageLoader.loadImage("resources/level/nextlevel/win.png");
		options_NextLevelOff = new BufferedImage [4];
		options_NextLevelOn = new BufferedImage [4];
		options_WinLoseOff = new BufferedImage [3];
		options_WinLoseOn = new BufferedImage [3];
		stars = new BufferedImage [3];
		
		for(int i=0; i<stars.length; i++)
			stars[i] = ImageLoader.loadImage("resources/level/nextlevel/"+i+".png");
		
		for(int i=0; i<options_NextLevelOff.length; i++)
			options_NextLevelOff[i] = ImageLoader.loadImage("resources/level/nextlevel/"+i+"off.png");
		
		for(int i=0; i<options_NextLevelOn.length; i++)
			options_NextLevelOn[i] = ImageLoader.loadImage("resources/level/nextlevel/"+i+"on.png");
	
		for(int i=0; i<options_WinLoseOff.length; i++)
			options_WinLoseOff[i] = ImageLoader.loadImage("resources/level/nextlevel/"+(i+1)+"off.png");
		for(int i=0; i<options_WinLoseOn.length; i++)
			options_WinLoseOn[i] = ImageLoader.loadImage("resources/level/nextlevel/"+(i+1)+"on.png");
		
		
	}
	
	public static void loadAudio(){
		menu_song = new MediaPlayer("resources/menu/menu.wav");
		menu_click_sound = new MediaPlayer("resources/menu/select.wav");
		ulti_tank = new MediaPlayer("resources/menu/choosescreen/ulti_orc.wav");
		ulti_mage = new MediaPlayer("resources/menu/choosescreen/ulti_mage.wav");
		ulti_ninja = new MediaPlayer("resources/menu/choosescreen/ulti_ninja.wav");
		drop  = new MediaPlayer("resources/level/elements/drop.wav");
		explosionSound = new MediaPlayer("resources/level/elements/explosion.wav");
		select = new MediaPlayer("resources/menu/select2.wav");
		locked = new MediaPlayer("resources/level/levelchoose/locked.wav");
		manholeSound = new MediaPlayer("resources/level/elements/manhole.wav");
		keySound = new MediaPlayer("resources/level/elements/key.wav");
		trapSound = new MediaPlayer("resources/level/elements/trap.wav");
		powerUp = new MediaPlayer("resources/level/elements/powerup.wav");
		powerDown = new MediaPlayer("resources/level/elements/powerdown.wav");
		hurtTank = new MediaPlayer("resources/level/elements/hurtTank.wav");
		hurt = new MediaPlayer("resources/level/elements/hurt.wav");
		winSound = new MediaPlayer("resources/level/elements/wins.wav");
		gameOver = new MediaPlayer("resources/level/elements/gameover.wav");
		nextLevel = new MediaPlayer("resources/level/elements/nextlevel.wav");
		splash = new MediaPlayer("resources/level/elements/splash.wav");
		save = new MediaPlayer("resources/level/elements/save.wav");
		
		
		game_song  = new MediaPlayer [GameConfig.NUM_LEVELS];
			for(int i = 0; i < GameConfig.NUM_LEVELS; i++ )
				game_song[i] = new MediaPlayer("resources/level/elements/"+ i +".wav");
		
		
	}
}
