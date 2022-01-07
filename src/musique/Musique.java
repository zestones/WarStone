package musique;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Class Musique.
 */
public class Musique {
	
	/** The clip. */
	public Clip clip;
	 
	/**
	 * Instancie une nouvelle musique.
	 *
	 * @param fichier : le nom du fichier
	 */
	public Musique(String fichier) {
		File f = new File("./res/sound/" + fichier);
		AudioInputStream audioIn = null;
		try {
			audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
		} catch (UnsupportedAudioFileException | IOException e1) {
			e1.printStackTrace();
		}  
		try {
			this.clip = AudioSystem.getClip();
			this.clip.loop(0);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	    try {
	    	this.clip.open(audioIn);
	    	this.clip.loop(Clip.LOOP_CONTINUOUSLY);
	    } catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}

}
