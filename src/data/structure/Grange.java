package data.structure;

import java.io.File;

import data.configuration.GameConfiguration;
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

public class Grange extends Structure{
	private static final long serialVersionUID = 1L;

	public Grange( String reference) {
		super( reference);
		setImage(GameConfiguration.IMAGE_PATH + Farm.saisonActuelle+File.separator+"Structure"+File.separator+"Grange.png");

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

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor, Activity activity)
			throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
			BeingCannotPerformSuchActionException, NotImplementYetException,
			NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
			UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
			return visitor.action(this, activity);
		}

}
