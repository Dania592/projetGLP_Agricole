package data.flore.terrains;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

import data.configuration.GameConfiguration;

public class ImagesTerrains {
	private static HashMap<TypeTerrain, HashMap<EvolutionTerrain, BufferedImage>> images = new HashMap<>();
	
	private static ImagesTerrains instance = new ImagesTerrains();
	
	private ImagesTerrains(){};
	
	public static ImagesTerrains getInstance() {
		return instance;
	}
	
	public HashMap<TypeTerrain, HashMap<EvolutionTerrain, BufferedImage>> getImages() {
		initializeImages();
		return images;
	}
	
	public void initializeImages() {
		HashMap<EvolutionTerrain, BufferedImage> images0 = new HashMap<>();
		HashMap<EvolutionTerrain, BufferedImage> images1 = new HashMap<>();
		HashMap<EvolutionTerrain, BufferedImage> images2 = new HashMap<>();
		HashMap<EvolutionTerrain, BufferedImage> images3 = new HashMap<>();
		try {
			images0.put(EvolutionTerrain.VIERGE, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"terrainVierge.png")));
			images0.put(EvolutionTerrain.LABOURE, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"terrainLaboure.png")));
			images0.put(EvolutionTerrain.PLANTE, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"b0.png")));
			images0.put(EvolutionTerrain.PLANTE_1, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"b1.png")));
			images0.put(EvolutionTerrain.PLANTE_2, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"b2.png")));
			images0.put(EvolutionTerrain.PLANTE_3, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"b3.png")));
			images0.put(EvolutionTerrain.PLANTE_4, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"b4.png")));
			images0.put(EvolutionTerrain.PLANTE_5, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"b5.png")));
			//images0.put(EvolutionTerrain.PLANTE_7, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"b7.png")));
			
			images1.put(EvolutionTerrain.VIERGE, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"terrainVierge.png")));
			images1.put(EvolutionTerrain.LABOURE, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"terrainLaboure.png")));
			images1.put(EvolutionTerrain.PLANTE, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"a0.png")));
			images1.put(EvolutionTerrain.PLANTE_1, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"a1.png")));
			images1.put(EvolutionTerrain.PLANTE_2, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"a2.png")));
			images1.put(EvolutionTerrain.PLANTE_3, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"a3.png")));
			images1.put(EvolutionTerrain.PLANTE_4, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"a4.png")));
			images1.put(EvolutionTerrain.PLANTE_5, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"a5.png")));
			
			
			images2.put(EvolutionTerrain.VIERGE, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"terrainVierge.png")));
			images2.put(EvolutionTerrain.LABOURE, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"terrainLaboure.png")));
			images2.put(EvolutionTerrain.PLANTE, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"e0.png")));
			images2.put(EvolutionTerrain.PLANTE_1, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"e1.png")));
			images2.put(EvolutionTerrain.PLANTE_2, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"e2.png")));
			images2.put(EvolutionTerrain.PLANTE_3, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"e3.png")));
			images2.put(EvolutionTerrain.PLANTE_4, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"e4.png")));
			images2.put(EvolutionTerrain.PLANTE_5, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"e5.png")));
			//images0.put(EvolutionTerrain.PLANTE_7, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"b7.png")));
			
			images3.put(EvolutionTerrain.VIERGE, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"terrainVierge.png")));
			images3.put(EvolutionTerrain.LABOURE, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"terrainLaboure.png")));
			images3.put(EvolutionTerrain.PLANTE, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"h0.png")));
			images3.put(EvolutionTerrain.PLANTE_1, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"h1.png")));
			images3.put(EvolutionTerrain.PLANTE_2, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"h2.png")));
			images3.put(EvolutionTerrain.PLANTE_3, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"h3.png")));
			images3.put(EvolutionTerrain.PLANTE_4, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"h4.png")));
			images3.put(EvolutionTerrain.PLANTE_5, ImageIO.read(new File(GameConfiguration.IMAGE_PATH+"Terrain"+File.separator+"h5.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		images.put(TypeTerrain.Pommier, images0);
		images.put(TypeTerrain.Poivron, images1);
		images.put(TypeTerrain.Choux, images3);
		images.put(TypeTerrain.Fraise, images2);
	}
	
}
