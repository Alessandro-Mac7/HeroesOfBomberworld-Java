package graphics;

import javax.swing.JFrame;
import launcher.Game;
import system.GameConfig;

public class Display {

	//Dichiare Finestra
	private JFrame frame;
	private Game game;
	
	//Costruisco la finestra 
	public Display(){
		frame = new JFrame("Heroes of BomberWorld");
		frame.setSize(GameConfig.DISPLAY_WIDTH, GameConfig.DISPLAY_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setFocusable(true);
	}
	
	//Avvia il gioco
	public void start(){
		game=new Game();
		frame.setContentPane(game);
		frame.addKeyListener(game);
		frame.pack();
		
		game.start();
	}
	
}
