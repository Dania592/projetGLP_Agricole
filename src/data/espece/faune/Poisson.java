package data.espece.faune;

import java.util.Date;

import data.espece.Milieu;
import data.map.Map;
import data.production.Oeuf;
import data.structure.CagePoisson;



public class Poisson extends AnimalProducteur{
	private final static int DUREE_VIE = 30 ;
	private final static int PRIX_ACHAT = 10 ;
	private final static int FREQUENCE_PRODUCTION = 1 ;
	private final static float POIDS = 10;
	private final static int QUANTITE = 10 ;
	
	public Poisson(int ligne_init, int colonne_init, Date naissance, String nom , String sexe, CagePoisson habitat,String reference , Map map) {
		
		super(ligne_init, colonne_init, Milieu.AQUATIQUE, DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, Alimentation.CARNIVORE, sexe, habitat,
				FREQUENCE_PRODUCTION, QUANTITE,new Oeuf() , reference ,map);

	}

}
