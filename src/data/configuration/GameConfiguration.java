package data.configuration;

import java.io.File;

/**
 * toutes les constantes du jeu 
 */
public abstract class GameConfiguration {
	
	// constructeur privare 
	public static final int WINDOW_WIDTH = 1000;
	
	public static final int WINDOW_HEIGHT = 800;
	
	public final static int CASE_DIMENSION = 32;
	
	public final static int NB_LIGNE = 60;
	
	public final static int NB_COLONNE = 60;
	
	public final static int GAME_SPEED = 100;
	
	public final static int X_MAP = -15*CASE_DIMENSION ;
	
	public final static int Y_MAP = -18*CASE_DIMENSION ;
	
	public final static int X_ADD_LABEL = WINDOW_WIDTH-100;

	public final static int y_ADD_LABEL = WINDOW_HEIGHT-180;
	
	public final static int X_HOME_LABEL = WINDOW_WIDTH-100;
	
	public final static int Y_HOME_LABEL = WINDOW_HEIGHT-110;
	
	public final static int HEIGHT_LABEL = 80 ;
	
	public final static int WIDHT_LABEL = 60 ;
	
	public final static int DIMENSION_STRUCUTRE = 4; 
	
	public final static String IMAGE_PATH = "src"+File.separator+"ressources"+File.separator;
	
	public final static int ANIMAL_MOUVE_SPEED = 5 ; // selon le thread 
	
	public final static int FREQUENCE_DECREMENTATION_ENCLOS = 5 ; // 1 animal => 10 min 
	
	public final static String FILE_NAME_SAVE = "saved_farm.ser";
	

}
