package data.espece.faune;


import java.io.File;

import data.espece.Milieu;
import data.espece.evolution.EvolutionAnimal;
import data.map.Map;
import data.production.Lait;
import data.production.Meat;
import data.production.Oeuf;
import data.production.Produit;
import data.production.Produits;
import data.structure.Poulallier;
import data.structure.Refuge;
import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.Structures;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.DomesticSpeciesVisitor;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;


public class Poule extends AnimalProducteur{

	private static final long serialVersionUID = 1L;
	
	private final static int DUREE_VIE = 500 ;
	private final static int PRIX_ACHAT = 1000 ;
	private final static int FREQUENCE_PRODUCTION = 50 ;
	private final static float POIDS = 50 ;
	private final static int QUANTITE = 10 ;
	private final static int SPEED_GROWTH = 5 ; 
	private static Oeuf oeuf = new Oeuf();
	private static Meat equivalentInMeat = new Meat();
	
	public Poule(int ligne_init, int colonne_init,Milieu milieu , int naissance, String nom,  String sexe, Poulallier habitat,String reference , Map map ) {
			 
		super(ligne_init, colonne_init, milieu, DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, Alimentation.GRAINIVORE, sexe, habitat,
				FREQUENCE_PRODUCTION, QUANTITE, oeuf, reference , map , SPEED_GROWTH);
		
			String imagePath = "src"+File.separator+"ressources"+File.separator+"Poule"
					+File.separator+EvolutionAnimal.JEUNE+File.separator+"STAND.png";
			
			setImage(imagePath);
		
	}


	@Override
	public <T> T launchAction(DomesticSpeciesVisitor<T> visitor) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException {
		return visitor.action(this);
	}

	@Override
	public TimeItTakes getTimeItTakes() {
		return TimeItTakes.POULE;
	}

	public Animals getKey() {
		return Animals.POULE;
	}
	
	@Override
	public boolean needSpecialPlaceToGetProduction() {
		return false;
	}

	@Override
	public Structures getHomeLabel() {
		return Structures.POULAILLER;
	}

	@Override
	public Meat getEquivalentInMeat() {
		return equivalentInMeat;
	}

	@Override
	public boolean needSpecialActionToGetProduction() {
		return false;
	}
	
}
