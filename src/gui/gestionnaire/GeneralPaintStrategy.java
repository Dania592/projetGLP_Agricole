package gui.gestionnaire;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import data.gestion.GestionnaireAnimaux;
import data.gestion.GestionnaireMateriel;
import data.gestion.GestionnaireStocks;
import data.gestion.GestionnaireStructures;
import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.Engins;
import gui.gestionnaire.keys.Graine;
import gui.gestionnaire.keys.Keys;
import gui.gestionnaire.keys.Outils;
import gui.gestionnaire.keys.Structures;
import process.transaction.Achat;

public class GeneralPaintStrategy {

	public static int MIN_SPACE_BETWEEN = 10;
	public static int WIDTH = 1000;
	public static int HEIGHT = 620;
	public static int RADIUS = 20;
	
	public static Color DARK_BROWN = new Color(68,40,24);
	public static Color MEDIUM_BROWN = new Color(188,149,88);
	public static Color LIGHT_BROWN = new Color(255,231,171);
	public static Color DARK_GREEN = new Color(82,121,112);
	public static Color RED = new Color(158,36,19);
	public static Color MEDIUM_GREEN = new Color(103,148,76);

	private ArrayList<Container> containers = new ArrayList<>();
	private HashMap<String, JPanel> toBuy = new HashMap<>();
	private int counter = 0;
	
	//ok
	public JPanel paintNormalPanel(int x, int y, int w, int h, Color color) {
		JPanel panel = new JPanel();
		panel.setBounds(x,y,w,h);
		panel.setBackground(color);
		panel.setLayout(null);
		return panel;
	}
	
	//ok
	public JLayeredPane paintLayeredPane(int x, int y, int w, int h, LayoutManager layout) {
		JLayeredPane jLayeredPane = new JLayeredPane();
		jLayeredPane.setBounds(x, y, w, h);
		jLayeredPane.setLayout(layout);
		return jLayeredPane;
	}
	
	//ok
	public JPanel paintNormalPanel(int x, int y, int w, int h, LayoutManager layout, Color color) {
		JPanel panel = new JPanel();
		panel.setBounds(x,y,w,h);
		panel.setBackground(color);
		panel.setLayout(layout);
		return panel;
	}
	
	//ok
	public JPanel paintNormalPanel(LayoutManager layout, Color color) {
		JPanel panel = new JPanel();
		panel.setBackground(color);
		panel.setLayout(layout);
		return panel;
	}
	
	//ok
	public JPanel paintRoundedPanel(int x, int y, int w, int h,LayoutManager layout, int radius, Color color) {
		RoundedPanel roundedPanel = new RoundedPanel(layout, radius);
		roundedPanel.setOpaque(false);
		roundedPanel.setBackground(color);
		roundedPanel.setBounds(x,y,w,h);
		return roundedPanel;
	}
	
	//ok
	public JTabbedPane paintTabbedPane(int x, int y, int w, int h,Color backgroundColor, Color foregroundColor) {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setUI(new TabbedPaneUI());
		tabbedPane.setBackground(backgroundColor);
		tabbedPane.setForeground(foregroundColor);
		tabbedPane.setBounds(x,y,w,h);
		return tabbedPane;
	}
	
	//ok
	public JTabbedPane paintTabs(JTabbedPane tabbedPane, HashMap<String,JPanel> composants) {
		for (String title : composants.keySet()) {
			tabbedPane.addTab(title, composants.get(title));
		}
		return tabbedPane;
	}
	
	//ok
	public JLayeredPane paintCardsContainer(int x, int y, int w, int h,int cardWidth, int cardHeight, int rowCount, int columnCount, CardLayout cardLayout, int radius, Color color, GestionnaireKey key, int gap,String type, Achat achat, MarketGUI market) {
		int cardCount;
		if (type.equals("Manager")) {
			cardCount = key.getElements().size();
		} else {
			cardCount = key.getArticles().size();
		}
		counter = cardCount - 1;
		JLayeredPane cardContainer = paintLayeredPane(x, y, w, h, cardLayout);
		JPanel innerContainer = paintNormalPanel(x, y, w, h, null);
		if (cardCount > 0 ) {
			if(cardCount <= (rowCount * columnCount)) {
				innerContainer = fillCardContainer(innerContainer, cardCount, key, cardWidth, cardHeight, columnCount, gap, color,type, achat, market);
				cardContainer.add(innerContainer);
			} else {
				// count le nombre de vues du cardLayout
				int count = cardCount / (rowCount * columnCount);
				
				if ( cardCount % (rowCount * columnCount) != 0) {
					count++;
				}
				// affecter le nombre de cartes par vue du cardLayout 
				int set;
				for ( int i = 0; i < count; i++) {
					if (cardCount >= (rowCount * columnCount)) {
						set = (rowCount * columnCount);
					} else {
						set = cardCount;
					}
					cardCount -= set;
					innerContainer = paintNormalPanel(gap, gap, w - gap, h - columnCount, null);
					innerContainer = fillCardContainer(innerContainer, set, key, cardWidth, cardHeight, columnCount, gap, color,type, achat, market);
					cardContainer.add(innerContainer);
				}
			}
		}
		return cardContainer;
	}
	
	// ok
	public JPanel fillCardContainer(JPanel innerContainer, int cardCount, GestionnaireKey key, int cardWidth, int cardHeight, int columnCount, int gap, Color color,String type, Achat achat, MarketGUI market) {
		ArrayList<Keys> elements;
		if (type.equals("Manager")) {
			elements = key.getElements();
		} else {
			elements = key.getArticles();
		}
		if (elements != null ) {
			int cpt = 0;
			int posX = 0;
			int posY = 0;
			for (int i = 0; i < cardCount; i++) {
				JPanel card;
				if (type.equals("Manager")) {
					card = paintManagerCard(posX, posY,cardWidth, cardHeight, gap, key, elements, color);
				} else {
					card = paintMarketCard(posX, posY,cardWidth, cardHeight, gap, key, elements, color,achat, market);
				}
				if (card != null) {
					innerContainer.add(card);
					cpt++;
					posX += cardWidth + gap;
					if (cpt == columnCount) {
						cpt = posX = 0;
						posY += gap + cardHeight;
					}	
				}
				counter--;
			}
		}
		return innerContainer;			
	}
	
	public JPanel paintManagerCard(int posX, int posY, int width, int height, int gap, GestionnaireKey key, ArrayList<Keys> elements, Color color) {
		Keys element = elements.get(0);
		String[] infos = getInformation(element, key);
		elements.remove(0);
		String imagePath = infos[1];
		int size = Integer.valueOf(infos[2]).intValue();
		if (size != 0) {
			JPanel card = paintRoundedPanel(posX, posY, width, height, null, GeneralPaintStrategy.RADIUS, color);
			
			JLabel icone = new JLabel();
			icone.setBounds(gap, gap, width - (2*gap), 100);
			ImageIcon elementImage= new ImageIcon(imagePath);
			icone.setIcon(elementImage);
			icone.setHorizontalAlignment(JLabel.CENTER);
			
			JLabel name = new JLabel();
			name.setBounds(gap, 2 * gap + 100 , width - (2*gap), 50);
			name.setText(infos[0]);
			name.setHorizontalAlignment(JLabel.CENTER);
			
			JLabel count = new JLabel();
			count.setBounds(gap, 3 * gap + 120, width - (2*gap), 50);
			count.setText(infos[2]);
			count.setHorizontalAlignment(JLabel.CENTER);
			count.setForeground(GeneralPaintStrategy.DARK_BROWN);
			
			card.add(icone);
			card.add(name);
			card.add(count);
			return card;
		}
		return null;
	}
	
	public String[] getInformation(Keys element, GestionnaireKey key) {
		String name = key.name();
		String[] infos = new String[3];
		switch (name) {
			case "OUTILS":
				Outils outil = (Outils)element;
				infos[0] = getName(outil);
				infos[1] = "src"+File.separator+"ressources"+File.separator+"Outils"+File.separator+outil.name()+".png";
				infos[2] = String.valueOf(GestionnaireMateriel.getInstance().getOutils().get(outil).size());
				break;
			case "ENGINS":
				Engins engin = (Engins)element;
				infos[0] = getName(engin);
				infos[1] = "src"+File.separator+"ressources"+File.separator+"Engins"+File.separator+engin.name()+".png";
				infos[2] = String.valueOf(GestionnaireMateriel.getInstance().getEngins().get(engin).size());
				break;
			case "SEEDS":
				Graine graine = (Graine)element;
				infos[0] = getName(graine);
				infos[1] = "src"+File.separator+"ressources"+File.separator+"Graine"+File.separator+graine.name()+".png"; 
				infos[2] = String.valueOf(GestionnaireStocks.getInstance().getGraines().get(graine));
				break;
			case "STRUCTURES":
				Structures structure = (Structures)element;
				infos[0] = getName(structure);
				infos[1] = "src"+File.separator+"ressources"+File.separator+"Structure"+File.separator+infos[0]+".png";
				infos[2] = String.valueOf(GestionnaireStructures.getInstance().getStructures().get(structure).size());	
				break;
			case "ANIMALS":
				Animals animal = (Animals)element;
				infos[0] = getName(animal);
				infos[1] = "src"+File.separator+"ressources"+File.separator+infos[0]+File.separator+"standAdulte.png";
				infos[2] = String.valueOf(GestionnaireAnimaux.getInstance().getAnimaux().get(animal).size());	
				break;
			default:
				infos[0] = "Non reconnu";
				infos[1] = "src"+File.separator+"ressources"+File.separator+"default-image.png";
				infos[2] = "/";
				break;
		}
		return infos;
	}
	
	public String[] getArticleInformation(Keys element, GestionnaireKey key, Achat achat) {
		String name = key.name();
		String[] infos = new String[4];
		switch (name) {
			case "OUTILS":
				Outils outil = (Outils)element;
				infos[0] = getName(outil);
				infos[1] = "src"+File.separator+"ressources"+File.separator+"Outils"+File.separator+outil.name()+".png";
				infos[2] = String.valueOf(achat.getCart().get(outil));
				infos[3] = String.valueOf(outil.getPrixAchat());
				break;
			case "ENGINS":
				Engins engin = (Engins)element;
				infos[0] = getName(engin);
				infos[1] = "src"+File.separator+"ressources"+File.separator+"Engins"+File.separator+engin.name()+".png";
				infos[2] = String.valueOf(achat.getCart().get(engin));
				infos[3] = String.valueOf(engin.getPrixAchat());
				break;
			case "SEEDS":
				Graine graine = (Graine)element;
				infos[0] = getName(graine);
				infos[1] = "src"+File.separator+"ressources"+File.separator+"Graine"+File.separator+graine.name()+".png"; 
				infos[2] = String.valueOf(achat.getCart().get(graine));
				infos[3] = String.valueOf(graine.getPrixAchat());
				break;
			case "STRUCTURES":
				Structures structure = (Structures)element;
				infos[0] = getName(structure);
				infos[1] = "src"+File.separator+"ressources"+File.separator+"Structure"+File.separator+infos[0]+".png";
				infos[2] = String.valueOf(achat.getCart().get(structure));	
				infos[3] = String.valueOf(structure.getPrixAchat());
				break;
			case "ANIMALS":
				Animals animal = (Animals)element;
				infos[0] = getName(animal);
				infos[1] = "src"+File.separator+"ressources"+File.separator+infos[0]+File.separator+"standJeune.png";
				infos[2] = String.valueOf(achat.getCart().get(animal));
				infos[3] = String.valueOf(animal.getPrixAchat());
				break;
			default:
				infos[0] = "Non reconnu";
				infos[1] = "src"+File.separator+"ressources"+File.separator+"default-image.png";
				infos[2] = "/";
				break;
		}
		return infos;
	}
	
	public JPanel paintMarketCard(int posX, int posY, int width, int height, int gap,GestionnaireKey key, ArrayList<Keys> elements, Color color, Achat achat, MarketGUI market) {
		Keys element = elements.get(counter);
		String[] infos = getArticleInformation(element, key,achat);
		String imagePath = infos[1];
		JPanel card = paintRoundedPanel(posX, posY, width, height, null, GeneralPaintStrategy.RADIUS, color);
		
		JLabel icone = new JLabel();
		icone.setBounds(gap, gap, width - (2*gap), 100);
		ImageIcon elementImage= new ImageIcon(imagePath);
		icone.setIcon(elementImage);
		icone.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel name = new JLabel();
		name.setBounds(gap, 2 * gap + 100 , width - (2*gap), 25);
		name.setText(infos[0]);
		name.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel price = paintPriceLabel(infos[3], 5*gap, 2 * gap + 125, width - (8*gap), 25);
		
		JButton acheter = paintButton(2*gap, 2 * gap + 160, width - (4*gap), 30, GestionnaireStocksGUI.DARK_GREEN, Color.WHITE,null, "Ajouter au panier");
		acheter.addActionListener(new AddToCart(element, market));
		
		card.add(icone);
		card.add(name);
		card.add(price);
		card.add(acheter);
		return card;
	}
	
	public JButton paintButton(int x, int y, int w, int h, Color bgColor, Color fgColor, Font font, String title) {
		JButton button = new JButton(title);
		button.setBounds(x, y, w, h);
		button.setBackground(bgColor);
		button.setForeground(fgColor);
		button.setFont(font);
		return button;
	}
		
	public static String getName(Keys key) {
		String title = key.name().replace('_', ' ');
		title = title.substring(0, 1).toUpperCase() + title.substring(1).toLowerCase();
		return title;
	}
	
	public Integer getQuantity(Keys key, Achat achat) {
		if ( achat != null) {
			return achat.getCart().get(key);
		} else {
			return 0;
		}
	}
	
	public JLabel paintPriceLabel(String price, int x, int y, int w, int h) {
	    ImageIcon icon = new ImageIcon("src"+File.separator+"ressources"+File.separator+"price.png");

	    JLabel label= new JLabel() {
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g) {
		        g.drawImage(icon.getImage(), 0, 0, null);
		        super.paintComponent(g);
		    }
	    };

		label.setBounds(x, y, w, h);
		label.setHorizontalAlignment(JLabel.CENTER);
	    label.setOpaque(false);
	    label.setText(price);
	    return label;
	}
		
	public JPanel paintGestionnaire(int x, int y, int width, int height, int rowCount, int columnCount, int gap, int cardWidth, int cardHeight, Color cardColor, String type, Achat achat, MarketGUI market) {
		JPanel principalPanel = paintNormalPanel(x, y, WIDTH, HEIGHT, GeneralPaintStrategy.LIGHT_BROWN);
		
		CardLayout cardLayout = new CardLayout();
		
		// 20 
		int radius = 20;
		// 10
		int posX = 10;
		int posY = 10;
		
		int labelWidth = 40;
		
		int tabX = 2*MIN_SPACE_BETWEEN + labelWidth;
		int tabY = MIN_SPACE_BETWEEN;
		
		
		JPanel animalsPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT,GeneralPaintStrategy.MEDIUM_BROWN);
		JPanel plantsPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);
		JPanel outilsPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);
		JPanel enginsPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);
		JPanel structuresPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);
		
		JTabbedPane tabbedPane = paintTabbedPane(tabX, tabY, width, height + 3*MIN_SPACE_BETWEEN, GeneralPaintStrategy.LIGHT_BROWN, GeneralPaintStrategy.DARK_BROWN);

		toBuy.put("Plantes", plantsPanel);
		toBuy.put("Animaux", animalsPanel);
		toBuy.put("Outils", outilsPanel);
		toBuy.put("Structures", structuresPanel);
		toBuy.put("Engins", enginsPanel);
		
		tabbedPane = paintTabs(tabbedPane, toBuy);

		JLayeredPane plantsCards = paintCardsContainer(posX, posY, width, height, cardWidth, cardHeight, rowCount, columnCount, 
				cardLayout, radius, cardColor, GestionnaireKey.SEEDS, gap, type, achat, market);
		plantsPanel.add(plantsCards);
		containers.add(plantsCards);

		JLayeredPane animalsCards = paintCardsContainer(posX, posY, width, height, cardWidth, cardHeight, rowCount, columnCount, 
				cardLayout, radius, cardColor, GestionnaireKey.ANIMALS, gap, type, achat, market);
		animalsPanel.add(animalsCards);
		containers.add(animalsCards);
		
		JLayeredPane outilsCards = paintCardsContainer(posX, posY, width, height, cardWidth, cardHeight, rowCount, columnCount, 
				cardLayout, radius, cardColor, GestionnaireKey.OUTILS, gap, type, achat, market);
		outilsPanel.add(outilsCards);
		containers.add(outilsCards);
		
		JLayeredPane structuresCards = paintCardsContainer(posX, posY, width, height, cardWidth, cardHeight, rowCount, columnCount, 
				cardLayout, radius, cardColor, GestionnaireKey.STRUCTURES, gap, type, achat, market);
		structuresPanel.add(structuresCards);
		containers.add(structuresCards);
		
		JLayeredPane enginsCards = paintCardsContainer(posX, posY, width, height, cardWidth, cardHeight, rowCount, columnCount, 
				cardLayout, radius, cardColor, GestionnaireKey.ENGINS, gap, type, achat, market);
		enginsPanel.add(enginsCards);
		containers.add(enginsCards);	
		
		JLabel next = new JLabel();
		next.setBounds(posX + width + 2*gap + labelWidth, HEIGHT/2, labelWidth, labelWidth);
		ImageIcon nextIcone= new ImageIcon("src"+File.separator+"ressources"+File.separator+"next.png");
		next.setIcon(nextIcone);	
		principalPanel.add(next);
		next.addMouseListener(new Next(cardLayout, tabbedPane,containers));
		
		JLabel previous = new JLabel();
		previous.setBounds(gap,HEIGHT/2,labelWidth,labelWidth);
		ImageIcon previousIcone= new ImageIcon("src"+File.separator+"ressources"+File.separator+"previous.png");
		previous.setIcon(previousIcone);	
		principalPanel.add(previous);
		previous.addMouseListener(new Previous(cardLayout, tabbedPane, containers));
		
		principalPanel.add(tabbedPane);
		
		return principalPanel;
	}
	
//	public void addToBill(Keys key, int x, int y, int w, int h, MarketGUI market) {
//	JPanel panel = new BillArticlePanel(getName(key), MIN_SPACE_BETWEEN, MIN_SPACE_BETWEEN, 250, 50, null);
//	market.getBill().put(key,panel);
//	market.getBillPanel().add(panel);
//}
	
//	public JPanel paintMarketCard(int posX, int posY, int width, int height, int gap,GestionnaireKey key, ArrayList<?> elements, Color color) {
//		Object element = elements.get(0);
//		String[] infos = getInformation(element, key);
//		elements.remove(0);
//		String imagePath = infos[1];
//		JPanel card = paintRoundedPanel(posX, posY, width, height, null, GeneralPaintStrategy.RADIUS, color);
//
//		JLabel icone = new JLabel();
//		icone.setBounds(gap, gap, width - (2*gap), 100);
//		ImageIcon elementImage= new ImageIcon(imagePath);
//		icone.setIcon(elementImage);
//		icone.setHorizontalAlignment(JLabel.CENTER);
//
//		JLabel name = new JLabel();
//		name.setBounds(gap, 2 * gap + 100 , width - (2*gap), 50);
//		name.setText(infos[0]);
//		name.setHorizontalAlignment(JLabel.CENTER);
//
//		JButton acheter = paintButton(3*gap, 3 * gap + 150, width - (3*gap), 50, Gestionnaire.DARK_GREEN, Gestionnaire.MEDIUM_GREEN,null, "Ajouter au panier");
//
//		card.add(acheter);
//		card.add(icone);
//		card.add(name);
//		return card;
//	}

}
