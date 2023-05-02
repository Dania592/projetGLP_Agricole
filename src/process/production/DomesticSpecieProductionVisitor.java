package process.production;

import java.io.Serializable;

import data.espece.characteristic.Produceur;
import data.espece.characteristic.Produceur.ProductifState;
import data.espece.faune.AnimalProducteur;
import data.espece.faune.Chevre;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.espece.flore.terrains.Terrain;
import data.notion.Mortel.EtatSante;
import data.notion.evolution.EvolutionTerrain;
import data.production.Produits;
import data.time.CyclicCounter;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.DomesticSpeciesVisitor;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;

public class DomesticSpecieProductionVisitor implements DomesticSpeciesVisitor<Produits>, Serializable {

    private void updateProduceurStatus(AnimalProducteur animal){
        CyclicCounter productifCycle = animal.getProductionCycle();
        if(isAnimalNewlySick(animal)){
            updateProduceurStatusForSickAnimal(animal);
        }else if(!(animal.getProductifState()==ProductifState.IN_WAIT) && !(animal.getProductifState()==ProductifState.IN_WAIT_TO_BE_TRANSPORTED)){
            productifCycle.increment();
            if(productifCycle.getValue() == 0 && animal.getProductifState()!= ProductifState.UNABLE_TO_PRODUCE){
                if(animal.needSpecialPlaceToGetProduction()){
                    animal.setProductifState(ProductifState.IN_WAIT_TO_BE_TRANSPORTED);
                }else if(animal.needSpecialActionToGetProduction()){
                    animal.setProductifState(ProductifState.IN_WAIT);
                }else{
                    animal.setProductifState(ProductifState.HAVE_PRODUCE);
                }
            }
        }
    }

    private boolean isAnimalNewlySick(AnimalProducteur animal){
        return (animal.getEtatSante()== EtatSante.GRAVEMENT_MALADE || animal.getEtatSante() == EtatSante.MALADE ||  animal.getEtatSante() == EtatSante.MOURANT) && !(animal.getProductifState()==ProductifState.UNABLE_TO_PRODUCE); 
    }

    private void updateProduceurStatusForSickAnimal(AnimalProducteur animal){
        animal.setProductifState(ProductifState.UNABLE_TO_PRODUCE);
    }

    private Produits getAnimalToProduce(AnimalProducteur animal) throws HaveNotProducedYetException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException {
        updateProduceurStatus(animal);
        switch(animal.getProductifState()){
            case PRODUCING : 
            case UNABLE_TO_PRODUCE:
                throw new HaveNotProducedYetException(animal);
            case HAVE_PRODUCE : 
                animal.setProductifState(ProductifState.PRODUCING);
                return animal.collectProduction();
            case IN_WAIT_TO_BE_TRANSPORTED :
                throw new NeedToBeSendToSpecialProductionPlaceException(animal);
            default:
                throw new ProblemOccursInProductionException(animal);
        }
        
    }

    @Override
    public Produits action(Mouton mouton) throws HaveNotProducedYetException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException {
        return getAnimalToProduce(mouton);

    }

    @Override
    public Produits action(Poule poule) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException {
        return getAnimalToProduce(poule);
    }

    @Override
    public Produits action(Vache vache) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException {
        return getAnimalToProduce(vache);
    }

    @Override
    public Produits action(Chevre chevre) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException {
        return getAnimalToProduce(chevre);
    }

    @Override
    public Produits action(Terrain terrain) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException{
        updateProducingAbilityOfTerrain(terrain);
            if(terrain.getProductifState() ==  ProductifState.HAVE_PRODUCE){
                terrain.setProductifState(ProductifState.IN_WAIT);
                terrain.getHydrationCounter().setMax(terrain.getHydrationCounter().getMax()*2);
                return terrain.collectProduction();
            }
            CyclicCounter productifCycle = terrain.getProductionCycle(); 
            productifCycle.increment();
            if(productifCycle.getValue() == 0 && terrain.getProductifState()== ProductifState.PRODUCING){
                terrain.evoluer();
            }if(terrain.getEvolution()==EvolutionTerrain.PLANTE_5){
                terrain.setProductifState(ProductifState.HAVE_PRODUCE);
            }else{
                throw new HaveNotProducedYetException(terrain);
            }
        throw new HaveNotProducedYetException(terrain);
    }

    private void updateProducingAbilityOfTerrain(Terrain terrain){
        if(!(terrain.getEtatSante() == EtatSante.BONNE_SANTE)){
            terrain.setProduceurType(Produceur.Type.BAD_PRODUCEUR);
        }
    } 

}
