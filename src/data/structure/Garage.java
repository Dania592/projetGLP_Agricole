package data.structure;

import java.util.ArrayList;

import data.map.Map;
import gui.gestionnaire.keys.Structures;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.place.PlaceVisitor;

public class Garage extends Structure{
	private static final long serialVersionUID = 1L;

	public Garage(int ligne_init, int colonne_init, String reference, Map map) {
		super(ligne_init, colonne_init, reference, map);
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable, BeingCannotPerformSuchActionException, NotImplementYetException {
				return visitor.action(this);
	}

	@Override
	public Structures getKey() {
		return Structures.GARAGE;
	}

	@Override
	public ArrayList<ActionnableKey> getASetOfAllActionnableKey() {
		ArrayList<ActionnableKey> keys = super.getASetOfAllActionnableKey();
		keys.add(getSpecificActionnableKey());
		return keys;
	}

	@Override
	public ActionnableKey getSpecificActionnableKey() {
		return ActionnableKey.GARAGE;
	}

}
