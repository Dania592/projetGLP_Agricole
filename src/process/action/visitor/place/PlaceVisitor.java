package process.action.visitor.place;


import data.espece.flore.terrains.Terrain;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.Abattoire;
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
import data.structure.hability.Actionnable;
import gui.gestionnaire.keys.Graine;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;

/**
 * Visiteur qui permet d'excécuter des traitements sur les {@link Actionnable}
 */
public interface PlaceVisitor<T>{
    T action(Etable etable) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException;
    T action(Poulallier poulallier) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException;
    T action(Enclos enclos) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException;
    T action(Abattoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException;
    T action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable;
    T action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException;
    T action(Entrepot entrepot)throws UnableToPerformSuchActionWithCurrentActionnable;
    T action(Terrain terrain)throws UnableToPerformSuchActionWithCurrentActionnable, ProblemOccursInProductionException;
    T action(BergerieChevre bergerieChevre) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException;
    T action(BergerieMouton bergerieMouton) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException;
    T action(Puit puit) throws UnableToPerformSuchActionWithCurrentActionnable;
    T action(Garage garage) throws UnableToPerformSuchActionWithCurrentActionnable;
    T action(Grange grange) throws UnableToPerformSuchActionWithCurrentActionnable;

    T action(Etable etable, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException;
    T action(Poulallier poulallier, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException;
    T action(Enclos enclos, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException;
    T action(Abattoire abatoire, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, UnableToGenerateNewTaskException;
    T action(Maison maison, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException;
    T action(SalleDeTraite salleDeTraite, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException;
    T action(Entrepot entrepot, Activity activity)throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException;
    T action(Terrain terrain, Activity activity)throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException, ProblemOccursInProductionException;
    T action(BergerieChevre bergerieChevre, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException;
    T action(BergerieMouton bergerieMouton, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException;
    T action(Puit puit, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException;
    T action(Garage garage, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException;
    T action(Grange grange, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException;

    T action(Terrain terrain, Activity activity, Graine graine)throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException, ProblemOccursInProductionException;



}
