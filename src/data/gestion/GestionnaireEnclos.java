package data.gestion;

import java.util.HashMap;

import data.structure.Enclos;

public class GestionnaireEnclos {

	private HashMap<String , Enclos > enclos = new HashMap<>();
	
	
	private static GestionnaireEnclos instance = new GestionnaireEnclos();
	
	private GestionnaireEnclos() {}

	public static GestionnaireEnclos getInstance() {
		return instance;
	}
	
	public HashMap<String, Enclos > getEnclos(){
		return enclos;
	}
	
}
