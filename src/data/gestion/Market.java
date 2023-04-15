package data.gestion;

import java.util.ArrayList;
import java.util.HashMap;

import gui.gestionnaire.GestionnaireKey;
import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.Engins;
import gui.gestionnaire.keys.Graine;
import gui.gestionnaire.keys.Keys;
import gui.gestionnaire.keys.Outils;
import gui.gestionnaire.keys.Structures;

public class Market {
	private HashMap<GestionnaireKey, ArrayList<Keys>> articles = new HashMap<>();
	
	private Market() {
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
	}

}
