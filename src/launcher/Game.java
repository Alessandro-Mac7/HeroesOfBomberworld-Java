package launcher;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import manager.GameStateManager;
import system.GameConfig;

//Classe che contiene tutto il necessario per avviare il gioco
@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable, KeyListener{
	
	//Manager degli Stati
	private GameStateManager gameStateManager;
	
	//Thread
	private Thread thread;

	//Condizione di vittoria
	private boolean goal=false;
	
	public Game(){
		init();
		setPanel();
	}
	
	//Setto le dimensioni del pannello
	public void setPanel(){
		this.setPreferredSize(new Dimension(GameConfig.DISPLAY_WIDTH, GameConfig.DISPLAY_HEIGHT));
		this.setMaximumSize(new Dimension(GameConfig.DISPLAY_WIDTH, GameConfig.DISPLAY_HEIGHT));
		this.setMinimumSize(new Dimension(GameConfig.DISPLAY_WIDTH, GameConfig.DISPLAY_HEIGHT));
		this.setFocusable(false);
	}
	
	//Inizializzo il manager degli stati
	private void init(){
		gameStateManager = new GameStateManager();
	}
	
	//Effettua l'update dello stato corrente
	public void update(){
		gameStateManager.update();
	}
	
	//Effettua il draw dello stato corrente
	public void paint(Graphics g){
		gameStateManager.draw(g);
		g.dispose();
	}
	
	
	//Gestione Thread
	@Override
	public void run() {
		
		/*Aggiornamenti per secondo*/
		int fps = 60;
		double timePerUpdate = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		
		while(goal){
			
			now = System.nanoTime();
			delta += (now - lastTime) / timePerUpdate;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				update();
				repaint();
				delta--;
			}
			
			if(timer >= 1000000000){
				timer = 0;
			}
		}
		
		stop();
	}
	
	public synchronized void start(){
		if(goal)
			return;
		goal=true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop(){
		if(!goal)
			return;
		goal=false;
		try{
			thread.join();
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
	}


	@Override
	public void keyPressed(KeyEvent e){
		gameStateManager.keyPressedEvent(e.getKeyCode());
	}


	@Override
	public void keyReleased(KeyEvent e) {
		gameStateManager.keyReleasedEvent(e.getKeyCode());

	}


	@Override
	public void keyTyped(KeyEvent e) {}
}
