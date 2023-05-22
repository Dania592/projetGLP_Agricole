package data.structure;

import java.io.File;
import java.util.ArrayList;

import data.configuration.GameConfiguration;
import data.espece.characteristic.Healable;
import data.espece.characteristic.Slaughtable;
import data.espece.faune.Chevre;
import data.espece.flore.Saison;
import data.map.Map;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.Distributor;
import data.structure.hability.HealablePlace;
import data.structure.hability.SlaughterHouseSender;
import gui.gestionnaire.keys.Structures;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;


public class BergerieChevre extends Refuge<Chevre> implements  Distributor<Chevre>, SlaughterHouseSender, HealablePlace{
    private boolean isUsedForATask = false;

    public BergerieChevre( String reference  ) {
		super( reference);
		
		setImage(GameConfiguration.IMAGE_PATH+Saison.PRINTEMPS+File.separator+"Structure"+File.separator+"BergerieChevre.png");	
		
		
	}

    @Override
    protected int getMaxCapacity() {
        return MaxCapacity.MAX_CAPACITE_BERGERIE_CHEVRE.getCapacity();
    }

    @Override
    public Structures getKey() {
        return Structures.BERGERIE_CHEVRE;
    }


    @Override
    public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException, UnableToMakeTheTransfertException {
        return visitor.action(this);
    }
    @Override
    public boolean isEmpty() {
       return getInHabitant().isEmpty();
    }
    
    @Override
    public void addSpecialSenderElement(Chevre specialSenderElement) {
        getInHabitant().add(specialSenderElement);
    }
    
    @Override
    public void removeAll(ArrayList<Chevre> transportableToRemoveList) {
        getInHabitant().removeAll(transportableToRemoveList);
    }

    @Override
    public ArrayList<ActionnableKey> getASetOfAllActionnableKey(){
        ArrayList<ActionnableKey> actionnableKey = super.getActionnableKey();
		actionnableKey.add(ActionnableKey.MILK_PRODUCEUR_REFUGE);
		actionnableKey.add(getSpecificActionnableKey());
		return actionnableKey;
    }

    @Override
    public ActionnableKey getSpecificActionnableKey() {
        return  ActionnableKey.BERGERIE_CHEVRE;
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
    public <T> T launchAction(PlaceVisitor<T> visitor, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
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
