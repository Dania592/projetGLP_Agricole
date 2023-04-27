package process.action.visitor.being.exception;

import data.espece.Produceur;

public class ProblemOccursInProductionException extends Exception {
    public ProblemOccursInProductionException(Produceur produceur){
        super(produceur + " just meet a problem while producing");
    }
}
