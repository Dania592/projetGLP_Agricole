package data.gestion;

import java.util.HashMap;

import data.flore.terrains.Terrain;
import data.flore.terrains.TypeTerrain;
import data.map.Map;

public class GestionnaireTerrains {
	private HashMap<String, Terrain> terrains = new HashMap<>();
	
	public HashMap<String, Terrain> getTerrains() {
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
	
	public String toString() {
		StringBuffer gestionnaire = new StringBuffer("\t"+ this.getClass().getSimpleName());
		gestionnaire.append("\n\t\t Terrains :");
		for (Terrain terrain : terrains.values()) {
			gestionnaire.append("\n\t\t\t"+ terrain.toString());
		}
		return gestionnaire.toString();
	}
}
