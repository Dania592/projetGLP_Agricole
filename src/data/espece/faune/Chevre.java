package data.espece.faune;

import java.io.File;

import data.espece.evolution.EvolutionAnimal;
import data.production.Lait;
import data.production.Meat;
import data.structure.Etable;
import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.Structures;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.DomesticSpeciesVisitor;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;

/**
 * class de donnée pour la chèvre
 * @author dania
 *
 */
public class Chevre extends AnimalProducteur implements MilkProduceur{
/**
 * 
 */
	private static final long serialVersionUID = 1L;
	/**
	 * Durée de vie de l'animal
	 */
	private final static int DUREE_VIE = 500 ;
	/**
	 * Prix d'achat de l'animal
	 */
	private final static int PRIX_ACHAT = 500 ;
	/**
	 * fréquence de production de l'animal
	 */
	private final static int FREQUENCE_PRODUCTION = 50 ;
	/**
	 * poids de l'animal 
	 */
	private final static float POIDS = 40 ;
	/**
	 * quantité produite par l'animal
	 */
	private final static int QUANTITE = 20 ;
	/**
	 * vitesse d'évolution de l'animal
	 */
	private final static int SPEED_GROWTH = 1 ; 
	/**
	 * produit de l'animal : lait 
	 */
	private final static Lait lait = new Lait();
	/**
	 * équivalent en viande 
	 */
	private final static Meat equivalentInMeat = new Meat();
	/**
	 * constructeur de la chèvre 
	 * @param naissance : heure de naissance de l'animal 
	 * @param nom : nom de l'animal
	 * @param sexe : sexe de l'animal 
	 * @param habitat : structure de l'animal 
	 * @param reference : référence par rapport à la map 
	 */
	public Chevre( int naissance, String nom,  String sexe, Etable habitat , String reference) {
		super( DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, sexe, habitat,
				FREQUENCE_PRODUCTION, QUANTITE, lait, reference , SPEED_GROWTH);
		
			String imagePath = "src"+File.separator+"ressources"+File.separator+"Chevre"
					+File.separator+EvolutionAnimal.JEUNE+File.separator+"STAND.png";
			setImage(imagePath);
	}

	/**
	 * visitor pour recupérer le produit 
	 */
	@Override
	public <T> T launchAction(DomesticSpeciesVisitor<T> visitor) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException {
		return visitor.action(this);
	}

	/**
	 * retourne le temps que l'animal met à produire
	 */
	@Override
	public TimeItTakes getTimeItTakes() {
		return TimeItTakes.CHEVRE;
	}

	/**
	 * retourne la clé de l'animal
	 */
	public Animals getKey() {
		return Animals.CHEVRE;
	}
	/**
	 * vérifie si l'animal a besoin d'une structure pour récupérer son produit 
	 */

	@Override
	public boolean needSpecialPlaceToGetProduction() {
		return true;
	}

	/**
	 * retourne la clé de l'habitat de l'animal 
	 */
	@Override
	public Structures getHomeLabel() {
		return Structures.BERGERIE_CHEVRE;
	}

	/**
	 * retourne l'équivalent en viande de l'animal 
	 */
	@Override
	public Meat getEquivalentInMeat() {
		return equivalentInMeat;
	}

	/**
	 * vérifie si l'animal a besoin d'une action spéciale pour recupérer son produit 
	 */
	@Override
	public boolean needSpecialActionToGetProduction() {
		return false;
	}





	

	
}
