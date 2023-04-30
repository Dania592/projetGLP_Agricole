package gui.gestionnaire.gestionnairesGUI;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.finance.Charge;
import data.finance.TypeCharge;
import data.gestion.GestionnaireFinancier;
import gui.gestionnaire.GeneralPaintStrategy;
import gui.gestionnaire.contolleurs.WindowDispose;
import gui.gestionnaire.keys.PaintKeys;
import process.transaction.Achat;
import process.transaction.Vente;

public class GestionnaireFinancierGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 1000;
	public static int HEIGHT = 620;
	public static int ROW_COUNT = 5;
	public static int COLUMN_COUNT = 1;
	
	private GeneralPaintStrategy generalPaintStrategy;
	
	public GestionnaireFinancierGUI(JFrame parent, int tab) {
		generalPaintStrategy  = new GeneralPaintStrategy();
		int width = 600;
		int height = 450;
		
		JPanel principalPanel = generalPaintStrategy.paintGestionnaire(width, height,ROW_COUNT, COLUMN_COUNT, GeneralPaintStrategy.MIN_SPACE_BETWEEN, width - 2*GeneralPaintStrategy.MIN_SPACE_BETWEEN, (height/ROW_COUNT) - 2*GeneralPaintStrategy.MIN_SPACE_BETWEEN, GeneralPaintStrategy.LIGHT_BROWN, PaintKeys.FINANCE, null, null,tab);
		
		Container container = getContentPane();
		container.setLayout(null);
		container.setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		container.add(principalPanel);
		
	    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	    addWindowListener(new WindowDispose(this, parent));
		
		setSize(755,560);
		setLocationRelativeTo(null);
		setVisible(true);

	}
	
	public static void main(String[] args) {
		Achat achat = new Achat();
		achat.setTotalCost(1000);
		achat.setValidated(true);
		GestionnaireFinancier.getInstance().add(achat);
		Vente vente = new Vente();
		vente.setTotalCost(1000);
		vente.setValidated(true);
		GestionnaireFinancier.getInstance().add(new Charge(TypeCharge.ENERGIE));
		GestionnaireFinancier.getInstance().add(vente);
		GestionnaireFinancierGUI financierGUI = new GestionnaireFinancierGUI(null,0);
	}
	
}