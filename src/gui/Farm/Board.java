
package gui.Farm;


import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import data.configuration.GameConfiguration;
import data.espece.characteristic.Produceur.ProductifState;
import data.espece.characteristic.WaterConsumer.HydrationLevel;
import data.espece.flore.terrains.Terrain;
import data.notion.basic.Element;
import data.notion.basic.Farm;
import data.notion.basic.Position;
import data.structure.Enclos;
import gui.Farm.actions.ActionsPane;
import gui.Farm.choix.Choix;
import gui.gestionnaire.gestionnairesGUI.UIGraph;
import process.action.task.coordinator.TaskManager;
import process.evolution.FullLevel;
import process.game.MapManager;

public class Board extends JLayeredPane implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private FarmPaintStrategy paintStrategy = new FarmPaintStrategy();
	private KeyControls keys;
	private Element selected;
	private Element clicked;
	private JPanel choixTerrain;
	private TaskManager taskManager ;
	private Choix choix ;
	private Hud hud ;
	private Farm farm;
	
	private UIGraph ui;
	private MainGuiTest frame ;

	public int gameState = 0;
	public final int playState = 0;
	public final int optionState = 1;
	public final int pauseState = 2;
	public final int gameOverState = 3;

	
	public Board(Farm farm  , Element selected , TaskManager taskManager , MainGuiTest frame) {
		this.farm = farm;
		this.frame = frame ;
		ui = new UIGraph(this);
		this.selected=selected;
		this.taskManager = taskManager ;
		keys = new KeyControls(farm.getManager() , selected, this); 
		new MouseHandler(farm.getManager(), this);
		choix = new Choix(farm, this);
		choix.init();
		init();
	}
	
	public int getGameState() {
		return gameState;
	}
	
	public void setGameState(int gameState) {
		this.gameState = gameState;
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
	public MainGuiTest getFrame() {
		return frame ;
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
		
		hud = new Hud(this);
		hud.build();
			
		setBounds(0, 0, WIDTH, HEIGHT);
		setLayout(null);
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g) {
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
			}else if(element instanceof Terrain){
				Terrain terrain = (Terrain)element;
				paintStrategy.paint(terrain, g);
				if(terrain.getHydrationLevel()!= HydrationLevel.FULLY_HYDRATED && terrain.getProductifState()!= ProductifState.UNABLE_TO_PRODUCE && terrain.getHydrationLevel()!= HydrationLevel.DEAD_FROM_DESHYDRATION){
					paintStrategy.paintLevelHeart(terrain, g);
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
		hud.water();
		ui.draw(g);
		if (gameState == pauseState) {
			
		}
		
		paintStrategy.paintProgressBar(g, taskManager);
				
		if(!farm.getJourMode()) {
			hud.remove_panels();
			paintStrategy.paintNight(farm.getManager().getMapManager().getMap(), g);
		}
		
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
