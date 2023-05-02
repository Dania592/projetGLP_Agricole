package process.action.visitor.being.transfert;

import java.util.ArrayList;
import java.util.Iterator;


import data.espece.faune.Chevre;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.flore.terrains.Terrain;
import data.gestion.GestionnaireStructures;
import data.structure.BergerieChevre;
import data.structure.BergerieMouton;
import data.structure.Etable;
import data.structure.Poulallier;
import data.structure.Structure;
import gui.gestionnaire.keys.Structures;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.DomesticSpeciesVisitor;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;

public class DomesticSpeciesHomeSender implements DomesticSpeciesVisitor<Void>{

    private Structure getAvalableHome(Structures structureKey) throws UnableToMakeTheTransfertException{
        ArrayList<Structure> structureList = GestionnaireStructures.getInstance().getStructures().get(structureKey);
        if(structureList == null){
            throw new UnableToMakeTheTransfertException("No home found");
        }
        Iterator<Structure> structureIter = structureList.iterator();  
        Structure currentStructure;
        boolean isAvalable = false;
        int indexOfAvalableStructure = -1;
        while(structureIter.hasNext() && !isAvalable){
            currentStructure =  structureIter.next();//TODO considérer la capacité max des structrues;
            isAvalable = currentStructure.isStatique();
            indexOfAvalableStructure++;
        }if(isAvalable){
            return structureList.get(indexOfAvalableStructure);
        }
        throw new UnableToMakeTheTransfertException(" No free "+ structureKey); 
    }
    
    @Override
    public Void action(Mouton mouton) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, UnableToMakeTheTransfertException {
        BergerieMouton freeBergerieMouton = (BergerieMouton)getAvalableHome(mouton.getHomeLabel());
        freeBergerieMouton.addInHabitant(mouton);
        return null;
    }
    
    @Override
    public Void action(Poule poule) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, UnableToMakeTheTransfertException {
        Poulallier freePoulallier = (Poulallier)getAvalableHome(poule.getHomeLabel());
        freePoulallier.addInHabitant(poule);
        return null;
    }
    
    @Override
    public Void action(Vache vache) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, UnableToMakeTheTransfertException {
        Etable freeEtable = (Etable)getAvalableHome(vache.getHomeLabel());
        freeEtable.addInHabitant(vache);
        return null;
    }

    @Override
    public Void action(Chevre chevre) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, UnableToMakeTheTransfertException {
        BergerieChevre freeBergerieChevre = (BergerieChevre)getAvalableHome(chevre.getHomeLabel());
        freeBergerieChevre.addInHabitant(chevre);
        return null;

    }


    @Override
    public Void action(Terrain terrain) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException, NotImplementYetException {
        throw new BeingCannotPerformSuchActionException();
    }
  
    
}
