package data.structure;

import java.io.File;
import java.util.ArrayList;

import data.configuration.GameConfiguration;
import data.gestion.GestionnaireMateriel;
import data.map.Map;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.stucture_base.Farm;
import gui.gestionnaire.keys.Structures;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;

public class Garage extends Structure{
	private static final long serialVersionUID = 1L;

	public Garage( String reference) {
		super(reference);
		setImage(GameConfiguration.IMAGE_PATH+Farm.saisonActuelle+File.separator+"Structure"+File.separator+"Garage.png");
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

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor, Activity activity)
			throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
			BeingCannotPerformSuchActionException, NotImplementYetException,
			NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
			UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
		return visitor.action(this, activity); 
	}

}
