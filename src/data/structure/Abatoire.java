package data.structure;

import java.util.ArrayList;
import java.util.HashMap;

import data.espece.Slaughtable;
import data.map.Map;
import data.production.Produits;
import data.structure.hability.Distributor;
import data.structure.hability.ProductifPlace;
import gui.gestionnaire.keys.Structures;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.place.PlaceVisitor;

public class Abatoire extends StructureAction implements Distributor<Slaughtable>{
	private ArrayList<Slaughtable> animaltoSlaughter = new ArrayList<>();
	private static final long serialVersionUID = 1L;
	private HashMap<Produits, Integer> production = new HashMap<>();
	private final static float PRIX_ACHAT = 50000 ;
	
	public ArrayList<Slaughtable> getAnimaltoSlaughter() {
		return animaltoSlaughter;
	}

	public Abatoire(int ligne_init, int colonne_init, String reference , Map map ) {
		super(ligne_init, colonne_init, reference , map);
	}

	public ArrayList<ActionnableKey> getASetOfAllActionnableKey(){
		ArrayList<ActionnableKey> actionnableKey = super.getASetOfAllActionnableKey();
		actionnableKey.add(getSpecificActionnableKey());
		return actionnableKey;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException {
		return visitor.action(this);
	}

	public Structures getKey() {
		return Structures.ABATTOIRE;
	}


	@Override
	public boolean canLaunchProduction() {
		return animaltoSlaughter.size()>0;
	}

	@Override
	public boolean isEmpty() {
		return animaltoSlaughter.isEmpty();
	}

	@Override
	public boolean readyToSend() {
		return animaltoSlaughter.isEmpty();
	}

	@Override
	public void addSpecialSenderElement(Slaughtable specialSenderElement) {
		animaltoSlaughter.add(specialSenderElement);
	}

	@Override
	public void removeAll(ArrayList<Slaughtable> transportableToRemoveList) {
		animaltoSlaughter.removeAll(transportableToRemoveList);
	}
	
	@Override
	public ActionnableKey getSpecificActionnableKey() {
		return ActionnableKey.ABATOIRE;
	}
	
	@Override
	public HashMap<Produits, Integer> getProduction() {
		return production;
	}

	@Override
	public int getNumberOfTarget() {
		return animaltoSlaughter.size();
	}

	@Override
	public boolean needPlayerIntervention() {
		return true;
	}

	
}
