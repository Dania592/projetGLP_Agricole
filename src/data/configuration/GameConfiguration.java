package data.configuration;

import java.io.File;

import data.espece.Produceur;
import data.stucture_base.Farm;

/**
 * toutes les constantes du jeu 
 */
public abstract class GameConfiguration {
	
	// constructeur privare 
	public static final int WINDOW_WIDTH = GameConfiguration.CASE_DIMENSION*48;
	
	public static final int WINDOW_HEIGHT = 800;
	
	public final static int CASE_DIMENSION = 32;
	
	public final static int NB_LIGNE = 60;
	
	public final static int NB_COLONNE = 60;
	
	public final static int GAME_SPEED = 50;
	
	public final static int TIME_SPEED = 50 ;
	
	public final static int X_MAP = 0;
	
	public final static int Y_MAP = 0 ;
	
	public final static int X_ADD_LABEL = WINDOW_WIDTH-70;

	public final static int y_ADD_LABEL = WINDOW_HEIGHT-140;
	
	public final static int X_HOME_LABEL = WINDOW_WIDTH-70;
	
	public final static int Y_HOME_LABEL = WINDOW_HEIGHT-70;
	
	public final static int HEIGHT_LABEL = 60 ;
	
	public final static int WIDHT_LABEL = 60 ;
	
	public final static int X_EXTEND_LABEL = 15;
	
	public final static int Y_EXTEND_LABEL = 20 + HEIGHT_LABEL;
	
	public final static int DIMENSION_STRUCUTRE = 4; 
	
	public final static String SEASON_IMAGE_PATH = "src"+File.separator+"ressources"+File.separator+Farm.saisonActuelle+File.separator;
	public final static String IMAGE_PATH = "src"+File.separator+"ressources"+File.separator;
	
	public final static int ANIMAL_MOUVE_SPEED = 5 ; // selon le thread 
	
	public final static int FREQUENCE_DECREMENTATION_ENCLOS_NOURRITURE = 30 ; // 1 animal => 25 min (60)
	
	public final static int FREQUENCE_DECREMENTATION_ENCLOS_EAU = 35 ; // 1 animal => 15 min (20)
	
	public final static int FREQUENCE_ANIMAL_BIRTH_ENCLOS = 5;
	
	public final static String FILE_NAME_SAVE = "saved_farm.ser";
	
	public final static Integer START_WATER_LEVEL = 25;
	
	public final static int NB_CARD_CHOIX = 5 ;
	
	public final static int FREQUENCE_CATASTROPHE = 10;

}
