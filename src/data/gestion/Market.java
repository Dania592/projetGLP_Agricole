package data.gestion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.Encloss;
import gui.gestionnaire.keys.Engins;
import gui.gestionnaire.keys.GestionnaireKey;
import gui.gestionnaire.keys.Graine;
import gui.gestionnaire.keys.Keys;
import gui.gestionnaire.keys.Outils;
import gui.gestionnaire.keys.Structures;
import gui.gestionnaire.keys.Terrains;

public class Market implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private HashMap<GestionnaireKey, ArrayList<Keys>> articles ;
	
	private Market() {
		articles = new HashMap<>();
		fillMarket();
	}
	
	private static Market instance = new Market();
	
	public static Market getInstance() {
		return instance;
	}
	
	public HashMap<GestionnaireKey, ArrayList<Keys>> getArticles() {
		return articles;
	}
	
	private void fillMarket() {
		ArrayList<Keys> seeds = new ArrayList<>();
		for (Graine graine : Graine.values()) {
			seeds.add(graine);
		}
		articles.put(GestionnaireKey.SEEDS, seeds);
		
		ArrayList<Keys> animals = new ArrayList<>();
		for (Animals animal : Animals.values()) {
			animals.add(animal);
		}
		articles.put(GestionnaireKey.ANIMALS, animals);
		
		ArrayList<Keys> outils = new ArrayList<>();
		for (Outils outil : Outils.values()) {
			outils.add(outil);
		}
		articles.put(GestionnaireKey.OUTILS, outils);
		
		ArrayList<Keys> engins = new ArrayList<>();
		for (Engins engin : Engins.values()) {
			engins.add(engin);
		}
		articles.put(GestionnaireKey.ENGINS, engins);
		
		ArrayList<Keys> structures = new ArrayList<>();
		for (Structures structure : Structures.values()) {
			structures.add(structure);
		}
		articles.put(GestionnaireKey.STRUCTURES, structures);
		
		ArrayList<Keys> terrains = new ArrayList<>();
		terrains.add(Terrains.TERRAIN);
		articles.put(GestionnaireKey.TERRAINS, terrains);
		
		ArrayList<Keys> enclos = new ArrayList<>();
		enclos.add(Encloss.ENCLOS);
		articles.put(GestionnaireKey.ENCLOS, enclos);
	}

}
