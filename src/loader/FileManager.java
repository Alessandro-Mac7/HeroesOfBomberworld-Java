package loader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import editor.MyFileFilter;
import graphics.Assets;
import system.GameConfig;

public class FileManager {

	/*Lista Score*/
	List <String> scoreList;
	
	/*Mappa*/
	char[][] level_map;
	
	/*Variabili per poter scrivere e leggere su file*/
	static BufferedWriter writer;
	File score;
	
	/*Costuttore per caricare le mappe*/
	public FileManager(char[][] map) {
		level_map = map;
	}
	
	/*Costruttore per caricare lo score*/
	public FileManager(List<String> score){
		this.scoreList = score;
	}
	
	/*Costruttore per scrivere lo score*/
	public FileManager(){
		
		writer = null;
		score = new File("score/score.txt");
	}

	/*Metodo che aggiunge lo score del player nel file score.txt*/
	public void addRecord(){
		
		try {
			/*Scrivo prima il nome e poi il punteggio */
			writer = new BufferedWriter(new FileWriter(score, true));
			if(GameConfig.isCustomLevel())
				writer.write(GameConfig.playerName + " " + String.valueOf(GameConfig.scoreCustom));
			else
				writer.write(GameConfig.playerName + " " + String.valueOf(GameConfig.getTotalScore()));
		    
			writer.newLine();
		    
		    if (writer != null) {
			     writer.close();
		    }
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*metodo che legge lo score e lo aggiunge allo scoreList*/
	public void readFile(){
		String path = new String("score/score.txt");
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null) {
				scoreList.add(line);
			}
			br.close();
		} 	catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*metodo che salva lo stato attuale del gioco con i livelli sbloccati e il personaggio usato*/
	public static void saveGame(){
		
		File currentFile = new File(GameConfig.getName());
		try (PrintWriter pw = new PrintWriter(currentFile)) {
			pw.println(GameConfig.getCurrentLevel());
			pw.print(GameConfig.player_type);
		
			Assets.save.play();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*metodo che carica il livello servendosi di uno chooser per poterlo selezionare*/
	public static boolean loadGame(){
	
		final JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("saved/"));
		chooser.setFileFilter(new MyFileFilter());
		final int openDialog = chooser.showOpenDialog(new JFrame());
		
		if (openDialog == JFileChooser.APPROVE_OPTION) {
			
			BufferedReader br;
			try {
				
				br = new BufferedReader(new FileReader(chooser.getSelectedFile()));
				String line;
				int row = 0;
				while ((line = br.readLine()) != null) {
					if(row == 0){
						int num = Integer.parseInt(line);
						GameConfig.setToSpecificLevel(num);
					}
					if(row > 0)
						GameConfig.player_type = line;
					row++;
				}

				JOptionPane.showMessageDialog(new JFrame(), "Livello caricato");

				br.close();
				return true;
			} 	catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(new JFrame(),	
						"Impossibile leggere il file: " + chooser.getSelectedFile().getAbsolutePath(),
							"Errore", JOptionPane.ERROR_MESSAGE);
			}
		}
		return false;
	
	}
	
	/*metodo per caricare la mappa del gioco a seconda del livello passato*/
	public void loadMap(int lvl) {
		
		String path = new String("resources/level/level_" + lvl + ".txt");
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			String line;
			int row = 0;
			while ((line = br.readLine()) != null) {
				
				if(row < GameConfig.SIZE_MAP){
					for (int i = 0; i < GameConfig.SIZE_MAP; i++) {
					level_map[row][i] = line.charAt(i);
					}
					row++;
				}
				else
					GameConfig.theme = line.charAt(0);
			}
			
			br.close();
		
		} 	catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/*metodo per caricare la mappa custom*/
	public void loadCustomMap() {
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(GameConfig.getPath()));
			String line;
			int row = 0;
			while ((line = br.readLine()) != null) {
				
				if(row < GameConfig.SIZE_MAP){
					for (int i = 0; i < GameConfig.SIZE_MAP; i++) {
						level_map[row][i] = line.charAt(i);
					}
					row++;
				}
				else
					GameConfig.theme = line.charAt(0);

			}
			br.close();
		} 	catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}	