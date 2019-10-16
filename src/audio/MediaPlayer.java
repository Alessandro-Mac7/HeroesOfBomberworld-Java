package audio;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/*Classe che riproduce i suoni*/
public class MediaPlayer {
	
	/*Clip per riprodurre l'audio*/
	private Clip clip;
	
	public MediaPlayer(String s) {
		
		/*Caricamento risorsa audio*/
		try {
			AudioInputStream input = AudioSystem.getAudioInputStream(new File(s));
			clip = AudioSystem.getClip();
			clip.open(input);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*Starta l'audio, se è gia partita allora stoppa la precedente e ristarta la corrente*/
	public void play() {
		if(clip == null) return;
			stop();
			
		if(clip.isActive())
			return; 
		
		clip.setFramePosition(0);
		clip.start();
		
	}
	
	/*Metodo per poter mandare in Loop l'audio*/
	public void loop(int number){
		clip.loop(number);
	}
	
	/*Metodo per stoppare l'audio*/
	public void stop() {
		if(clip.isRunning()) clip.stop();
	}
	
	/*Metodo per chiudere l'audio*/
	public void close() {
		stop();
		clip.close();
	}
	
}
