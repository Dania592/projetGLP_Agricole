package process.action.visitor.being.transfert;

import java.util.ArrayList;
import java.util.Iterator;

import data.espece.faune.Chevre;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.flore.terrains.Terrain;
import data.gestion.GestionnaireStructures;
import data.structure.SalleDeTraite;
import data.structure.Structure;
import gui.gestionnaire.keys.Structures;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.DomesticSpeciesVisitor;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;

public class DomesticSpeciesSendToProductifPlace implements DomesticSpeciesVisitor<Void>{


    private Structure getProductifPlace(Structures structures) throws UnableToMakeTheTransfertException{
        ArrayList<Structure> potencielStructure =  GestionnaireStructures.getInstance().getStructures().get(structures);
        boolean isAvalable = false;
        Iterator<Structure> structureIter = potencielStructure.iterator();
        int index=-1;
        while(structureIter.hasNext() && isAvalable){
            isAvalable = structureIter.next().isStatique(); //TODO prendreen compte la  capacité ???
            index++;
        }if(isAvalable){
            return potencielStructure.get(index);
        }
        throw new UnableToMakeTheTransfertException(" No free "+ structures); 
    }

    @Override
    public Void action(Mouton mouton) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, NotImplementYetException {
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
        SalleDeTraite avalableSalleDeTraite = (SalleDeTraite)getProductifPlace(Structures.SALLE_DE_TRAITE);
        avalableSalleDeTraite.addSpecialSenderElement(vache);
        return null;
    }

    @Override
    public Void action(Chevre chevre) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException {
            SalleDeTraite avalableSalleDeTraite = (SalleDeTraite)getProductifPlace(Structures.SALLE_DE_TRAITE);
            avalableSalleDeTraite.addSpecialSenderElement(chevre);
            return null;
    }


    @Override
    public Void action(Terrain terrain) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException, NotImplementYetException {
        throw new BeingCannotPerformSuchActionException();
    }
    
}
