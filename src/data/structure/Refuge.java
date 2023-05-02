package data.structure;

import java.util.ArrayList;

import data.map.Map;



public abstract class Refuge<T> extends Structure {
	private ArrayList<T> inHabitant;
	private int currentNumberOfInhabitant;

	public enum MaxCapacity{
		MAX_CAPACITE_POULAILLER(30),
		MAX_CAPACITE_MAISON(40),
		MAX_CAPACITE_ETABLE(40),
		MAX_CAPACITE_BERGERIE_MOUTON(40),
		MAX_CAPACITE_BERGERIE_CHEVRE(40),
		;
		int capacity;
		
		private MaxCapacity(int capacity){
			this.capacity = capacity;
		}

		public int getCapacity() {
			return capacity;
		}
	}

	public Refuge( String reference) {
		super( reference);
		this.inHabitant = new ArrayList<>();
		currentNumberOfInhabitant = 0;
	}

	public ArrayList<T> getInHabitant() {
		return inHabitant;
	}

	public void addInHabitant(T animal) {
		// if(ableToAcceptInhabitant()){
		// 	currentNumberOfInhabitant++;
		// }
		inHabitant.add(animal);
	}

	public void removeAnimal(T animal) {
		inHabitant.remove(animal);
		currentNumberOfInhabitant--;
	}

	public ArrayList<ActionnableKey> getActionnableKey() {
		ArrayList<ActionnableKey> actionnableKeys = super.getASetOfAllActionnableKey();
		actionnableKeys.add(ActionnableKey.REFUGE);
		return actionnableKeys;
	}

	protected abstract int getMaxCapacity();

	public boolean ableToAcceptInhabitant(){
		return currentNumberOfInhabitant+1<=getMaxCapacity();
	}


	
}
