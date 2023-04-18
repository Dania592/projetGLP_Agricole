package data.espece.faune;


import data.espece.Milieu;
import data.map.Map;
import data.structure.Maison;
import process.action.visitor.being.HaveNotProducedYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.DomesticSpeciesVisitor;


public class Chien extends AnimalCompagnie {
	
	private final static int DUREE_VIE = 500 ;
	private final static int PRIX_ACHAT = 1000 ;
	private final static float POIDS = 50 ;
	private final static int SPEED_GROWTH = 50 ; 
	
	
	public Chien(int ligne_init, int colonne_init, Milieu milieu, int naissance, String nom, String sexe, Maison habitat , String reference , Map map ) {
		super(ligne_init, colonne_init, milieu, DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, Alimentation.CARNIVORE, sexe, habitat , reference , map , SPEED_GROWTH);
	}


	@Override
	public <T> T launchAction(DomesticSpeciesVisitor<T> visitor) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
		return visitor.action(this); 
	}

}
