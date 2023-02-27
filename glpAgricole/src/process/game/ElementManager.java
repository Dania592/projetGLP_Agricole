package process.game;

import data.configuration.GameConfiguration;
import data.map.Case;
import data.stucture_base.Element;

/**
 * reponsable de la gestion des composants de la map et de la coordination des mouvement 
 * @author dania
 *
 */
public class ElementManager {
	private MapManager mapManager;

	public ElementManager(MapManager mapManager) {
		this.mapManager = mapManager;
	}

	/**
	 * ajoute un élément à la map 
	 * @param element
	 */
	
	public void add(Element element) {	
		mapManager.addElement(element);
	}

	/**
	 * verifie si l'element peut avancer vers la droite et modifie sa position 
	 * @param element : l'element à deplacer 
	 */
	public void moveRight(Element element) {
		if (!element.isOnRight()) {
			Case new_case = mapManager.getMap().getCase(element.getPosition().getLigne_init(),
					element.getPosition().getColonne_init() + 1);
			mapManager.moveElement(element, new_case);
		}
	}

	public void moveLeft(Element element) {
		if (!element.isOnLeft()) {
			Case new_case = mapManager.getMap().getCase(element.getPosition().getLigne_init(),
					element.getPosition().getColonne_init() - 1);
			mapManager.moveElement(element, new_case);
		}
	}
	

	public void moveUp(Element element) {
		if (!element.isOntop()) {
			Case new_case = mapManager.getMap().getCase(element.getPosition().getLigne_init() - 1,
					element.getPosition().getColonne_init());
			mapManager.moveElement(element, new_case);
		}
	}

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

	public Element search(int x, int y) {
		int ligne = y /GameConfiguration.CASE_DIMENSION + nb_ligne_out_of_screen();
		int colone = x /GameConfiguration.CASE_DIMENSION + nb_colonne_out_of_screen();

		Case mouse = new Case(true, ligne, colone);
		Element elementMouse = mapManager.getElement(mouse);

		return elementMouse;

	}

	public int nb_ligne_out_of_screen() {
		return -mapManager.getMap().getY() / GameConfiguration.CASE_DIMENSION;
	}

	public int nb_colonne_out_of_screen() {
		return -mapManager.getMap().getX() / GameConfiguration.CASE_DIMENSION;
	}

}
