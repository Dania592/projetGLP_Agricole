package data.espece.faune;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import data.espece.Milieu;
import data.espece.evolution.EvolutionAnimal;
import data.map.Map;
import data.production.Laine;
import data.production.Produit;
import data.structure.Etable;

public class Mouton extends AnimalProducteur {
	
	private final static int DUREE_VIE = 50 ;
	private final static int PRIX_ACHAT = 100 ;
	private final static int FREQUENCE_PRODUCTION = 50 ;
	private final static float POIDS = 50 ;
	private final static int QUANTITE = 10 ;
	private final static int SPEED_GROWTH = 2; 
	
	public Mouton(int ligne_init, int colonne_init, int naissance,String nom, String sexe, Etable habitat,String reference ,Map map) {
			
		super(ligne_init, colonne_init, Milieu.PLAINE, DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, Alimentation.HERBIVORE, sexe, habitat,
				FREQUENCE_PRODUCTION, QUANTITE, new Laine() , reference , map ,SPEED_GROWTH);
		try {
			String imagePath = "src"+File.separator+"ressources"+File.separator+"Mouton"
					+File.separator+EvolutionAnimal.JEUNE+File.separator+"stand.png";
			BufferedImage image = ImageIO.read(new File(imagePath));
			setImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public Produit collectProduction() {
		return new Laine();
	}

}
