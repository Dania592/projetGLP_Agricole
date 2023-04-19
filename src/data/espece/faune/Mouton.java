package data.espece.faune;

import java.io.File;

import data.espece.Milieu;
import data.espece.evolution.EvolutionAnimal;
import data.map.Map;
import data.production.Laine;
import data.production.Produit;
import data.structure.Etable;
import gui.gestionnaire.keys.Animals;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.DomesticSpeciesVisitor;
import process.action.visitor.being.HaveNotProducedYetException;

public class Mouton extends AnimalProducteur{

	private static final long serialVersionUID = 1L;
	
	private final static int DUREE_VIE = 50 ;
	private final static int PRIX_ACHAT = 100 ;
	private final static int FREQUENCE_PRODUCTION = 50 ;
	private final static float POIDS = 50 ;
	private final static int QUANTITE = 10 ;
	private final static int SPEED_GROWTH = 2; 
	
	public Mouton(int ligne_init, int colonne_init, int naissance,String nom, String sexe, Etable habitat,String reference ,Map map) {
			
		super(ligne_init, colonne_init, Milieu.PLAINE, DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, Alimentation.HERBIVORE, sexe, habitat,
				FREQUENCE_PRODUCTION, QUANTITE, new Laine() , reference , map ,SPEED_GROWTH);
		
			String imagePath = "src"+File.separator+"ressources"+File.separator+"Mouton"
					+File.separator+EvolutionAnimal.JEUNE+File.separator+"stand.png";
			setImage(imagePath);
	}
	

	@Override
	public Produit collectProduction() {
		return new Laine();
	}

	@Override
	public <T> T launchAction(DomesticSpeciesVisitor<T> visitor) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
		return visitor.action(this); 
	}


	@Override
	public TimeItTakes getTimeItTakes() {
		return TimeItTakes.MOUTON;
	}
	
	public Animals getKey() {
		return Animals.MOUTON;
	}

}
