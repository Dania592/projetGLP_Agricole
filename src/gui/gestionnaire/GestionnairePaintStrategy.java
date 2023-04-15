package gui.gestionnaire;

import java.awt.Color;

public class GestionnairePaintStrategy {
	
//	private GeneralPaintStrategy generalPaintStrategy = new GeneralPaintStrategy();
//	private ArrayList<Container> containers = new ArrayList<>();
//	private HashMap<String, JPanel> toBuy = new HashMap<>();
	
	public static Color MANAGER_CARD_COLOR = GeneralPaintStrategy.LIGHT_BROWN;
	public static int MIN_SPACE_BETWEEN = GeneralPaintStrategy.MIN_SPACE_BETWEEN;
	public static int MANAGER_ROW_COUNT = 2;
	public static int MANAGER_COLUMN_COUNT = 4;
	public static int MANAGER_CARD_WIDTH = 182;
	public static int MANAGER_CARD_HEIGHT = 222;
	public static int WIDTH = 895;
	public static int HEIGHT = 530;
	
//	public JPanel paintGestionnaire(int x, int y, int width, int height, int rowCount, int columnCount, int gap, int cardWidth, int cardHeight, Color cardColor, String type, Achat achat) {
//		JPanel principalPanel = generalPaintStrategy.paintNormalPanel(x, y, WIDTH, HEIGHT, GeneralPaintStrategy.LIGHT_BROWN);
//		
//		CardLayout cardLayout = new CardLayout();
//		
//		// 20 
//		int radius = 20;
//		// 10
//		int posX = 10;
//		int posY = 10;
//		
//		int labelWidth = 40;
//		
//		int tabX = 2*MIN_SPACE_BETWEEN + labelWidth;
//		int tabY = MIN_SPACE_BETWEEN;
//		
//		
//		JPanel animalsPanel = generalPaintStrategy.paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT,GeneralPaintStrategy.MEDIUM_BROWN);
//		JPanel plantsPanel = generalPaintStrategy.paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);
//		JPanel outilsPanel = generalPaintStrategy.paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);
//		JPanel enginsPanel = generalPaintStrategy.paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);
//		JPanel structuresPanel = generalPaintStrategy.paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);
//		
//		JTabbedPane tabbedPane = generalPaintStrategy.paintTabbedPane(tabX, tabY, width, height + 3*MIN_SPACE_BETWEEN, GeneralPaintStrategy.LIGHT_BROWN, GeneralPaintStrategy.DARK_BROWN);
//
//		toBuy.put("Plantes", plantsPanel);
//		toBuy.put("Animaux", animalsPanel);
//		toBuy.put("Outils", outilsPanel);
//		toBuy.put("Structures", structuresPanel);
//		toBuy.put("Engins", enginsPanel);
//		
//		tabbedPane = generalPaintStrategy.paintTabs(tabbedPane, toBuy);
//				
//		JLayeredPane plantsCards = generalPaintStrategy.paintCardsContainer(posX, posY, width, height, cardWidth, cardHeight, rowCount, columnCount, 
//				cardLayout, radius, cardColor, GestionnaireKey.SEEDS, gap, type, achat);
//		plantsPanel.add(plantsCards);
//		containers.add(plantsCards);
//		
//		JLayeredPane animalsCards = generalPaintStrategy.paintCardsContainer(posX, posY, width, height, cardWidth, cardHeight, rowCount, columnCount, 
//				cardLayout, radius, cardColor, GestionnaireKey.ANIMALS, gap, type, achat);
//		animalsPanel.add(animalsCards);
//		containers.add(animalsCards);
//		
//		JLayeredPane outilsCards = generalPaintStrategy.paintCardsContainer(posX, posY, width, height, cardWidth, cardHeight, rowCount, columnCount, 
//				cardLayout, radius, cardColor, GestionnaireKey.OUTILS, gap, type, achat);
//		outilsPanel.add(outilsCards);
//		containers.add(outilsCards);
//		
//		JLayeredPane structuresCards = generalPaintStrategy.paintCardsContainer(posX, posY, width, height, cardWidth, cardHeight, rowCount, columnCount, 
//				cardLayout, radius, cardColor, GestionnaireKey.STRUCTURES, gap, type, achat);
//		structuresPanel.add(structuresCards);
//		containers.add(structuresCards);
//		
//		JLayeredPane enginsCards = generalPaintStrategy.paintCardsContainer(posX, posY, width, height, cardWidth, cardHeight, rowCount, columnCount, 
//				cardLayout, radius, cardColor, GestionnaireKey.ENGINS, gap, type, achat);
//		enginsPanel.add(enginsCards);
//		containers.add(enginsCards);	
//		
//		JLabel next = new JLabel();
//		next.setBounds(posX + width + 2*gap + labelWidth, HEIGHT/2, labelWidth, labelWidth);
//		ImageIcon nextIcone= new ImageIcon("src"+File.separator+"ressources"+File.separator+"next.png");
//		next.setIcon(nextIcone);	
//		principalPanel.add(next);
//		next.addMouseListener(new Next(cardLayout, tabbedPane,containers));
//		
//		JLabel previous = new JLabel();
//		previous.setBounds(gap,HEIGHT/2,labelWidth,labelWidth);
//		ImageIcon previousIcone= new ImageIcon("src"+File.separator+"ressources"+File.separator+"previous.png");
//		previous.setIcon(previousIcone);	
//		principalPanel.add(previous);
//		previous.addMouseListener(new Previous(cardLayout, tabbedPane, containers));
//		
//		principalPanel.add(tabbedPane);
//		
//		return principalPanel;
//	}
	
}