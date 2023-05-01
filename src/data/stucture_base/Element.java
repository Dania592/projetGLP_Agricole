package data.stucture_base;


import java.io.Serializable;

import data.configuration.GameConfiguration;
import data.map.Case;
import data.map.Map;

/**
 * 
 * Classe abstraite mére de tous les éléments qui sont placés sur la map
 *
 */
public  abstract class Element implements Serializable {
	/**
	 * Constante necéssaire pour implementer Serializable
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Détermine si l'élément peut bouger sur la map
	 */
	private boolean statique;
	/**
	 * Position de référence par rapport à la map
	 */
	private Position position ;
	/**
	 * Nombre de cases occupées par l'élément 
	 */
	private int nbCase ;
	
	/**
	 * la reference d'un objet une fois instancier est unique 
	 */
	private String reference;
	/**
	 * Path de l'image 
	 */
	private String image = GameConfiguration.IMAGE_PATH+"terre.png";
	
	/**
	 * Constructeur abstrait necéssaire pour le polymorphisme 
	 * @param reference : réference de l'élément
	 * @param statique : possibilité de mouvement sur la map
	 * @param nbCase : nombre de casses occupées 
	 */
	public Element(String reference ,boolean statique, int nbCase ) {
		this.statique = statique;
		this.nbCase=nbCase;
		position = new Position(nbCase , 0 , 0 , Map.getInstance() );
		this.reference = reference  ;
		
	}
	
	/**
	 *  
	 * @return : la référence de l'objet 
	 */
	public String getReference() {
		return reference ;
	}
	/**
	 * le nombre de cases occupées par l'objet
	 * @return
	 */
	public int getNbCase() {
		return nbCase;
	}

	/**
	 * 
	 * @return possibilité de mouvement sur la map
	 */
	public boolean isStatique() {
		return statique;
	}

	/**
	 * Modification de mouvement de l'objet
	 * @param statique 
	 */
	public void setStatique(boolean statique) {
		this.statique = statique;
	}

 	/**
 	 * retourne la position sur la map
 	 * @return position
 	 */
	public Position getPosition() {
		return position ;
	}
	
	/**
	 * modifie la position
	 * @param new_ligne : nouvelle ligne initiale
	 * @param new_colonne : nouvelle colonne initiale 
	 */
	public void setPosition(int new_ligne , int new_colonne) {
		
		position.setTabCase(new_ligne, new_colonne);
	}
	
	
	/**
	 * Sur le bord supperieur  de la map
	 * @return
	 */
	  public boolean isOntop() { 
		  return position.getLigne_init()== 0;
	  }
	  
	  /**
	   * Tout à gauche de la map
	   * @return
	   */
	  public boolean isOnLeft() {
		  return position.getColonne_init()==0;
	  }
	  /**
	   * Tout à droite de la map 
	   * @return
	   */
	  public boolean isOnRight() { 	  
		  return (position.getColonne_init()+ position.getNbColonne()) == GameConfiguration.NB_COLONNE  ; 
		 
	  }
	  /**
	   * Tout en bas de la map
	   * @return
	   */
	  public boolean isOnBottom() { 
		 return (position.getLigne_init()+position.getNbLigne()) == GameConfiguration.NB_LIGNE -1; 
	 }
	  
	  /**
	   * Sur un des bords de la map
	   * @return
	   */
	  public boolean isOnBorder() {
		  return isOnBottom() || isOnLeft() || isOnRight() || isOntop() ;
	  }
	  
	  /**
	   * retourne le path de l'image de l'objet	
	   * @return
	   */
	public String getImage() {
		return image;
	}

	/**
	 * modifie le path de l'image de l'objet
	 * @param image
	 */
	public void setImage( String image) {
		this.image = image;
	}
	

	/**
	 * libére toutes les cases occupées par l'élément 
	 */
	public void freePosition() { 
		Case[][] cases = position.getTabCase(); 
	  for(int indexligne =0 ;indexligne < position.getNbLigne() ;indexligne++) { 
		  for(int indexColone=0 ; indexColone < position.getNbColonne(); indexColone++ ) {
			  cases[indexligne][indexColone].setLibre(true); 
			  } 
		  } 
	}
	
		
}
