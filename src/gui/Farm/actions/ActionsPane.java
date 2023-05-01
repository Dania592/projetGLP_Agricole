package gui.Farm.actions;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JLayeredPane;

import gui.Farm.Hud;
import gui.gestionnaire.GeneralPaintStrategy;
import gui.gestionnaire.RoundedPanel;
import process.action.task.Task;

public class ActionsPane extends RoundedPanel {


	private static final long serialVersionUID = 1L;
	private ArrayList<Task<?>> taches = new ArrayList<>();
	private int x ;
	private int y ;
	private Hud hud ;
	
	public ActionsPane(ArrayList<Task<?>> taches , int x , int y , Hud hud ) {
		super(null, 30, GeneralPaintStrategy.MEDIUM_BROWN);
		this.taches=taches;
		this.hud = hud;
		this.x = x;
		this.y = y;	
		init();
		
	}
	
	
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
