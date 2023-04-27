package process.action.visitor.place;


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
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;

public interface PlaceVisitor<T>{
    T action(Etable etable) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException;
    T action(Poulallier poulallier) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, NotImplementYetException;
    T action(Enclos enclos) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, NotImplementYetException;
    T action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException;
    T action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable;
    T action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException;
    T action(Entrepot entrepot)throws UnableToPerformSuchActionWithCurrentActionnable;
    T action(Terrain terrain)throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException;
    T action(BergerieChevre bergerieChevre) throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, UnableToMakeTheTransfertException;
    T action(BergerieMouton bergerieMouton) throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, UnableToMakeTheTransfertException;
    T action(Puit puit) throws UnableToPerformSuchActionWithCurrentActionnable;
    T action(Garage garage) throws UnableToPerformSuchActionWithCurrentActionnable;
    T action(Grange grange) throws UnableToPerformSuchActionWithCurrentActionnable;
}
