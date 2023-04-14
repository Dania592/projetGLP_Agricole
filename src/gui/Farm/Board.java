
package gui.Farm;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import data.configuration.GameConfiguration;
import data.flore.terrains.Terrain;
import data.planning.Activity;
import data.structure.Enclos;
import data.structure.hability.Actionnable;
import data.structure.hability.Actionnable.ActionnableKey;
import data.stucture_base.Element;
import data.stucture_base.Farm;
import process.action.TaskFactory;
import process.action.task.Task;
import process.game.MapManager;

public class Board  extends JLayeredPane {
	
	private static final long serialVersionUID = 1L;
	private FarmPaintStrategy paintStrategy = new FarmPaintStrategy();
	private KeyControls keys;
	private Element selected;
	private Element clicked;
	private JPanel choixTerrain;
	private MouseHandler mouseHandler;
	private Choix choix ;
	private Hud hud ;
	private Farm farm;
	
 	
	public Board(Farm farm  , Element selected  ) {
		this.farm = farm;
		this.selected=selected;
		keys = new KeyControls(farm.getManager() , selected); 
		mouseHandler = new MouseHandler(farm.getManager(), this);
		choix = new Choix(farm, this);
		choix.init();
		init();
	}
	
	public void setClicked(Element clicked) {
		if(clicked!=null && clicked.isStatique() ) {
			this.clicked = clicked;			
		}
	}
	
	public Element getClicked() {
		return clicked;
	}

	public Farm getFarm() {
		return farm;
	}
	
	public Hud getHud() {
		return hud ;
	}

	public Choix getChoix() {
		return choix ;
	}
	
		
	public void init() {
		addKeyListener(keys);
		//addMouseListener(mouseHandler);
		
		hud = new Hud(this);
		hud.build();
			
		setBounds(0, 0, GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);
		setLayout(null);
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		MapManager mapManager = farm.getManager().getMapManager();
		paintStrategy.paint(mapManager.getMap(), g);
		paintStrategy.paint(farm, g);
		
		for(Element element : mapManager.getElements().values()) {
			if(element instanceof Enclos) {
				Enclos enclos = (Enclos)element;
				paintStrategy.paint(enclos, g);
				if(enclos.getNiveauEau()==FullLevel.EMPTY  && enclos.getAnimals().size()!=0) {
					paintStrategy.paintLevelHeart(enclos, g);
				}
			}
			else {
				paintStrategy.paint(element, g);				
				
			}
			
			
			
			if (clicked != null && clicked instanceof Terrain ) {
				Terrain terrain = (Terrain)clicked;
				//add(choixTerrain, JLayeredPane.DEFAULT_LAYER);
				terrain.evoluer();	
			}// }else if(clicked instanceof Actionnable){
			// 	ArrayList<ActionnableKey> actionnableKey = ((Actionnable)clicked).getActionnableKey(); 
			// 	System.out.println(Activity.getPossibleActivity(actionnableKey));
			// // }else if(clicked instanceof Actionnable){
			// // 	System.out.println("On a selecté un actionnable !");
			// // 	Actionnable actionnable =(Actionnable)clicked;
			// // 	// ArrayList<Task<?>> taskCanBePerform = getTaskThatCanBePerform(actionnable);
			// // 	// System.out.println(taskCanBePerform);
			// }
			else {
				if ( choixTerrain != null ) {
					remove(choixTerrain);
				}
			}
			
		}
		hud.time();
		
		// les bords de la ferme 
		
		
//		if(farm.getTimeManager().getClock().getMinute().getValue() == 2) {
//			paintStrategy.paintNight(farm.getManager().getMapManager().getMap(), g);
//		}
	}


	private ArrayList<Task<?>> getTaskThatCanBePerform(Actionnable actionnable){
		ArrayList<Activity> activities  = Activity.getPossibleActivity(actionnable.getActionnableKey());
		Iterator<Activity> activitiesIter = activities.iterator(); 
		ArrayList<Task<?>> tasksThatCanBePerform = new ArrayList<>();
		Task<?> newTask;
		while(activitiesIter.hasNext()){
			try {
					newTask = TaskFactory..newTask(farm.getTimeManager().getClock().getHour().getValue(), activitiesIter.next(), actionnable);
				tasksThatCanBePerform.add(newTask);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return tasksThatCanBePerform;
	}

	public void setChoixTerrain(JPanel choixTerrain) {
		this.choixTerrain = choixTerrain;
	}

	public Element getSelected() {
		return selected;
	}

	public void setSelected(Element selected) {
		this.selected = selected;
		keys.setSelected(selected);
	}
	
	

	
}
