package data.espece.faune;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import data.espece.Milieu;
import data.espece.evolution.EvolutionAnimal;
import data.map.Map;
import data.production.Lait;
import data.production.Oeuf;
import data.production.Produit;
import data.structure.Enclos;
import data.structure.Poulallier;
import data.structure.Refuge;
import process.action.visitor.being.HaveNotProducedYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.DomesticSpeciesVisitor;


public class Poule extends AnimalProducteur{

	private final static int DUREE_VIE = 500 ;
	private final static int PRIX_ACHAT = 1000 ;
	private final static int FREQUENCE_PRODUCTION = 50 ;
	private final static float POIDS = 50 ;
	private final static int QUANTITE = 10 ;
	private final static int SPEED_GROWTH = 5 ; 
	
	public Poule(int ligne_init, int colonne_init,Milieu milieu , int naissance, String nom,  String sexe, Poulallier habitat,String reference , Map map ) {
			 
		super(ligne_init, colonne_init, milieu, DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, Alimentation.GRAINIVORE, sexe, habitat,
				FREQUENCE_PRODUCTION, QUANTITE, new Oeuf(), reference , map , SPEED_GROWTH);
		
			String imagePath = "src"+File.separator+"ressources"+File.separator+"Poule"
					+File.separator+EvolutionAnimal.JEUNE+File.separator+"STAND.png";
			setImage(imagePath);
		
	}

	@Override
	public Produit collectProduction() {
		return new Lait();
	}

	@Override
	public <T> T launchAction(DomesticSpeciesVisitor<T> visitor) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
		return visitor.action(this);
	}

	@Override
	public TimeItTakes getTimeItTakes() {
		return TimeItTakes.POULE;
	}

	
	 

	
}
