package data.espece.faune;

import data.espece.Milieu;
import data.map.Map;
import data.structure.Maison;
import gui.gestionnaire.keys.Animals;



public class Chat extends AnimalCompagnie{

	
	private final static int DUREE_VIE = 500 ;
	private final static int PRIX_ACHAT = 100 ;
	private final static float POIDS = 50 ;	
	
	public Chat(int ligne_init, int colonne_init, Milieu milieu,  int naissance,String nom, Maison habitat , String sexe, String reference , Map map ) {
		super(ligne_init, colonne_init, milieu, DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, Alimentation.CARNIVORE, sexe, habitat ,reference , map );
	}

	public Animals getKey() {
		return Animals.CHAT;
	}

}

// habitat == maison 