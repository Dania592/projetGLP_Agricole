package process.game;

import java.util.HashMap;

import java.util.NoSuchElementException;


import data.configuration.GameConfiguration;
import data.map.Case;
import data.map.Map;
import data.stucture_base.Element;



/**
 * responsable de la disposition des elements sur la map (pas de mouvement)
 * @author dania
 *
 */
public class MapManager {

	private Map map ; 
	private HashMap<String, Element> composants ;
	
	
	public MapManager(Map map ) {
		this.map=map ;
		composants = new HashMap<>();
	}
	
	/**
	 * verifie que toutes les cases qu'on veut affecter au nouvelle objet sont libre puis ajoute l'objet 
	 * @param element l'element à placer
	 */
	public void addElement(Element element ) {
		
		Case case_init= new Case(false,element.getPosition().getLigne_init(),element.getPosition().getColonne_init());
		if(verificationLiberte(element,case_init )) {
			composants.put(element.getReference(), element );
			reserve(element);			
		}
		else {
			System.out.println("case non dispo");
		}
	}
	
	
	public void removeElement(Element element) {
		if(composants.containsKey(element.getReference())) {
			composants.remove(element.getReference());
		}
	}
	
	/**
	 * change l'etat libre de la case en false pour la reserver 
	 * @param element
	 */
	public void reserve(Element element ) {
		Case[][] cases = element.getPosition().getTabCase();
		for(int indexLignePosition = 0 ; indexLignePosition< element.getPosition().getNbLigne() ; indexLignePosition ++) {
			for(int indexColonnePosition = 0 ; indexColonnePosition < element.getPosition().getNbColonne() ; indexColonnePosition ++) {
				Case el = cases[indexLignePosition][indexColonnePosition];
				map.getCase(el.getLigne(), el.getColonne()).setLibre(false);				
			}
		}
	}
	
	/**
	 * responsable du mouvement des element mobiles (fermier , animaux ...)
	 * @param element
	 * @param new_case
	 */
	public void moveElement(Element element , Case new_case ) {
		try {
		
			Element el = get(element.getReference());
			
			el.freePosition();
			if(verificationLiberte(element, new_case)) {	
				el.setPosition(new_case.getLigne(), new_case.getColonne());		
			}
			reserve(element);
			
			
		}catch(NoSuchElementException e){
			System.err.println(e.getMessage());	
		}			
	}
	
	/**
	 * verifie que la nouvelle position que l'element veut occuper libre 
	 * @param element : l'element à placer
	 * @param init : la case initial d'où commence la position 
	 * @return vrai si position libre faux sinon
	 */
	public boolean verificationLiberte(Element element , Case init) {
		
		int lignes = element.getPosition().getNbLigne();
		int colonnes = element.getPosition().getNbColonne();
		
		for(int indexl = init.getLigne() ; indexl < init.getLigne() + lignes ; indexl ++) {
			for(int indexc = init.getColonne() ; indexc <  init.getColonne() + colonnes ; indexc ++) {
				if(!map.getCase(indexl, indexc).isLibre()) {
					return false ;
				}
			}
		}
		
		return true ;	
	}

	/**
	 * recuperer un element dans la map (il faut verfier que equals est bien redefini dans la classe de l'element )
	 * @param element
	 * @return
	 * @throws NoSuchElementException
	 */
	public Element get(String element) throws NoSuchElementException{
		if(composants.containsKey(element)) {
			return composants.get(element);
		}
		throw new NoSuchElementException("l'element à deplacer n'existe pas");
	}
	
	public Map getMap() {
		return map ;
	}
	
	public HashMap<String, Element> getElements(){
		return composants;
	}

	public Element getElement(Case block) {
		for(Element element : composants.values()) {
			if(element.getPosition().contains(block)) {
				return element ;
			}
		}
		return null ;
	}
	
	public void movingMap(int dx , int dy ) {
		int xmap = map.getX();
		
		if((xmap +dx <= 0) && (xmap+ map.getNbColones()*GameConfiguration.CASE_DIMENSION +dx)>= GameConfiguration.WINDOW_WIDTH ) {
			map.setX(xmap + decalage(dx));
		}
		int ymap = map.getY();
		if(ymap +dy <=0 && (ymap +map.getNbLignes()*GameConfiguration.CASE_DIMENSION +dy )>= GameConfiguration.WINDOW_HEIGHT) {
			map.setY(ymap + decalage(dy) );			
		}
		
	}
	
	public int decalage(int dx) {
		return dx - dx%GameConfiguration.CASE_DIMENSION;
	}
	
}
