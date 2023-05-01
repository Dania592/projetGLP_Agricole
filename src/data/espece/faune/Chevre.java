package data.espece.faune;

import java.io.File;

import data.espece.Milieu;
import data.espece.evolution.EvolutionAnimal;
import data.map.Map;
import data.production.Lait;
import data.production.Meat;
import data.production.Produit;
import data.production.Produits;
import data.structure.Etable;
import data.structure.Refuge;
import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.Structures;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.DomesticSpeciesVisitor;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;


public class Chevre extends AnimalProducteur implements MilkProduceur{

	private static final long serialVersionUID = 1L;
	
	private final static int DUREE_VIE = 500 ;
	private final static int PRIX_ACHAT = 500 ;
	private final static int FREQUENCE_PRODUCTION = 50 ;
	private final static float POIDS = 40 ;
	private final static int QUANTITE = 20 ;
	private final static int SPEED_GROWTH = 1 ; 
	private final static Lait lait = new Lait();
	private final static Meat equivalentInMeat = new Meat();
	
	public Chevre( int naissance, String nom,  String sexe, Etable habitat , String reference) {
		super( Milieu.MONTAGNE, DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, Alimentation.HERBIVORE, sexe, habitat,
				FREQUENCE_PRODUCTION, QUANTITE, lait, reference , SPEED_GROWTH);
		
			String imagePath = "src"+File.separator+"ressources"+File.separator+"Chevre"
					+File.separator+EvolutionAnimal.JEUNE+File.separator+"STAND.png";
			setImage(imagePath);
	}

	@Override
	public <T> T launchAction(DomesticSpeciesVisitor<T> visitor) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException {
		return visitor.action(this);
	}

	@Override
	public TimeItTakes getTimeItTakes() {
		return TimeItTakes.CHEVRE;
	}

	public Animals getKey() {
		return Animals.CHEVRE;
	}

	@Override
	public boolean needSpecialPlaceToGetProduction() {
		return true;
	}

	@Override
	public Structures getHomeLabel() {
		return Structures.BERGERIE_CHEVRE;
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
