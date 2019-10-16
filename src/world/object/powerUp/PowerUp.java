package world.object.powerUp;

import graphics.Assets;
import world.object.AbstractObject;

//CLASSE DA CUI DERIVARE TUTTI I POWERUP, UTILE PER USARE UNA SOLA LISTA PER TUTTI I POTENZIAMENTI
public abstract class PowerUp extends AbstractObject{

	public PowerUp(int row, int col) {
		super(row, col);
	}
	
	public void play(){
		Assets.powerUp.play();

	}
}
