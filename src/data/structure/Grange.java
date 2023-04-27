package data.structure;

import data.map.Map;
import gui.gestionnaire.keys.Structures;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.place.PlaceVisitor;

public class Grange extends Structure{
	private static final long serialVersionUID = 1L;

	public Grange(int ligne_init, int colonne_init, String reference, Map map) {
		super(ligne_init, colonne_init, reference, map);
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable, BeingCannotPerformSuchActionException, NotImplementYetException {
		return visitor.action(this);
	}

	@Override
	public Structures getKey() {
		return Structures.GRANGE;
	}

	@Override
	public ActionnableKey getSpecificActionnableKey() {
		return ActionnableKey.GRANGE;
	}

}
