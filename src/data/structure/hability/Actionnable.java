package data.structure.hability;

import java.util.ArrayList;

import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.HaveNotProducedYetException;
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
    }

    ArrayList<ActionnableKey> getActionnableKey();
    <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException;

}   
