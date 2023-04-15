package data.gestion;

import java.util.ArrayList;
import java.util.HashMap;

import data.espece.faune.Animal;
import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.Structures;

public class GestionnaireAnimaux implements GestionnaireInterface{
	
	private HashMap<Animals, ArrayList<Animal>> animaux = new HashMap<>();

	private static GestionnaireAnimaux instance = new GestionnaireAnimaux();
	
	private GestionnaireAnimaux() {}
	
	public static GestionnaireAnimaux getInstance() {
		return instance;
	}
	
	public HashMap<Animals, ArrayList<Animal>> getAnimaux(){
		return animaux;
	}
	
	public void add(Animal animal) {
		Animals key = animal.getKey();
		if (animaux.containsKey(key)) {
			animaux.get(key).add(animal);
		} else {
			ArrayList<Animal> animals = new ArrayList<>();
			animals.add(animal);
			animaux.put(key, animals);
		}
	}
	
	public void remove(Animal animal) {
		ArrayList<Animal> animals = GestionnaireAnimaux.getInstance().getAnimaux().get(animal.getGestionnaireKey());
		if (animals != null ) {
			if (animals.size() == 1) {
				GestionnaireAnimaux.getInstance().getAnimaux().remove(animal.getGestionnaireKey());
			} else {
				animals.remove(animal);
			}
		}
	}
	
	public void remove(Animals key,int quantity) {
		int i = 0;
		while(i<quantity) {
			animaux.get(key).remove(0);
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
