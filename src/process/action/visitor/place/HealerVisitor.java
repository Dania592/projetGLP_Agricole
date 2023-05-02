package process.action.visitor.place;

import java.util.Iterator;

import data.espece.characteristic.Produceur.Type;
import data.espece.faune.AnimalProducteur;
import data.espece.flore.terrains.Terrain;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.notion.Mortel.EtatSante;
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
import gui.gestionnaire.keys.Graine;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;

public class HealerVisitor implements PlaceVisitor<Void> {

    public <T extends AnimalProducteur> Void healAnimals(Iterator<T> iteratorAnimal){
        T currentProduceur;
        while(iteratorAnimal.hasNext()){
            currentProduceur = iteratorAnimal.next();  
            currentProduceur.setEtatSante(EtatSante.BONNE_SANTE);
            currentProduceur.setProduceurType(Type.AVERAGE_PRODUCEUR);
        }
        return null;
    }

    @Override
    public Void action(Etable etable)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException{
        return healAnimals(etable.getInHabitant().iterator());
    }

    @Override
    public Void action(Poulallier poulallier)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException {
        return healAnimals(poulallier.getInHabitant().iterator());
    }

    @Override
    public Void action(Enclos enclos)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException {
        return healAnimals(enclos.getAnimals().iterator());
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
    public Void action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException {
        throw new UnableToPerformSuchActionWithCurrentActionnable(salleDeTraite);
    }

    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(entrepot);
    }

    @Override
    public Void action(Terrain terrain)
            throws UnableToPerformSuchActionWithCurrentActionnable {
        terrain.setEtatSante(EtatSante.BONNE_SANTE);
        terrain.setProduceurType(Type.AVERAGE_PRODUCEUR);
        return null;
    }

    @Override
    public Void action(BergerieChevre bergerieChevre) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException {
        return healAnimals(bergerieChevre.getInHabitant().iterator());
    }

    @Override
    public Void action(BergerieMouton bergerieMouton) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException {
        return healAnimals(bergerieMouton.getInHabitant().iterator());
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
    public Void action(Etable etable, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
        return action(etable);
    }

    @Override
    public Void action(Poulallier poulallier, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
        return action(poulallier);
    }

    @Override
    public Void action(Enclos enclos, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
        return action(enclos);
    }

    @Override
    public Void action(Abatoire abatoire, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, UnableToGenerateNewTaskException {
        return action(abatoire);
    }

    @Override
    public Void action(Maison maison, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException {
        return action(maison);
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
        return action(salleDeTraite);
    }

    @Override
    public Void action(Entrepot entrepot, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException {
        return action(entrepot);
    }

    @Override
    public Void action(Terrain terrain, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException {
        return action(terrain);
    }

    @Override
    public Void action(BergerieChevre bergerieChevre, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable,
            UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
        return action(bergerieChevre);
    }

    @Override
    public Void action(BergerieMouton bergerieMouton, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable,
            UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
        return action(bergerieMouton);
    }

    @Override
    public Void action(Puit puit, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException {
        return action(puit);
    }

    @Override
    public Void action(Garage garage, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException {
        return action(garage);
    }

    @Override
    public Void action(Grange grange, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException {
        return action(grange);
    }

    @Override
    public Void action(Terrain terrain, Activity activity, Graine graine)
            throws UnableToPerformSuchActionWithCurrentActionnable,
            UnableToGenerateNewTaskException {
        return action (terrain);
    }
    
}
