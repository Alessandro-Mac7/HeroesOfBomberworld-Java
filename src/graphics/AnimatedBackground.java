package graphics;

import java.awt.Graphics;
import system.GameConfig;

public class AnimatedBackground {

	/*Variabile posizioni e velocita*/
	private double row;
	private double col;
	private double velX;
	private double velY;
	
	public AnimatedBackground() {
		row = 0;
		col = 0;
	}
	
	/*Setto le posizioni dell'immagine*/
	public void setPosition(double row, double col) {
		this.row = row;
		this.col = col;
	}
	
	/*Setto la velocità di movimento o verso destra o verso sinistra*/
	public void setVelocity(double velX, double velY) {
		this.velX = velX;
		this.velY = velY;
	}
	
	public void update() {
		
		/*Incremento le posizioni x e y in base alla velocità finche non raggionge la larghezza dello schermo*/
		
		if(row<GameConfig.DISPLAY_WIDTH){
			row += velX;
			col += velY;
		}
		else {
			row=0;
			col=0;
		}
	}
	
	public void draw(Graphics g) {
		
		g.drawImage(Assets.animatedBackground, (int)row, (int)col, null);
		 
		/*	Sposta a sinistra <0 a destra >0	*/
		if(row < 0) {
			g.drawImage(Assets.animatedBackground,(int)row + GameConfig.DISPLAY_WIDTH, (int)col, null);
		}
		
		if(row > 0) {
			g.drawImage(Assets.animatedBackground, (int)row - GameConfig.DISPLAY_WIDTH, (int)col, null);
		}
	}

}
