package data.structure.hability;

import data.myExceptions.UnableToGenerateNewTaskException;
import data.myExceptions.UnknownActivityException;
import data.planning.Activity;
import gui.gestionnaire.keys.Graine;
import process.action.exception.NotImplementYetException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.place.PlaceVisitor;

public interface SpecialActionPerformer extends Actionnable{
    <T> T launchAction(PlaceVisitor<T> visitor, Activity activity, Graine graine) throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, UnableToGenerateNewTaskException;
}
