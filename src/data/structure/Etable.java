package data.structure;

import java.io.File;
import java.util.ArrayList;

import data.espece.Slaughtable;
import data.espece.faune.Vache;
import data.map.Map;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.Distributor;
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

public class Etable extends Refuge<Vache> implements SlaughterHouseSender, Distributor<Vache>{
	private ArrayList<Slaughtable> animalToSlaughter = new ArrayList<>();
	private static boolean usedForAnAction = false;

	public Etable(int ligne_init, int colonne_init , String reference , Map map ) {
		super(ligne_init, colonne_init, reference , map );
		
			setImage("src"+File.separator+"ressources"+File.separator+"minietable.png");
		
	}
	
	@Override
	public ArrayList<ActionnableKey> getASetOfAllActionnableKey(){
		ArrayList<ActionnableKey> actionnableKeys = super.getActionnableKey();
		actionnableKeys.add(getSpecificActionnableKey());
		return actionnableKeys;
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
	public void addToSlaughter(Slaughtable slaughtable) {
		animalToSlaughter.add(slaughtable);
		getInHabitant().remove(slaughtable);
	}

	@Override
	public ArrayList<Slaughtable> getAnimalToSlaugther() {
		return animalToSlaughter;
	}

	@Override
	public boolean readyToSend() {
		return true; 
	}

	@Override
	public void addSpecialSenderElement(Vache specialSenderElement) {
		addInHabitant(specialSenderElement);
	}

	@Override
	public boolean isReadyToSendToSlaughterHouse() {
		return !animalToSlaughter.isEmpty();
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
			BeingCannotPerformSuchActionException, NotImplementYetException,
			NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
			UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
			return visitor.action(this, activity); 
	}



}
