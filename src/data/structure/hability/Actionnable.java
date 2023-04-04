package data.structure.hability;

import java.util.ArrayList;

import process.action.place.PlaceVisitor;
import process.action.place.UnableToPerformSuchActionWithCurrentActionnable;


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
        STRUCTURE

    }

    ArrayList<ActionnableKey> getActionnableKey();
    <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable;

}   
