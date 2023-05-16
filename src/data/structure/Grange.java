package data.structure;

import java.io.File;

import data.configuration.GameConfiguration;
import data.espece.flore.Saison;
import data.map.Map;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import gui.gestionnaire.keys.Structures;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;

public class Grange extends Structure{
	private static final long serialVersionUID = 1L;

	public Grange( String reference) {
		super( reference);
		setImage(GameConfiguration.IMAGE_PATH + Saison.PRINTEMPS+File.separator+"Structure"+File.separator+"Grange.png");

	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable, BeingCannotPerformSuchActionException {
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
			BeingCannotPerformSuchActionException,
			NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
			UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
			return visitor.action(this, activity);
		}

}
