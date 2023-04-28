package data.structure;

import java.io.File;
import java.util.ArrayList;

import data.map.Map;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.production.Produit;
import gui.gestionnaire.keys.Structures;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;


public class Entrepot extends Structure{

	private ArrayList<Produit> produits ;
	private int capacite =100  ; 
	private boolean usedForAnAction = false;
	public Entrepot(int ligne_init, int colonne_init, String reference , Map map ) {
		super(ligne_init, colonne_init, reference , map);
		this.produits = new ArrayList<>();
		
		setImage("src"+File.separator+"ressources"+File.separator+"Structure"+File.separator+"Entrepot1.png");
		
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
			BeingCannotPerformSuchActionException, NotImplementYetException,
			NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
			UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
		return visitor.action(this, activity);
	}

	
	
	
	
}
