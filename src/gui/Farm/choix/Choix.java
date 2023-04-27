package gui.Farm.choix;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import data.espece.faune.Animal;
import data.flore.terrains.Terrain;
import data.gestion.RessourcesManager;
import data.structure.Enclos;
import data.structure.Structure;
import data.stucture_base.Element;
import data.stucture_base.Farm;
import gui.Farm.Board;

public class Choix implements Serializable {

	private RessourcesManager resources ;
	private HashMap<String, ElementCard> cards = new HashMap<>();
	private Farm farm ;
	private Board component ; 

	public Choix(Farm farm  , Board component) {
		this.farm=farm;
		resources= farm.getRessourcesManager();
		this.component=component;
		init();	
	}
	
	public void init() {
		// parcours des structures 
		for(ArrayList<Structure> structures : resources.getGestionnaireStructure().getStructures().values()) {
			ArrayList<Element> cardliste = new ArrayList<>();
			for(Structure structure : structures) {
				if(!structure.isStatique()) {
					cardliste.add(structure);					
				}
			}
			if(cardliste.size()>0) {
				ElementCard newCard = new ElementCard(cardliste, farm, component);
				cards.put(cardliste.get(0).getClass().getSimpleName(), newCard);
			}
		}
		
		// parcours des terrains
		ArrayList<Element> cardlisteT = new ArrayList<>();
		for( ArrayList<Terrain> terrains : farm.getRessourcesManager().getGestionnaireTerrains().getTerrains().values()) {	
			for(Terrain terrain : terrains) {
				if(!terrain.isStatique()) {					
					cardlisteT.add(terrain);
				}
			}			
		}
		if(cardlisteT.size()>0) {
			ElementCard newCard = new ElementCard(cardlisteT, farm, component);
			cards.put(cardlisteT.get(0).getClass().getSimpleName() , newCard);					
		}
		
		// parcours des animaux 
		for( ArrayList<Animal> animals : farm.getRessourcesManager().getGestionnaireAnimaux().getAnimaux().values()) {
			ArrayList<Element> cardliste = new ArrayList<>();
			for(Animal animal : animals) {
				if(!animal.isStatique()){					
					cardliste.add(animal);
				}
			}
			if(cardliste.size()>0) {
				ElementCard newCard = new ElementCard(cardliste, farm, component);
				cards.put(cardliste.get(0).getClass().getSimpleName(), newCard);					
			}	
		}
		
		// parcours des enclos
		ArrayList<Element> cardliste = new ArrayList<>();
		for( Enclos enclos : farm.getRessourcesManager().getGestionnaireEnclos().getEnclos()) {
			if(!enclos.isStatique()) {
				cardliste.add(enclos);				
			}
		}	
		if(cardliste.size()>0) {
			ElementCard newCard = new ElementCard(cardliste, farm, component);
			cards.put(cardliste.get(0).getClass().getSimpleName(), newCard);					
		}	

			//cards.get("Maison").removeOneElement(cards.get("Maison").getElements().get(0));
	}
	
	
	
	public HashMap<String, ElementCard> getCards() {
		return cards;
	}

	public void removeElement(Element element ) {
		if(cards.containsKey(element.getClass().getSimpleName())) {
			cards.get(element.getClass().getSimpleName()).removeOneElement(element);
		}
	}

	public void addElement(Element element) {
		if(cards.containsKey(element.getClass().getSimpleName())) {
			cards.get(element.getClass().getSimpleName()).addElement(element);
		}
		else {
			ArrayList<Element> cardliste = new ArrayList<>();
			cardliste.add(element);
			ElementCard newCard = new ElementCard(cardliste , farm , component);
			cards.put(element.getClass().getSimpleName(), newCard);
		}
	}
	

}