package music;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundLauncher {
	public Clip clip;
	 
	/**
	 * Instancie un nouveau sound launcher.
	 *
	 * @param fichier le nom du fichier
	 */
	public SoundLauncher(String fichier) {
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
