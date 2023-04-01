package gui.Farm;


import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JLayeredPane;

import data.espece.faune.Animal;
import data.flore.terrains.Terrain;
import data.structure.Enclos;
import data.structure.Structure;
import data.stucture_base.Element;
import data.stucture_base.Farm;



public class ChoixPanel extends JLayeredPane {
	
	private static final long serialVersionUID = 1L;
	private Farm farm ;
	private Board component ; 
	
	// la clé de hashMap et le simple name de la classe 
	private HashMap<String, ElementCard> cards = new HashMap<>();
	
	public ChoixPanel(Farm farm , Board component  ) {
		super();
		this.component=component;
		this.farm=farm;
		init();
	}
	
	
	public void init() {
		setLayout(new GridLayout(1, 0, 0, 1));
		setOpaque(true);
		//setLayout(null);
		setBackground( Color.gray);
		addChoixPanel();
		
	}

	/**
	 * fait un appel à l'initialisation des cartes et les ajoute au panel 
	 */
	public void addChoixPanel() {
		initialisingPanel();
		int x = 10 ;
		
		for(Element element : farm.getManager().getMapManager().getElements().values()) {
			if(cards.containsKey(element.getClass().getSimpleName())) {
				cards.get(element.getClass().getSimpleName()).removeOneElement();				
			}
		}
		
		for(ElementCard card : cards.values()) {
			card.setBounds(x,5, card.getWidth(), card.getHeight());
			card.removePositionforEmpty();
			add(card);
			x+= card.getWidth()+10;
		}
	}
	
	/**
	 * parcours tous les gestionnaires ( pour l'instant structure et stock ) et cree autant de carte que necessaire
	 * 
	 * 
	 *    ici à modifier apres adaptation des gestionnaires pour definition de méthode avec code moins redondant 
	 * 
	 * 
	 */
	public void initialisingPanel() {
		for(ArrayList<Structure> structures : farm.getRessourcesManager().getGestionnaireStructure().getStructures().values()) {
			for(Structure structure : structures) {
				if(cards.containsKey(structure.getClass().getSimpleName())) {
					cards.get(structure.getClass().getSimpleName()).addElement(structure);
				}
				else {
					ArrayList<Element> cardliste = new ArrayList<>();
					cardliste.add(structure);
					ElementCard newCard = new ElementCard(cardliste , farm , component);
					cards.put(structure.getClass().getSimpleName(), newCard);
				}			
			}		
		}
		
		for( ArrayList<Terrain> terrains : farm.getRessourcesManager().getGestionnaireTerrains().getTerrains().values()) {
			for(Terrain terrain : terrains) {
				if(cards.containsKey(terrain.getClass().getSimpleName())) {
					cards.get(terrain.getClass().getSimpleName()).addElement(terrain);
				}
				else {
					ArrayList<Element> cardliste = new ArrayList<>();
					cardliste.add(terrain);
					ElementCard newCard = new ElementCard(cardliste , farm ,component);
					cards.put(terrain.getClass().getSimpleName(), newCard);
				}
			}
		}
		
		for( ArrayList<Animal> animals : farm.getRessourcesManager().getGestionnaireAnimaux().getAnimaux().values()) {
			for(Animal animal : animals) {
				if(cards.containsKey(animal.getClass().getSimpleName())) {
					cards.get(animal.getClass().getSimpleName()).addElement(animal);
				}
				else {
					ArrayList<Element> cardliste = new ArrayList<>();
					cardliste.add(animal);
					ElementCard newCard = new ElementCard(cardliste , farm , component);
					cards.put(animal.getClass().getSimpleName(), newCard);
				}
			}
		}
		
		for( Enclos enclos : farm.getRessourcesManager().getGestionnaireEnclos().getEnclos()) {
			if(cards.containsKey(enclos.getClass().getSimpleName())) {
				cards.get(enclos.getClass().getSimpleName()).addElement(enclos);
			}
			else {
				ArrayList<Element> cardliste = new ArrayList<>();
				cardliste.add(enclos);
				ElementCard newCard = new ElementCard(cardliste , farm , component);
				cards.put(enclos.getClass().getSimpleName(), newCard);
			}
		}
		
		
	}
	
	
	
	
	
}
