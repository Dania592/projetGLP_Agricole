package data.production.exception;

import data.espece.faune.Animal;
import gui.gestionnaire.keys.Animals;

public class NonExistantProduceException extends Exception {
    public NonExistantProduceException(Animals origin){
        super("Meat form "+ origin +" does not exist!");
    }

    public NonExistantProduceException(){
        super("Look for produce does not exist!");
    }    
}
