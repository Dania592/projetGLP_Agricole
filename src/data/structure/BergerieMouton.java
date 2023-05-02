package data.structure;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import data.espece.characteristic.Slaughtable;
import data.espece.faune.Mouton;
import data.map.Map;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.production.Produit;
import data.structure.hability.Distributor;
import data.structure.hability.HealablePlace;
import data.structure.hability.ProductifPlace;
import data.structure.hability.SlaughterHouseSender;
import gui.gestionnaire.keys.Structures;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;

public class BergerieMouton extends Refuge<Mouton> implements  Distributor<Mouton>, SlaughterHouseSender, HealablePlace{
    private boolean isUsedForATask= false;

    public BergerieMouton( String reference ) {
		super( reference  );
		
			setImage("src"+File.separator+"ressources"+File.separator+"minietable.png");
		
	}

    @Override
    public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException, UnableToMakeTheTransfertException {
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
    public boolean isEmpty() {
        return getInHabitant().isEmpty();
    }


    @Override
    public void addSpecialSenderElement(Mouton specialSenderElement) {
        addInHabitant(specialSenderElement);
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
    public <T> T launchAction(PlaceVisitor<T> visitor, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException{
        return visitor.action(this, activity);
    }

    @Override
    public boolean isReadyToSendToSlaughterHouse() {
        return getInHabitant().size()>0;
    }

    @Override
    public void removeAll() {
        getInHabitant().clear();        
    }

    



}
