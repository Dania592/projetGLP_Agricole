package data.structure;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import data.espece.Slaughtable;
import data.espece.faune.Poule;
import data.map.Map;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.production.Produits;
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

public class Poulallier extends Refuge<Poule> implements ProductifPlace, Distributor<Poule>, SlaughterHouseSender{
	private ArrayList<Slaughtable> animalToKill = new ArrayList<>(); 
	private HashMap<Produits, Integer> production = new HashMap<>();
	
	
	public Poulallier(int ligne_init, int colonne_init,String reference , Map map ) {
		super(ligne_init, colonne_init, reference , map);
		setImage("src"+File.separator+"ressources"+File.separator+"minimoulin.png");	
	}

	@Override
	public ArrayList<ActionnableKey> getASetOfAllActionnableKey() {
		ArrayList<ActionnableKey> actionnableKey = super.getActionnableKey();
		actionnableKey.add(getSpecificActionnableKey());
		return actionnableKey;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, NotImplementYetException {
		return visitor.action(this);
	}

	@Override
	public boolean isEmpty() {
		return getInHabitant().isEmpty();
	}

	@Override
	protected int getMaxCapacity(){
		return MaxCapacity.MAX_CAPACITE_POULAILLER.getCapacity();
	}
	
	public Structures getKey() {
		return Structures.POULAILLER;
	}

	@Override
	public void addToSlaughter(Slaughtable slaughtable) {
		animalToKill.add(slaughtable);
		getInHabitant().remove(slaughtable);
	}
	@Override
	public ArrayList<Slaughtable> getAnimalToSlaugther() {
		return animalToKill;
	}

	@Override
	public boolean readyToSend() {
		return true;
	}

	@Override
	public boolean canLaunchProduction() {
		return true;
	}

	@Override
	public void addSpecialSenderElement(Poule specialSenderElement) {
		addInHabitant(specialSenderElement);
	}

	@Override
	public boolean isReadyToSendToSlaughterHouse() {
		return !animalToKill.isEmpty();
	}

	@Override
	public void removeAll(ArrayList<Poule> transportableToRemoveList) {
		getInHabitant().removeAll(transportableToRemoveList); 
	}

	@Override
	public ActionnableKey getSpecificActionnableKey() {
		return ActionnableKey.POULAILLER;
	}


	@Override
	public HashMap<Produits, Integer> getProduction() {
		return production;
	}

	@Override
	public int getNumberOfTarget() {
		return getInHabitant().size();
	}

	@Override
	public boolean needPlayerIntervention() {
		return false;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor, Activity activity)
			throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
			BeingCannotPerformSuchActionException, NotImplementYetException,
			NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
			UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
		// TODO Auto-generated method stub
		return null;
	}


}
