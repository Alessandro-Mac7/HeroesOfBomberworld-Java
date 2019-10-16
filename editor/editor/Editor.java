package editor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JFrame;


import system.AssetsEditor;

public class Editor {

	public static void main(String[] args) {
		/*Creo Finestra*/
		JFrame frame = new JFrame("Editor Maps");
		
		/*Carico le immagini*/
		AssetsEditor.loadAssets();
		
		/*Creo i pannelli editor e object*/
		EditorPanel panel = new EditorPanel();
		ObjectPanel objectPanel = new ObjectPanel(panel);
		
		/*Creo il menuBar*/
		MyMenu menuBar = new MyMenu(panel);
		frame.setJMenuBar(menuBar);
		
		/*Creo il contenitore e aggiungo i pannelli utilizzando i Layout*/
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new FlowLayout());
		contentPane.add(panel, BorderLayout.EAST);
		contentPane.add(objectPanel, BorderLayout.WEST);
		
		/*Setto la finestra*/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	
	
			
	}

}
