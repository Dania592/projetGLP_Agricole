package data.acteur;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import data.map.Map;


public class Fermier extends Personne{
	private Image farmerImage ;

	private Date dateNaissance ;
	
	public Fermier(String nom, int ligne, int colonne , Date dateNaissance ,String reference , Map map ) {
		super(nom, ligne, colonne , reference , map);
		this.dateNaissance=dateNaissance;
		try {
			setImage(ImageIO.read(new File("src"+File.separator+"ressources"+File.separator+"stand.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public Image getFarmerImage() {
		return farmerImage;
	}
	
	public void setFarmerImage(Image farmerImage) {
		this.farmerImage = farmerImage;
	}

	@Override
	public int getMaxHourOfWork() {
		return Personne.MAX_HOUR_OF_WORK_FARMER;
	}
	

}
