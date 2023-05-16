package data.structure;

import java.io.File;
import java.util.ArrayList;

import data.espece.characteristic.Healable;
import data.espece.characteristic.Slaughtable;
import data.espece.faune.Vache;
import data.espece.flore.Saison;
import data.map.Map;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.notion.basic.Farm;
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

public class Etable extends Refuge<Vache> implements SlaughterHouseSender, Distributor<Vache>, HealablePlace{
	private static boolean usedForAnAction = false;

	public Etable( String reference ) {
		super( reference );
		
			setImage("src"+File.separator+"ressources"+File.separator+Saison.PRINTEMPS+File.separator+"Structure"+File.separator+"Etable.png");
		
	}
	
	@Override
	public ArrayList<ActionnableKey> getASetOfAllActionnableKey(){
		ArrayList<ActionnableKey> actionnableKeys = super.getActionnableKey();
		actionnableKeys.add(ActionnableKey.MILK_PRODUCEUR_REFUGE);
		actionnableKeys.add(getSpecificActionnableKey());
		return actionnableKeys; //MILK_PRODUCEUR_REFUGE
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException {
		return visitor.action(this);
	}

	@Override
	public boolean isEmpty() {
		return getInHabitant().isEmpty();
	}

	@Override
	protected int getMaxCapacity() {
		return MaxCapacity.MAX_CAPACITE_MAISON.getCapacity();
	}


	public Structures getKey() {
		return Structures.ETABLE;
	}


	@Override
	public void addSpecialSenderElement(Vache specialSenderElement) {
		addInHabitant(specialSenderElement);
	}

	@Override
	public void removeAll(ArrayList<Vache> transportableToRemoveList) {
		getInHabitant().removeAll(transportableToRemoveList);
	}

	@Override
	public ActionnableKey getSpecificActionnableKey() {
		return ActionnableKey.ETABLE;
	}
	
	@Override
	public boolean isCurrentlyUsedForAnotherTask() {
		return usedForAnAction;
	}

	@Override
	public void setStructureStatus(boolean isCurrentlyUsedForAnotherTask) {
		usedForAnAction = isCurrentlyUsedForAnotherTask;
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
		return !(getInHabitant().size() == 0);
	}

	@Override
	public void removeAll() {
		getInHabitant().clear();
	}



}
