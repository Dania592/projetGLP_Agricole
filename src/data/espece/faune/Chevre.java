package data.espece.faune;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import data.configuration.GameConfiguration;
import data.espece.Milieu;
import data.espece.evolution.EvolutionAnimal;
import data.map.Map;
import data.production.Lait;
import data.production.Produit;
import data.structure.Etable;
import process.action.visitor.being.HaveNotProducedYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.DomesticSpeciesVisitor;


public class Chevre extends AnimalProducteur{

	private final static int DUREE_VIE = 500 ;
	private final static int PRIX_ACHAT = 500 ;
	private final static int FREQUENCE_PRODUCTION = 50 ;
	private final static float POIDS = 40 ;
	private final static int QUANTITE = 20 ;
	private final static int SPEED_GROWTH = 1 ; 
	
	public Chevre(int ligne_init, int colonne_init, int naissance, String nom,  String sexe, Etable habitat , String reference ,Map map) {
		super(ligne_init, colonne_init, Milieu.MONTAGNE, DUREE_VIE, PRIX_ACHAT, naissance, POIDS, nom, Alimentation.HERBIVORE, sexe, habitat,
				FREQUENCE_PRODUCTION, QUANTITE, new Lait(), reference , map , SPEED_GROWTH);
		
					String imagePath = "src"+File.separator+"ressources"+File.separator+"Chevre"
					+File.separator+EvolutionAnimal.JEUNE+File.separator+"STAND.png";
					setImage(imagePath);
//			try {
//				BufferedImage image = ImageIO.read(new File(imagePath));
//				setImage(image);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}


		
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
		return TimeItTakes.CHEVRE;
	}


	
}
