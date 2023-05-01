package data.gestion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import data.espece.faune.Animal;
import gui.gestionnaire.keys.Animals;

public class GestionnaireAnimaux implements GestionnaireInterface, Serializable{

	private static final long serialVersionUID = 1L;

	private HashMap<Animals, ArrayList<Animal>> animaux = new HashMap<>();

	private static GestionnaireAnimaux instance = new GestionnaireAnimaux();
	
	private GestionnaireAnimaux() {}
	
	public static GestionnaireAnimaux getInstance() {
		return instance;
	}
	
	public HashMap<Animals, ArrayList<Animal>> getAnimaux(){
		return animaux;
	}
	
	public void reset() {
		animaux.clear();
	}
	
	public void add(Animal animal) {
		Animals key = animal.getKey();
		if (animaux.containsKey(key)) {
			animaux.get(key).add(animal);
			System.out.println("Added animal contained");
		} else {
			ArrayList<Animal> animals = new ArrayList<>();
			animals.add(animal);
			animaux.put(key, animals);
			System.out.println("Added animal new");
		}
	}
	
	public void remove(Animal animal) {
		ArrayList<Animal> animals = GestionnaireAnimaux.getInstance().getAnimaux().get(animal.getKey());
		if (animals != null ) {
			if (animals.size() == 1) {
				System.out.println("removing 1 animal");
				GestionnaireAnimaux.getInstance().getAnimaux().remove(animal.getKey());
			} else {
				System.out.println("removing more animal");
				animals.remove(animal);
			}
		}
	}
	
	public void remove(Animals key,int quantity) {
		int i = 0;
		int count = animaux.get(key).size();
		if (quantity == count) {
			animaux.remove(key);
		} else {
			while(i<quantity && quantity < count) {
				System.out.println("removing quantity animal");
				animaux.get(key).remove(0);
				i++;
			}
		}
	}
	
	public int getSize() {
		int size = 0;
		for(ArrayList<Animal> animals : animaux.values()) {
			size += animals.size();
		}
		return size;
	}
	
	public String toString() {
		StringBuffer gestionnaire = new StringBuffer("\t"+ this.getClass().getSimpleName());
		gestionnaire.append("\n\t\t Animaux :");
		for (ArrayList<Animal> animals : animaux.values()) {
			for(Animal animal : animals) {
				gestionnaire.append("\n\t\t\t"+ animal.toString());
			}
		}
		return gestionnaire.toString();
	}
	
}
