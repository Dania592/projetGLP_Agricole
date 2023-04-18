package data.structure.hability.list;

import java.io.Serializable;
import java.util.ArrayList;

import data.espece.faune.AnimalProducteur;
import data.espece.faune.Chevre;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;

public class EnclosStorageStructure implements Serializable{
	private ArrayList<Vache> vaches = new ArrayList<>(); 
	private ArrayList<Poule> poules = new ArrayList<>(); 
	private ArrayList<Mouton> moutons = new ArrayList<>();
	private ArrayList<Chevre> chevres = new ArrayList<>();


	public ArrayList<Vache> getVaches() {
		return vaches;
	}

	public ArrayList<Poule> getPoules() {
		return poules;
	}

	public ArrayList<Mouton> getMoutons() {
		return moutons;
	}

	public ArrayList<Chevre> getChevres(){
		return chevres;
	}
	public void add(Vache vache){
		vaches.add(vache);
	}

	public void add(Poule poule){
		poules.add(poule);
	}

	public void add(Mouton mouton){
		moutons.add(mouton);
	}

	public void add(Chevre chevre) {
		chevres.add(chevre);
	}

	public ArrayList<AnimalProducteur>  getAnimals() {
		ArrayList<AnimalProducteur> animals = new ArrayList<>();
		animals.addAll(vaches);
		animals.addAll(moutons);
		animals.addAll(poules);
		animals.addAll(chevres);
		return animals ;
	}


	public void remove(AnimalProducteur animal){
		String type = animal.getClass().getSimpleName();
		switch(type) {
		case "Vache" :
			vaches.remove(animal);
			break;
		case "Poule" : 
			poules.remove(animal);
			break;
		case "Mouton" :
			moutons.remove(animal);
			break;
		case "Chevre" : 
			chevres.remove(animal);
		default:
			break;
		}

	}
	
	public void add(AnimalProducteur animal){
		String type = animal.getClass().getSimpleName();
		switch(type) {
		case "Vache" :
			vaches.add((Vache)animal);
			break;
		case "Poule" : 
			poules.add((Poule)animal);
			break;
		case "Mouton" :
			moutons.add((Mouton)animal);
			break;
		case "Chevre" : 
			chevres.add((Chevre)animal);
		default:
			break;
		}

	}



}
