package data.espece.faune;

import java.util.Date;

import data.espece.Milieu;
import data.map.Map;
import data.production.Oeuf;
import data.structure.RefugeCanardLune;
import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.Keys;
import process.visitor.GestionVisitor;



public class CanardLune extends AnimalProducteur {
	
	private final static int DUREE_VIE = 100 ;
	private final static int PRIX_ACHAT = 500 ;
	private final static int FREQUENCE_PRODUCTION = 50 ;
	private final static float POIDS = 50 ;
	private final static int QUANTITE = 10 ;

	// l'habitat d'un canard est fixe pour tout les canards ==> constante 
	public CanardLune(int ligne_init, int colonne_init, int naissance,String nom, String sexe, RefugeCanardLune habitat,String reference ,Map map) {
		super(ligne_init, colonne_init, Milieu.ESPACE, DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, Alimentation.OMNIVRE, sexe, habitat,
				FREQUENCE_PRODUCTION, QUANTITE, new Oeuf() ,reference ,map);

	}

	@Override
	public Animals getKey() {
		return Animals.CANARD_LUNE;
	}


}
