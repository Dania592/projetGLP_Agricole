package data.structure;

import java.util.ArrayList;

import data.espece.faune.Animal;
import data.map.Map;

public abstract class Refuge<T> extends Structure {
	// public abstract class Refuge<T> extends Structureimplements Healable {
	private ArrayList<T> inHabitant;
	private int currentNumberOfInhabitant;

	public enum MaxCapacity{
		MAX_CAPACITE_POULAILLER(30),
		MAX_CAPACITE_MAISON(40),
		MAX_CAPACITE_ETABLE(40),
		;
		int capacity;
		
		private MaxCapacity(int capacity){
			this.capacity = capacity;
		}

		public int getCapacity() {
			return capacity;
		}
	}

	public Refuge(int ligne_init, int colonne_init, float prixAchat, String reference, Map map) {
		super(ligne_init, colonne_init, prixAchat, reference, map);
		this.inHabitant = new ArrayList<>();
		currentNumberOfInhabitant = 0;
	}

	public ArrayList<T> getInHabitant() {
		return inHabitant;
	}

	public void addInHabitant(T animal) {
		if(ableToAcceptInhabitant()){
			inHabitant.add(animal);
			currentNumberOfInhabitant++;
		}
	}

	public void removeAnimal(T animal) {
		inHabitant.remove(animal);
		currentNumberOfInhabitant--;
	}

	public ArrayList<ActionnableKey> getActionnableKey() {
		ArrayList<ActionnableKey> actionnableKeys = super.getActionnableKey();
		actionnableKeys.add(ActionnableKey.REFUGE);
		return actionnableKeys;
	}

	protected abstract int getMaxCapacity();

	public boolean ableToAcceptInhabitant(){
		return currentNumberOfInhabitant+1<=getMaxCapacity();
	}


	
}
