package data.gestion;

import java.util.ArrayList;
import java.util.HashMap;

import data.flore.terrains.Terrain;
import data.flore.terrains.TypeGraine;

public class GestionnaireTerrains {
	
	private HashMap<TypeGraine, ArrayList<Terrain>> terrains = new HashMap<>();
	
	public HashMap<TypeGraine, ArrayList<Terrain>> getTerrains() {
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
		TypeGraine type = terrain.getType();
		if (terrains.containsKey(type)) { 
			terrains.get(type).add(terrain);
		} else {
			ArrayList<Terrain> terrains = new ArrayList<>();
			terrains.add(terrain);
			this.terrains.put(type, terrains);
		}
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
