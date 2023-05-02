package data.espece.characteristic.exception;

import data.espece.faune.AnimalProducteur;

public class NoNeedToSendToAProductifPlace extends Exception {
    public NoNeedToSendToAProductifPlace(AnimalProducteur animalProducteur){
        super("No need to send "+ animalProducteur+ " to special productif Place");
    } 
}
