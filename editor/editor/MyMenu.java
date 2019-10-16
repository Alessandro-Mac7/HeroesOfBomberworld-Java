package editor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import system.ControlEditor;

@SuppressWarnings("serial")
public class MyMenu extends JMenuBar{
	
	private EditorPanel editor;
	
	public MyMenu(EditorPanel editor){
		
		this.editor=editor;
		init();
		
	}
	
	private void init() {
		/*Creo Menu e gli ItemMenu*/
		final JMenu menu = new JMenu("File");
		final JMenuItem load = new JMenuItem("Apri");
		final JMenuItem save = new JMenuItem("Salva");
		final JMenuItem saveAs = new JMenuItem("Salva con nome..");
		final JMenuItem reset = new JMenuItem("Reset");
		
		/*Setto gli actionListener*/
		load.addActionListener(e -> load());
		save.addActionListener(e -> save());
		saveAs.addActionListener(e -> saveAs());
		reset.addActionListener(e -> reset());
		
		/*Aggiungo gli Item al Menu*/
		menu.add(load);
		menu.add(save);
		menu.add(saveAs);
		menu.add(reset);
		
		/*Aggiungo il menu al menuBar*/
		this.add(menu);		
	}

	private void save() {
		
		if(editor.limitKey() && editor.limitManhole() && editor.limitPlayer()){
	
			File currentFile = new File(ControlEditor.getName());
			
			try (PrintWriter pw = new PrintWriter(currentFile)) {
				
				editor.fillMap();
				editor.screenShoot(ControlEditor.getScreenName());
				
				for(int i = 0; i< ControlEditor.SIZE_MAP; i++){
					for(int j = 0; j < ControlEditor.SIZE_MAP; j++){
						pw.print(editor.getMap(i, j));
					}
					pw.println();
				}
				pw.print(ControlEditor.getCurrentTheme());
				JOptionPane.showMessageDialog(new JFrame(), "File salvato con successo!");
			
			
			} catch (final IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(new JFrame(), "Impossibile scrivere il file!" + currentFile.getAbsolutePath(),
						"Errore", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
			JOptionPane.showMessageDialog(new JFrame(), "Impossibile salvare il file, inserire almeno una botola, una chiave e un player!" ,
					"Errore", JOptionPane.ERROR_MESSAGE);
	}

	private void reset() {
		editor.resetMap();
	}

	public void load() {
		
		final JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("resources/custom_level"));
		chooser.setFileFilter(new MyFileFilter());
		editor.resetMap();
		final int openDialog = chooser.showOpenDialog(new JFrame());
		
		if (openDialog == JFileChooser.APPROVE_OPTION) {
			
			BufferedReader br;
			try {
				
				br = new BufferedReader(new FileReader(chooser.getSelectedFile()));
				String line;
				int row = 0;
				while ((line = br.readLine()) != null) {
					if(row < ControlEditor.SIZE_MAP){
						for (int i = 0; i < ControlEditor.SIZE_MAP; i++) {
							editor.setMap(row, i, line.charAt(i));
						}
						row++;
					}
					else
						ControlEditor.setCurrentTheme(line.charAt(0));
				}
				br.close();
			} 	catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(new JFrame(),	
						"Impossibile leggere il file: " + chooser.getSelectedFile().getAbsolutePath(),
							"Errore", JOptionPane.ERROR_MESSAGE);
			}
			
			editor.drawMatrixLoaded();
		}
	
	}
	
	public void saveAs(){
		
		if(editor.limitKey() && editor.limitManhole() && editor.limitPlayer()){
			
			final JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("resources/custom_level"));
			chooser.setFileFilter(new MyFileFilter());
			final int saveDialog = chooser.showSaveDialog(new JFrame());
			
			if (saveDialog == JFileChooser.APPROVE_OPTION) {
				final File selectedFile = chooser.getSelectedFile();
				if (selectedFile.exists()) {
					final int confirmDialog = JOptionPane.showConfirmDialog(new JFrame(), "File già esistente, vuoi sovrascriverlo?");
					if (confirmDialog != JOptionPane.YES_OPTION) {
						return;
					}
				}
				try (PrintWriter pw = new PrintWriter(selectedFile)) {
					
					editor.fillMap();
					
					String path = selectedFile.getAbsolutePath().substring(0, selectedFile.getAbsolutePath().length() - 3);
					System.out.println(path);
					
					editor.screenShoot(path + "png");
					
					for(int i = 0; i< ControlEditor.SIZE_MAP; i++){
						for(int j = 0; j < ControlEditor.SIZE_MAP; j++){
							pw.print(editor.getMap(i, j));
						}
						if(i < ControlEditor.SIZE_MAP - 1)
							pw.println();
					}
					JOptionPane.showMessageDialog(new JFrame(), "File salvato con successo!");
				
				
				} catch (final IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(new JFrame(), "Impossibile scrivere il file!" + selectedFile.getAbsolutePath(),
							"Errore", JOptionPane.ERROR_MESSAGE);
				}
			
			}

		}
		else
			JOptionPane.showMessageDialog(new JFrame(), "Impossibile salvare il file, inserire almeno una botola, una chiave e un player!" ,
					"Errore", JOptionPane.ERROR_MESSAGE);
	}
}

