package data.configuration;

import java.io.File;

import data.espece.Produceur;
import data.stucture_base.Farm;

/**
 * Class regroupant toute les constante fonctionnels du programme 
 */
public abstract class GameConfiguration {
	
	/**
	 * largeur de la frame principale 
	 */
	public static final int WINDOW_WIDTH = GameConfiguration.CASE_DIMENSION*48;
	
	/**
	 * hauteur de la frame principale 
	 */
	public static final int WINDOW_HEIGHT = 800;
	
	/**
	 * dimension de la case de la map 
	 */	
	public final static int CASE_DIMENSION = 32;
	
	/**
	 * nombre de ligne de la map
	 */
	public final static int NB_LIGNE = 60;
	
	/**
	 * nombre de colonne de la map 	
	 */
	public final static int NB_COLONNE = 60;
	/**
	 * vitesse du jeu 
	 */
	public final static int GAME_SPEED = 100;
	
	public final static int TIME_SPEED = 100 ;
	
	/**
	 * abscisse de la map par rapport à l'écran 
	 */
	public final static int X_MAP = 0;
	/**
	 * ordonné de la map par rapport à l'écran 
	 */
	public final static int Y_MAP = 0 ;
	/**
	 * position des labels sur la frame principale 
	 */
	public final static int X_ADD_LABEL = WINDOW_WIDTH-70;
	/**
	 * position des labels sur la frame principale 
	 */
	public final static int y_ADD_LABEL = WINDOW_HEIGHT-140;
	/**
	 * position des labels sur la frame principale 
	 */
	public final static int X_HOME_LABEL = WINDOW_WIDTH-70;
	/**
	 * position des labels sur la frame principale 
	 */
	public final static int Y_HOME_LABEL = WINDOW_HEIGHT-70;
	/**
	 * hauteur des labels 
	 */
	public final static int HEIGHT_LABEL = 60 ;
	/**
	 * largeur des labels 
	 */
	public final static int WIDHT_LABEL = 60 ;
	/**
	 * abscisse pour étendre la ferme 
	 */
	public final static int X_EXTEND_LABEL = 15;
	/**
	 * ordonné pour étendre la ferme 
	 */
	public final static int Y_EXTEND_LABEL = 20 + HEIGHT_LABEL;
	/**
	 * dimension des structures 
	 */
	public final static int DIMENSION_STRUCUTRE = 4; 
	
	public final static String IMAGE_PATH = "src"+File.separator+"ressources"+File.separator;
	/**
	 * vitesse de mouvement des animaux 
	 */
	public final static int ANIMAL_MOUVE_SPEED = 5 ; // selon le thread 
	
	public final static int FREQUENCE_DECREMENTATION_ENCLOS_NOURRITURE = 5 ; // 1 animal => 25 min (60)
	
	public final static int FREQUENCE_DECREMENTATION_ENCLOS_EAU = 5 ; // 1 animal => 15 min (20)
	
	public final static int FREQUENCE_ANIMAL_BIRTH_ENCLOS = 5;
	/*
	 * path vers la ferme sauvegardée 
	 */
	public final static String FILE_NAME_SAVE = "saved_farm.ser";
	
	/**
	 * nombre de carte à afficher sur le panel de choix 
	 */
	public final static int NB_CARD_CHOIX = 5 ;
	/**
	 * fréquence d'apparition des catastrophes 
	 */
	public final static int FREQUENCE_CATASTROPHE = 10;

}
