package process.action.visitor.place.transfert;

import java.util.ArrayList;
import java.util.Iterator;

import data.espece.Transportable;
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
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;
import process.action.visitor.being.transfert.DomesticSpeciesSendToProductifPlace;

public class ProductifPlaceSender implements PlaceVisitor<Void>{
    DomesticSpeciesSendToProductifPlace productifPlaceSender = new DomesticSpeciesSendToProductifPlace();

    private <T extends Distributor<E>, E extends Transportable> Void sendToProductifPlace(T distributor, Iterator<E> iterator){
        ArrayList<E> tranportableToRemove = new ArrayList<>(); 
        E currentTransportable;
        while(iterator.hasNext()){
            currentTransportable = iterator.next();
            try {
                currentTransportable.launchAction(productifPlaceSender);
                tranportableToRemove.add(currentTransportable);
            } catch (HaveNotProducedYetException | BeingCannotPerformSuchActionException
                    | NeedToBeSendToSpecialProductionPlaceException | ProblemOccursInProductionException
                    | UnableToMakeTheTransfertException e) {
                e.printStackTrace();
            } catch (NotImplementYetException e) {
                e.printStackTrace();
            }
        }
        distributor.removeAll(tranportableToRemove);
        return null;
    }


    @Override
    public Void action(Etable etable) throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException {
        return sendToProductifPlace(etable, etable.getInHabitant().iterator());
    }

    @Override
    public Void action(Poulallier poulallier) throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException {
        throw new UnableToPerformSuchActionWithCurrentActionnable(poulallier);
    }

    @Override
    public Void action(Enclos enclos) throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException {
        return sendToProductifPlace(enclos, enclos.getAnimals().iterator());
        
    }

    @Override
    public Void action(Abatoire abatoire)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException {
        throw new UnableToPerformSuchActionWithCurrentActionnable(abatoire);
    }

    @Override
    public Void action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(maison);
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable,
            NotImplementYetException, HaveNotProducedYetException {
        throw new UnableToPerformSuchActionWithCurrentActionnable(salleDeTraite);
    }

    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(entrepot);
    }

    @Override
    public Void action(Terrain terrain)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        throw new NotImplementYetException();
    }


    @Override
    public Void action(BergerieChevre bergerieChevre)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        return sendToProductifPlace(bergerieChevre, bergerieChevre.getInHabitant().iterator());
    }


    @Override
    public Void action(BergerieMouton bergerieMouton)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        return sendToProductifPlace(bergerieMouton, bergerieMouton.getInHabitant().iterator());
        
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
