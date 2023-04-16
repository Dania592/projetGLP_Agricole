package data.myExceptions;

import data.espece.faune.Animal;

public class DyingOfHungerException extends Exception{
    public DyingOfHungerException(Animal animal){
        super(animal + "is dying of Hunger");
    }
    public DyingOfHungerException(){
        super("Is dying of Hunger");
    }
    
    
    
}

