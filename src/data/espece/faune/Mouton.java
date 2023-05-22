package data.espece.faune;

import java.io.File;
import java.util.ArrayList;

import data.map.Map;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.notion.evolution.EvolutionAnimal;
import data.planning.Activity;
import data.production.Laine;
import data.production.Lait;
import data.production.Meat;
import data.production.Produit;
import data.production.Produits;
import data.structure.Etable;
import data.structure.Refuge;
import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.Structures;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.DomesticSpeciesVisitor;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;



public class Mouton extends AnimalProducteur{

	private static final long serialVersionUID = 1L;
	
	private final static int DUREE_VIE = 50 ;
	private final static int PRIX_ACHAT = 100 ;
	private final static int FREQUENCE_PRODUCTION = 50 ;
	private final static float POIDS = 50 ;
	private final static int QUANTITE = 1 ;
	private final static int SPEED_GROWTH = 5; 
	private final static Laine laine = new Laine();
	private final static Meat equivalentInMeat = new Meat();
	
	public Mouton( int naissance,String nom, String sexe, Etable habitat,String reference ) {
		super( DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, sexe, habitat,
				FREQUENCE_PRODUCTION, QUANTITE, laine, reference , SPEED_GROWTH);
		
			String imagePath = "src"+File.separator+"ressources"+File.separator+"Mouton"
					+File.separator+EvolutionAnimal.JEUNE+File.separator+"stand.png";
			setImage(imagePath);
	}
	

	@Override
	public <T> T launchAction(DomesticSpeciesVisitor<T> visitor) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException{
		return visitor.action(this); 
	}


	@Override
	public TimeItTakes getTimeItTakes() {
		return TimeItTakes.MOUTON;
	}
	
	public Animals getKey() {
		return Animals.MOUTON;
	}


	@Override
	public boolean needSpecialPlaceToGetProduction() {
		return false;
	}


	@Override
	public Structures getHomeLabel() {
		return Structures.BERGERIE_MOUTON;
	}


	@Override
	public Meat getEquivalentInMeat() {
		return equivalentInMeat;
	}


	@Override
	public boolean needSpecialActionToGetProduction() {
		return true;
	}

}
