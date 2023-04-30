package data.structure;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import data.espece.Slaughtable;
import data.espece.faune.Mouton;
import data.map.Map;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.production.Produit;
import data.structure.hability.Distributor;
import data.structure.hability.ProductifPlace;
import data.structure.hability.SlaughterHouseSender;
import gui.gestionnaire.keys.Structures;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;

public class BergerieMouton extends Refuge<Mouton> implements  Distributor<Mouton>, SlaughterHouseSender{
    private ArrayList<Produit> production =new ArrayList<>();
    private ArrayList<Slaughtable> moutonToSlaughter = new ArrayList<>();
    private boolean isUsedForATask= false;

    public BergerieMouton(int ligne_init, int colonne_init , String reference , Map map ) {
		super(ligne_init, colonne_init, reference , map );
		
			setImage("src"+File.separator+"ressources"+File.separator+"minietable.png");
		
	}

    @Override
    public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException, UnableToMakeTheTransfertException {
        return visitor.action(this);
    }

    @Override
    protected int getMaxCapacity() {
        return MaxCapacity.MAX_CAPACITE_BERGERIE_MOUTON.getCapacity();
    }

    @Override
    public Structures getKey() {
        return Structures.BERGERIE_MOUTON;
    }

    @Override
    public void addToSlaughter(Slaughtable slaughtable) {
        moutonToSlaughter.add(slaughtable);
        getInHabitant().remove(slaughtable);
    }

    @Override
    public ArrayList<Slaughtable> getAnimalToSlaugther() {
        return moutonToSlaughter;
    }

    @Override
    public boolean isEmpty() {
        return getInHabitant().isEmpty();
    }

    @Override
    public boolean readyToSend() {
        Iterator<Mouton> moutonIter = getInHabitant().iterator();
        boolean isReady = false;
        while(moutonIter.hasNext() && !isReady){
            isReady = moutonIter.next().haveProduced();
        }
        return isReady;
    }

    @Override
    public void addSpecialSenderElement(Mouton specialSenderElement) {
        addInHabitant(specialSenderElement);
    }

    @Override
    public boolean isReadyToSendToSlaughterHouse() {
        return !moutonToSlaughter.isEmpty();
    }

    @Override
    public void removeAll(ArrayList<Mouton> transportableToRemoveList) {
        getInHabitant().removeAll(transportableToRemoveList);
    }

    @Override
    public ArrayList<ActionnableKey> getASetOfAllActionnableKey() {
        ArrayList<ActionnableKey> actionnableKey = super.getActionnableKey();
		actionnableKey.add(getSpecificActionnableKey());
		return actionnableKey;
    }

    @Override
    public ActionnableKey getSpecificActionnableKey() {
        return ActionnableKey.BERGERIE_MOUTON;
    }
    
    @Override
    public boolean isCurrentlyUsedForAnotherTask() {
        return isUsedForATask;
    }

    @Override
    public void setStructureStatus(boolean isCurrentlyUsedForAnotherTask) {
        isUsedForATask = isCurrentlyUsedForAnotherTask;
    }

    @Override
    public int getNumberOfTarget() {
        return getInHabitant().size();
    }

    @Override
    public <T> T launchAction(PlaceVisitor<T> visitor, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException{
        return visitor.action(this, activity);
    }



}
