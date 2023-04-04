package data.espece.faune;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import data.espece.Milieu;
import data.espece.evolution.EvolutionAnimal;
import data.map.Map;
import data.production.Oeuf;
import data.production.Produit;
import data.structure.Poulallier;


public class Poule extends AnimalProducteur {

	private final static int DUREE_VIE = 500 ;
	private final static int PRIX_ACHAT = 1000 ;
	private final static int FREQUENCE_PRODUCTION = 50 ;
	private final static float POIDS = 50 ;
	private final static int QUANTITE = 10 ;
	
	public Poule(int ligne_init, int colonne_init,Milieu milieu , int naissance, String nom,  String sexe, Poulallier habitat,String reference , Map map ) {
			 
		super(ligne_init, colonne_init, milieu, DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, Alimentation.GRAINIVORE, sexe, habitat,
				FREQUENCE_PRODUCTION, QUANTITE, reference , map);
		
		String imagePath = "src"+File.separator+"ressources"+File.separator+"Poule"
				+File.separator+EvolutionAnimal.JEUNE+File.separator+"up.png";
		BufferedImage image ;
		
		try {
			image = ImageIO.read(new File(imagePath));
			setImage(image);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public Produit collectProduction() {
			return new Oeuf();
	}

	

}
