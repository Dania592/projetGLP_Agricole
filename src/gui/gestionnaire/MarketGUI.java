package gui.gestionnaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

import data.finance.Banque;
import data.finance.Compte;
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
	public final static Font LABEL_FONT = new Font("Monospaced", Font.PLAIN|Font.BOLD, 20);
	private GeneralPaintStrategy gestionnairePaintStrategy = new GeneralPaintStrategy();
	private HashMap<Keys, BillArticlePanel> bill = new HashMap<>();
	private JPanel billPanel = new JPanel();
	private BoxLayout boxLayout = new BoxLayout(billPanel, BoxLayout.Y_AXIS);
	private ValidationPanel validationPanel;
	private Compte compte = Banque.getInstance().getCompte();
	private JPanel billContainer;
	private Achat achat;

	public void paintBill(int x, int y, int w, int h) {
		billContainer = new RoundedPanel(20,GeneralPaintStrategy.MEDIUM_BROWN, false);
		billContainer.setLayout(new BorderLayout());
		billContainer.setBounds(x, y, w, h);
		
		JLabel name = new JLabel();
		name.setText("Panier");
		name.setFont(LABEL_FONT);
		name.setForeground(Color.WHITE);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		billContainer.add(name,BorderLayout.NORTH);
		
		billPanel.setLayout(boxLayout);
		billPanel.setBackground(GestionnaireStocksGUI.MEDIUM_BROWN);
		billPanel.add(Box.createRigidArea(new Dimension(0,5)));
		
		JScrollPane scrollPane = new JScrollPane(billPanel);
		scrollPane.setVerticalScrollBar(new CustomizedScrollBar());
		scrollPane.setBorder(null);
		scrollPane.setBackground(GeneralPaintStrategy.MEDIUM_BROWN);
		scrollPane.setViewportView(billPanel);
		
		validationPanel = new ValidationPanel(0, this);
		
		billContainer.add(scrollPane, BorderLayout.CENTER);
		billContainer.add(validationPanel, BorderLayout.SOUTH);		
	}
	
	public void addToBill(Keys key, int w, int h) {
		if (compte.getSolde() >= (validationPanel.getTotalCost() + key.getPrixAchat())) {
			if (!bill.containsKey(key)) {	
				BillArticlePanel panel = new BillArticlePanel(key, this, w, h, null);
				bill.put(key,panel);
				billPanel.add(panel);
				billPanel.add(Box.createRigidArea(new Dimension(0,5)));
				billPanel.revalidate();
				billPanel.repaint();
				billContainer.revalidate();
				billContainer.repaint();
				AddToCart.posY++;
			} else {
				JSpinner spinner = bill.get(key).getQuantitySpinner();
				spinner.setValue(spinner.getNextValue());
			}
		} else {
			new InfosTransaction("Solde insuffisant", this);
			dispose();
		}
	}
	
	public void removeFromBill(Keys key) {
		if (bill.containsKey(key)) {
			billPanel.remove(bill.get(key));
			int quantity = (Integer) bill.get(key).getQuantitySpinner().getValue();
			bill.remove(key);
			billPanel.revalidate();
			billPanel.repaint();
			validationPanel.updateTotalCost((-quantity)*key.getPrixAchat());
		}
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
		
	}

	public JPanel getBillPanel() {
		return billPanel;
	}

	public HashMap<Keys, BillArticlePanel> getBill() {
		return bill;
	}

	public void init() {

		int width = MARKET_CARD_WIDTH*MARKET_COLUMN_COUNT + ((MARKET_COLUMN_COUNT + 1)*MIN_SPACE_BETWEEN);
		int height = MARKET_CARD_HEIGHT*MARKET_ROW_COUNT + ((MARKET_ROW_COUNT + 1 )*MIN_SPACE_BETWEEN);

		BILL_HEIGHT = height;
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(GestionnaireStocksGUI.LIGHT_BROWN);
		
		paintBill(700, 40, BILL_WIDTH, BILL_HEIGHT);
		contentPane.add(billContainer);

		JPanel principalPanel = gestionnairePaintStrategy.paintGestionnaire(0,0, width, height, MARKET_ROW_COUNT, MARKET_COLUMN_COUNT, MIN_SPACE_BETWEEN, MARKET_CARD_WIDTH, MARKET_CARD_HEIGHT, MARKET_CARD_COLOR, PaintKeys.ARTICLE, achat, this);
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
	}
	
}
