
package gui.Farm;


import java.awt.Graphics;

import java.io.File;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;

import data.configuration.GameConfiguration;
import process.game.MapManager;
import data.stucture_base.Element;
import data.stucture_base.Farm;

public class Board  extends JLayeredPane {
	
	private static final long serialVersionUID = 1L;
	private FarmPaintStrategy paintStrategy = new FarmPaintStrategy();
	private KeyControls keys;
	private Element selected;
	
	private Farm farm;
	
	// Jlabel   
	private JLabel adding = new JLabel();
	private JLabel home = new JLabel();
	private JScrollPane choixScroll;
	
	
	
	public Board(Farm farm  , Element selected  ) {
		this.farm = farm;
		this.selected=selected;
		keys = new KeyControls(farm.getManager() , selected); 
		init();
	}
		
	public void init() {
		
		addKeyListener(keys);
		adding.setBounds( GameConfiguration.X_ADD_LABEL, GameConfiguration.y_ADD_LABEL,GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
		ImageIcon addIcon= new ImageIcon("src"+File.separator+"ressources"+File.separator+"add.png");
		adding.setIcon(addIcon);		
		add(adding, JLayeredPane.DRAG_LAYER);
		
		
		home.setBounds( GameConfiguration.X_HOME_LABEL, GameConfiguration.Y_HOME_LABEL,GameConfiguration.WIDHT_LABEL,GameConfiguration.HEIGHT_LABEL);
		ImageIcon homeIcone= new ImageIcon("src"+File.separator+"ressources"+File.separator+"home.png");
		home.setIcon(homeIcone);		
		add(home, JLayeredPane.DRAG_LAYER);

		
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
		}	
		
		// les bords de la ferme 
		paintStrategy.paint(farm, g);
		
		
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
