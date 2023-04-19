package gui.gestionnaire;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import data.gestion.GestionnaireFinancier;
import process.transaction.Achat;
import process.transaction.Vente;

public class GestionnaireFinancierGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 1000;
	public static int HEIGHT = 620;
	public static int ROW_COUNT = 5;
	public static int COLUMN_COUNT = 1;
	
	private HashMap<String, JPanel> tabs = new HashMap<>();
	private ArrayList<Container> containers = new ArrayList<>();
	
	private GestionnaireFinancier gestionnaireFinancier = GestionnaireFinancier.getInstance();
	
	private GeneralPaintStrategy generalPaintStrategy = new GeneralPaintStrategy();
	
	public GestionnaireFinancierGUI(JFrame parent) {
		
		int width = 600;
		int height = 450;
		
		JPanel principalPanel = paintFinanceManger(GeneralPaintStrategy.MIN_SPACE_BETWEEN, GeneralPaintStrategy.MIN_SPACE_BETWEEN, width, height,ROW_COUNT, COLUMN_COUNT, GeneralPaintStrategy.MIN_SPACE_BETWEEN, GeneralPaintStrategy.LIGHT_BROWN);
		
		Container container = getContentPane();
		container.setLayout(null);
		container.setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		container.add(principalPanel);
		
	    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	    addWindowListener(new WindowDispose(this, parent));
		
		setSize(1050,560);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//JTabbedPane tabbedPane = generalPaintStrategy.paintTabbedPane(GeneralPaintStrategy.MIN_SPACE_BETWEEN, GeneralPaintStrategy.MIN_SPACE_BETWEEN, width, height, GeneralPaintStrategy.MEDIUM_BROWN, GeneralPaintStrategy.DARK_BROWN);
	}
	
	//ok
	public JLayeredPane paintCardsContainer(int x, int y, int w, int h,int cardWidth, int cardHeight, int rowCount, int columnCount, CardLayout cardLayout, int radius, Color color, String type, int gap) {
		int cardCount = 0;
		ArrayList<?> elements = null;
		if (type.equals("Achat")) {
			elements = gestionnaireFinancier.getAchats();
			cardCount = gestionnaireFinancier.getAchats().size();
		} else if (type.equals("Vente")){
			elements = gestionnaireFinancier.getVentes();
			cardCount = gestionnaireFinancier.getVentes().size();
		}
		JLayeredPane cardContainer = generalPaintStrategy.paintLayeredPane(x, y, w, h, cardLayout);
		JPanel innerContainer = generalPaintStrategy.paintNormalPanel(x, y, w, h, null);
		if (cardCount > 0 ) {
			if(cardCount <= (rowCount * columnCount)) {
				innerContainer = fillCardContainer(innerContainer, cardCount, type, cardWidth, cardHeight, columnCount, gap, color,elements);
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
					innerContainer = generalPaintStrategy.paintNormalPanel(gap, gap, w - gap, h - columnCount, null);
					innerContainer = fillCardContainer(innerContainer, set, type, cardWidth, cardHeight, columnCount, gap, color,elements);
					cardContainer.add(innerContainer);
				}
			}
		}
		return cardContainer;
	}
	
	
	public JPanel paintFinanceManger(int x, int y, int width, int height, int rowCount, int columnCount, int gap, Color cardColor) {
		
		JPanel principalPanel = generalPaintStrategy.paintNormalPanel(x, y, WIDTH, HEIGHT, GeneralPaintStrategy.LIGHT_BROWN);
		
		CardLayout cardLayout = new CardLayout();
		
		// 20 
		int radius = 20;
		// 10
		int posX = 10;
		int posY = 10;
		
		int labelWidth = 40;
		
		int tabX = 2*GeneralPaintStrategy.MIN_SPACE_BETWEEN + labelWidth;
		int tabY = GeneralPaintStrategy.MIN_SPACE_BETWEEN;
		
		JPanel achatsPanel = generalPaintStrategy.paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT,GeneralPaintStrategy.MEDIUM_BROWN);
		JPanel ventesPanel = generalPaintStrategy.paintNormalPanel(10, 10, GeneralPaintStrategy.WIDTH, GeneralPaintStrategy.HEIGHT, GeneralPaintStrategy.MEDIUM_BROWN);

		JTabbedPane tabbedPane = generalPaintStrategy.paintTabbedPane(tabX, tabY, width, height + 3*GeneralPaintStrategy.MIN_SPACE_BETWEEN, GeneralPaintStrategy.LIGHT_BROWN, GeneralPaintStrategy.DARK_BROWN);
		
		tabs.put("Achats", achatsPanel);
		tabs.put("Ventes", ventesPanel);
		
		tabbedPane = generalPaintStrategy.paintTabs(tabbedPane, tabs);
		
		JLayeredPane achatsCards = paintCardsContainer(posX, posY, width, height, width - 2*GeneralPaintStrategy.MIN_SPACE_BETWEEN, (height/ROW_COUNT) - 2*GeneralPaintStrategy.MIN_SPACE_BETWEEN, ROW_COUNT,COLUMN_COUNT, 
				cardLayout, radius, GeneralPaintStrategy.LIGHT_BROWN, "Achat", GeneralPaintStrategy.MIN_SPACE_BETWEEN);
		achatsPanel.add(achatsCards);
		containers.add(achatsCards);	
		
		JLayeredPane ventesCards = paintCardsContainer(posX, posY, width, height, width - 2*GeneralPaintStrategy.MIN_SPACE_BETWEEN, height - 2*GeneralPaintStrategy.MIN_SPACE_BETWEEN, ROW_COUNT, COLUMN_COUNT, 
				cardLayout, radius, GeneralPaintStrategy.LIGHT_BROWN, "Vente", GeneralPaintStrategy.MIN_SPACE_BETWEEN);
		ventesPanel.add(ventesCards);
		containers.add(ventesCards);
		
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
	
	// ok
	public JPanel fillCardContainer(JPanel innerContainer, int cardCount,String type, int cardWidth, int cardHeight, int columnCount, int gap, Color color, ArrayList<?> elements) {
		if (elements != null ) {
			int cpt = 0;
			int posX = 0;
			int posY = 0;
			for (int i = 0; i < cardCount; i++) {
				JPanel card;
				card = paintTransactionCard(posX, posY,cardWidth, cardHeight, gap, type, elements, color);
				if (card != null) {
					innerContainer.add(card);
					cpt++;
					posX += cardWidth + gap;
					if (cpt == columnCount) {
						cpt = posX = 0;
						posY += gap + cardHeight;
					}	
				}
			}
		}
		return innerContainer;			
	}
	
	public JPanel paintTransactionCard(int posX, int posY, int width, int height, int gap, String type, ArrayList<?> elements, Color color) {
		String[] infos;
		Object element = elements.get(0);
		infos = getInformation(element);
		elements.remove(0);
		Float size = Float.valueOf(infos[2]);
		if (size != 0) {
			JPanel card = generalPaintStrategy.paintRoundedPanel(posX, posY, width, height, new FlowLayout(FlowLayout.LEADING, 10, 10), GeneralPaintStrategy.RADIUS, color);
			
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
			transactionType.setText(infos[0] + " " +elements.size());
			transactionType.setHorizontalAlignment(JLabel.CENTER);
			transactionType.setVerticalAlignment(JLabel.CENTER);
			transactionType.setForeground(GeneralPaintStrategy.DARK_BROWN);
			
			card.add(icone);
			card.add(amount);
			card.add(doro);
			card.add(transactionType);
			
			return card;
		}
		return null;
	}
	
	public String[] getInformation(Object transaction) {
		String[] infos = new String[3];
		infos[0] = transaction.getClass().getSimpleName();
		switch (infos[0]) {
			case "Achat":
				Achat achat = (Achat) transaction;
				infos[1] = "src"+File.separator+"ressources"+File.separator+"-.png";
				infos[2] = String.valueOf(achat.getTotalCost());
				break;
			case "Vente":
				Vente vente = (Vente) transaction;
				infos[1] = "src"+File.separator+"ressources"+File.separator+"+.png";
				infos[2] = String.valueOf(vente.getTotalCost());
				break;
			default:
				infos[0] = "Non reconnu";
				infos[1] = "src"+File.separator+"ressources"+File.separator+"default-image.png";
				infos[2] = "/";
				break;
		}
		return infos;
	}
	
}