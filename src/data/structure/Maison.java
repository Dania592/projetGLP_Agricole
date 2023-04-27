package data.structure;

import java.io.File;
import java.util.ArrayList;

import data.acteur.Personne;
import data.map.Map;
import gui.gestionnaire.keys.Structures;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.place.PlaceVisitor;

public class Maison extends Refuge<Personne>{
	
	private final static float PRIX_ACHAT = 5000 ;

	public Maison(int ligne_init, int colonne_init , String reference , Map map ) {
		super(ligne_init, colonne_init, reference , map );
			setImage("src"+File.separator+"ressources"+File.separator+"minimaison.png");
	}
	
	
	@Override
	public ArrayList<ActionnableKey> getASetOfAllActionnableKey(){
		ArrayList<ActionnableKey> actionnableKeys = new ArrayList<>();
		actionnableKeys.add(ActionnableKey.STRUCTURE);
		actionnableKeys.add(ActionnableKey.MAISON);
		return actionnableKeys;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable {
		return visitor.action(this);
	}

	@Override
	protected int getMaxCapacity() {
		return MaxCapacity.MAX_CAPACITE_MAISON.getCapacity();
	}

	public Structures getKey() {
		return Structures.MAISON;
	}

	@Override
	public ActionnableKey getSpecificActionnableKey() {
		return ActionnableKey.MAISON;
	}



	
}
