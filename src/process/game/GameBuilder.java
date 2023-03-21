package process.game;

import java.util.Date;

import data.acteur.Fermier;
import data.configuration.GameConfiguration;
import data.espece.faune.Vache;
import data.flore.terrains.Terrain;
import data.flore.terrains.TypeTerrain;
import data.gestion.RessourcesManager;
import data.map.Map;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.Structure;
import data.stucture_base.Farm;


public class GameBuilder {
	
	/**
	 * initialiser la map et son manager 
	 * @return
	 */
	public static MapManager MapBuilder() {
		Map map = new Map(GameConfiguration.NB_LIGNE , GameConfiguration.NB_COLONNE , GameConfiguration.X_MAP, GameConfiguration.Y_MAP);
		MapManager manager = new MapManager(map);
		return manager ;
	}
	
	public static Farm buildinFarm() {
		
		// instanciation de l'element manager ferme et fermier 
		ElementManager elementManager = new ElementManager(MapBuilder());
		Fermier farmer = new Fermier("pierre",20,10,new Date(),"fermier",elementManager.getMapManager().getMap());
		Farm farm = new Farm( elementManager , farmer);
		farm.reservePlaceToFarm();
		
		
		// instanciation et initialisation du stock de depart 
		initialize( farm.getRessourcesManager() , elementManager.getMapManager().getMap());
		
		
		// positionnement du fermier et de la maison sur la map 
		sethousePosition(farm, farm.getRessourcesManager().getGestionnaireStructure().getStructures().get("maison"));
		farm.getRessourcesManager().getGestionnaireStructure().getStructures().get("maison").setStatique();
		initisaliseFarmerPosition(farm, farmer);
		
		
		// ajout de la maison et du fermier sur la map 
		farm.getManager().add(farmer); 
		farm.getManager().add(farm.getRessourcesManager().getGestionnaireStructure().getStructures().get("maison"));
		
		return farm ;
	}
	
	
	/**
	 * initialization of the stock with the initial state of the farm
	 * @param stock : the ressources manager of the farm 
	 * @param map : the map of the farm 
	 */
	private static void initialize( RessourcesManager stock , Map map) {
			
		// initialisation du stock pour l'etat initial 
		Vache vacheInitial = new Vache(0, 0, new Date(),"violette", "F", null, "v0", map);
		Vache vache1 = new Vache(0, 0, new Date(),"violette", "F", null, "v1", map);
		Vache vache2 = new Vache(0, 0, new Date(),"violette", "F", null, "v2", map);
		Vache vache3 = new Vache(0, 0, new Date(),"violette", "F", null, "v3", map);
		Vache vache4 = new Vache(0, 0, new Date(),"violette", "F", null, "v4", map);
		Vache vache5 = new Vache(0, 0, new Date(),"violette", "F", null, "v5", map);
		Vache vache6 = new Vache(0, 0, new Date(),"violette", "F", null, "v6", map);
		Vache vache7 = new Vache(0, 0, new Date(),"violette", "F", null, "v7", map);
		Vache vache8 = new Vache(0, 0, new Date(),"violette", "F", null, "v8", map);
		stock.getGestionnaireStocks().getGestionnaireAnimaux().put(vacheInitial.getReference(), vacheInitial);
		stock.getGestionnaireStocks().getGestionnaireAnimaux().put(vache1.getReference(), vache1);
		stock.getGestionnaireStocks().getGestionnaireAnimaux().put(vache2.getReference(), vache2);
		stock.getGestionnaireStocks().getGestionnaireAnimaux().put(vache3.getReference(), vache3);
		stock.getGestionnaireStocks().getGestionnaireAnimaux().put(vache4.getReference(), vache4);
		stock.getGestionnaireStocks().getGestionnaireAnimaux().put(vache5.getReference(), vache5);
		stock.getGestionnaireStocks().getGestionnaireAnimaux().put(vache6.getReference(), vache6);
		stock.getGestionnaireStocks().getGestionnaireAnimaux().put(vache7.getReference(), vache7);
		stock.getGestionnaireStocks().getGestionnaireAnimaux().put(vache8.getReference(), vache8);

		// sera remplacer par une instance de terrain 
		Terrain terrainInitial = new Terrain("t0",false, 0, 0,map, TypeTerrain.Pommier);
		Terrain terrainInitial2 = new Terrain("t1",false, 0, 0,map, TypeTerrain.Poivron);
		stock.getGestionnaireTerrains().getTerrains().put(terrainInitial.getReference(), terrainInitial);
		stock.getGestionnaireTerrains().getTerrains().put(terrainInitial2.getReference(), terrainInitial2);
		
		Maison maison = new Maison(0,0,"maison",map);
		Entrepot entrepotInitial = new Entrepot(0, 0, "en0", map);
		Entrepot entrepotSecond = new Entrepot(0, 0, "en1", map);
		Poulallier poulallierInitial = new Poulallier(0, 0, "p0", map);
		
		Enclos enclos1 = new Enclos(26, 21, "enclos1",map);
		Enclos enclos2 = new Enclos(26, 21, "enclos2",map);
		
		stock.getGestionnaireStructure().getStructures().put(maison.getReference(), maison);
		stock.getGestionnaireStructure().getStructures().put(entrepotInitial.getReference(), entrepotInitial);
		//stock.getGestionnaireStructure().getStructures().put(poulallierInitial.getReference(), poulallierInitial);
		stock.getGestionnaireStructure().getStructures().put(entrepotSecond.getReference(), entrepotSecond);
		 
		
		stock.getGestionnaireEnclos().getEnclos().put(enclos1.getReference(), enclos1);
		stock.getGestionnaireEnclos().getEnclos().put(enclos2.getReference(), enclos2);
		
	}
	
	private static void sethousePosition(Farm farm , Structure maison ) {
		int colonne = farm.getColonne()+1 + (farm.getDimension()-2 - GameConfiguration.DIMENSION_STRUCUTRE)/2 ;
		int ligne = farm.getLigne()+1;
		maison.setPosition(ligne, colonne);
				
	}
	
	private static void initisaliseFarmerPosition(Farm farm , Fermier farmer) {
		int ligne = farm.getLigne()+ 1 + GameConfiguration.DIMENSION_STRUCUTRE;
		int colonne = farm.getColonne()-1 + farm.getDimension()/2;
		farmer.setPosition(ligne, colonne);
	}
	
	
	
	
}