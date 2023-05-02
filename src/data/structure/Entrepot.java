package data.structure;

import java.io.File;
import java.util.ArrayList;

import data.map.Map;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.notion.basic.Farm;
import data.planning.Activity;
import data.production.Produit;
import gui.gestionnaire.keys.Structures;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;


public class Entrepot extends Structure{

	private ArrayList<Produit> produits ;
	private int capacite =100  ; 
	private boolean usedForAnAction = false;
	public Entrepot( String reference ) {
		super( reference );
		this.produits = new ArrayList<>();
		
		setImage("src"+File.separator+"ressources"+File.separator+Farm.saisonActuelle+File.separator+"Structure"+File.separator+"Entrepot.png");
		
	}

	public ArrayList<Produit> getProduits() {
		return produits;
	}

	public void addProduit(Produit produit) {
		produits.add(produit);
	}
	public void removeProduit(Produit produit) {
		produits.remove(produit);
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable {
		return visitor.action(this);
	}

	public Structures getKey() {
		return Structures.ENTREPOT;
	}

	@Override
	public ActionnableKey getSpecificActionnableKey() {
		return ActionnableKey.ENTREPOT;
	}

	@Override
	public ArrayList<ActionnableKey> getASetOfAllActionnableKey() {
		ArrayList<ActionnableKey> actionnableKeys = new ArrayList<>();
		actionnableKeys.add(getSpecificActionnableKey());
		return actionnableKeys;
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
	public <T> T launchAction(PlaceVisitor<T> visitor, Activity activity)
			throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
			BeingCannotPerformSuchActionException,
			NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
			UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
		return visitor.action(this, activity);
	}

	
	
	
	
}
