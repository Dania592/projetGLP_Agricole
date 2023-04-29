package data.structure.hability;

import java.util.ArrayList;

import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.Fixable.FixableState;
import data.stucture_base.Position;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;


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
        ;
    }

    ArrayList<ActionnableKey> getASetOfAllActionnableKey();
    ActionnableKey getSpecificActionnableKey();
    boolean isCurrentlyUsedForAnotherTask();
    void setStructureStatus(boolean isCurrentlyUsedForAnotherTask);
    <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException;
    <T> T launchAction(PlaceVisitor<T> visitor, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException;
    public Position getPosition();
    boolean isNeedToBeFixed();
    boolean isStatique();
    void setFixableState(FixableState fixableState);

}   
