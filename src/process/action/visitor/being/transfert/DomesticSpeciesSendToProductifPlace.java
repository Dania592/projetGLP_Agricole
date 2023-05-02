package process.action.visitor.being.transfert;

import java.util.ArrayList;
import java.util.Iterator;

import data.espece.characteristic.Produceur.ProductifState;
import data.espece.faune.Chevre;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.espece.flore.terrains.Terrain;
import data.gestion.GestionnaireStructures;
import data.structure.SalleDeTraite;
import data.structure.Structure;
import gui.gestionnaire.keys.Structures;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.DomesticSpeciesVisitor;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;

public class DomesticSpeciesSendToProductifPlace implements DomesticSpeciesVisitor<Void>{

    private Structure getProductifPlace(Structures structures) throws UnableToMakeTheTransfertException{
        ArrayList<Structure> potencielStructure =  GestionnaireStructures.getInstance().getStructures().get(structures);
        boolean isAvalable = false;
        Iterator<Structure> structureIter = potencielStructure.iterator();
        Structure currentStructure = structureIter.next();
        while(structureIter.hasNext() && !isAvalable){
            currentStructure = structureIter.next();
            isAvalable = currentStructure.isStatique() && !(currentStructure.isNeedToBeFixed());
        }
        return currentStructure;
    }

    @Override
    public Void action(Mouton mouton) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException {
        throw new BeingCannotPerformSuchActionException(mouton);
    }

    @Override
    public Void action(Poule poule) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException {
        throw new BeingCannotPerformSuchActionException(poule);
    }

    @Override
    public Void action(Vache vache) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException {
        if(vache.getProductifState() == ProductifState.IN_WAIT_TO_BE_TRANSPORTED){
            SalleDeTraite avalableSalleDeTraite = (SalleDeTraite)getProductifPlace(Structures.SALLE_DE_TRAITE);
            avalableSalleDeTraite.addSpecialSenderElement(vache);
            vache.setProductifState(ProductifState.HAVE_PRODUCE);
        }
        return null;
    }

    @Override
    public Void action(Chevre chevre) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException {
            if(chevre.getProductifState() == ProductifState.IN_WAIT_TO_BE_TRANSPORTED){
                SalleDeTraite avalableSalleDeTraite = (SalleDeTraite)getProductifPlace(Structures.SALLE_DE_TRAITE);
                avalableSalleDeTraite.addSpecialSenderElement(chevre);
                chevre.setProductifState(ProductifState.HAVE_PRODUCE);
            }
            return null;
    }


    @Override
    public Void action(Terrain terrain) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException {
        throw new BeingCannotPerformSuchActionException();
    }
    
}
