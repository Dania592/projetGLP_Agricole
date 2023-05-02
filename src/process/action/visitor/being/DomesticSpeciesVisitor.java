package process.action.visitor.being;

import data.espece.characteristic.DomesticSpecie;
import data.espece.faune.Chevre;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.espece.flore.terrains.Terrain;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;


/**
 * Visiteur qui permet de lancer des traitements particuliers sur les {@link DomesticSpecie}
 */
public interface DomesticSpeciesVisitor<T>{
    T action(Terrain terrain) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException; 
    T action(Poule poule) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException; 
    T action(Vache vache) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException;
    T action(Chevre chevre) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException;
    T action(Mouton mouton) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, UnableToMakeTheTransfertException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException;
}
