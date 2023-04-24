package process.action.visitor.place.transfert;

import java.util.ArrayList;
import java.util.Iterator;

import data.espece.Transportable;
import data.espece.faune.AnimalProducteur;
import data.espece.faune.Chevre;
import data.espece.faune.MilkProduceur;
import data.espece.faune.Mouton;
import data.flore.terrains.Terrain;
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
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.transfert.DomesticSpeciesHomeSender;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.place.PlaceVisitor;

public class HomeSenderVisitor implements PlaceVisitor<Void>{
    DomesticSpeciesHomeSender homeSender = new DomesticSpeciesHomeSender();

    private <T extends Distributor<E>, E extends Transportable> Void sendHome(T distributor, Iterator<E> iterator){
        ArrayList<E> tranportableToRemove = new ArrayList<>(); 
        E currentTransportable;
        while(iterator.hasNext()){
            currentTransportable = iterator.next();
            try {
                currentTransportable.launchAction(homeSender);
                tranportableToRemove.add(currentTransportable);
            } catch (HaveNotProducedYetException | BeingCannotPerformSuchActionException
                    | NeedToBeSendToSpecialProductionPlaceException | ProblemOccursInProductionException
                    | UnableToMakeTheTransfertException| NotImplementYetException e) {
                e.printStackTrace();
            }
        }
        distributor.removeAll(tranportableToRemove);
        return null;
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
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        throw new UnableToPerformSuchActionWithCurrentActionnable(terrain); 
    }

    @Override
    public Void action(BergerieChevre bergerieChevre)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        Iterator<Chevre> chevreIter = bergerieChevre.getInHabitant().iterator();
        return sendHome(bergerieChevre, chevreIter);
    }

    @Override
    public Void action(BergerieMouton bergerieMouton)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
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

}
