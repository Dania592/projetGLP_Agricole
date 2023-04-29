package process.action.visitor.place.transfert;

import java.util.ArrayList;
import java.util.Iterator;

import data.espece.Transportable;
import data.espece.faune.AnimalProducteur;
import data.espece.faune.Chevre;
import data.espece.faune.MilkProduceur;
import data.espece.faune.Mouton;
import data.espece.faune.NoNeedToSendToAProductifPlace;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.flore.terrains.Terrain;
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
import gui.gestionnaire.keys.Graine;
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
            } catch (NoNeedToSendToAProductifPlace e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        distributor.removeAll(tranportableToRemove);
        return null;
    }

    @Override
    public Void action(Terrain terrain, Activity activity, Graine graine)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
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
        Iterator<AnimalProducteur> animIterator = enclos.getAnimals().iterator();
        return sendHome(enclos, animIterator);
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

    
    @Override
    public Void action(Etable etable, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException {
        return action(etable);
    }

    @Override
    public Void action(Poulallier poulallier, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException, NotImplementYetException {
        return action(poulallier);
    }

    @Override
    public Void action(Enclos enclos, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException, NotImplementYetException {
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
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
            UnableToMakeTheTransfertException {
        return action(salleDeTraite);
    }

    @Override
    public Void action(Entrepot entrepot, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(entrepot);
    }

    @Override
    public Void action(Terrain terrain, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        return action(terrain);
    }

    @Override
    public Void action(BergerieChevre bergerieChevre, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
            UnableToMakeTheTransfertException {
            return action(bergerieChevre);
    }

    @Override
    public Void action(BergerieMouton bergerieMouton, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
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
