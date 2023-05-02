package process.action.visitor.place;

import data.espece.characteristic.FoodConsumer.HungerLevel;
import data.espece.flore.terrains.Terrain;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
// import data.espece.faune.Poule;
// import data.espece.faune.Vache;
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
import process.evolution.FullLevel;

public class FeedVisitor implements PlaceVisitor<Void>{
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
    	enclos.setNiveauNourriture(FullLevel.FULL);
    	enclos.setAnimalsHungerLevel(HungerLevel.FULL);
        return null;
    }

    @Override
    public Void action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(entrepot);
    }

    @Override
    public Void action(Terrain terrain) throws UnableToPerformSuchActionWithCurrentActionnable{
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(BergerieChevre bergerieChevre) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException {
        throw new UnableToPerformSuchActionWithCurrentActionnable();

    }

    @Override
    public Void action(BergerieMouton bergerieMouton) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException {
        throw new UnableToPerformSuchActionWithCurrentActionnable();
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
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException {
        return action(etable);
    }

    @Override
    public Void action(Poulallier poulallier, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException{
        return action(poulallier);
    }

    @Override
    public Void action(Enclos enclos, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException{
        return action(enclos);
    }

    @Override
    public Void action(Abatoire abatoire, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException {
            return action(abatoire);
    }

    @Override
    public Void action(Maison maison, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(maison);
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException {
        return action(salleDeTraite);
    }

    @Override
    public Void action(Entrepot entrepot, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(entrepot);
    }

    @Override
    public Void action(Terrain terrain, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable{
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

    @Override
    public Void action(Terrain terrain, Activity activity, Graine graine)
            throws UnableToPerformSuchActionWithCurrentActionnable,
            UnableToGenerateNewTaskException {
        return action(terrain);
    }

}
