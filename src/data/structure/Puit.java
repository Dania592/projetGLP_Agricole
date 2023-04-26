package data.structure;

import java.io.File;

import data.map.Map;
import gui.gestionnaire.keys.Structures;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.HaveNotProducedYetException;
import process.action.visitor.place.PlaceVisitor;

public class Puit extends StructureAction {

	public Puit(int ligne_init, int colonne_init, float prixAchat, String reference, Map map) {
		super(ligne_init, colonne_init, prixAchat, reference, map);
		
		setImage("src"+File.separator+"ressources"+File.separator+"puit.png");
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable,
			HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Structures getKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
