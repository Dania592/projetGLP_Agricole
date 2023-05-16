package process.game;

import java.io.Serializable;

import data.configuration.GameConfiguration;
import data.map.Case;
import data.notion.basic.Element;
import data.structure.Enclos;
import data.structure.hability.ProductifPlace;
import process.production.ProductionManager;

/**
 * reponsable de la gestion des composants de la map et de la coordination des mouvement 
 * @author dania
 *
 */
public class ElementManager implements Serializable{
	private MapManager mapManager;

	public ElementManager(MapManager mapManager) {
		this.mapManager = mapManager;
	}

	/**
	 * add element to the map 
	 * @param element : the element to add 
	 */
	public void add(Element element) {	
		mapManager.addElement(element);
		if(element instanceof ProductifPlace) {
			if(!(((ProductifPlace) element).needPlayerIntervention())){
				ProductionManager.getInstance().addToProductifList((ProductifPlace)element);
			}
		}
	}

	/**
	 * ajout de l'enclos sur la map 
	 * @param enclos
	 */
	public void add(Enclos enclos) {
		mapManager.addEnclos(enclos);
		ProductionManager.getInstance().addToProductifList(enclos);
	}
	
	/**
	 * suppression d'un element de sur la map
	 * @param element
	 */
	public void remove(Element element ) {
		element.freePosition();
		mapManager.removeElement(element);
	}


	/**
	 * try to move the element to the right if it's not on right border 
	 * @param element : the element to move 
	 */
	public void moveRight(Element element) {
		if (!element.isOnRight()) {
			Case new_case = mapManager.getMap().getCase(element.getPosition().getLigne_init(),
					element.getPosition().getColonne_init() + 1);
			mapManager.moveElement(element, new_case);
		}
	}

	/**
	 * try to move the element to the left if it's not on left border
	 * @param element : the element to move 
	 */
	public void moveLeft(Element element) {
		if (!element.isOnLeft()) {
			Case new_case = mapManager.getMap().getCase(element.getPosition().getLigne_init(),
					element.getPosition().getColonne_init() - 1);
			mapManager.moveElement(element, new_case);
		}
	}
	
	/**
	 * try to move the element to the up if it's not on up border
	 * @param element
	 */
	public void moveUp(Element element) {
		if (!element.isOntop()) {
			Case new_case = mapManager.getMap().getCase(element.getPosition().getLigne_init() - 1,
					element.getPosition().getColonne_init());
			mapManager.moveElement(element, new_case);
		}
	}
	/**
	 * try to move the element down if it's not on down border
	 * @param element
	 */
	public void moveDown(Element element) {
		if (!element.isOnBottom()) {
			Case new_case = mapManager.getMap().getCase(element.getPosition().getLigne_init() + 1,
					element.getPosition().getColonne_init());
			mapManager.moveElement(element, new_case);
		}
	}

	public MapManager getMapManager() {
		return mapManager;
	}

	/**
	 * find the element that corresponds to the given position
	 * @param x : coordinate on x 
	 * @param y : coordinate on y 
	 * @return : the element that corresponds to the coordinate 
	 */
	public Element search(int x, int y) {
		int ligne = y /GameConfiguration.CASE_DIMENSION + nb_ligne_out_of_screen();
		int colone = x /GameConfiguration.CASE_DIMENSION + nb_colonne_out_of_screen();

		Case mouse = new Case(true, ligne, colone);
		Element elementMouse = mapManager.getElement(mouse);

		return elementMouse;

	}
 
	/**
	 * calcule du nombre de ligne de la map en dehors de l'ecran
	 * @return
	 */
	public int nb_ligne_out_of_screen() {
		return -mapManager.getMap().getY() / GameConfiguration.CASE_DIMENSION;
	}

	/**
	 * calcule du nombre de colonne de la map en dehors de l'ecran 
	 * @return
	 */
	public int nb_colonne_out_of_screen() {
		return -mapManager.getMap().getX() / GameConfiguration.CASE_DIMENSION;
	}

	

}
