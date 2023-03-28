package data.espece.faune;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import data.espece.Milieu;
import data.map.Map;
import data.production.Lait;
import data.structure.Etable;


public class Chevre extends AnimalProducteur{

	
	private final static int DUREE_VIE = 500 ;
	private final static int PRIX_ACHAT = 500 ;
	private final static int FREQUENCE_PRODUCTION = 50 ;
	private final static float POIDS = 40 ;
	private final static int QUANTITE = 20 ;
	
	public Chevre(int ligne_init, int colonne_init, int naissance, String nom,  String sexe, Etable habitat , String reference ,Map map) {
		super(ligne_init, colonne_init, Milieu.MONTAGNE, DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, Alimentation.HERBIVORE, sexe, habitat,
				FREQUENCE_PRODUCTION, QUANTITE, new Lait(), reference , map);
		
		try {
			setImage(ImageIO.read(new File("src"+File.separator+"ressources"+File.separator+"minichevre.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
