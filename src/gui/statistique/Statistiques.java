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
import gui.gestionnaire.UI.TabbedPaneUI;



public class Statistiques extends JFrame {

	private JPanel panelProduit = new JPanel();
	private JPanel panelStock = new JPanel();
	private JPanel panelEvolution = new JPanel();
	
	private JFrame source ;
	
	public Statistiques(JFrame source) {
		super("stat");
		this.source=source;
		init();
	}
	
	
	public void init() {
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(GeneralPaintStrategy.DARK_BROWN);
		
		GeneralPaintStrategy strategie = new GeneralPaintStrategy();
		
		JTabbedPane tabbed = GeneralPaintStrategy.paintTabbedPane(0, 0, 900, 580, GeneralPaintStrategy.LIGHT_BROWN,GeneralPaintStrategy.DARK_BROWN);
		
		RoundedPanel buttonPane = new RoundedPanel(null , 30 , GeneralPaintStrategy.LIGHT_BROWN);
		buttonPane.setBounds(20,590,850,60);
		contentPane.add(buttonPane);
		
		JButton retour = strategie.paintButton(GeneralPaintStrategy.DARK_GREEN, GeneralPaintStrategy.LIGHT_BROWN, new Font(Font.SANS_SERIF , Font.BOLD, 18), "Retour au jeu");
		retour.setBounds(350, 7, 200, 40);
		retour.addActionListener(new Retour());
		buttonPane.add(retour);
		
		tabbed.addTab("Stocks", panelStock);
		tabbed.addTab("Production", panelProduit);
		tabbed.addTab("Finance", panelEvolution);
			
		// bar graph 
		statistiqueStock();
		
		// pie chart 
		statistiqueProduit();
		
		
		// evolution graph 
		statistiqueFinance();
		
		contentPane.add(tabbed);
			
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setSize(900, 700);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
	}

	
	
	public void piePanelStat( int x , int y , int w , int h , String title ) {	
		PiePanel pie = new PiePanel(title);
		pie.reelStat();
		pie.setBounds(x,y,w,h);	
		panelProduit.add(pie);
		
	}

	public void barPanelStat( int x , int y , int w , int h , String title) {
		BarPanel bar = new BarPanel(title);
		bar.reelStat();
		bar.setBounds(x, y, w, h);
		panelStock.add(bar);
	}
	
	public void evolutionPanelStat( int x , int y , int w , int h , String title ) {	
		EvolutionPanel evolution = new EvolutionPanel(title);
		evolution.reelStat();
		evolution.setBounds(x,y,w,h);	
		panelEvolution.add(evolution);
		
	}
	
public void statistiqueFinance() {
		
		panelEvolution.setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		panelEvolution.setLayout(null);
		
		JLabel title = new JLabel("Statistiques Finance");
		title.setFont(new Font(Font.SERIF , Font.BOLD  , 30));
		title.setBounds(300, 50, 400, 50);
		panelEvolution.add(title);
		
		evolutionPanelStat( 30, 120, 400,400 , "Vente");
		evolutionPanelStat( 460, 120, 400,400 , "Achat");
		
			
	}
	
	public void statistiqueProduit() {
		
		panelProduit.setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		panelProduit.setLayout(null);
		
		JLabel title = new JLabel("Statistiques Production");
		title.setFont(new Font(Font.SERIF , Font.BOLD  , 30));
		title.setBounds(300, 50, 400, 50);
		panelProduit.add(title);
		
		
		piePanelStat( 250, 120, 400,400 , "Produits");
		
	}
	
	public void statistiqueStock() {
		panelStock.setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		panelStock.setLayout(null);
		
		JLabel title = new JLabel("Statistiques Stock");
		title.setFont(new Font(Font.SERIF , Font.BOLD  , 30));
		title.setBounds(300, 50, 400, 50);
		panelStock.add(title);
		
		barPanelStat( 30, 120, 400,400, "Animaux");
		barPanelStat( 460, 120, 400,400, "Végétaux");
		
	}
	
	
	private class Retour implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(source!=null) {
				source.setVisible(true);				
			}
			Statistiques.this.dispose();
			
		}
		
	}
	
	
	public static void main(String[]args) {
		new Statistiques(null);
	}
}


