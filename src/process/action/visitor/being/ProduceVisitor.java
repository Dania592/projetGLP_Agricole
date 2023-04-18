package process.action.visitor.being;

import data.espece.ProductionManager;
import data.espece.Produceur.ProductifState;
import data.espece.evolution.EvolutionAnimal;
import data.espece.faune.AnimalProducteur;
import data.espece.faune.Chevre;
import data.espece.faune.Chien;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.time.BoundedCounter;
import data.time.CyclicCounter;
import process.action.exception.being.BeingCannotPerformSuchActionException;

public class 
ProduceVisitor implements DomesticSpeciesVisitor<Void> {
    
    private Void getAnimalToProduce(AnimalProducteur animal){
        CyclicCounter productifCycle = animal.getProductionCycle();
        productifCycle.increment();
        System.out.println("Dans la méthode productif cycle : "+ productifCycle);
        if(productifCycle.getValue() == 0){
            animal.setProductifState(ProductifState.HAVE_PRODUCE);
            
        }
        return null;
    }


    @Override
    public Void action(Chien chien) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        throw new BeingCannotPerformSuchActionException(chien); 
    }

    @Override
    public Void action(Mouton mouton) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        return getAnimalToProduce(mouton);

    }

    @Override
    public Void action(Poule poule) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        return getAnimalToProduce(poule);
    }

    @Override
    public Void action(Vache vache) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        return getAnimalToProduce(vache);
    }

    @Override
    public Void action(Chevre chevre) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        return getAnimalToProduce(chevre);
    }

}
