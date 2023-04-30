package data.structure;

import java.io.File;
import java.util.ArrayList;

import data.acteur.Personne;
import data.map.Map;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import gui.gestionnaire.keys.Structures;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;

public class Maison extends Refuge<Personne>{

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


	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor, Activity activity)
			throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
			BeingCannotPerformSuchActionException, NotImplementYetException,
			NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
			UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
			return visitor.action(this, activity);
	}

}