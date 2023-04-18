package process.action.visitor.place;


import data.flore.terrains.Terrain;
import data.structure.Abatoire;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.SalleDeTraite;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.HaveNotProducedYetException;

public interface PlaceVisitor<T>{
    T action(Etable etable) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException;
    T action(Poulallier poulallier) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException;
    T action(Enclos enclos) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException;
    T action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable;
    T action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable;
    T action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException;
    T action(Entrepot entrepot)throws UnableToPerformSuchActionWithCurrentActionnable;
    T action(Terrain terrain)throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException;
}
