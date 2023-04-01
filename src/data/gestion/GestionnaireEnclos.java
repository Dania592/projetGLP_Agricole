package data.gestion;

import java.util.ArrayList;

import data.structure.Enclos;

public class GestionnaireEnclos {

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
	
}
