package data.espece.faune;

import data.espece.Milieu;
import data.map.Map;
import data.production.Lait;
import data.structure.Etable;
import gui.gestionnaire.keys.Animals;



// equivalent de la vache dans l'espace 
public class Tradigarde extends AnimalProducteur {

	
	private final static int DUREE_VIE = 500 ;
	private final static int PRIX_ACHAT = 1500 ;
	private final static int FREQUENCE_PRODUCTION = 10 ;
	private final static float POIDS = 500 ;
	private final static int QUANTITE = 20 ;
	
	public Tradigarde(int ligne_init, int colonne_init,int naissance,String nom, String sexe, Etable habitat , String reference ,Map map) {
		super(ligne_init, colonne_init, Milieu.ESPACE, DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, Alimentation.OMNIVRE, sexe, habitat,
				FREQUENCE_PRODUCTION, QUANTITE,new Lait() , reference , map);
		
	}

	public Animals getKey() {
		return Animals.TRADIGARDE;
	}
}
