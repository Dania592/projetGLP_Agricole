package data.acteur;

import java.awt.Image;
import java.io.File;
import java.util.Date;

import javax.swing.ImageIcon;

import data.map.Map;
import data.travail.Planning;


public class Fermier extends Personne{
	private Image farmerImage ;

	private Date dateNaissance ;
	
	public Fermier(String nom, Planning planning, int ligne, int colonne , Date dateNaissance ,String reference , Map map ) {
		super(nom, planning, ligne, colonne , reference , map);
		this.dateNaissance=dateNaissance;
		setImage(new ImageIcon("src"+File.separator+"ressources"+File.separator+"stand.png"));
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
	

}
