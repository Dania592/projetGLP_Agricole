package data.espece.flore.terrains;

import java.io.File;
import java.util.HashMap;

import data.configuration.GameConfiguration;
import data.notion.evolution.EvolutionTerrain;
import gui.gestionnaire.keys.Graine;


public class ImagesTerrains {
	private static HashMap<Graine, HashMap<EvolutionTerrain, String>> images = new HashMap<>();
	
	private static ImagesTerrains instance = new ImagesTerrains();
	
	private ImagesTerrains(){};
	
	public static ImagesTerrains getInstance() {
		return instance;
	}
	
	public HashMap<Graine, HashMap<EvolutionTerrain, String>> getImages() {
		initializeImages();
		return images;
	}
	
	public void initializeImages() {
		HashMap<EvolutionTerrain, String> images0 = new HashMap<>();
		HashMap<EvolutionTerrain, String> images1 = new HashMap<>();
		HashMap<EvolutionTerrain, String> images2 = new HashMap<>();
		HashMap<EvolutionTerrain, String> images3 = new HashMap<>();
		images0.put(EvolutionTerrain.VIERGE, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"terrainVierge.png");
		images0.put(EvolutionTerrain.LABOURE, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"terrainLaboure.png");
		images0.put(EvolutionTerrain.PLANTE, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"b0.png");
		images0.put(EvolutionTerrain.PLANTE_1, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"b1.png");
		images0.put(EvolutionTerrain.PLANTE_2, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"b2.png");
		images0.put(EvolutionTerrain.PLANTE_3, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"b3.png");
		images0.put(EvolutionTerrain.PLANTE_4, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"b4.png");
		images0.put(EvolutionTerrain.PLANTE_5, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"b5.png");
		images0.put(EvolutionTerrain.POURRI, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"toDELETE.png");
		
		images1.put(EvolutionTerrain.VIERGE, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"terrainVierge.png");
		images1.put(EvolutionTerrain.LABOURE, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"terrainLaboure.png");
		images1.put(EvolutionTerrain.PLANTE, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"a0.png");
		images1.put(EvolutionTerrain.PLANTE_1, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"a1.png");
		images1.put(EvolutionTerrain.PLANTE_2, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"a2.png");
		images1.put(EvolutionTerrain.PLANTE_3, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"a3.png");
		images1.put(EvolutionTerrain.PLANTE_4, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"a4.png");
		images1.put(EvolutionTerrain.PLANTE_5, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"a5.png");
		images1.put(EvolutionTerrain.POURRI, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"toDELETE.png");
		
		images2.put(EvolutionTerrain.VIERGE, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"terrainVierge.png");
		images2.put(EvolutionTerrain.LABOURE, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"terrainLaboure.png");
		images2.put(EvolutionTerrain.PLANTE, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"e0.png");
		images2.put(EvolutionTerrain.PLANTE_1, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"e1.png");
		images2.put(EvolutionTerrain.PLANTE_2, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"e2.png");
		images2.put(EvolutionTerrain.PLANTE_3, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"e3.png");
		images2.put(EvolutionTerrain.PLANTE_4, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"e4.png");
		images2.put(EvolutionTerrain.PLANTE_5, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"e5.png");
		images2.put(EvolutionTerrain.POURRI, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"toDELETE.png");
		
		images3.put(EvolutionTerrain.VIERGE, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"terrainVierge.png");
		images3.put(EvolutionTerrain.LABOURE, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"terrainLaboure.png");
		images3.put(EvolutionTerrain.PLANTE, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"h0.png");
		images3.put(EvolutionTerrain.PLANTE_1, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"h1.png");
		images3.put(EvolutionTerrain.PLANTE_2, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"h2.png");
		images3.put(EvolutionTerrain.PLANTE_3, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"h3.png");
		images3.put(EvolutionTerrain.PLANTE_4, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"h4.png");
		images3.put(EvolutionTerrain.PLANTE_5, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"h5.png");
		images3.put(EvolutionTerrain.POURRI, GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"toDELETE.png");

		images.put(Graine.GRAINE_TOMATE, images0);
		images.put(Graine.GRAINE_CACTUS, images2);
		images.put(Graine.GRAINE_CARROTTE, images2);
		images.put(Graine.GRAINE_CHOUX_ROUGE, images2);
		images.put(Graine.GRAINE_MYRTILLE, images2);

	}
}
