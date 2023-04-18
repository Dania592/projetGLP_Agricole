package process.action.visitor.being;

import data.espece.FoodConsumer.HungerLevel;
import data.espece.faune.Animal;
import data.espece.faune.Chevre;
import data.espece.faune.Chien;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.flore.terrains.Terrain;
import process.action.exception.being.BeingCannotPerformSuchActionException;
//TODO Il faudra vérifier que l'on a la bonne nourriture
public class BeingFeederVisitor implements DomesticSpeciesVisitor<Void> {

    public Void feedAnimal(Animal animal){
        animal.setHungerLevel(HungerLevel.FULL);
        return null;
    }



    @Override
    public Void action(Poule poule) throws HaveNotProducedYetException {
        return feedAnimal(poule); 
    }

    @Override
    public Void action(Vache vache) throws HaveNotProducedYetException {
        return feedAnimal(vache);
    }

    @Override
    public Void action(Chevre chevre) throws HaveNotProducedYetException {
        return feedAnimal(chevre);
    }

    @Override
    public Void action(Chien chien) throws HaveNotProducedYetException {
        return feedAnimal(chien);
    }

    @Override
    public Void action(Mouton mouton) throws HaveNotProducedYetException {
        return feedAnimal(mouton);
    }

    // @Override
    // public Void action(Terrain terrain) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
    //     throw new BeingCannotPerformSuchActionException(terrain); 
    // }

    
}
