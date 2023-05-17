package gui.gestionnaire;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import data.acteur.Employee;
import data.configuration.GameConfiguration;
import data.finance.Charge;
import data.gestion.GestionnaireAnimaux;
import data.gestion.GestionnaireEnclos;
import data.gestion.GestionnaireFinancier;
import data.gestion.GestionnaireMateriel;
import data.gestion.GestionnaireRH;
import data.gestion.GestionnaireStocks;
import data.gestion.GestionnaireStructures;
import data.gestion.GestionnaireTerrains;
import data.notion.basic.Farm;
import data.production.Produits;
import gui.Farm.FarmPaintStrategy;
import gui.Farm.MainGuiTest;
import gui.gestionnaire.UI.TabbedPaneUI;
import gui.gestionnaire.contolleurs.AddToCart;
import gui.gestionnaire.contolleurs.Next;
import gui.gestionnaire.contolleurs.Previous;
import gui.gestionnaire.gestionnairesGUI.GestionnaireStocksGUI;
import gui.gestionnaire.gestionnairesGUI.MarketGUI;
import gui.gestionnaire.gestionnairesGUI.RHManagerGUI;
import gui.gestionnaire.keys.*;
import process.gestion.transaction.Achat;
import process.gestion.transaction.FinanceManager;
import process.gestion.transaction.Transaction;
import process.gestion.transaction.Vente;

public class GeneralPaintStrategy {
	
	private MainGuiTest frame;

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
	public static int FINANCE_MANAGER_ROW_COUNT = 5;
	public static int FINANCE_MANAGER_COLUMN_COUNT = 1;
	private HashMap<String, JPanel> tabs = new HashMap<>();

	public static Font font = new Font("Sans serif", Font.BOLD, 16);
	private ArrayList<Container> containers = new ArrayList<>();
	private int counter = 0;

	private JPanel principalPanel;
	private JPanel terrainsPanel = new JPanel();
	private JPanel enclosPanel = new JPanel();

	private ArrayList<Color> statColors = new ArrayList<>();
	
	public GeneralPaintStrategy(MainGuiTest frame) {
		this.frame = frame;
	}
	
	public GeneralPaintStrategy() {
	}
		
	public ArrayList<Color> getStatColors(){
		initStatColors();
		return statColors;
	}

	public void initStatColors() {
		statColors.add(LIGHT_BROWN);
		statColors.add(MEDIUM_GREEN);
		statColors.add(new Color(82, 121, 112));
		statColors.add(new Color(133, 69, 144 ));
		statColors.add(new Color(255, 195, 0 ));				
		statColors.add(DARK_BROWN);
		statColors.add(RED);
	}

	//ok
	public static JPanel paintNormalPanel(int x, int y, int w, int h, Color color) {
		JPanel panel = new JPanel();
		panel.setBounds(x,y,w,h);
		panel.setBackground(color);
		panel.setLayout(null);
		return panel;
	}

	//ok
	public static JLayeredPane paintLayeredPane(int x, int y, int w, int h, LayoutManager layout) {
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
	public static JPanel paintRoundedPanel(int x, int y, int w, int h,LayoutManager layout, int radius, Color color) {
		RoundedPanel roundedPanel = new RoundedPanel(layout, radius);
		roundedPanel.setOpaque(false);
		roundedPanel.setBackground(color);
		roundedPanel.setBounds(x,y,w,h);
		return roundedPanel;
	}

	//ok
	public static JLabel printImageLabel(String price, int x, int y, int w, int h, String imagePath, Font font) {
		ImageIcon icon = new ImageIcon(imagePath);

		JLabel label= new JLabel() {
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				super.paintComponent(g);
				if (font == null) {
					setFont(getFont());
				} else {
					setFont(font);
				}
			}
		};

		label.setBounds(x, y, w, h);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setOpaque(false);
		label.setText(price);
		return label;
	}

	//ok
	public static JTabbedPane paintTabbedPane(int x, int y, int w, int h,Color backgroundColor, Color foregroundColor) {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setUI(new TabbedPaneUI());
		tabbedPane.setBackground(backgroundColor);
		tabbedPane.setForeground(foregroundColor);
		tabbedPane.setBounds(x,y,w,h);
		return tabbedPane;
	}

	//ok
	public static JTabbedPane paintTabs(JTabbedPane tabbedPane, HashMap<String,JPanel> composants) {
		for (String title : composants.keySet()) {
			tabbedPane.addTab(title, composants.get(title));
		}
		return tabbedPane;
	}

	//ok
	public JButton paintButton(int x, int y, int w, int h, Color bgColor, Color fgColor, Font font, String title) {
		JButton button = new JButton(title);
		button.setBounds(x, y, w, h);
		button.setBackground(bgColor);
		button.setForeground(fgColor);
		button.setFont(font);
		return button;
	}

	//ok
	public JButton paintButton(Color bgColor, Color fgColor, Font font, String title) {
		JButton button = new JButton(title);
		button.setBackground(bgColor);
		button.setForeground(fgColor);
		button.setFont(font);
		return button;
	}

	public void fillContainers(PaintKeys key, int x, int y, int w, int h, int cardWidth, int cardHeight, int rowCount, int columnCount, CardLayout cardLayout, int radius, Color cardColor,int gap, Transaction achat, MarketGUI market ) {
		if (key.equals(PaintKeys.EMPLOY)){
			JLayeredPane recruterCards = paintCardsContainer(x, y, w, h, cardWidth, cardHeight, rowCount, columnCount, 
					cardLayout, radius, cardColor, GestionnaireKey.RECRUT, gap, key, achat, market);
			tabs.get("Recrutement").add(recruterCards);
			containers.add(recruterCards);

			JLayeredPane mesEmployesCards = paintCardsContainer(x, y, w, h, cardWidth, cardHeight, rowCount, columnCount, 
					cardLayout, radius, cardColor, GestionnaireKey.EMPLOYE, gap, key, achat, null);
			tabs.get("Mes Employes").add(mesEmployesCards);
			containers.add(mesEmployesCards);

		} else if (key.equals(PaintKeys.FINANCE)) {
			JLayeredPane achatsCards = paintCardsContainer(x, y, w, h, cardWidth, cardHeight, rowCount, columnCount, 
					cardLayout, radius, cardColor, GestionnaireKey.ACHAT, gap, key, achat, null);
			tabs.get("Achats").add(achatsCards);
			containers.add(achatsCards);	

			JLayeredPane ventesCards = paintCardsContainer(x, y, w, h, cardWidth, cardHeight, rowCount, columnCount, 
					cardLayout, radius, cardColor, GestionnaireKey.VENTE, gap, key, achat, null);
			tabs.get("Ventes").add(ventesCards);
			containers.add(ventesCards);

			JLayeredPane chargesCards = paintCardsContainer(x, y, w, h, cardWidth, cardHeight, rowCount, columnCount, 
					cardLayout, radius, cardColor, GestionnaireKey.CHARGE, gap, key, achat, null);
			tabs.get("Charges").add(chargesCards);
			containers.add(chargesCards);
		} else {
			JLayeredPane plantsCards = paintCardsContainer(x, y, w, h, cardWidth, cardHeight, rowCount, columnCount, 
					cardLayout, radius, cardColor, GestionnaireKey.SEEDS, gap, key, achat, market);
			tabs.get("Plantes").add(plantsCards);
			containers.add(plantsCards); 

			JLayeredPane animalsCards = paintCardsContainer(x, y, w, h, cardWidth, cardHeight, rowCount, columnCount, 
					cardLayout, radius, cardColor, GestionnaireKey.ANIMALS, gap, key, achat, market);
			tabs.get("Animaux").add(animalsCards);
			containers.add(animalsCards);

			JLayeredPane outilsCards = paintCardsContainer(x, y, w, h, cardWidth, cardHeight, rowCount, columnCount, 
					cardLayout, radius, cardColor, GestionnaireKey.OUTILS, gap, key, achat, market);
			tabs.get("Outils").add(outilsCards);
			containers.add(outilsCards);

			if (!key.equals(PaintKeys.SELL)) {
				JLayeredPane structuresCards = paintCardsContainer(x, y, w, h, cardWidth, cardHeight, rowCount, columnCount, 
						cardLayout, radius, cardColor, GestionnaireKey.STRUCTURES, gap, key, achat, market);
				tabs.get("Structures").add(structuresCards);
				containers.add(structuresCards);	
			}

			if (!key.equals(PaintKeys.BUY)){
				JLayeredPane productsCards = paintCardsContainer(x, y, w, h, cardWidth, cardHeight, rowCount, columnCount, 
						cardLayout, radius, cardColor, GestionnaireKey.PRODUIT, gap, key, achat, market);
				tabs.get("Produits").add(productsCards);
				containers.add(productsCards);	
			}else {
				JLayeredPane terrainsCards = paintCardsContainer(x, y, w, h, cardWidth, cardHeight, rowCount, columnCount, 
						cardLayout, radius, cardColor, GestionnaireKey.TERRAINS, gap, key, achat, market);
				tabs.get("Terrains").add(terrainsCards);
				containers.add(terrainsCards);

				JLayeredPane enclosCards = paintCardsContainer(x, y, w, h, cardWidth, cardHeight, rowCount, columnCount, 
						cardLayout, radius, cardColor, GestionnaireKey.ENCLOS, gap, key, achat, market);
				tabs.get("Enclos").add(enclosCards);
				containers.add(enclosCards);
			}

			JLayeredPane enginsCards = paintCardsContainer(x, y, w, h, cardWidth, cardHeight, rowCount, columnCount, 
					cardLayout, radius, cardColor, GestionnaireKey.ENGINS, gap, key, achat, market);
			tabs.get("Engins").add(enginsCards);
			containers.add(enginsCards);	

		}
	}

	//ok
	public JLayeredPane paintCardsContainer(int x, int y, int w, int h,int cardWidth, int cardHeight, int rowCount, int columnCount, CardLayout cardLayout, int radius, Color color, GestionnaireKey key, int gap,PaintKeys type, Transaction achat, MarketGUI market) {
		int cardCount = 0;
		ArrayList<?> elements = getElements(type, key);
		if (elements != null) {
			cardCount = elements.size();
		}
		counter = cardCount - 1;
		JLayeredPane cardContainer = paintLayeredPane(x, y, w, h, cardLayout);
		JPanel innerContainer = paintNormalPanel(x, y, w, h, null);
		if (cardCount > 0 ) {
			if(cardCount <= (rowCount * columnCount)) {
				innerContainer = fillCardContainer(innerContainer, cardCount, key, cardWidth, cardHeight, columnCount, gap, color,type, achat, market, elements);
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
					innerContainer = fillCardContainer(innerContainer, set, key, cardWidth, cardHeight, columnCount, gap, color,type, achat, market, elements);
					cardContainer.add(innerContainer);
				}
			}
		}
		return cardContainer;
	}

	//ok
	public JPanel fillCardContainer(JPanel innerContainer, int cardCount, GestionnaireKey key, int cardWidth, int cardHeight, int columnCount, int gap, Color color,PaintKeys type, Transaction achat, MarketGUI market, ArrayList<?> elements) {
		if (elements != null ) {
			int cpt = 0;
			int posX = 0;
			int posY = 0;
			for (int i = 0; i < cardCount; i++) {
				JPanel card = paintCard(posX, posY, cardWidth, cardHeight, gap, key, elements, color, type, achat, market);
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


	@SuppressWarnings("unchecked")
	public JPanel paintCard(int posX, int posY, int width, int height, int gap,GestionnaireKey key, ArrayList<?> elements, Color color, PaintKeys type, Transaction transaction, MarketGUI market) {
		switch (type) {
		case FINANCE: //charges,transaction
			return paintTransactionCard(posX, posY, width, height, gap, elements, color);
		case BUY: //achat
			return paintMarketCard(posX, posY, width, height, gap,type, key, (ArrayList<Keys>) elements, color, (Achat) transaction, market);
		case STOCK: // stock
			return paintManagerCard(posX, posY, width, height, gap, key, (ArrayList<Keys>) elements, color);
		case EMPLOY: // employe, recruter
			return paintMarketCard(posX, posY, width, height, gap,type, key, (ArrayList<Keys>) elements, color, (Achat) transaction, market);
		case SELL: //vente
			return paintMarketCard(posX, posY, width, height, gap,type, key, (ArrayList<Keys>) elements, color, (Vente) transaction, market);
		default:
			return null;
		}
	}

	//ok
	public JPanel paintTransactionCard(int posX, int posY, int width, int height, int gap, ArrayList<?> elements, Color color) {
		String[] infos;
		Object element = elements.get(counter);
		infos = getFinancialInformation(element);
		Float size = Float.valueOf(infos[2]);
		if (size != 0) {
			JPanel card = paintRoundedPanel(posX, posY, width, height, new FlowLayout(FlowLayout.LEADING, 10, 10), GeneralPaintStrategy.RADIUS, color);

			JLabel icone = new JLabel();
			icone.setPreferredSize(new Dimension(40,40));
			icone.setIcon(new ImageIcon(infos[1]));
			icone.setHorizontalAlignment(JLabel.CENTER);

			JLabel amount = new JLabel();
			amount.setPreferredSize(new Dimension(100, 40));
			amount.setText(infos[2]);
			amount.setHorizontalAlignment(JLabel.CENTER);
			amount.setVerticalAlignment(JLabel.CENTER);
			amount.setForeground(GeneralPaintStrategy.DARK_BROWN);

			JLabel doro = new JLabel();
			doro.setPreferredSize(new Dimension(40,40));
			doro.setIcon(new ImageIcon("src"+File.separator+"ressources"+File.separator+"doro.png"));
			doro.setHorizontalAlignment(JLabel.CENTER);
			doro.setVerticalAlignment(JLabel.CENTER);

			JLabel transactionType = new JLabel();
			transactionType.setPreferredSize(new Dimension(100, 40));
			transactionType.setText(infos[0]);
			transactionType.setHorizontalAlignment(JLabel.CENTER);
			transactionType.setVerticalAlignment(JLabel.CENTER);
			transactionType.setForeground(GeneralPaintStrategy.DARK_BROWN);

			card.add(icone);
			card.add(amount);
			card.add(doro);
			card.add(transactionType);

			if (!infos[0].equals("Achat") && !infos[0].equals("Vente")) {
				Charge charge = (Charge) element;
				if ( !charge.isPaid()) {
					JLabel delais = new JLabel();
					delais.setPreferredSize(new Dimension(100,40));
					delais.setText(" Delais : " + String.valueOf(charge.getDelais()) + "j");
					delais.setHorizontalAlignment(JLabel.CENTER);
					delais.setVerticalAlignment(JLabel.CENTER);
					JButton pay = paintButton(DARK_GREEN, Color.WHITE, null, "Payer");
					pay.setPreferredSize(new Dimension(100,40));
					pay.addActionListener(new Pay(charge, pay));
					card.add(delais);
					card.add(pay);
				}
			}

			return card;
		}
		return null;
	}

	//ok
	public String[] getFinancialInformation(Object element) {
		String[] infos = new String[3];
		infos[0] = element.getClass().getSimpleName();
		switch (infos[0]) {
		case "Achat":
			Achat achat = (Achat) element;
			infos[1] = "src"+File.separator+"ressources"+File.separator+"-.png";
			infos[2] = String.valueOf(achat.getTotalCost());
			break;
		case "Vente":
			Vente vente = (Vente) element;
			infos[1] = "src"+File.separator+"ressources"+File.separator+"+.png";
			infos[2] = String.valueOf(vente.getTotalCost());
			break;
		case "Charge":
			Charge charge = (Charge) element;
			infos[0] = charge.getType();
			infos[1] = "src"+File.separator+"ressources"+File.separator+infos[0]+".png";
			infos[2] = String.valueOf(charge.getMontant());
			break;
		default:
			infos[0] = "Non reconnu";
			infos[1] = "src"+File.separator+"ressources"+File.separator+"default-image.png";
			infos[2] = "0";
			break;
		}
		return infos;
	}

	// normalement ok
	public ArrayList<?> getElements(PaintKeys type, GestionnaireKey key){
		switch(type) {
		case FINANCE:
			if (key.equals(GestionnaireKey.CHARGE)) {
				ArrayList<Charge> charges = new ArrayList<>();
				if (GestionnaireFinancier.getInstance().getCharges() != null) {
					charges.addAll(0,GestionnaireFinancier.getInstance().getCharges());
				}
				if (FinanceManager.getInstance().getCharges() != null) {
					charges.addAll(FinanceManager.getInstance().getCharges());
				} 
				return charges;
			} else {
				return key.getElements();
			}
		case STOCK:
			return getElementsByKey(key);
		case BUY:
			return key.getArticles();		
		case SELL:
			return getElementsByKey(key);
		case EMPLOY:
			return getElementsByKey(key);
		default: 
			return null;
		}
	}

	// ok
	public JPanel paintManagerCard(int posX, int posY, int width, int height, int gap, GestionnaireKey key, ArrayList<Keys> elements, Color color) {
		Keys element = (Keys) elements.get(counter);
		String[] infos = getInformation(element, key);
		if ( infos != null ) {
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
		}
		return null;
	}

	// ok
	public static String[] getInformation(Keys element, GestionnaireKey key) {
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
		case "TERRAINS":
			infos[0] = "Terrain";
			infos[1] = "src"+File.separator+"ressources"+File.separator+"Terrain"+File.separator+"terrain.png";
			infos[2] = String.valueOf(GestionnaireTerrains.getInstance().getSize());	
			break;
		case "ENCLOS":
			infos[0] = "Enclos";
			infos[1] = "src"+File.separator+"ressources"+File.separator+"Enclos"+File.separator+"entier.png";
			infos[2] = String.valueOf(GestionnaireEnclos.getInstance().getSize());	
			break;
		case "PRODUIT":
			Produits produit = (Produits)element;
			infos[0] = produit.toString();
			if (infos[0].equals("Water")) {
				infos = null;
				break;
			}
			infos[1] = "src"+File.separator+"ressources"+File.separator+"Employee"+File.separator+infos[0]+".png";
			infos[2] = String.valueOf(GestionnaireStocks.getInstance().getProduits().get(element));
			break;
		default:
			infos[0] = "Non reconnu";
			infos[1] = "src"+File.separator+"ressources"+File.separator+"default-image.png";
			infos[2] = "0";
			break;
		}
		return infos;
	}

	//ok
	public String[] getArticleInformation(Keys element, GestionnaireKey key, PaintKeys type) {
		String name = key.name();
		String[] infos = new String[4];
		switch (name) {
		case "OUTILS":
			Outils outil = (Outils)element;
			infos[0] = getName(outil);
			infos[1] = "src"+File.separator+"ressources"+File.separator+"Outils"+File.separator+outil.name()+".png";
			if (type.equals(PaintKeys.BUY)) {
				infos[2] = String.valueOf(outil.getPrixAchat());
			} else {
				infos[2] = String.valueOf(outil.getPrixVente());
			}
			break;
		case "ENGINS":
			Engins engin = (Engins)element;
			infos[0] = getName(engin);
			infos[1] = "src"+File.separator+"ressources"+File.separator+"Engins"+File.separator+engin.name()+".png";
			if (type.equals(PaintKeys.BUY)) {
				infos[2] = String.valueOf(engin.getPrixAchat());
			} else {
				infos[2] = String.valueOf(engin.getPrixVente());
			}
			break;
		case "SEEDS":
			Graine graine = (Graine)element;
			infos[0] = getName(graine);
			infos[1] = "src"+File.separator+"ressources"+File.separator+"Graine"+File.separator+graine.name()+".png"; 
			if (type.equals(PaintKeys.BUY)) {
				infos[2] = String.valueOf(graine.getPrixAchat());
			} else {
				infos[2] = String.valueOf(graine.getPrixVente());
			}
			break;
		case "STRUCTURES":
			Structures structure = (Structures)element;
			infos[0] = getName(structure);
			infos[1] = "src"+File.separator+"ressources"+File.separator+"Structure"+File.separator+structure.name()+".png";	
			infos[2] = String.valueOf(structure.getPrixAchat());
			break;
		case "ANIMALS":
			Animals animal = (Animals)element;
			infos[0] = getName(animal);
			infos[1] = "src"+File.separator+"ressources"+File.separator+infos[0]+File.separator+"standJeune.png";
			if (type.equals(PaintKeys.BUY)) {
				infos[2] = String.valueOf(animal.getPrixAchat());
			} else {
				infos[2] = String.valueOf(animal.getPrixVente());
			}
			break;
		case "TERRAINS":
			Terrains terrain = (Terrains)element;
			infos[0] = "Terrain";
			infos[1] = "src"+File.separator+"ressources"+File.separator+infos[0]+File.separator+"terrain.png";
			infos[2] = String.valueOf(terrain.getPrixAchat());
			break;
		case "ENCLOS":
			Encloss enclos = (Encloss)element;
			infos[0] = "Enclos";
			infos[1] = "src"+File.separator+"ressources"+File.separator+infos[0]+File.separator+"entier.png";
			infos[2] = String.valueOf(enclos.getPrixAchat());
			break;
		case "EMPLOYE":
			Employees employee = (Employees)element;
			infos[0] = employee.toString();
			infos[1] = "src"+File.separator+"ressources"+File.separator+"Employee"+File.separator+infos[0]+".png";
			infos[2] = String.valueOf(employee.getPrixAchat());
			break;
		case "RECRUT":
			Employees employe = (Employees)element;
			infos[0] = employe.toString();
			infos[1] = "src"+File.separator+"ressources"+File.separator+"Employee"+File.separator+infos[0]+".png";
			infos[2] = String.valueOf(employe.getPrixAchat());
			break;
		case "PRODUIT":
			Produits produit = (Produits)element;
			infos[0] = produit.toString();
			if (infos[0].equals("Water")) {
				infos = null;
				break;
			}
			infos[1] = "src"+File.separator+"ressources"+File.separator+"Produits"+File.separator+infos[0]+".png";
			infos[2] = String.valueOf(produit.getPrixVente());
			break;
		default:
			infos[0] = "Non reconnu";
			infos[1] = "src"+File.separator+"ressources"+File.separator+"default-image.png";
			infos[2] = "0";
			break;
		}
		return infos;
	}

	public JPanel paintMarketCard(int posX, int posY, int width, int height, int gap,PaintKeys type, GestionnaireKey key, ArrayList<Keys> elements, Color color, Transaction achat, MarketGUI market) {
		Keys element = elements.get(counter);
		String[] infos = getArticleInformation(element, key, type);
		if ( infos != null ) {
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
			
			String image = "src"+File.separator+"ressources"+File.separator+"price.png";
			JLabel price = printImageLabel(infos[2], 5*gap, 2 * gap + 125, width - (8*gap), 25, image, null);
			
			JButton acheter;
			if (!type.equals(PaintKeys.SELL)) {
				if (key.equals(GestionnaireKey.RECRUT)) {
					acheter = paintButton(2*gap, 2 * gap + 160, width - (4*gap), 30, GestionnaireStocksGUI.DARK_GREEN, Color.WHITE,null, "Recruter");
					acheter.addActionListener(new Recruter((Employees) element, acheter));
				}else if (key.equals(GestionnaireKey.EMPLOYE)){
					acheter = paintButton(2*gap, 2 * gap + 160, width - (4*gap), 30, GestionnaireStocksGUI.DARK_GREEN, Color.WHITE,null, "Consulter");
					acheter.addActionListener(new Consulter((Employees) element, acheter));
				} else {
					acheter = paintButton(2*gap, 2 * gap + 160, width - (4*gap), 30, GestionnaireStocksGUI.DARK_GREEN, Color.WHITE,null, "Ajouter au panier");
					acheter.addActionListener(new AddToCart(element, market,type));
				}	
			}else {
				acheter = paintButton(2*gap, 2 * gap + 160, width - (4*gap), 30, GestionnaireStocksGUI.DARK_GREEN, Color.WHITE,null, "Vendre");
				acheter.addActionListener(new AddToCart(element, market,type));
			}
			
			card.add(icone);
			card.add(name);
			card.add(price);
			card.add(acheter);
			return card;
		}
		return null;
	}

	//ok
	public static String getName(Keys key) {
		String title = key.name().replace('_', ' ');
		title = title.substring(0, 1).toUpperCase() + title.substring(1).toLowerCase();
		return title;
	}

	//ok
	public Integer getQuantity(Keys key, Achat achat) {
		if ( achat != null) {
			return achat.getCart().get(key);
		} else {
			return 0;
		}
	}

	//ok
	public JPanel paintGestionnaire(int width, int height, int rowCount, int columnCount, int gap, int cardWidth, int cardHeight, Color cardColor, PaintKeys type, Transaction transaction, MarketGUI market, int tab) {
		principalPanel = paintNormalPanel(0, 0, WIDTH, HEIGHT, GeneralPaintStrategy.LIGHT_BROWN);

		CardLayout cardLayout = new CardLayout();

		int radius = 20;

		int posX = 10;
		int posY = 10;

		int labelWidth = 40;

		int tabX = 2*MIN_SPACE_BETWEEN + labelWidth;
		int tabY = MIN_SPACE_BETWEEN;

		fillTabs(type);

		JTabbedPane tabbedPane = paintTabbedPane(tabX, tabY, width, height + 3*MIN_SPACE_BETWEEN, GeneralPaintStrategy.LIGHT_BROWN, GeneralPaintStrategy.DARK_BROWN);
		tabbedPane = paintTabs(tabbedPane, tabs);

		fillContainers(type, posX, posY, width, height, cardWidth, cardHeight, rowCount, columnCount, cardLayout, radius, cardColor, gap, transaction, market);

		if (!type.equals(PaintKeys.EMPLOY)) {
			JLabel next = new JLabel();
			next.setBounds(posX + width + 2*gap + labelWidth, (HEIGHT - tabX - labelWidth)/2, labelWidth, labelWidth);
			ImageIcon nextIcone= new ImageIcon("src"+File.separator+"ressources"+File.separator+"next0.png");
			next.setIcon(nextIcone);	
			principalPanel.add(next);
			next.addMouseListener(new Next(cardLayout, tabbedPane,containers));

			JLabel previous = new JLabel();
			previous.setBounds(gap,(HEIGHT - tabX - labelWidth)/2,labelWidth,labelWidth);
			ImageIcon previousIcone= new ImageIcon("src"+File.separator+"ressources"+File.separator+"previous0.png");
			previous.setIcon(previousIcone);	
			principalPanel.add(previous);
			previous.addMouseListener(new Previous(cardLayout, tabbedPane, containers));	
		}

		principalPanel.add(tabbedPane);
		tabbedPane.setSelectedIndex(tab);

		return principalPanel;
	}

	public void fillTabs(PaintKeys key) {
		if (key.equals(PaintKeys.EMPLOY)){
			JPanel recruterPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT,GeneralPaintStrategy.MEDIUM_BROWN);
			JPanel mesEmployesPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);

			tabs.put("Recrutement", recruterPanel);
			tabs.put("Mes Employes", mesEmployesPanel);
		} else if (key.equals(PaintKeys.FINANCE)){
			JPanel achatsPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT,GeneralPaintStrategy.MEDIUM_BROWN);
			JPanel ventesPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);
			JPanel chargesPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);

			tabs.put("Achats", achatsPanel);
			tabs.put("Ventes", ventesPanel);
			tabs.put("Charges", chargesPanel);
		} else {
			JPanel animalsPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT,GeneralPaintStrategy.MEDIUM_BROWN);
			JPanel plantsPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);
			JPanel outilsPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);
			JPanel enginsPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);
			tabs.put("Plantes", plantsPanel);
			tabs.put("Animaux", animalsPanel);
			tabs.put("Outils", outilsPanel);
			tabs.put("Engins", enginsPanel);

			if (!key.equals(PaintKeys.SELL)) {
				JPanel structuresPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);
				tabs.put("Structures", structuresPanel);
			}
			if (!key.equals(PaintKeys.BUY)){
				JPanel productsPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);
				tabs.put("Produits", productsPanel);
			}

			if (key.equals(PaintKeys.BUY)) {
				terrainsPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);
				tabs.put("Terrains", terrainsPanel);

				enclosPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);
				tabs.put("Enclos", enclosPanel);
			}
		}
	}

	private class Pay implements ActionListener{

		private Charge charge;
		private JButton button;

		public Pay(Charge charge, JButton button) {
			this.charge = charge;
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			charge.payer();
			FinanceManager.getInstance().remove(charge);
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(button);
			button.getParent().revalidate();
			button.getParent().repaint();
			frame.dispose();
			new InfosTransaction("Charge payée", frame);

		}

	}

	private class Recruter implements ActionListener{

		private Employees employee;
		private JButton button;
		private GestionnaireRH gestionnaireRH = GestionnaireRH.getInstance();

		public Recruter(Employees key, JButton button) {
			this.button = button;
			employee = key;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Employee employe = gestionnaireRH.getARecruter().get(employee);
			gestionnaireRH.recruter(employee);
			Farm farm = frame.getFarm();
			int ligne = farm.getLigne()+ 1 + GameConfiguration.DIMENSION_STRUCUTRE;
			int colonne = farm.getColonne()-1 + farm.getWidth()/2 + gestionnaireRH.getEmployees().size();
			employe.setPosition(ligne, colonne);
			frame.getFarm().getElementManager().add(employe);
			RHManagerGUI rhManagerGUI = (RHManagerGUI)SwingUtilities.getWindowAncestor(button);
			Home frame = rhManagerGUI.getHome();
			new InfosTransaction(employee.toString() + " recruté !", frame);
			rhManagerGUI.dispose();
		}

	}

	private class Consulter implements ActionListener{

		private Employees employee;
		private JButton button;

		public Consulter(Employees key, JButton button) {
			employee = key;
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			button.getParent().revalidate();
			button.getParent().repaint();
			new InfosTransaction(employee.toString() + " infos !", null);
		}

	}


	public JPanel paintFinanceManger(int x, int y, int width, int height, int rowCount, int columnCount, int gap, Color cardColor) {

		JPanel principalPanel = paintNormalPanel(x, y, WIDTH, HEIGHT, GeneralPaintStrategy.LIGHT_BROWN);

		CardLayout cardLayout = new CardLayout();

		// 20 
		int radius = 20;
		// 10
		int posX = 10;
		int posY = 11;

		int labelWidth = 40;

		int tabX = 2*GeneralPaintStrategy.MIN_SPACE_BETWEEN + labelWidth;
		int tabY = GeneralPaintStrategy.MIN_SPACE_BETWEEN;

		JPanel achatsPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT,GeneralPaintStrategy.MEDIUM_BROWN);
		JPanel ventesPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);
		JPanel chargesPanel = paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);

		JTabbedPane tabbedPane = paintTabbedPane(tabX, tabY, width, height + 3*GeneralPaintStrategy.MIN_SPACE_BETWEEN, GeneralPaintStrategy.LIGHT_BROWN, GeneralPaintStrategy.DARK_BROWN);

		tabs.put("Achats", achatsPanel);
		tabs.put("Ventes", ventesPanel);
		tabs.put("Charges", chargesPanel);

		tabbedPane = paintTabs(tabbedPane, tabs);

		JLayeredPane achatsCards = paintCardsContainer(posX, posY, width, height, width - 2*GeneralPaintStrategy.MIN_SPACE_BETWEEN, (height/FINANCE_MANAGER_ROW_COUNT) - 2*GeneralPaintStrategy.MIN_SPACE_BETWEEN, FINANCE_MANAGER_ROW_COUNT,FINANCE_MANAGER_COLUMN_COUNT, 
				cardLayout, radius, GeneralPaintStrategy.LIGHT_BROWN, null, GeneralPaintStrategy.MIN_SPACE_BETWEEN, PaintKeys.ACHAT, null, null);
		achatsPanel.add(achatsCards);
		containers.add(achatsCards);	

		JLayeredPane ventesCards = paintCardsContainer(posX, posY, width, height, width - 2*GeneralPaintStrategy.MIN_SPACE_BETWEEN, (height/FINANCE_MANAGER_ROW_COUNT) - 2*GeneralPaintStrategy.MIN_SPACE_BETWEEN, FINANCE_MANAGER_ROW_COUNT,FINANCE_MANAGER_COLUMN_COUNT, 
				cardLayout, radius, GeneralPaintStrategy.LIGHT_BROWN, null, GeneralPaintStrategy.MIN_SPACE_BETWEEN, PaintKeys.VENTE, null, null);
		ventesPanel.add(ventesCards);
		containers.add(ventesCards);

		JLayeredPane chargesCards = paintCardsContainer(posX, posY, width, height, width - 2*GeneralPaintStrategy.MIN_SPACE_BETWEEN, (height/FINANCE_MANAGER_ROW_COUNT) - 2*GeneralPaintStrategy.MIN_SPACE_BETWEEN, FINANCE_MANAGER_ROW_COUNT,FINANCE_MANAGER_COLUMN_COUNT, 
				cardLayout, radius, GeneralPaintStrategy.LIGHT_BROWN, null, GeneralPaintStrategy.MIN_SPACE_BETWEEN, PaintKeys.CHARGE, null, null);
		chargesPanel.add(chargesCards);
		containers.add(chargesCards);

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


	public static ArrayList<?> getElementsByKey(GestionnaireKey key){
		switch (key) {
		case SEEDS :
			return new ArrayList<>(GestionnaireStocks.getInstance().getAvailableGraines());
		case STRUCTURES :
			return new ArrayList<>(GestionnaireStructures.getInstance().getStructures().keySet());
		case OUTILS :
			return new ArrayList<>(GestionnaireMateriel.getInstance().getOutils().keySet());
		case ENGINS :
			return new ArrayList<>(GestionnaireMateriel.getInstance().getEngins().keySet());
		case ANIMALS :
			return new ArrayList<>(GestionnaireAnimaux.getInstance().getAnimaux().keySet());
		case ENCLOS :
			return GestionnaireEnclos.getInstance().getEnclos();
		case TERRAINS :
			return new ArrayList<>(GestionnaireTerrains.getInstance().getTerrains().keySet());
		case EMPLOYE :
			return new ArrayList<>(GestionnaireRH.getInstance().getEmployees().keySet());
		case RECRUT :
			return new ArrayList<>(GestionnaireRH.getInstance().getARecruter().keySet());
		case CHARGE :
			return GestionnaireFinancier.getInstance().getCharges();
		case VENTE :
			return GestionnaireFinancier.getInstance().getVentes();
		case ACHAT :
			return GestionnaireFinancier.getInstance().getAchats();
		case PRODUIT :
			return new ArrayList<>(GestionnaireStocks.getInstance().getProduits().keySet());
		default:
			return null;
		}
	}

}
