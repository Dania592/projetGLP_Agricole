package data.gestion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import data.flore.terrains.Terrain;
import gui.gestionnaire.keys.Graine;

public class GestionnaireTerrains implements GestionnaireInterface, Serializable{

	private static final long serialVersionUID = 1L;
	
	private HashMap<Graine, ArrayList<Terrain>> terrains = new HashMap<>();
	
	public HashMap<Graine, ArrayList<Terrain>> getTerrains() {
		return terrains;
	}
	
	private static GestionnaireTerrains instance = new GestionnaireTerrains();
	
	private GestionnaireTerrains(){}
	
	public static GestionnaireTerrains getInstance() {
		return instance;
	}
	
//	public void initializeGestionnaire(Map map ) {
//		Terrain terrain = new Terrain("terrain", false, 9, 0, 0, map, 10, TypeTerrain.Pommier);		
//		terrains.put(terrain.getReference(), terrain);	
//	}
	
	public void add(Terrain terrain) {
		Graine type = terrain.getType();
		if (terrains.containsKey(type)) { 
			terrains.get(type).add(terrain);
		} else {
			ArrayList<Terrain> terrains = new ArrayList<>();
			terrains.add(terrain);
			this.terrains.put(type, terrains);
		}
	}
	
	public void remove(Terrain terrain) {
		Graine type = terrain.getType();
		if (terrains.containsKey(type)) { 
			if (terrains.get(type).size() == 1) {
				terrains.remove(type);
			} else {
				terrains.get(type).remove(terrain);
			}
		}
	}
	
	public int getSize() {
		int size = 0;
		for(ArrayList<Terrain> terr : terrains.values()) {
			size += terr.size();
		}
		return size;
	}
	
	public String toString() {
		StringBuffer gestionnaire = new StringBuffer("\t"+ this.getClass().getSimpleName());
		gestionnaire.append("\n\t\t Terrains :");
		for (ArrayList<Terrain> terrains : terrains.values()) {
			for (Terrain terrain : terrains ) {
				gestionnaire.append("\n\t\t\t"+ terrain.toString());
			}
		}
		gestionnaire.append(terrains.size());
		return gestionnaire.toString();
	}
}
