package editor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import system.AssetsEditor;
import system.ControlEditor;


@SuppressWarnings("serial")
public class ObjectPanel extends JPanel implements ActionListener  {

	private EditorPanel editor;
	
	/*Button*/
	private MyButton wall;
	private MyButton breakable_wall;
	private MyButton player;
	private MyButton delete;
	private MyButton frecciadx;
	private MyButton frecciasx;
	private MyButton frecciadxP; //Freccia player
	private MyButton frecciasxP; //Freccia player
	private MyButton theme;
	private MyButton title;
	private MyButton subTitle;
	private MyButton subTitle1;
	private MyButton subTitle2;
	private MyButton subTitle3;
	private MyButton separator1;
	private MyButton separator2;
	private MyButton powerUpLife;
	private MyButton powerUpBomb;
	private MyButton powerUpInvincible;
	private MyButton powerUpBoost;
	private MyButton trap;
	private MyButton manhole;
	private MyButton key;
	private MyButton boss;
	private MyButton enemy;

	/*Scelta corrente*/
	private int currentChoose; //scelta tema
	private int currentChooseP; //scelta player

	public void paintComponent(Graphics g){
		/*Disegno lo sfondo*/
		drawBackground(g);
		
		/*Disegno il contenuto dell'editor*/
		//g.drawImage(Assets.title, 0, 0, null);
		
	}
	
	private void drawBackground(Graphics g) {
		
		g.drawImage(AssetsEditor.background, 0, 0, null);
	}
		
	public ObjectPanel(EditorPanel editor){
		super();
		this.editor=editor;
		init();
	}

	
	
	public void init(){
		/*Setto dimensioni schermo*/
		this.setPreferredSize(new Dimension(300,ControlEditor.DISPLAY_HEIGHT));
			
		/*Setto la prima immagine di default nel ControlEditor*/
		ControlEditor.paintImage(AssetsEditor.wallImage);
		currentChoose = 0;
		currentChooseP = 0;
			
		/*Inizializzo i MyButton con le immagini caricate*/
		frecciadx = new MyButton(AssetsEditor.frecciadx, AssetsEditor.frecciadxOn, ControlEditor.BUTTON_SIZE, ControlEditor.BUTTON_SIZE, 0, 0);
		frecciasx = new MyButton(AssetsEditor.frecciasx, AssetsEditor.frecciasxOn, ControlEditor.BUTTON_SIZE, ControlEditor.BUTTON_SIZE, 0, 0);
		frecciadxP = new MyButton(AssetsEditor.frecciadx, AssetsEditor.frecciadxOn, ControlEditor.BUTTON_SIZE, ControlEditor.BUTTON_SIZE, 0, 0);
		frecciasxP = new MyButton(AssetsEditor.frecciasx, AssetsEditor.frecciasxOn, ControlEditor.BUTTON_SIZE, ControlEditor.BUTTON_SIZE, 0, 0);
		theme = new MyButton(AssetsEditor.ninja_theme, AssetsEditor.ninja_theme, AssetsEditor.ninja_theme.getWidth(), AssetsEditor.ninja_theme.getHeight(), 0, 0);
		title = new MyButton(AssetsEditor.title, AssetsEditor.title, AssetsEditor.title.getWidth(), AssetsEditor.title.getHeight(), 0, 0);
		subTitle = new MyButton(AssetsEditor.subTitle, AssetsEditor.subTitle, AssetsEditor.subTitle.getWidth(), AssetsEditor.subTitle.getHeight(), 0, 0);
		subTitle1 = new MyButton(AssetsEditor.subTitle1, AssetsEditor.subTitle1, AssetsEditor.subTitle1.getWidth(), AssetsEditor.subTitle1.getHeight(), 0, 0);
		subTitle2 = new MyButton(AssetsEditor.subTitle2, AssetsEditor.subTitle2, AssetsEditor.subTitle2.getWidth(), AssetsEditor.subTitle2.getHeight(), 0, 0);
		subTitle3 = new MyButton(AssetsEditor.subTitle3, AssetsEditor.subTitle3, AssetsEditor.subTitle3.getWidth(), AssetsEditor.subTitle3.getHeight(), 0, 0);
		wall = new MyButton(AssetsEditor.wallImage, AssetsEditor.wallImageOn, ControlEditor.BUTTON_SIZE,  ControlEditor.BUTTON_SIZE, 0, 0);
		breakable_wall = new MyButton(AssetsEditor.breakableWallImage,AssetsEditor.breakableWallImageOn,  ControlEditor.BUTTON_SIZE,  ControlEditor.BUTTON_SIZE, 0, 0);
		player = new MyButton(AssetsEditor.ninjaPlayer, AssetsEditor.ninjaPlayerOn,  ControlEditor.BUTTON_SIZE,  ControlEditor.BUTTON_SIZE, 0,0);
		powerUpLife  = new MyButton(AssetsEditor.powerUpOff[0], AssetsEditor.powerUpOn[0],  ControlEditor.BUTTON_SIZE,  ControlEditor.BUTTON_SIZE, 0,0);
		powerUpBomb = new MyButton(AssetsEditor.powerUpOff[1], AssetsEditor.powerUpOn[1],  ControlEditor.BUTTON_SIZE,  ControlEditor.BUTTON_SIZE, 0,0);
		powerUpInvincible = new MyButton(AssetsEditor.powerUpOff[2], AssetsEditor.powerUpOn[2],  ControlEditor.BUTTON_SIZE,  ControlEditor.BUTTON_SIZE, 0,0);
		powerUpBoost = new MyButton(AssetsEditor.powerUpOff[3], AssetsEditor.powerUpOn[3],  ControlEditor.BUTTON_SIZE,  ControlEditor.BUTTON_SIZE, 0,0);
		trap = new MyButton(AssetsEditor.trap[0], AssetsEditor.trap[1],  ControlEditor.BUTTON_SIZE,  ControlEditor.BUTTON_SIZE, 0,0);
		manhole = new MyButton(AssetsEditor.manhole[0], AssetsEditor.manhole[1],  ControlEditor.BUTTON_SIZE,  ControlEditor.BUTTON_SIZE, 0,0);
		key = new MyButton(AssetsEditor.key[0], AssetsEditor.key[1],  ControlEditor.BUTTON_SIZE,  ControlEditor.BUTTON_SIZE, 0,0);
		boss = new MyButton(AssetsEditor.bossOff, AssetsEditor.bossOn,  ControlEditor.BUTTON_SIZE,  ControlEditor.BUTTON_SIZE, 0,0);
		enemy = new MyButton(AssetsEditor.enemyOff, AssetsEditor.enemyOn,  ControlEditor.BUTTON_SIZE,  ControlEditor.BUTTON_SIZE, 0,0);
		delete = new MyButton(AssetsEditor.deleteImage, AssetsEditor.deleteImageOn,  ControlEditor.BUTTON_SIZE,  ControlEditor.BUTTON_SIZE, 0, 0);;
		separator1 = new MyButton(AssetsEditor.separator, AssetsEditor.separator, ControlEditor.BUTTON_SIZE+20,  ControlEditor.BUTTON_SIZE, 0, 0);
		separator2 = new MyButton(AssetsEditor.separator, AssetsEditor.separator, ControlEditor.BUTTON_SIZE+20,  ControlEditor.BUTTON_SIZE, 0, 0);
		
		/*Aggiungo gli ActionListener ai bottoni*/
		frecciadx.addActionListener(this);
		frecciasx.addActionListener(this);
		frecciadxP.addActionListener(this);
		frecciasxP.addActionListener(this);
		wall.addActionListener(this);
		breakable_wall.addActionListener(this);
		player.addActionListener(this);
		delete.addActionListener(this);
		powerUpLife.addActionListener(this);
		powerUpBomb.addActionListener(this);
		powerUpInvincible.addActionListener(this);
		powerUpBoost.addActionListener(this);
		trap.addActionListener(this);
		manhole.addActionListener(this);
		key.addActionListener(this);
		enemy.addActionListener(this);
		boss.addActionListener(this);
		
		/*Aggiungo i bottoni al pannello*/
		add(title);
		add(frecciasx);
		add(theme);
		add(frecciadx);
		add(subTitle1);
		add(separator1);
		add(frecciasxP);
		add(player);
		add(frecciadxP);
		add(separator2);
		add(subTitle);
		add(wall);		
		add(breakable_wall);
		add(manhole);
		add(key);
		add(trap);
		add(subTitle2);
		add(powerUpLife);
		add(powerUpBomb);
		add(powerUpInvincible);
		add(powerUpBoost);
		add(subTitle3);
		add(enemy);
		add(boss);
		add(delete);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == wall){
			switch(ControlEditor.getCurrentTheme()){
			case ControlEditor.NINJA:
				ControlEditor.paintImage(AssetsEditor.wallImage);
				break;
			case ControlEditor.TANK:
				ControlEditor.paintImage(AssetsEditor.wallTank);
				break;
			case ControlEditor.MAGE:
				ControlEditor.paintImage(AssetsEditor.wallMage);
				break;
			}	
			ControlEditor.type=ControlEditor.WALL;
			ControlEditor.deleteIsActive=false;
		}
		
		if (e.getSource() == breakable_wall){
			switch(ControlEditor.getCurrentTheme()){
			case ControlEditor.NINJA:
				ControlEditor.paintImage(AssetsEditor.breakableWallImage);
				break;
			case ControlEditor.TANK:
				ControlEditor.paintImage(AssetsEditor.breakableTank);
				break;
			case ControlEditor.MAGE:
				ControlEditor.paintImage(AssetsEditor.breakableMage);
				break;
			}
			ControlEditor.type=ControlEditor.BLOCK;
			ControlEditor.deleteIsActive=false;
		}
		
		if(e.getSource() == player){
			switch(currentChooseP){
			case 0:
				ControlEditor.paintImage(AssetsEditor.ninjaPlayer);
				break;
			case 1:
				ControlEditor.paintImage(AssetsEditor.tankPlayer);
				break;
			case 2:
				ControlEditor.paintImage(AssetsEditor.magePlayer);
				break;
			}	
			ControlEditor.type=ControlEditor.PLAYER;
			//ControlEditor.playerLimit=true;
			ControlEditor.deleteIsActive=false;
		}

		
		if (e.getSource() == delete){
			ControlEditor.deleteIsActive=true;
		}
		
		if (e.getSource() == frecciadx){
				currentChoose--;
				if(currentChoose == -1) {
					currentChoose = 2;
				}
				
				printTheme();
				ControlEditor.deleteIsActive=false;
		}
		
		if(e.getSource() == frecciasx) {
				currentChoose++;
				if(currentChoose == 3) {
					currentChoose = 0;
				
				}
				printTheme();
				ControlEditor.deleteIsActive=false;
		}
		
		if (e.getSource() == frecciadxP){
			currentChooseP--;
			if(currentChooseP == -1) {
				currentChooseP = 2;
			}
			wall.soundClick();
			setPlayer();
			ControlEditor.deleteIsActive=false;
		}
	
		if(e.getSource() == frecciasxP) {
				currentChooseP++;
				if(currentChooseP == 3) {
					currentChooseP = 0;
					System.out.println(currentChooseP);
				
				}
				setPlayer();
				ControlEditor.deleteIsActive=false;
		}
		
		if (e.getSource() == manhole){
			ControlEditor.paintImage(AssetsEditor.manhole[0]);
			ControlEditor.type=ControlEditor.MANHOLE;
			ControlEditor.deleteIsActive=false;
		}
	
		if (e.getSource() == key){
			ControlEditor.paintImage(AssetsEditor.key[0]);
			ControlEditor.type=ControlEditor.LEVER;
			ControlEditor.deleteIsActive=false;
		}
		if (e.getSource() == trap){
			ControlEditor.paintImage(AssetsEditor.trap[0]);
			ControlEditor.type=ControlEditor.TRAP;
			ControlEditor.deleteIsActive=false;
		}
		if (e.getSource() == powerUpLife){
			ControlEditor.paintImage(AssetsEditor.powerUpOff[0]);
			ControlEditor.type=ControlEditor.pLIFE;
			ControlEditor.deleteIsActive=false;
		}
		if (e.getSource() == powerUpBomb){
			ControlEditor.paintImage(AssetsEditor.powerUpOff[1]);
			ControlEditor.type=ControlEditor.pBOMB;
			ControlEditor.deleteIsActive=false;
		}
		if (e.getSource() == powerUpInvincible){
			ControlEditor.paintImage(AssetsEditor.powerUpOff[2]);
			ControlEditor.type=ControlEditor.pSTAR;
			ControlEditor.deleteIsActive=false;
		}
		if (e.getSource() == powerUpBoost){
			ControlEditor.paintImage(AssetsEditor.powerUpOff[3]);
			ControlEditor.type=ControlEditor.pBOOST;
			ControlEditor.deleteIsActive=false;
		}
		if (e.getSource() == enemy){
			ControlEditor.paintImage(AssetsEditor.enemyOff);
			ControlEditor.type=ControlEditor.ENEMY;
			ControlEditor.deleteIsActive=false;
		}
		if (e.getSource() == boss){
			ControlEditor.paintImage(AssetsEditor.bossOff);
			ControlEditor.type=ControlEditor.BOSS;
			ControlEditor.deleteIsActive=false;
		}
	}


	private void setPlayer() {
		if(currentChooseP==0){
			player.setImage(AssetsEditor.ninjaPlayer, AssetsEditor.ninjaPlayerOn);
			//ControlEditor.paintImage(AssetsEditor.ninjaPlayer);
		}
			
		if(currentChooseP==1){
			player.setImage(AssetsEditor.tankPlayer, AssetsEditor.tankPlayerOn);
			//ControlEditor.paintImage(AssetsEditor.tankPlayer);
		}
		if(currentChooseP==2){
			player.setImage(AssetsEditor.magePlayer, AssetsEditor.magePlayerOn);
			//ControlEditor.paintImage(AssetsEditor.magePlayer);
		}
				
	}

	public void printTheme(){
	
		if(currentChoose==0){
			ControlEditor.setCurrentTheme(ControlEditor.NINJA);
			theme.setImage(AssetsEditor.ninja_theme, AssetsEditor.ninja_theme);
			wall.setImage(AssetsEditor.wallImage, AssetsEditor.wallImageOn);
			breakable_wall.setImage(AssetsEditor.breakableWallImage, AssetsEditor.breakableWallImageOn);
			ControlEditor.setBackgroundEditor(AssetsEditor.background_ninja);
			editor.repaint();
		}
			
		if(currentChoose==1){
			ControlEditor.setCurrentTheme(ControlEditor.TANK);
			theme.setImage(AssetsEditor.tank_theme, AssetsEditor.tank_theme);
			wall.setImage(AssetsEditor.wallTank, AssetsEditor.wallTankOn);
			breakable_wall.setImage(AssetsEditor.breakableTank, AssetsEditor.breakableTankOn);
			ControlEditor.setBackgroundEditor(AssetsEditor.background_tank);
			editor.repaint();
		}
		if(currentChoose==2){
			ControlEditor.setCurrentTheme(ControlEditor.MAGE);
			theme.setImage(AssetsEditor.mage_theme, AssetsEditor.mage_theme);
			wall.setImage(AssetsEditor.wallMage, AssetsEditor.wallMageOn);
			breakable_wall.setImage(AssetsEditor.breakableMage, AssetsEditor.breakableMageOn);
			ControlEditor.setBackgroundEditor(AssetsEditor.background_mage);
			editor.repaint();
		}
		
		editor.setTheme();
	}
}
