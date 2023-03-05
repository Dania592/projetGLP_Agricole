
package gui.Farm;


import java.awt.Graphics;
import java.io.File;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.configuration.GameConfiguration;
import data.flore.terrains.Terrain;
import data.stucture_base.Element;
import data.stucture_base.Farm;
import gui.gestionnaire.GestionnairePaintStrategy;
import process.game.MapManager;

public class Board  extends JLayeredPane {
	
	private static final long serialVersionUID = 1L;
	private FarmPaintStrategy paintStrategy = new FarmPaintStrategy();
	private KeyControls keys;
	private Element selected;
	private Element clicked;
	private JPanel choixTerrain;
	public Element getClicked() {
		return clicked;
	}

	private Farm farm;
	public Farm getFarm() {
		return farm;
	}

	private MouseHandler mouseHandler;
	
	// Jlabel   
	private JLabel adding = new JLabel();
	private JLabel home = new JLabel();
	private JScrollPane choixScroll;
	
	public Board(Farm farm  , Element selected  ) {
		this.farm = farm;
		this.selected=selected;
		keys = new KeyControls(farm.getManager() , selected); 
		mouseHandler = new MouseHandler(farm.getManager(), this);
		init();
	}
	
	public void setClicked(Element clicked) {
		this.clicked = clicked;
	}
		
	public void init() {
		addKeyListener(keys);
		addMouseListener(mouseHandler);
		adding.setBounds( GameConfiguration.X_ADD_LABEL, GameConfiguration.y_ADD_LABEL,GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
		ImageIcon addIcon= new ImageIcon("src"+File.separator+"ressources"+File.separator+"add.png");
		adding.setIcon(addIcon);		
		add(adding, JLayeredPane.DRAG_LAYER);
		
		home.setBounds( GameConfiguration.X_HOME_LABEL, GameConfiguration.Y_HOME_LABEL,GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
		ImageIcon homeIcone= new ImageIcon("src"+File.separator+"ressources"+File.separator+"home.png");
		home.setIcon(homeIcone);		
		add(home);
		
		setBounds(0, 0, GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);
		setLayout(null);
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		MapManager mapManager = farm.getManager().getMapManager();
		paintStrategy.paint(mapManager.getMap(), g);
		
		for(Element element : mapManager.getElements().values()) {
			paintStrategy.paint(element, g);
			
			if (clicked != null && clicked instanceof Terrain ) {
				Terrain terrain = (Terrain)clicked;
				add(choixTerrain, JLayeredPane.DRAG_LAYER);
				terrain.evoluer();	
			} else {
				if ( choixTerrain != null ) {
					remove(choixTerrain);
				}
			}
		}	
		
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
	
	public void addingChoix() {
		choixScroll = new JScrollPane();
		ChoixPanel choix = new ChoixPanel( farm , selected);
		
		choixScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		choixScroll.setBounds(50, GameConfiguration.WINDOW_HEIGHT-200, GameConfiguration.WINDOW_WIDTH-170, 160);
		add(choixScroll, JLayeredPane.DRAG_LAYER);
		choixScroll.setViewportView(choix);
	}
	
	public void removeChoix() {
		remove(choixScroll);	
	}
	
	public void changeState() {
		if (Arrays.asList(this.getComponents()).contains(choixScroll)) {
			removeChoix();
		} else {
			addingChoix();
		}
	}
	
	// temporaire avant de ajouter un hud 
	public Boolean mouseIsOnAdd(int x , int y ) {
		return (x < (GameConfiguration.X_ADD_LABEL +GameConfiguration.WIDHT_LABEL))&&(x>GameConfiguration.X_ADD_LABEL) &&( y <(GameConfiguration.y_ADD_LABEL+GameConfiguration.HEIGHT_LABEL ))&& (y >GameConfiguration.y_ADD_LABEL);
	}
	
	public Boolean mouseIsOnHome(int x , int y ) {
		return (x<(GameConfiguration.X_HOME_LABEL +GameConfiguration.WIDHT_LABEL))&&( x>GameConfiguration.X_HOME_LABEL )&& (y <(GameConfiguration.Y_HOME_LABEL+GameConfiguration.HEIGHT_LABEL)) &&( y>GameConfiguration.Y_HOME_LABEL);
	}
	
	
	
}
