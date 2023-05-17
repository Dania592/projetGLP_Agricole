package gui.gestionnaire.gestionnairesGUI;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.Farm.MainGuiTest;
import gui.gestionnaire.GeneralPaintStrategy;
import gui.gestionnaire.GestionnairePaintStrategy;
import gui.gestionnaire.Home;
import gui.gestionnaire.contolleurs.WindowDispose;
import gui.gestionnaire.keys.PaintKeys;

public class RHManagerGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private GeneralPaintStrategy generalPaintStrategy;
	public static int ROW_COUNT = 1;
	public static int COLUMN_COUNT = 4;

	private Home frame;
	
	public RHManagerGUI(Home frame ,MainGuiTest ferme, int tab) {
		super("Gestionnaire des ressources humaines");
		generalPaintStrategy  = new GeneralPaintStrategy(ferme);
		this.frame = frame;

		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		contentPane.add(paint(tab));	
		
		setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		setSize(900,350);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		addWindowListener(new WindowDispose(this, frame));
	}
	
	public Home getHome() {
		return frame;
	}
	
	public JPanel paint(int tab) {
		
		int width = GestionnairePaintStrategy.MANAGER_CARD_WIDTH*COLUMN_COUNT + ((COLUMN_COUNT + 1)*GestionnairePaintStrategy.MIN_SPACE_BETWEEN);
		int height = GestionnairePaintStrategy.MANAGER_CARD_HEIGHT*ROW_COUNT + ((ROW_COUNT + 1 )*GestionnairePaintStrategy.MIN_SPACE_BETWEEN);
		
		return generalPaintStrategy.paintGestionnaire(width, height, ROW_COUNT, COLUMN_COUNT, GestionnairePaintStrategy.MIN_SPACE_BETWEEN, GestionnairePaintStrategy.MANAGER_CARD_WIDTH, GestionnairePaintStrategy.MANAGER_CARD_HEIGHT, GestionnairePaintStrategy.MANAGER_CARD_COLOR, PaintKeys.EMPLOY, null, null, tab);	

	}
	
	
	public static void main(String[] args) {
		//GestionnaireRH.getInstance().recruter(Employees.JEAN);
		new RHManagerGUI(null,null,1);
	}
	
}