package data.acteur;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import data.configuration.GameConfiguration;
import data.espece.evolution.EvolutionAnimal;
import data.map.Map;


public class Fermier extends Personne {

	private Date dateNaissance ;
	
	public Fermier(String nom, int ligne, int colonne , Date dateNaissance ,String reference , Map map ) {
		super(nom, ligne, colonne , reference , map);
		this.dateNaissance=dateNaissance;
		setImage(GameConfiguration.IMAGE_PATH+"Fermier"+File.separator+"default"+File.separator+"stand.png");	
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	@Override
	public int getMaxHourOfWork() {
		return Personne.MAX_HOUR_OF_WORK_FARMER;
	}
	

}
