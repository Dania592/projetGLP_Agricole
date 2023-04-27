package data.espece.faune;

import java.io.File;

import data.espece.Milieu;
import data.espece.evolution.EvolutionAnimal;
import data.map.Map;
import data.production.Laine;
import data.production.Lait;
import data.production.Meat;
import data.production.Produit;
import data.production.Produits;
import data.structure.Etable;
import data.structure.Refuge;
import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.Structures;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.DomesticSpeciesVisitor;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;

public class Mouton extends AnimalProducteur{

	private static final long serialVersionUID = 1L;
	
	private final static int DUREE_VIE = 50 ;
	private final static int PRIX_ACHAT = 100 ;
	private final static int FREQUENCE_PRODUCTION = 50 ;
	private final static float POIDS = 50 ;
	private final static int QUANTITE = 10 ;
	private final static int SPEED_GROWTH = 2; 
	private final static Laine laine = new Laine();
	private final static Meat equivalentInMeat = new Meat();
	
	public Mouton(int ligne_init, int colonne_init, int naissance,String nom, String sexe, Etable habitat,String reference ,Map map) {
		super(ligne_init, colonne_init, Milieu.PLAINE, DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, Alimentation.HERBIVORE, sexe, habitat,
				FREQUENCE_PRODUCTION, QUANTITE, laine, reference , map ,SPEED_GROWTH);
		
			String imagePath = "src"+File.separator+"ressources"+File.separator+"Mouton"
					+File.separator+EvolutionAnimal.JEUNE+File.separator+"stand.png";
			setImage(imagePath);
	}
	

	@Override
	public <T> T launchAction(DomesticSpeciesVisitor<T> visitor) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, NotImplementYetException {
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
		return false;
	}





}
