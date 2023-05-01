package gui.Farm.actions;

import java.util.ArrayList;

import gui.Farm.Hud;
import gui.gestionnaire.GeneralPaintStrategy;
import gui.gestionnaire.RoundedPanel;
import process.action.task.Task;

/**
 * 
 * Panel qui accueil les actions pouvant être affectuer 
 *
 */
public class ActionsPane extends RoundedPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * liste des taches à afficher 
	 */
	private ArrayList<Task<?>> taches = new ArrayList<>();
	/**
	 * abscisse pour positionner sur l'écran
	 */
	private int x ;
	/**
	 * ordonné pour positionner sur l'écran
	 */
	private int y ;
	/**
	 * hud associé au jeu 
	 */
	private Hud hud ;
	/**
	 * constructeur du panel 
	 * @param taches : liste des actions à afficher 
	 * @param x : coordonnée sur la map 
	 * @param y : coordonnée sur la map 
	 * @param hud : le hud du jeu 
	 */
	public ActionsPane(ArrayList<Task<?>> taches , int x , int y , Hud hud ) {
		super(null, 30, GeneralPaintStrategy.MEDIUM_BROWN);
		this.taches=taches;
		this.hud = hud;
		this.x = x;
		this.y = y;	
		init();
		
	}
	
	/**
	 * initilise le panel en ajoutant tout les panel des differents taches 
	 * @see TachePane
	 */
	public void init() {
		// soit faire un scrol ou voir comment adapter selon le nombre de taches 
		int height = 60*taches.size();
		
		setBounds(x,y,200,height);
		int x = 5 ;
		int y = 5 ;
		for(Task<?> tache : taches ) {
			TachePane pane = new TachePane(tache , hud );
			pane.setBounds(x,y,190,50);
			y+=55;
			add(pane);
		}
		
	}
}
