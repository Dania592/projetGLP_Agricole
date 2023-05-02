package gui.gestionnaire.gestionnairesGUI;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.gestion.GestionnaireStocks;
import gui.gestionnaire.GeneralPaintStrategy;
import gui.gestionnaire.GestionnairePaintStrategy;
import gui.gestionnaire.contolleurs.WindowDispose;
import gui.gestionnaire.keys.PaintKeys;

public class GestionnaireStocksGUI extends JFrame {
	
	public static Color DARK_BROWN = new Color(68,40,24);
	public static Color MEDIUM_BROWN = new Color(188,149,88);
	public static Color LIGHT_BROWN = new Color(255,231,171);
	public static Color DARK_GREEN = new Color(82,121,112);
	public static Color RED = new Color(158,36,19);
	public static Color MEDIUM_GREEN = new Color(103,148,76);
	
	public static Color MANAGER_CARD_COLOR = GeneralPaintStrategy.LIGHT_BROWN;
	public static int MIN_SPACE_BETWEEN = GeneralPaintStrategy.MIN_SPACE_BETWEEN;
	public static int MANAGER_ROW_COUNT = 2;
	public static int MANAGER_COLUMN_COUNT = 4;
	public static int MANAGER_CARD_WIDTH = 182;
	public static int MANAGER_CARD_HEIGHT = 222;
	public static int WIDTH = 895;
	public static int HEIGHT = 530;

	private GeneralPaintStrategy paintStrategy  = new GeneralPaintStrategy();;

	private static final long serialVersionUID = 1L;
	
	public GestionnaireStocksGUI(String title, JFrame container, int tab) {
		super(title);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		contentPane.add(paint(tab));	
		
	    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	    addWindowListener(new WindowDispose(this, container));
		setBackground(LIGHT_BROWN);
		setSize(910,560);
		
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	
	public JPanel paint(int tab) {
		
		int width = GestionnairePaintStrategy.MANAGER_CARD_WIDTH*GestionnairePaintStrategy.MANAGER_COLUMN_COUNT + ((GestionnairePaintStrategy.MANAGER_COLUMN_COUNT + 1)*GestionnairePaintStrategy.MIN_SPACE_BETWEEN);
		int height = GestionnairePaintStrategy.MANAGER_CARD_HEIGHT*GestionnairePaintStrategy.MANAGER_ROW_COUNT + ((GestionnairePaintStrategy.MANAGER_ROW_COUNT + 1 )*GestionnairePaintStrategy.MIN_SPACE_BETWEEN);
		
		return paintStrategy.paintGestionnaire(width, height, MANAGER_ROW_COUNT, MANAGER_COLUMN_COUNT, MIN_SPACE_BETWEEN, MANAGER_CARD_WIDTH, MANAGER_CARD_HEIGHT, MANAGER_CARD_COLOR, PaintKeys.STOCK, null, null,tab);			
	}
	
	
}