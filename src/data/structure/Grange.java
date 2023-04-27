package data.structure;

import data.map.Map;
import gui.gestionnaire.keys.Structures;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.HaveNotProducedYetException;
import process.action.visitor.place.PlaceVisitor;

public class Grange extends Structure{

	private static final long serialVersionUID = 1L;

	public Grange(int ligne_init, int colonne_init, float prixAchat, String reference, Map map) {
		super(ligne_init, colonne_init, prixAchat, reference, map);
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable,
			HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException {
		return null;
	}

	@Override
	public Structures getKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
