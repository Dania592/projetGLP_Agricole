package gui.Farm.actions;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLayeredPane;

import gui.Farm.Hud;
import process.action.task.Task;

public class ActionsPane extends JLayeredPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Task<?>> taches = new ArrayList<>();
	private int x ;
	private int y ;
	private Hud hud ;
	
	public ActionsPane(ArrayList<Task<?>> taches , int x , int y , Hud hud ) {
		this.taches=taches;
		this.hud = hud;
		this.x = x;
		this.y = y;	
		init();
	}
	
	public void init() {
		setLayout(new GridLayout(0,1,1,0));
		// soit faire un scrol ou voir comment adapter selon le nombre de taches 
		int height = 50*taches.size();
		setBounds(x,y,200,height);
		for(Task<?> tache : taches ) {
			TachePane pane = new TachePane(tache , hud );
			add(pane);
		}
		
	}
}
