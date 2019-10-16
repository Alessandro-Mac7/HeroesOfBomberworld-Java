package graphics;

import java.awt.image.BufferedImage;

/*Classe che anima i vari oggetti presenti nel gioco*/
public class Animation {

	/*Velocità in millisecondi e indice*/
	private int velocity, index;
	/*tempo corrente e ultimo tempo*/
	private long lastTime, time;
	/*variabile necessaria a capire se devo disegnare solo l'indice 0 di un'immagine*/
	private boolean first = false;
	/*array di immagini*/
	private BufferedImage[] animation_frame;
	 
	/*Imposto l'array nel costruttore e la velocità in millisecondi*/
	public Animation(int velocity, BufferedImage[] animation_frame){
		this.velocity = velocity;
		this.animation_frame = animation_frame;
		index = 0;
		time = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void update(){
		/*imposto il tempo attuale meno il tempo passato*/
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		/*fin quando il tempo è maggiore della velocita incrementa gli indici*/
		if(time > velocity){
			index++;
			time = 0;
			if(index >= animation_frame.length)
				{
					index = 0;
				}
		}
	}
	
	/*restituisce il frame corrente*/
	public BufferedImage getCurrentFrame(){
		if(first)
			return animation_frame[index];
		else
			return animation_frame[0];
		
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean moving) {
		this.first = moving;
	}
	
	public int getIndex(){
		return index;
	}
	
}
