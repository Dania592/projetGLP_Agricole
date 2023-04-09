package data.structure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import data.acteur.Personne;
import data.map.Map;
import process.action.place.PlaceVisitor;
import process.action.place.UnableToPerformSuchActionWithCurrentActionnable;

public class Maison extends Refuge<Personne>{
	
	private final static float PRIX_ACHAT = 5000 ;
	public Maison(int ligne_init, int colonne_init , String reference , Map map ) {
		super(ligne_init, colonne_init, PRIX_ACHAT , reference , map );
		try {
			setImage(ImageIO.read(new File("src"+File.separator+"ressources"+File.separator+"minimaison.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<ActionnableKey> getActionnableKey(){
		ArrayList<ActionnableKey> actionnableKeys = new ArrayList<>();
		actionnableKeys.add(ActionnableKey.STRUCTURE);
		actionnableKeys.add(ActionnableKey.MAISON);
		return actionnableKeys;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable {
		return visitor.action(this);
	}

	
}
