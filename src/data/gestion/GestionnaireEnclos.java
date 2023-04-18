package data.gestion;

import java.io.Serializable;
import java.util.ArrayList;

import data.espece.faune.Animal;
import data.structure.Enclos;

public class GestionnaireEnclos implements Serializable {

	private ArrayList<Enclos> enclos = new ArrayList<Enclos>();
	
	
	private static GestionnaireEnclos instance = new GestionnaireEnclos();
	
	private GestionnaireEnclos() {}

	public static GestionnaireEnclos getInstance() {
		return instance;
	}
	
	public ArrayList<Enclos> getEnclos(){
		return enclos;
	}
	
	public void add(Enclos enclos) {
		this.enclos.add(enclos);
	}
	
	public void remove(Enclos enclos) {
		this.enclos.remove(enclos);
	}
	
	public int getSize() {
		return enclos.size();
	}
	
}
