package process.action.visitor.place.transfert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Condition;

import data.espece.characteristic.MilkProduceur;
import data.espece.characteristic.Transportable;
import data.espece.faune.AnimalProducteur;
import data.espece.faune.Chevre;
import data.espece.faune.Mouton;
import data.espece.flore.terrains.Terrain;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.Abatoire;
import data.structure.BergerieChevre;
import data.structure.BergerieMouton;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Garage;
import data.structure.Grange;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.Puit;
import data.structure.SalleDeTraite;
import data.structure.hability.Distributor;
import data.structure.hability.Actionnable.ActionnableKey;
import gui.gestionnaire.keys.Graine;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.coordinator.ConditionTester;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
import process.action.visitor.being.transfert.DomesticSpeciesHomeSender;
import process.action.visitor.place.PlaceVisitor;
import process.game.ElementManager;

public class HomeSenderVisitor implements PlaceVisitor<Void>{
    DomesticSpeciesHomeSender homeSender = new DomesticSpeciesHomeSender();
    ConditionTester conditionTester = new ConditionTester();

    private <T extends Distributor<E>, E extends Transportable> Void sendHome(T distributor, Iterator<E> iterator){
        ArrayList<E> tranportableToRemove = new ArrayList<>(); 
        E currentTransportable;
        while(iterator.hasNext()){
            currentTransportable = iterator.next();
            try {
                if(conditionTester.isThereAvalableHomes(currentTransportable)){
                    currentTransportable.launchAction(homeSender);
                    tranportableToRemove.add(currentTransportable);
                }
            } catch (HaveNotProducedYetException | BeingCannotPerformSuchActionException
                    | NeedToBeSendToSpecialProductionPlaceException | ProblemOccursInProductionException
                    | UnableToMakeTheTransfertException e) {
            }
        }
        distributor.removeAll(tranportableToRemove);
        if(distributor.getSpecificActionnableKey()== ActionnableKey.ENCLOS){
            suppressFromGraphicEnclosure((Enclos)distributor, tranportableToRemove);
        }
        return null;
    }



    private <E extends Transportable> Void suppressFromGraphicEnclosure(Enclos enclos, ArrayList<E> transportable){
        Iterator<E> transportableIter = transportable.iterator();
        E currentTransportable;
        while(transportableIter.hasNext()){
            currentTransportable = transportableIter.next();
            currentTransportable.freePosition();
            currentTransportable.setHidden(true);
        }
        return null; 
    }

    @Override
    public Void action(Terrain terrain, Activity activity, Graine graine)
            throws UnableToPerformSuchActionWithCurrentActionnable,
            UnableToGenerateNewTaskException {
        return action(terrain, activity);
    }

    @Override
    public Void action(Etable etable) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(etable);
    }

    @Override
    public Void action(Poulallier poulallier) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(poulallier);
    }

    @Override
    public Void action(Enclos enclos){
        Iterator<AnimalProducteur> animalProducteurIter = enclos.getAnimalStorage().getAnimals().iterator();

        return sendHome(enclos , animalProducteurIter);
    }

    @Override
    public Void action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(abatoire);
    }

    @Override
    public Void action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(maison);
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite) throws UnableToMakeTheTransfertException{
        Iterator<MilkProduceur> milkProduceurIt = salleDeTraite.getMilkProduceur().iterator();
        return sendHome(salleDeTraite, milkProduceurIt);
    }  

    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(entrepot); 
    }

    @Override
    public Void action(Terrain terrain)
            throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(terrain); 
    }

    @Override
    public Void action(BergerieChevre bergerieChevre)
            throws UnableToPerformSuchActionWithCurrentActionnable {
        Iterator<Chevre> chevreIter = bergerieChevre.getInHabitant().iterator();
        return sendHome(bergerieChevre, chevreIter);
    }

    @Override
    public Void action(BergerieMouton bergerieMouton)
            throws UnableToPerformSuchActionWithCurrentActionnable {
        Iterator<Mouton> moutonIter = bergerieMouton.getInHabitant().iterator();
        return sendHome(bergerieMouton, moutonIter);
    }

    @Override
    public Void action(Puit puit) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(puit);
    }

    @Override
    public Void action(Garage garage) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(garage);
    }

    @Override
    public Void action(Grange grange) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(grange);
    }

    
    @Override
    public Void action(Etable etable, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException {
        return action(etable);
    }

    @Override
    public Void action(Poulallier poulallier, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException {
        return action(poulallier);
    }

    @Override
    public Void action(Enclos enclos, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException {
        return action(enclos);
    }

    @Override
    public Void action(Abatoire abatoire, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable {
            return action(abatoire);
    }

    @Override
    public Void action(Maison maison, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(maison);
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable,
            UnableToMakeTheTransfertException {
        return action(salleDeTraite);
    }

    @Override
    public Void action(Entrepot entrepot, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(entrepot);
    }

    @Override
    public Void action(Terrain terrain, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(terrain);
    }

    @Override
    public Void action(BergerieChevre bergerieChevre, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable,
            UnableToMakeTheTransfertException {
            return action(bergerieChevre);
    }

    @Override
    public Void action(BergerieMouton bergerieMouton, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable,
            UnableToMakeTheTransfertException {
        return action(bergerieMouton);
    }

    @Override
    public Void action(Puit puit, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(puit);
    }

    @Override
    public Void action(Garage garage, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(garage);
    }

    @Override
    public Void action(Grange grange, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(grange);
    }
}
