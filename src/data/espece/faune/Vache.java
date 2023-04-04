package data.espece.faune;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import data.espece.Milieu;
import data.espece.evolution.EvolutionAnimal;
import data.map.Map;
import data.production.Lait;
import data.production.Produit;
import data.structure.Etable;



public class Vache extends AnimalProducteur{

	private final static int DUREE_VIE = 300 ;
	private final static int PRIX_ACHAT = 1000 ;
	private final static int FREQUENCE_PRODUCTION = 50 ;
	private final static float POIDS = 300 ;
	private final static int QUANTITE = 100 ;
	
	public Vache(int ligne_init, int colonne_init, int naissance, String nom, String sexe, Etable habitat , String reference , Map map) {
		
		super(ligne_init, colonne_init, Milieu.PLAINE, DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, Alimentation.HERBIVORE, sexe, habitat,
				FREQUENCE_PRODUCTION, QUANTITE, reference , map);
		try {
			String imagePath = "src"+File.separator+"ressources"+File.separator+"Vache"
					+File.separator+EvolutionAnimal.JEUNE+File.separator+"stand.png";
			BufferedImage image = ImageIO.read(new File(imagePath));
			setImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Produit collectProduction() {
		return new Lait();
	}


}
