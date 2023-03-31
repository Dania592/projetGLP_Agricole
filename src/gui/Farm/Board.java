
package gui.Farm;


import java.awt.Graphics;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import data.configuration.GameConfiguration;
import data.flore.terrains.Terrain;
import data.structure.Enclos;
import data.stucture_base.Element;
import data.stucture_base.Farm;
import process.game.MapManager;

public class Board  extends JLayeredPane {
	
	private static final long serialVersionUID = 1L;
	private FarmPaintStrategy paintStrategy = new FarmPaintStrategy();
	private KeyControls keys;
	private Element selected;
	private Element clicked;
	private JPanel choixTerrain;
	private Hud hud ;
	private Farm farm;
	
	public Element getClicked() {
		return clicked;
	}

	public Farm getFarm() {
		return farm;
	}
	
	public Hud getHud() {
		return hud ;
	}

	private MouseHandler mouseHandler;
	
	// Jlabel   
//	private JScrollPane choixScroll;
	
	public Board(Farm farm  , Element selected  ) {
		this.farm = farm;
		this.selected=selected;
		keys = new KeyControls(farm.getManager() , selected); 
		mouseHandler = new MouseHandler(farm.getManager(), this);
		init();
	}
	
	public void setClicked(Element clicked) {
		if(clicked!=null && clicked.isStatique() ) {
			this.clicked = clicked;			
		}
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
		
		for(Element element : mapManager.getElements().values()) {
			if(element instanceof Enclos) {
				Enclos enclos = (Enclos)element;
				paintStrategy.paint(enclos, g);
			}
			else {
				paintStrategy.paint(element, g);				
			}
			
//			if (clicked != null && clicked instanceof Terrain ) {
//				Terrain terrain = (Terrain)clicked;
//				//add(choixTerrain, JLayeredPane.DEFAULT_LAYER);
//				terrain.evoluer();	
//			} else {
//				if ( choixTerrain != null ) {
//					remove(choixTerrain);
//				}
//			}
		}
		hud.time();
		
		// les bords de la ferme 
		paintStrategy.paint(farm, g);
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
