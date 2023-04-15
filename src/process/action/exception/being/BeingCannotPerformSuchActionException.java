package process.action.exception.being;

import data.espece.DomesticSpecie;

public class BeingCannotPerformSuchActionException extends Exception {
    public BeingCannotPerformSuchActionException(){
        super("Current being can't Perform such action");
    }

    public BeingCannotPerformSuchActionException(DomesticSpecie productifBeing){
        super(productifBeing + "can't Perform such action");
    }
}
