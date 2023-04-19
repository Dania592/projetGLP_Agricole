package data.gestion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import data.map.Map;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.Structure;
import gui.gestionnaire.keys.Structures;

public class GestionnaireStructures implements GestionnaireInterface, Serializable{

	private static final long serialVersionUID = 1L;
	
	private HashMap<Structures, ArrayList<Structure>> structures = new HashMap<>();
	
	public HashMap<Structures, ArrayList<Structure>> getStructures() {
		return structures;
	}

	private static GestionnaireStructures instance = new GestionnaireStructures();
	
	private GestionnaireStructures(){}
	
	public static GestionnaireStructures getInstance() {
		return instance;
	}
	
	public void add(Structure structure) {
		Structures key = structure.getKey();
		if (structures.containsKey(key)) {
			structures.get(key).add(structure);
		} else {
			ArrayList<Structure> structs = new ArrayList<>();
			structs.add(structure);
			structures.put(key, structs);
		}
	}
	
	public void remove(Structure structure) {
		Structures key = structure.getKey();
		if (structures.containsKey(key)) {
			if (structures.get(key).size() == 1) {
				structures.remove(key);
			} else {
				structures.get(key).remove(structure);
			}
		}
	}
	
	public void remove(Structures key,int quantity) {
		int i = 0;
		while(i<quantity) {
			structures.get(key).remove(0);
		}
	}
	
	public int getSize() {
		int size = 0;
		for (ArrayList<Structure> structs : structures.values()) {
			size += structs.size();
		}
		return size;
	}
	
	public void initializeGestionnaire(Map map ) {
		Maison maison0 = new Maison(0, 0, "ma0", map);
		Etable etable = new Etable(0, 0, "et0", map);
		Poulallier poulallier = new Poulallier(0, 0, "po0", map);
		Entrepot entrepot = new Entrepot(0, 0, "en0", map);
		Maison maison1 = new Maison(0, 0, "ma1", map);
		
		ArrayList<Structure> maisons = new ArrayList<>();
		maisons.add(maison0);
		maisons.add(maison1);
		structures.put(Structures.MAISON, maisons);
		maisons = new ArrayList<>();
		maisons.add(etable);
		structures.put(Structures.ETABLE, maisons);
		maisons = new ArrayList<>();
		maisons.add(poulallier);
		structures.put(poulallier.getKey(), maisons);
		maisons = new ArrayList<>();
		maisons.add(entrepot);
		structures.put(entrepot.getKey(), maisons);
	}
	
	public String toString() {
		StringBuffer gestionnaire = new StringBuffer("\t"+ this.getClass().getSimpleName());
		gestionnaire.append("\n\t\t Structures :");
		for (ArrayList<Structure> structs : structures.values()) {
			for (Structure structure : structs) {
				gestionnaire.append("\n\t\t\t"+ structure.toString());				
			}
		}
		return gestionnaire.toString();
	}
	
}
