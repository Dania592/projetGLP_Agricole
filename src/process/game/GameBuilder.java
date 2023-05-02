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
import data.structure.Garage;
import data.structure.Maison;
import data.structure.Puit;
import data.structure.Structure;
import data.stucture_base.Farm;
import gui.gestionnaire.keys.Graine;
import gui.gestionnaire.keys.Structures;


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
		Fermier farmer = new Fermier("Asalas",new Date(),"fermier");
		
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
		Vache vacheInitial = new Vache(0, "violette", "F", null, "v0");
		stock.getGestionnaireAnimaux().add(vacheInitial);

		Mouton mouton = new Mouton(0, "Moumout1", null, null, "m1");
		stock.getGestionnaireAnimaux().add(mouton);
	
		Poule poule = new Poule(0, "Poupou1", null, null, "p1");
		stock.getGestionnaireAnimaux().add(poule);

		Chevre chevre1 = new Chevre(0,"sheshe1",null,null,"jd");
		stock.getGestionnaireAnimaux().add(chevre1);


		Terrain terrainInitial = new Terrain("t0",false, Graine.TOMATO_SEED);
		stock.getGestionnaireTerrains().add(terrainInitial);

		
		Maison maison = new Maison("maison");
		stock.getGestionnaireStructure().add(maison);
	

		Enclos enclos1 = new Enclos("enclos1");
		stock.getGestionnaireEnclos().add(enclos1);

		 			
		Puit puit = new Puit("puit");
		stock.getGestionnaireStructure().add(puit);


		Garage garage = new Garage("garage");
		stock.getGestionnaireStructure().add(garage);
	
		Entrepot entrepot = new Entrepot("entrepot");
		stock.getGestionnaireStructure().add(entrepot);		
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