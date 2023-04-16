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


public class Fermier extends Personne{
	private ImageIcon farmerImage ;

	private Date dateNaissance ;
	
	public Fermier(String nom, int ligne, int colonne , Date dateNaissance ,String reference , Map map ) {
		super(nom, ligne, colonne , reference , map);
		this.dateNaissance=dateNaissance;
		
		String imagePath = "src"+File.separator+"ressources"+File.separator+"Fermier"
				+File.separator+"default"+File.separator+"STAND.png";
		setImage(imagePath);
//		try {
//			BufferedImage image = ImageIO.read(new File(imagePath));
//			setImage(image);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public ImageIcon getFarmerImage() {
		return farmerImage;
	}
	
	public void setFarmerImage(ImageIcon farmerImage) {
		this.farmerImage = farmerImage;
	}

	@Override
	public int getMaxHourOfWork() {
		return Personne.MAX_HOUR_OF_WORK_FARMER;
	}
	

}
