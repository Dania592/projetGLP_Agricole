package data.espece.faune;

public class NoNeedToSendToAProductifPlace extends Exception {
    public NoNeedToSendToAProductifPlace(AnimalProducteur animalProducteur){
        super("No need to send "+ animalProducteur+ " to special productif Place");
    } 
}
