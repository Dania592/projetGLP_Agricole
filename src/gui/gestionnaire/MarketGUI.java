package gui.gestionnaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

import gui.gestionnaire.keys.Keys;
import gui.gestionnaire.keys.WrapLayout;
import process.game.Game;


public class MarketGUI extends JFrame{

	private static final long serialVersionUID = 1L;

	public static int MARKET_ROW_COUNT = 2;
	public static int MARKET_COLUMN_COUNT = 3;
	public static int MARKET_CARD_WIDTH = 182;
	public static int MARKET_CARD_HEIGHT = 222;
	public static int MIN_SPACE_BETWEEN = 10;
	public static int WIDTH = 600;
	public static int HEIGHT = 530;
	public static int BILL_WIDTH = 300;
	public static int BILL_HEIGHT = 475;

	public static Color MARKET_CARD_COLOR = Gestionnaire.LIGHT_BROWN;

	private GeneralPaintStrategy gestionnairePaintStrategy = new GeneralPaintStrategy();
	private HashMap<Keys, BillArticlePanel> bill = new HashMap<>();
	private JPanel billContainer;
	private JPanel billPanel;
	private ValidationPanel validationPanel;
	private Game game = new Game();

	public void paintBill(int x, int y, int w, int h) {
		billContainer = new JPanel();
		billContainer.setLayout(new BorderLayout());
		billContainer.setBounds(x, y, w, h);
		
		JLabel name = new JLabel();
		name.setText("Panier");
		name.setHorizontalTextPosition(JLabel.CENTER);
		name.setVerticalTextPosition(JLabel.CENTER);
		billContainer.add(name,BorderLayout.NORTH);
		
		billPanel = new JPanel();
		billPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
		billPanel.setBackground(Gestionnaire.MEDIUM_BROWN);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(billPanel);
		
		validationPanel = new ValidationPanel(1000);
		
		billContainer.add(billPanel, BorderLayout.CENTER);
		billContainer.add(validationPanel, BorderLayout.SOUTH);
		
		//billPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
		//billPanel.setLayout(new BoxLayout(billPanel, BoxLayout.Y_AXIS));
	}
	
	public void addToBill(Keys key, int w, int h) {
		if (!bill.containsKey(key)) {
			BillArticlePanel panel = new BillArticlePanel(getName(key), w, h, null);
			bill.put(key,panel);
			billPanel.add(panel);
			billPanel.revalidate();
			billPanel.repaint();
			AddToCart.posY++;
			//billPanel.setPreferredSize(new Dimension(billPanel.getWidth() + panel.getWidth(), billPanel.getHeight() + panel.getHeight()));
		} else {
			JSpinner spinner = bill.get(key).getQuantitySpinner();
			spinner.setValue(((Integer)spinner.getValue()) + 1);
		}
		validationPanel.riseTotalCost(key.getPrixAchat());
	}
	
	public void removeFromBill(Keys key, int w, int h) {
		if (bill.containsKey(key)) {
			BillArticlePanel panel = new BillArticlePanel(getName(key), w, h, null);
			bill.put(key,panel);
			billPanel.add(panel);
			billPanel.revalidate();
			billPanel.repaint();
			AddToCart.posY++;
			//billPanel.setPreferredSize(new Dimension(billPanel.getWidth() + panel.getWidth(), billPanel.getHeight() + panel.getHeight()));
		} else {
			JSpinner spinner = bill.get(key).getQuantitySpinner();
			spinner.setValue(((Integer)spinner.getValue()) + 1);
		}
		validationPanel.riseTotalCost(key.getPrixAchat());
	}
	
	public String getName(Keys key) {
		String title = key.name().replace('_', ' ');
		title = title.substring(0, 1).toUpperCase() + title.substring(1).toLowerCase();
		return title;
	}

	public MarketGUI() {
		init();
		
		setBackground(MARKET_CARD_COLOR);
		setSize(1050,560);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public JPanel getBillPanel() {
		return billPanel;
	}

	public HashMap<Keys, BillArticlePanel> getBill() {
		return bill;
	}

	public void init() {
		// 599
		int width = MARKET_CARD_WIDTH*MARKET_COLUMN_COUNT + ((MARKET_COLUMN_COUNT + 1)*MIN_SPACE_BETWEEN);
		// 512
		int height = MARKET_CARD_HEIGHT*MARKET_ROW_COUNT + ((MARKET_ROW_COUNT + 1 )*MIN_SPACE_BETWEEN);

		BILL_HEIGHT = height;
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(Gestionnaire.LIGHT_BROWN);
		
		paintBill(700, 40, BILL_WIDTH, BILL_HEIGHT);
		contentPane.add(billContainer);
		
		JPanel principalPanel = gestionnairePaintStrategy.paintGestionnaire(0,0, width, height, MARKET_ROW_COUNT, MARKET_COLUMN_COUNT, MIN_SPACE_BETWEEN, MARKET_CARD_WIDTH, MARKET_CARD_HEIGHT, MARKET_CARD_COLOR, "Market", game.getAchat(), this);
		contentPane.add(principalPanel);

//		JPanel panier = generalPaintStrategy.paintRoundedPanel(690, 115, 260, 495, new BorderLayout(), 40, Gestionnaire.MEDIUM_BROWN);
//		JLabel panierHeaderLabel = new JLabel("Panier");
//		panierHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
//		panier.add(panierHeaderLabel, BorderLayout.NORTH);
//		panier.add(paintBill(game.getAchat().getCart()), BorderLayout.CENTER);

	}
	

	public static void main(String[] args) {
		MarketGUI market = new MarketGUI();
		for(GestionnaireKey key : data.gestion.Market.getInstance().getArticles().keySet()) {
			System.out.println(key.name() + " : " + data.gestion.Market.getInstance().getArticles().get(key).size() + "\n");
		}
	}
	
	//	// Pas encore fonctionnelle 
	//	public JScrollPane paintBill(ArrayList<Buyable> articles) {
	//		JPanel bill = generalPaintStrategy.paintNormalPanel(new GridLayout(0,1), Gestionnaire.MEDIUM_BROWN);
	//		for (Buyable article : articles) {
	//			// JPanel parentPanel = new JPanel();
	//			// parentPanel.setBackground(MEDIUM_BROWN);
	//			// parentPanel.setLayout(null);
	//			JPanel articleBill = generalPaintStrategy.paintRoundedPanel(10, 10, 200, 100, null, 20, MARKET_CARD_COLOR);
	//			bill.add(articleBill);
	//			// bill.add(parentPanel);
	//		}
	//		JScrollPane billScrollPane = new JScrollPane(bill,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	//		// billScrollPane.setViewportView(bill);
	//		// billScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	//		return billScrollPane;
	//	}
	//
	//	// Pas encore fonctionnelle 
	//	public JPanel addToBill(JPanel panier,ArrayList<Buyable> articles) {
	//		JPanel bill = generalPaintStrategy.paintNormalPanel(0, 0, 50, 460, new GridLayout(0,1,20,20), Gestionnaire.MEDIUM_BROWN);
	//		JScrollPane billScrollPane = new JScrollPane();
	//		panier.add(billScrollPane);
	//		billScrollPane.setViewportView(bill);
	//		billScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	//		for (Buyable article : articles) {
	//			JPanel articleBill = generalPaintStrategy.paintRoundedPanel(10, 10, 200, 100, null, 20, MARKET_CARD_COLOR);
	//			bill.add(articleBill);
	//		}
	//		return bill;
	//	}
	//
	//	public JPanel paintBill(JPanel panier,HashMap<String, Buyable> articles) {
	//		JPanel bill = generalPaintStrategy.paintNormalPanel(0, 0, 240, 475, new GridLayout(0,1), MARKET_CARD_COLOR);
	//		for (Buyable article : articles.values()) {
	//			JPanel parentPanel = new JPanel();
	//			parentPanel.setBackground(Gestionnaire.LIGHT_BROWN);
	//			parentPanel.setLayout(null);
	//			JPanel articleBill = generalPaintStrategy.paintRoundedPanel(10, 10, 200, 50, null, 20, MARKET_CARD_COLOR);
	//			parentPanel.add(articleBill);
	//			bill.add(parentPanel);
	//		}
	//		return bill;
	//	}

}
