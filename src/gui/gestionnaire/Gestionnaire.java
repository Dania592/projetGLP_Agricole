package gui.gestionnaire;



import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;

import data.gestion.RessourcesManager;
import process.game.Game;
import process.game.GameBuilder;
import process.game.MapManager;
import process.transaction.Achat;
import javax.swing.ImageIcon;

public class Gestionnaire extends JFrame {
	
	public static Achat achat = new Achat();
	
	public static Color DARK_BROWN = new Color(68,40,24);
	public static Color MEDIUM_BROWN = new Color(188,149,88);
	public static Color LIGHT_BROWN = new Color(255,231,171);
	public static Color DARK_GREEN = new Color(82,121,112);
	public static Color RED = new Color(158,36,19);
	public static Color MEDIUM_GREEN = new Color(103,148,76);
	
	private GestionnairePaintStrategy paintStrategy = new GestionnairePaintStrategy();
	private HashMap<String, JPanel> toBuy = new HashMap<>();
	
	private RessourcesManager ressourcesManager = new RessourcesManager();
	private JFrame test; 
	private ArrayList<Container> containers = new ArrayList<>();
	private JToggleButton jToggleButton = new JToggleButton();
	
	private static final long serialVersionUID = 1L;
	//private static final Color WHITE = new Color(255,255,255);
	
	public Gestionnaire(String title , JFrame main ) {
		super(title);
		init();
		test=main;
		setVisible(true);
		setResizable(false);
	}
	
	
	public void init() {
		
		setBackground(LIGHT_BROWN);
		
		CardLayout cardLayout = new CardLayout();
		
		JPanel animalsPanel = paintStrategy.paintNormalPanel(10, 10, WIDTH, HEIGHT,MEDIUM_BROWN);
		JPanel plantsPanel = paintStrategy.paintNormalPanel(10, 10, WIDTH, HEIGHT, MEDIUM_BROWN);
		JPanel outilsPanel = paintStrategy.paintNormalPanel(10, 10, WIDTH, HEIGHT, MEDIUM_BROWN);
		JPanel enginsPanel = paintStrategy.paintNormalPanel(10, 10, WIDTH, HEIGHT, MEDIUM_BROWN);
		JPanel structuresPanel = paintStrategy.paintNormalPanel(10, 10, WIDTH, HEIGHT, MEDIUM_BROWN);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		JPanel principalPanel = paintStrategy.paintNormalPanel(0, 0, 1000, 620, LIGHT_BROWN);
		contentPane.add(principalPanel);
		getContentPane().add(principalPanel);
			
		JTabbedPane tabbedPane = paintStrategy.paintTabbedPane(55, 89, 585, 500, LIGHT_BROWN, DARK_BROWN);
		JPanel panier = paintStrategy.paintRoundedPanel(690, 115, 260, 495, new BorderLayout(), 40, MEDIUM_BROWN);
		JLabel panierHeaderLabel = new JLabel("Panier");
		panierHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
		panier.add(panierHeaderLabel, BorderLayout.NORTH);
		panier.add(paintStrategy.paintBill(panier, achat.getCart()), BorderLayout.CENTER);

		toBuy.put("Plantes", plantsPanel);
		toBuy.put("Animaux", animalsPanel);
		toBuy.put("Outils", outilsPanel);
		toBuy.put("Structures", structuresPanel);
		toBuy.put("Engins", enginsPanel);
		
		
		tabbedPane = paintStrategy.paintTabs(tabbedPane, toBuy);
		
		JLayeredPane plantsCards = paintStrategy.paintMarketCardsContainer(10, 10, 599, 512, cardLayout, 20, MEDIUM_BROWN, ressourcesManager.getGestionnaireStocks().getGraines().size());
		plantsPanel.add(plantsCards);
		containers.add(plantsCards);
		
		JLayeredPane animalsCards = paintStrategy.paintMarketCardsContainer(10, 10, 599, 512, cardLayout, 20, MEDIUM_BROWN, ressourcesManager.getGestionnaireAnimaux().getSize());
		animalsPanel.add(animalsCards);
		containers.add(animalsCards);
		
		JLayeredPane outilsCards = paintStrategy.paintMarketCardsContainer(10, 10, 599, 512, cardLayout, 20, MEDIUM_BROWN, ressourcesManager.getGestionnaireMateriel().getOutilsSize());
		outilsPanel.add(outilsCards);
		containers.add(outilsCards);
		
		JLayeredPane structuresCards = paintStrategy.paintMarketCardsContainer(10, 10, 599, 512, cardLayout, 20, MEDIUM_BROWN, ressourcesManager.getGestionnaireStructure().getSize());
		structuresPanel.add(structuresCards);
		containers.add(structuresCards);
		
		JLayeredPane enginsCards = paintStrategy.paintMarketCardsContainer(10, 10, 599, 512, cardLayout, 20, MEDIUM_BROWN, ressourcesManager.getGestionnaireMateriel().getEnginsSize());
		enginsPanel.add(enginsCards);
		containers.add(enginsCards);
		
		
		
		System.out.println(tabbedPane.getSelectedIndex());
		
		JLabel next = new JLabel();
		next.setBounds(645, 320, 40, 40);
		ImageIcon nextIcone= new ImageIcon("src"+File.separator+"ressources"+File.separator+"next.png");
		next.setIcon(nextIcone);	
		principalPanel.add(next);
		next.addMouseListener(new Next(cardLayout, tabbedPane,containers));
		
		JLabel previous = new JLabel();
		previous.setBounds(10,320,40,40);
		ImageIcon previousIcone= new ImageIcon("src"+File.separator+"ressources"+File.separator+"previous.png");
		previous.setIcon(previousIcone);	
		principalPanel.add(previous);
		previous.addMouseListener(new Previous(cardLayout, tabbedPane, containers));
		
		// just don't set v t in run 
		Button ventes = new Button("Suivant");
		ventes.setBounds(25, 25, 66, 21);
		principalPanel.add(ventes);
		ventes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.next(animalsCards);
			}
		});
		
//		Button achats = new Button("Achat");
//		achats.setBounds(120, 25, 66, 21);
//		principalPanel.add(achats);
		
		principalPanel.add(tabbedPane);
		principalPanel.add(panier);
		setSize(1000,670);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
						
	}
	
	private class Next implements MouseListener{
		
		private CardLayout layout;
		private JTabbedPane container;
		private ArrayList<Container> containers;
		
		Next(CardLayout layout,JTabbedPane container,ArrayList<Container> containers){
			this.layout = layout;
			this.container = container;
			this.containers = containers;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			layout.next(containers.get(container.getSelectedIndex()));
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {
		}
		
	}
	
	
	private class Previous implements MouseListener{
		
		private CardLayout layout;
		private JTabbedPane container;
		private ArrayList<Container> containers;
		
		Previous(CardLayout layout,JTabbedPane container,ArrayList<Container> containers){
			this.layout = layout;
			this.container = container;
			this.containers = containers;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			layout.previous(containers.get(container.getSelectedIndex()));
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {
		}
		
	}
	
	public static void main(String[] args) {
		
		Game game = new Game();
		MapManager manager = GameBuilder.MapBuilder();
		game.acheter(manager.getMap());
		Gestionnaire.achat = game.getAchat();
		
		Gestionnaire gestionnaire = new Gestionnaire("Gestionnaire" , null);
	}
	
}