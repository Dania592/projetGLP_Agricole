package data.espece.faune;


import data.espece.Milieu;
import data.map.Map;
import data.structure.Maison;
import gui.gestionnaire.keys.Animals;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.DomesticSpeciesVisitor;
import process.action.visitor.being.exception.HaveNotProducedYetException;


public class Chien extends AnimalCompagnie {

	private static final long serialVersionUID = 1L;
	
	private final static int DUREE_VIE = 500 ;
	private final static int PRIX_ACHAT = 1000 ;
	private final static float POIDS = 50 ;
	private final static int SPEED_GROWTH = 50 ; 
	
	
	public Chien( Milieu milieu, int naissance, String nom, String sexe, Maison habitat , String reference  ) {
		super(milieu, DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, Alimentation.CARNIVORE, sexe, habitat , reference ,  SPEED_GROWTH);
	}


	@Override
	public <T> T launchAction(DomesticSpeciesVisitor<T> visitor) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
		return visitor.action(this); 
	}

	public Animals getKey() {
		return Animals.CHIEN;
	}
	
}
