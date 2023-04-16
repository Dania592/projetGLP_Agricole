package data.structure;

import java.io.File;
import java.util.ArrayList;

import data.acteur.Personne;
import data.map.Map;
import process.action.place.PlaceVisitor;
import process.action.place.UnableToPerformSuchActionWithCurrentActionnable;

public class Maison extends Refuge<Personne>{
	
	private final static float PRIX_ACHAT = 5000 ;
	public Maison(int ligne_init, int colonne_init , String reference , Map map ) {
		super(ligne_init, colonne_init, PRIX_ACHAT , reference , map );
		String imagePath = "src"+File.separator+"ressources"+File.separator+"minimaison.png";
		setImage(imagePath);

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
