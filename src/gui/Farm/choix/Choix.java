package gui.Farm.choix;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import data.espece.faune.Animal;
import data.espece.flore.terrains.Terrain;
import data.gestion.RessourcesManager;
import data.notion.basic.Element;
import data.notion.basic.Farm;
import data.structure.Enclos;
import data.structure.Structure;
import gui.Farm.Board;
/**
 * Class qui regroupe tous les éléments pouvant être placé dans la map 
 *
 */
public class Choix implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ressources manager qui regroupe tous les gestionnaires 
	 */
	private RessourcesManager resources ;
	/**
	 * cartes des differents elements à placer
	 */
	private HashMap<String, ElementCard> cards = new HashMap<>();
	/**
	 * ferme du jeu 
	 */
	private Farm farm ;
	/**
	 * panel principal qui accueil la map principal
	 */
	private Board component ; 
/**
 * contructeur des choix 
 * @param farm : ferme du jeu
 * @param component : panel principal 
 */
	public Choix(Farm farm  , Board component) {
		this.farm=farm;
		resources= farm.getRessourcesManager();
		this.component=component;
		init();	
	}
	
	/**
	 * initialisation des choix en parcourant les gestionnaires  
	 */
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

	}
	
	
	/**
	 * retourne les cartes de tous les éléments 
	 * @return
	 */
	public HashMap<String, ElementCard> getCards() {
		return cards;
	}

	/**
	 * retire un élément des cartes 
	 * @param element : élément à retirer
	 */
	public void removeElement(Element element ) {
		if(cards.containsKey(element.getClass().getSimpleName())) {
			cards.get(element.getClass().getSimpleName()).removeOneElement(element);
		}
	}
	/**
	 * ajouter un élément au panel de choix 
	 * @param element : élément à ajouter 
	 */
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
