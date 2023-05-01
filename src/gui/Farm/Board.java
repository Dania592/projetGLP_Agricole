
package gui.Farm;


import java.awt.Graphics;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import data.configuration.GameConfiguration;
import data.flore.terrains.Terrain;
import data.structure.Enclos;
import data.stucture_base.Element;
import data.stucture_base.Farm;
import data.stucture_base.Position;
import gui.Farm.actions.ActionsPane;
import gui.Farm.choix.Choix;
import process.action.TaskManager;
import process.action.task.Task;
import process.evolution.FullLevel;
import process.game.MapManager;

public class Board  extends JLayeredPane implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private FarmPaintStrategy paintStrategy = new FarmPaintStrategy();
	private KeyControls keys;
	private Element selected;
	private Element clicked;
	private JPanel choixTerrain;
	private TaskManager taskManager ;
	private MouseHandler mouseHandler;
	//private ChoixPanel choixScroll;
	//private ActionsPane actions;
	private Choix choix ;
	private Hud hud ;
	private Farm farm;

	
 	
	public Board(Farm farm  , Element selected , TaskManager taskManager , MainGuiTest ferme) {
		this.farm = farm;
		this.selected=selected;
		this.taskManager = taskManager ;
		keys = new KeyControls(farm.getManager() , selected); 
		mouseHandler = new MouseHandler(farm.getManager(), this);
		choix = new Choix(farm, this);
		choix.init();
		init();
	}
	
	public void setFarm(Farm farm) {
		this.farm=farm;
		
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
	public void setChoix(Choix choix) {
		this.choix=choix;
	}
		
	public void init() {
		addKeyListener(keys);
		//addMouseListener(mouseHandler);
		
		hud = new Hud(this);
		hud.build();
			
		setBounds(0, 0, WIDTH, HEIGHT);
		setLayout(null);
		setFocusable(true);
	}
	
	public  void paintComponent(Graphics g) {
		super.paintComponent(g);
		MapManager mapManager = farm.getManager().getMapManager();
		paintStrategy.paint(mapManager.getMap(), g);
		paintStrategy.paint(farm, g);
		
		ArrayList<Element> test = new ArrayList<>();
		for(Element element : mapManager.getElements().values()) {
			test.add(element);
		}
		
		Iterator<Element> it = test.iterator();
		
		while(it.hasNext() ) {
			Element element = it.next();

			if(element instanceof Enclos) {
				Enclos enclos = (Enclos)element;
				paintStrategy.paint(enclos, g);
				if((enclos.getNiveauEau().equals(FullLevel.EMPTY) || enclos.getNiveauNourriture().equals(FullLevel.EMPTY)) && enclos.getAnimals().size()>0) {
					
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
			}else {
				if ( choixTerrain != null ) {
					remove(choixTerrain);
				}
			}
			
		}
		hud.time();
		hud.solde();
		paintStrategy.paintProgressBar(g, taskManager);
				
		//night mode 
//		if(farm.getClock().getMinute().getValue() == 1) {
//			paintStrategy.paintNight(farm.getManager().getMapManager().getMap(), g);
//		}
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
	
	
//	 public void paintProgressBar(Graphics g ) {
//	 	ArrayList<Task<?>> tasks = TaskManager.getInstance().getinProcess();
//	 	//g.drawLine(0, 0, 300, 300);
//	 	for(Task<?> task : tasks) {
//	 		Position position =task.getActionnableTarget().getPosition();
//	 		int x = (position.getColonne_init()+2)*GameConfiguration.CASE_DIMENSION + farm.getManager().getMapManager().getMap().getX() ; 
//	 		int y = (position.getLigne_init()-2)*GameConfiguration.CASE_DIMENSION + farm.getManager().getMapManager().getMap().getY();
//	 		ImageIcon bar = new ImageIcon(GameConfiguration.IMAGE_PATH+"Taches"+File.separator+task.getState()+".png");
//	 		g.drawImage(bar.getImage(), x, y, 100,20, null);
//	 	}
//	 }
	

	
}
