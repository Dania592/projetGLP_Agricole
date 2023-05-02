package gui.gestionnaire;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.gestionnaire.contolleurs.WindowDispose;
import gui.gestionnaire.gestionnairesGUI.GestionnaireStocksGUI;
import gui.gestionnaire.keys.PaintKeys;
import process.game.Game;
import process.game.GameBuilder;
import process.game.MapManager;
import process.gestion.transaction.Achat;

public class NewStockGUI extends JFrame{
	
	public static Color MANAGER_CARD_COLOR = GeneralPaintStrategy.LIGHT_BROWN;
	public static int MIN_SPACE_BETWEEN = GeneralPaintStrategy.MIN_SPACE_BETWEEN;
	public static int MANAGER_ROW_COUNT = 2;
	public static int MANAGER_COLUMN_COUNT = 4;
	public static int MANAGER_CARD_WIDTH = 182;
	public static int MANAGER_CARD_HEIGHT = 222;
	public static int WIDTH = 895;
	public static int HEIGHT = 530;

	private GeneralPaintStrategy paintStrategy;
	private static Achat achat = new Achat();

	public NewStockGUI(String title, JFrame container, int tab) {
		super(title);
		paintStrategy  = new GeneralPaintStrategy();
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		contentPane.add(paint(tab));	
		
	    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	    addWindowListener(new WindowDispose(this, container));
		setBackground(GeneralPaintStrategy.LIGHT_BROWN);
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
	

	public static void main(String[] args) {
		
		Game game = new Game();
		MapManager manager = GameBuilder.MapBuilder();
		game.acheter(manager.getMap());
		NewStockGUI.achat = game.getAchat();
		
		NewStockGUI gestionnaire = new NewStockGUI("Gestionnaire",null, 0);
	}
}
