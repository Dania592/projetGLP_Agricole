package process.action.visitor.being;

import data.espece.Produceur;

public class HaveNotProducedYetException extends Exception {   
    public  HaveNotProducedYetException(Produceur produceur){
        super(produceur + "have not produced yet");
    }

}