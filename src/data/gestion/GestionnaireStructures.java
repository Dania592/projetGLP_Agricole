package data.gestion;

import java.util.ArrayList;
import java.util.HashMap;

import data.map.Map;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.Structure;

public class GestionnaireStructures {
	private HashMap<String, ArrayList<Structure>> structures = new HashMap<>();
	
	public HashMap<String, ArrayList<Structure>> getStructures() {
		return structures;
	}

	private static GestionnaireStructures instance = new GestionnaireStructures();
	
	private GestionnaireStructures(){}
	
	public static GestionnaireStructures getInstance() {
		return instance;
	}
	
	public void add(Structure structure) {
		String name = structure.getClass().getSimpleName();
		if (structures.containsKey(name)) {
			structures.get(name).add(structure);
		} else {
			ArrayList<Structure> structs = new ArrayList<>();
			structs.add(structure);
			structures.put(name, structs);
		}
	}
	
	public void remove(Structure structure) {
		String name = structure.getClass().getSimpleName();
		if (structures.containsKey(name)) {
			if (structures.get(name).size() == 1) {
				structures.remove(name);
			} else {
				structures.get(name).remove(structure);
			}
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
		structures.put("Maison", maisons);
		maisons = new ArrayList<>();
		maisons.add(etable);
		structures.put("Etable", maisons);
		maisons = new ArrayList<>();
		maisons.add(poulallier);
		structures.put(poulallier.getReference(), maisons);
		maisons = new ArrayList<>();
		maisons.add(entrepot);
		structures.put(entrepot.getReference(), maisons);
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
