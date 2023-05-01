package process.game;

import java.util.Date;

import data.acteur.Fermier;
import data.configuration.GameConfiguration;
import data.espece.faune.Chevre;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.flore.terrains.Terrain;
import data.gestion.RessourcesManager;
import data.map.Map;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.Puit;
import data.structure.Structure;
import data.stucture_base.Farm;
import gui.gestionnaire.keys.Graine;
import gui.gestionnaire.keys.Structures;
import data.structure.Garage;


public class GameBuilder {
	
	/**
	 * initialiser la map et son manager 
	 * @return
	 */
	public static MapManager MapBuilder() {
		Map map = Map.getInstance();
		MapManager manager = new MapManager(map);
		return manager ;
	}
	
	public static Farm buildinFarm() {
		
		ElementManager elementManager = new ElementManager(MapBuilder());
		Fermier farmer = new Fermier("Asalas",20,10,new Date(),"fermier",elementManager.getMapManager().getMap());
		
		Farm farm = new Farm( elementManager , farmer );
		farm.reservePlaceToFarm();
		
	
		// instanciation et initialisation du stock de depart 
		initialize(farm.getRessourcesManager(), elementManager.getMapManager().getMap());
		
		// positionnement du fermier et de la maison sur la map 
		sethousePosition(farm, farm.getRessourcesManager().getGestionnaireStructure().getStructures().get(Structures.MAISON).get(0));
		farm.getRessourcesManager().getGestionnaireStructure().getStructures().get(Structures.MAISON).get(0).setStatique();
		initisaliseFarmerPosition(farm, farmer);
		
		// positionnement de l'entrepot sur la map
		setEntrepotPosition(farm, farm.getRessourcesManager().getGestionnaireStructure().getStructures().get(Structures.ENTREPOT).get(0));
		farm.getRessourcesManager().getGestionnaireStructure().getStructures().get(Structures.ENTREPOT).get(0).setStatique();

		setGaragePosition(farm, farm.getRessourcesManager().getGestionnaireStructure().getStructures().get(Structures.GARAGE).get(0));
		farm.getRessourcesManager().getGestionnaireStructure().getStructures().get(Structures.GARAGE).get(0).setStatique();
		// positionnement du puit sur la map
		setPuitPosition(farm, farm.getRessourcesManager().getGestionnaireStructure().getStructures().get(Structures.PUIT).get(0));
		farm.getRessourcesManager().getGestionnaireStructure().getStructures().get(Structures.PUIT).get(0).setStatique();

		
		// positionnement du puit sur la map
		setPuitPosition(farm, farm.getRessourcesManager().getGestionnaireStructure().getStructures().get(Structures.PUIT).get(0));
		farm.getRessourcesManager().getGestionnaireStructure().getStructures().get(Structures.PUIT).get(0).setStatique();

		
		// ajout de la maison et du fermier sur la map 
		farm.getManager().add(farmer);
		farm.getManager().add(farm.getRessourcesManager().getGestionnaireStructure().getStructures().get(Structures.MAISON).get(0));
		farm.getManager().add(farm.getRessourcesManager().getGestionnaireStructure().getStructures().get(Structures.ENTREPOT).get(0));

		farm.getManager().add(farm.getRessourcesManager().getGestionnaireStructure().getStructures().get(Structures.GARAGE).get(0));
		farm.getManager().add(farm.getRessourcesManager().getGestionnaireStructure().getStructures().get(Structures.PUIT).get(0));

		return farm ;
	}
	
	
	/**
	 * initialization of the stock with the initial state of the farm
	 * @param stock : the ressources manager of the farm 
	 * @param map : the map of the farm 
	 */
	private static void initialize( RessourcesManager stock , Map map) {
			
		// initialisation du stock pour l'etat initial 
		Vache vacheInitial = new Vache(0, 0, 0,"violette1", "F", null, "v0", map);
		Vache vache1 = new Vache(0, 0, 0,"violette2", "F", null, "v1", map);
		Vache vache2 = new Vache(0, 0, 0,"violette3", "F", null, "v2", map);
		Vache vache3 = new Vache(0, 0,0,"violette4", "F", null, "v3", map);
		Vache vache4 = new Vache(0, 0, 0,"violette5", "F", null,"v4", map);

		stock.getGestionnaireAnimaux().add(vacheInitial);
		stock.getGestionnaireAnimaux().add(vache1);
		stock.getGestionnaireAnimaux().add(vache2);
		stock.getGestionnaireAnimaux().add(vache3);
		stock.getGestionnaireAnimaux().add(vache4);

		
		Mouton mouton = new Mouton(0, 0, 0, "Moumout1", null, null, "m1", map);
		Mouton mouton1 = new Mouton(0, 0, 0,"Moumout2", null, null, "m2", map);
		Mouton mouton2 = new Mouton(0, 0, 0,"Moumout3", null, null, "m3", map);
		
		stock.getGestionnaireAnimaux().add(mouton);
		stock.getGestionnaireAnimaux().add(mouton1);
		stock.getGestionnaireAnimaux().add(mouton2);
	
		Poule poule = new Poule(0, 0, null, 0, "Poupou1", null, null, "p1", map);
		Poule poule1 = new Poule(0, 0, null, 0, "Poupou2", null, null, "p2", map);
		Poule poule2 = new Poule(0, 0, null, 0, "Poupou3", null, null, "p3", map);
		
		
		stock.getGestionnaireAnimaux().add(poule);
		stock.getGestionnaireAnimaux().add(poule1);
		stock.getGestionnaireAnimaux().add(poule2);

		// sera remplacer par une instance de terrain 
		Terrain terrainInitial = new Terrain("t0",false, 0, 0,map, Graine.TOMATO_SEED);
		Terrain terrainInitial2 = new Terrain("t1",false, 0, 0,map, Graine.BROCCOLI_SEED);
		
		stock.getGestionnaireTerrains().add(terrainInitial);
		stock.getGestionnaireTerrains().add(terrainInitial2);
		
		//Terrain terrain3 = new Terrain("t3", false, 0, 0,map, Graine.AMARANTH_SEED);
		Terrain terrain4 = new Terrain("t4", false, 0, 0,map,Graine.CACTUS_SEED);
		
		//stock.getGestionnaireTerrains().add(terrain3);
		stock.getGestionnaireTerrains().add(terrain4);
		

		Chevre chevre1 = new Chevre(0,0,0,"sheshe1",null,null,"jd",map);
		Chevre chevre2 = new Chevre(0,0,0,"sheshe2",null,null,"jd2",map);
//		stock.getGestionnaireAnimaux().add(chevre2);
//		stock.getGestionnaireAnimaux().add(chevre1);
//		
		Maison maison = new Maison(0,0,"maison",map);
		Poulallier poulallierInitial = new Poulallier(0, 0, "p0", map);
		
		Enclos enclos1 = new Enclos(26, 21, "enclos1",map);
		Enclos enclos2 = new Enclos(26, 21, "enclos2",map);
		
		stock.getGestionnaireStructure().add(maison);
		stock.getGestionnaireStructure().add(poulallierInitial);
		 		
		stock.getGestionnaireEnclos().add(enclos1);
		stock.getGestionnaireEnclos().add(enclos2);
		
		Puit puit = new Puit(0,0,"puit",map);
		stock.getGestionnaireStructure().add(puit);
		
		Entrepot entrepot = new Entrepot(0,0,"entrepot", map);
		stock.getGestionnaireStructure().add(entrepot);
		
		Garage garage = new Garage(0,0,"garage", map);
		stock.getGestionnaireStructure().add(garage);
		
	}
	
	private static void sethousePosition(Farm farm , Structure maison ) {
		int colonne = farm.getColonne()+1 + (farm.getWidth()-2 - GameConfiguration.DIMENSION_STRUCUTRE)/2 ;
		int ligne = farm.getLigne()+1;
		maison.setPosition(ligne, colonne);
				
	}
	
	private static void setEntrepotPosition(Farm farm , Structure entrepot ) {
		int colonne = farm.getColonne()+2 ;
		int ligne = farm.getLigne()+1;
		entrepot.setPosition(ligne, colonne);		
	}

	private static void setGaragePosition(Farm farm , Structure garage ) {
		int colonne = farm.getColonne()+6;
		int ligne = farm.getLigne()+1;
		garage.setPosition(ligne, colonne);		
	}

	private static void setPuitPosition(Farm farm , Structure puit) {
		int colonne = farm.getColonne()+farm.getWidth()-GameConfiguration.DIMENSION_STRUCUTRE-1  ;
		int ligne = farm.getLigne()+1;
		puit.setPosition(ligne, colonne);
	}
	
	private static void initisaliseFarmerPosition(Farm farm , Fermier farmer) {
		int ligne = farm.getLigne()+ 1 + GameConfiguration.DIMENSION_STRUCUTRE;
		int colonne = farm.getColonne()-1 + farm.getWidth()/2;
		farmer.setPosition(ligne, colonne);
	}
	
	
	
	
}