package gui.Farm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import data.acteur.Fermier;
import data.map.Map;
import gui.Farm.farmer.FermierGui;
public class Musique {

	  public static void main(String[] args) {
	  
		  FermierGui fermier = new FermierGui(null, new Fermier(null, new Date(), null));
	       try {
	           URL url= Musique.class.getClassLoader().getResource("src/ressources/musique/m.wav");
	           File file = new File("src/ressources/musique/EZ02.wav");
	           
	           AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
	           Clip clip = AudioSystem.getClip();
	           clip.open(audioIn);
	           clip.start();
	           clip.loop(clip.LOOP_CONTINUOUSLY);
	       }
		catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			throw new RuntimeException(e);
		}
	    }
}
