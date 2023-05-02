package data.structure.hability;

import java.util.ArrayList;

import data.myExceptions.UnableToGenerateNewTaskException;
import data.notion.basic.Position;
import data.planning.Activity;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;

/**
 * Une {@link Structure} actionnable peut lancé une {@link Task}
 */
public interface Actionnable{
    
    public enum ActionnableKey{
        ABATOIRE,
        ETABLE,
        POULAILLER,
        SALLE_TRAITE,
        MAISON,
        REFUGE,
        ENCLOS,
        ENTREPOT,
        STRUCTURE,
        TERRAIN,
        BERGERIE_CHEVRE,
        BERGERIE_MOUTON,
        PUIT,
        GARAGE,
        GRANGE,
        MILK_PRODUCEUR_REFUGE,
        PLACE_OF_ANIMAL_PRODUCTION,
        ;
    }

    ArrayList<ActionnableKey> getASetOfAllActionnableKey();
    ActionnableKey getSpecificActionnableKey();
    boolean isCurrentlyUsedForAnotherTask();
    void setStructureStatus(boolean isCurrentlyUsedForAnotherTask);
    <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException;
    <T> T launchAction(PlaceVisitor<T> visitor, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException;
    public Position getPosition();
    boolean isNeedToBeFixed();
    boolean isStatique();

}   
