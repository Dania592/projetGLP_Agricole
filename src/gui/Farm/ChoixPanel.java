package gui.Farm;


import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JLayeredPane;

import data.espece.faune.Animal;
import data.flore.Culture;
import data.flore.terrains.Terrain;
import data.structure.Structure;
import data.stucture_base.Element;
import data.stucture_base.Farm;



public class ChoixPanel extends JLayeredPane {
	
	private static final long serialVersionUID = 1L;
	private Farm farm ;
	private Element selected ;
	
	// la clé de hashMap et le simple name de la classe 
	private HashMap<String, ElementCard> cards = new HashMap<>();
	
	public ChoixPanel(Farm farm ,Element selected  ) {
		super();
		this.selected = selected;
		this.farm=farm;
		init();
	}
	
	
	public void init() {
		//setLayout(new GridLayout(1, 0, 0, 1));
		setOpaque(true);
		setLayout(null);
		setBackground( Color.gray);
		
		addChoixPanel();
		
	}

	/**
	 * fait un appel à l'initialisation des cartes et les ajoute au panel 
	 */
	public void addChoixPanel() {
		initialisingPanel();
		int x = 10 ;
		for(ElementCard card : cards.values()) {
			card.setBounds(x,5, card.getWidth(), card.getHeight());
			add(card);
			x+= card.getWidth()+10;
		}
	}
	
	/**
	 * parcours tous les gestionnaires ( pour l'instant structure et stock ) et cree autant de carte que necessaire
	 */
	public void initialisingPanel() {
		for(Structure structure : farm.getRessourcesManager().getGestionnaireStructure().getStructures().values()) {
			if(cards.containsKey(structure.getClass().getSimpleName())) {
				cards.get(structure.getClass().getSimpleName()).addElement(structure);
			}
			else {
				ArrayList<Element> cardliste = new ArrayList<>();
				cardliste.add(structure);
				ElementCard newCard = new ElementCard(cardliste , farm , selected);
				cards.put(structure.getClass().getSimpleName(), newCard);
			}
		}
		
		for( Terrain terrain : farm.getRessourcesManager().getGestionnaireTerrains().getTerrains().values()) {
			if(cards.containsKey(terrain.getClass().getSimpleName())) {
				cards.get(terrain.getClass().getSimpleName()).addElement(terrain);
			}
			else {
				ArrayList<Element> cardliste = new ArrayList<>();
				cardliste.add(terrain);
				ElementCard newCard = new ElementCard(cardliste , farm , selected);
				cards.put(terrain.getClass().getSimpleName(), newCard);
			}
		}
		
		for( Animal animal : farm.getRessourcesManager().getGestionnaireStocks().getGestionnaireAnimaux().values()) {
			if(cards.containsKey(animal.getClass().getSimpleName())) {
				cards.get(animal.getClass().getSimpleName()).addElement(animal);
			}
			else {
				ArrayList<Element> cardliste = new ArrayList<>();
				cardliste.add(animal);
				ElementCard newCard = new ElementCard(cardliste , farm , selected);
				cards.put(animal.getClass().getSimpleName(), newCard);
			}
		}
	}
	
	
	
//	public ImageIcon getMiniIcon(Element element) {
//		switch (element.getReference()) {
//		case "ma0":
//			return new ImageIcon("src"+File.separator+"ressources"+File.separator+"minitracteur.png");
//		case "et0":
//			return new ImageIcon("src"+File.separator+"ressources"+File.separator+"minietable.png");
//		case "po0" :
//			return new ImageIcon("src"+File.separator+"ressources"+File.separator+"minicamion.png");
//		case "en0" :
//			return new ImageIcon("src"+File.separator+"ressources"+File.separator+"minimoulin.png");
//			
//		case "ma1" :
//			return new ImageIcon("src"+File.separator+"ressources"+File.separator+"minimaison.png");
//			
//		default :
//				return null ;
//		}
//		
//		
//	}
//	
//	private class MouseControls implements MouseListener{
//
//		@Override
//		public void mouseClicked(MouseEvent e) {
//			int x = e.getX();
//			if(x>100 && x<200) {
//				selected = gestionnaire.getStructures().get("ma0");
//				
//			}
//			else {
//				if(x>250 && x<350) {
//					selected = gestionnaire.getStructures().get("et0");
//					
//				}
//				else {
//					if(x>400 && x<500) {
//						selected = gestionnaire.getStructures().get("po0");	
//						
//					}
//					else {
//						if(x>550 && x< 650) {
//							selected = gestionnaire.getStructures().get("en0");
//						
//						}
//						else {
//							if(x>700) {
//								selected = gestionnaire.getStructures().get("ma1");
//							
//							}
//						}
//					}
//				}
//			}
//			randomPosition();
//			
//		}
//
//		@Override
//		public void mousePressed(MouseEvent e) {
//			
//				
//		}
//
//		@Override
//		public void mouseReleased(MouseEvent e) {
//		
//			
//		}
//
//		@Override
//		public void mouseEntered(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void mouseExited(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//	}
//	

	
	
	
	
}
