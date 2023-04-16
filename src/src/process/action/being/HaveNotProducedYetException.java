package process.action.being;

import data.espece.faune.Animal;

public class HaveNotProducedYetException extends Exception {   
    public  HaveNotProducedYetException(Animal animal){
        super(animal + "have not produced yet");
    }

}
