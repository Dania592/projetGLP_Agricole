package data.espece.characteristic;

import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.DomesticSpeciesVisitor;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;

/**
 * Cette interface permet de gérer les traitements sur les espèces domestiquées de la ferme.
 * @see data.espece.faune.Vache
 * @see data.espece.faune.Poule
 * @see data.espece.faune.Mouton
 * @see data.espece.faune.Chevre
 * @see data.espece.flore.terrains.Terrain
 */
public interface DomesticSpecie{
    <T> T launchAction(DomesticSpeciesVisitor<T> visitor) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException;
}
