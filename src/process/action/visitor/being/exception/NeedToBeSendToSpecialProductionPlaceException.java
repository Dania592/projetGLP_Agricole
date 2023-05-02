package process.action.visitor.being.exception;

import data.espece.characteristic.Produceur;

public class NeedToBeSendToSpecialProductionPlaceException extends Exception {
    public NeedToBeSendToSpecialProductionPlaceException(Produceur produceur){
        super(produceur + " need to be send to a special place to get collected produce");
    }
}
