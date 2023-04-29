package gui.statistique;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import gui.gestionnaire.GeneralPaintStrategy;
import gui.gestionnaire.RoundedPanel;
import gui.gestionnaire.TabbedPaneUI;



public class TestStat extends JFrame {

	private JPanel panelProduit = new JPanel();
	private JPanel panelStock = new JPanel();
	private JPanel panelEvolution = new JPanel();
	
	private JFrame source ;
	public TestStat(JFrame source) {
		super("stat");
		this.source=source;
		init();
	}
	
	
	public void init() {
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(GeneralPaintStrategy.DARK_BROWN);
		
		GeneralPaintStrategy strategie = new GeneralPaintStrategy();
		
		JTabbedPane tabbed = strategie.paintTabbedPane(0, 0, 900, 580, GeneralPaintStrategy.LIGHT_BROWN,GeneralPaintStrategy.DARK_BROWN);
		
		RoundedPanel buttonPane = new RoundedPanel(null , 30 , GeneralPaintStrategy.LIGHT_BROWN);
		buttonPane.setBounds(20,590,850,60);
		contentPane.add(buttonPane);
		
		JButton retour = strategie.paintButton(GeneralPaintStrategy.DARK_GREEN, GeneralPaintStrategy.LIGHT_BROWN, new Font(Font.SANS_SERIF , Font.BOLD, 18), "Retour au jeu");
		retour.setBounds(350, 7, 200, 40);
		retour.addActionListener(new Retour());
		buttonPane.add(retour);
		
		tabbed.addTab("Production", panelProduit);
		tabbed.addTab("Finance", panelEvolution);
		tabbed.addTab("Stocks", panelStock);
		
		
		
		// pie chart 
		statistiqueProduit(statTestAnimal(), statTestVegetal());
		//contentPane.add(panelProduit);
		
		// bar graph 
		statistiqueStock(statTestAnimal(), statTestVegetal());
		//contentPane.add(panelStock);
		
		
		// evolution graph 
		statistiqueFinance(statTestEvolution(), statTestEvolution());
		//contentPane.add(panelEvolution);
		contentPane.add(tabbed);
		
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setSize(900, 700);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
	}
	
	private HashMap<String, Integer> statTestAnimal() {
		HashMap<String, Integer> donnees = new HashMap<>();
		donnees.put("Lait", new Integer(10));
		donnees.put("Oeuf", new Integer(50));
		donnees.put("Laine", new Integer(80));
		return donnees;
	}
	
	private ArrayList<Integer> statTestEvolution(){
		ArrayList<Integer> donnees = new ArrayList<>();
		donnees.add(new Integer(2));
		donnees.add(new Integer(4));
		donnees.add(new Integer(8));
		donnees.add(new Integer(3));
		donnees.add(new Integer(1));
		donnees.add(new Integer(5));
		donnees.add(new Integer(2));
		donnees.add(new Integer(5));
		donnees.add(new Integer(4));
		return donnees;
	}
	
	private HashMap<String, Integer> statTestVegetal() {
		HashMap<String, Integer> donnees = new HashMap<>();
		donnees.put("Pomme", new Integer(10));
		donnees.put("Poire", new Integer(50));
		donnees.put("Carotte", new Integer(80));
		donnees.put("Aubergine", new Integer(50));
		donnees.put("Tomate", new Integer(50));
		return donnees;
	}
	
	
	public void piePanelStat(HashMap<String, Integer> donnee , int x , int y , int w , int h , String title ) {	
		PiePanel pie = new PiePanel(title);
		pie.initDonnee(donnee);
		pie.setBounds(x,y,w,h);	
		panelProduit.add(pie);
		
	}
	
	public void barPanelStat(HashMap<String, Integer> donnee , int x , int y , int w , int h , String title) {
		BarPanel bar = new BarPanel(title);
		bar.reelStat();
		bar.setBounds(x, y, w, h);
		panelStock.add(bar);
	}
	
	public void evolutionPanelStat(ArrayList<Integer> donnee , int x , int y , int w , int h , String title ) {	
		EvolutionPanel evolution = new EvolutionPanel(title);
		evolution.initDonnee(donnee);
		evolution.setBounds(x,y,w,h);	
		panelEvolution.add(evolution);
		
	}
	
public void statistiqueFinance(ArrayList<Integer> achat , ArrayList<Integer> vente ) {
		
		panelEvolution.setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		panelEvolution.setLayout(null);
		
		JLabel title = new JLabel("Statistiques Finance");
		title.setFont(new Font(Font.SERIF , Font.BOLD  , 30));
		title.setBounds(300, 50, 400, 50);
		panelEvolution.add(title);
		
		evolutionPanelStat(vente, 30, 120, 400,400 , "Vente");
		evolutionPanelStat(achat, 460, 120, 400,400 , "Achat");
		
			
	}
	
	public void statistiqueProduit(HashMap<String , Integer> animals , HashMap<String, Integer> vegetals) {
		
		panelProduit.setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		panelProduit.setLayout(null);
		
		JLabel title = new JLabel("Statistiques Production");
		title.setFont(new Font(Font.SERIF , Font.BOLD  , 30));
		title.setBounds(300, 50, 400, 50);
		panelProduit.add(title);
		
		
		piePanelStat(animals , 30, 120, 400,400 , "Produits Animals");
		
		piePanelStat(vegetals , 460, 120, 400,400 , "Produits Végétals");	
	}
	
	public void statistiqueStock(HashMap<String, Integer> animals , HashMap<String, Integer> vegetals) {
		panelStock.setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		panelStock.setLayout(null);
		
		JLabel title = new JLabel("Statistiques Stock");
		title.setFont(new Font(Font.SERIF , Font.BOLD  , 30));
		title.setBounds(300, 50, 400, 50);
		panelStock.add(title);
		
		barPanelStat(animals, 30, 120, 400,400, "Animaux");
		barPanelStat(vegetals,  460, 120, 400,400, "Végétaux");
		
	}
	
	
	private class Retour implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(source!=null) {
				source.setVisible(true);				
			}
			TestStat.this.dispose();
			
		}
		
	}
	
	
	public static void main(String[]args) {
		new TestStat(null);
	}
}


