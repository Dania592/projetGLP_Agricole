package gui.gestionnaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

import gui.gestionnaire.keys.Keys;
import process.transaction.Achat;


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
	
	private JFrame frame;

	public static Color MARKET_CARD_COLOR = GestionnaireStocksGUI.LIGHT_BROWN;

	private GeneralPaintStrategy gestionnairePaintStrategy = new GeneralPaintStrategy();
	private HashMap<Keys, BillArticlePanel> bill = new HashMap<>();
	private JPanel billContainer;
	private JPanel billPanel;
	private ValidationPanel validationPanel;
	private Achat achat;

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
		billPanel.setBackground(GestionnaireStocksGUI.MEDIUM_BROWN);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(billPanel);
		
		validationPanel = new ValidationPanel(0, this);
		
		billContainer.add(billPanel, BorderLayout.CENTER);
		billContainer.add(validationPanel, BorderLayout.SOUTH);
		
		//billPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
		//billPanel.setLayout(new BoxLayout(billPanel, BoxLayout.Y_AXIS));
	}
	
	public void addToBill(Keys key, int w, int h) {
		if (!bill.containsKey(key)) {
			BillArticlePanel panel = new BillArticlePanel(key, this, w, h, null);
			bill.put(key,panel);
			billPanel.add(panel);
			billPanel.revalidate();
			billPanel.repaint();
			AddToCart.posY++;
			//billPanel.setPreferredSize(new Dimension(billPanel.getWidth() + panel.getWidth(), billPanel.getHeight() + panel.getHeight()));
		} else {
			JSpinner spinner = bill.get(key).getQuantitySpinner();
			spinner.setValue(spinner.getNextValue());
		}
	}
	
	public void removeFromBill(Keys key, int w, int h) {
		if (bill.containsKey(key)) {
			BillArticlePanel panel = new BillArticlePanel(key, this, w, h, null);
			bill.put(key,panel);
			billPanel.add(panel);
			billPanel.revalidate();
			billPanel.repaint();
			AddToCart.posY++;
		} else {
			JSpinner spinner = bill.get(key).getQuantitySpinner();
			spinner.setValue(spinner.getPreviousValue());
		}
		validationPanel.updateTotalCost(key.getPrixAchat());
	}
	
	public String getName(Keys key) {
		String title = key.name().replace('_', ' ');
		title = title.substring(0, 1).toUpperCase() + title.substring(1).toLowerCase();
		return title;
	}

	public MarketGUI(JFrame frame) {
		this.frame = frame;
		achat = new Achat();
		init();
		
	    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	    addWindowListener(new WindowDispose(this, frame));
		
		setBackground(MARKET_CARD_COLOR);
		setSize(1050,560);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
		System.out.println("***********************************");
		
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
		contentPane.setBackground(GestionnaireStocksGUI.LIGHT_BROWN);
		
		paintBill(700, 40, BILL_WIDTH, BILL_HEIGHT);
		contentPane.add(billContainer);
		
		System.out.println("call to gestionnairePaintStrategy.paintGestionnaire");
		JPanel principalPanel = gestionnairePaintStrategy.paintGestionnaire(0,0, width, height, MARKET_ROW_COUNT, MARKET_COLUMN_COUNT, MIN_SPACE_BETWEEN, MARKET_CARD_WIDTH, MARKET_CARD_HEIGHT, MARKET_CARD_COLOR, "Market", achat, this);
		contentPane.add(principalPanel);

	}
	
	public ValidationPanel getValidationPanel() {
		return validationPanel;
	}
	
	public GeneralPaintStrategy getGestionnairePaintStrategy() {
		return gestionnairePaintStrategy;
	}

	public JPanel getBillContainer() {
		return billContainer;
	}

	public Achat getAchat() {
		return achat;
	}

	public JFrame getFrame() {
		return frame;
	}
	
	public static void main(String[] args) {
		MarketGUI market = new MarketGUI(null);
//for(GestionnaireKey key : data.gestion.Market.getInstance().getArticles().keySet()) {
//			//System.out.println(key.name() + " : " + data.gestion.Market.getInstance().getArticles().get(key).size() + "\n");
//		}
	}
	
}
