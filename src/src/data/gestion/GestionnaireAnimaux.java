package data.gestion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import data.espece.faune.Animal;

public class GestionnaireAnimaux implements Serializable {
	
	private HashMap<String, ArrayList<Animal>> animaux = new HashMap<>();

	private static GestionnaireAnimaux instance = new GestionnaireAnimaux();
	
	private GestionnaireAnimaux() {}
	
	public static GestionnaireAnimaux getInstance() {
		return instance;
	}
	
	public HashMap<String, ArrayList<Animal>> getAnimaux(){
		return animaux;
	}
	
	public void add(Animal animal) {
		String name = animal.getClass().getSimpleName();
		if (animaux.containsKey(name)) {
			animaux.get(name).add(animal);
		} else {
			ArrayList<Animal> animals = new ArrayList<>();
			animals.add(animal);
			animaux.put(name, animals);
		}
	}
	
	public void remove(Animal animal) {
		ArrayList<Animal> animals = GestionnaireAnimaux.getInstance().getAnimaux().get(animal.getClass().getSimpleName());
		if (animals != null ) {
			if (animals.size() == 1) {
				GestionnaireAnimaux.getInstance().getAnimaux().remove(animal.getClass().getSimpleName());
			} else {
				animals.remove(animal);
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
