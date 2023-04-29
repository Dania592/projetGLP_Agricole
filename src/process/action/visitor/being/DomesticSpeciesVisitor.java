package process.action.visitor.being;

import data.espece.faune.Chevre;
import data.espece.faune.Chien;
import data.espece.faune.Mouton;
import data.espece.faune.NoNeedToSendToAProductifPlace;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.flore.terrains.Terrain;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;

public interface DomesticSpeciesVisitor<T>{
    T action(Terrain terrain) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, NotImplementYetException; 
    T action(Poule poule) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, NoNeedToSendToAProductifPlace; 
    T action(Vache vache) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, NoNeedToSendToAProductifPlace;
    T action(Chevre chevre) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException;
    T action(Chien chien) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException;
    T action(Mouton mouton) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, UnableToMakeTheTransfertException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, NotImplementYetException, NoNeedToSendToAProductifPlace;
}
