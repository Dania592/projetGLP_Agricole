package gui.statistique;

import java.awt.Font;
import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import data.gestion.GestionnaireStocks;
import data.production.Produits;
import gui.gestionnaire.GeneralPaintStrategy;


public class PiePanel extends StatPanel {
	
	private  PiePlot piePlot;
	private DefaultPieDataset pieDataset ; 
	private HashMap<String, Integer> donnees = new HashMap<>();
	
	public PiePanel(String title) {
		super(title);
		
	}

	public void initPie() {

		pieDataset = new DefaultPieDataset();
		for(String key : donnees.keySet()) {
			pieDataset.setValue(key, donnees.get(key));
		}
		
	    JFreeChart pieChart = ChartFactory.createPieChart(getTitle(), pieDataset, true,true, false); 
	    piePlot = (PiePlot) pieChart.getPlot();
	    piePlot.setBackgroundPaint(GeneralPaintStrategy.MEDIUM_BROWN);
	    piePlot.setBaseSectionOutlinePaint(GeneralPaintStrategy.MEDIUM_BROWN);
	    piePlot.setLabelLinksVisible(false);
	    piePlot.setLabelBackgroundPaint(GeneralPaintStrategy.MEDIUM_BROWN);
	    piePlot.setLabelFont(new Font(Font.SANS_SERIF, Font.BOLD , 15));
	    piePlot.setLabelOutlinePaint(GeneralPaintStrategy.MEDIUM_BROWN);
	    piePlot.setAutoPopulateSectionPaint(true);
	    piePlot.setShadowPaint(null);
	    
	    GeneralPaintStrategy paintstrategie = new GeneralPaintStrategy();
	    for(int index =0 ; index < donnees.keySet().size() ; index++) {
	    	piePlot.setSectionPaint(pieDataset.getKey(index),paintstrategie.getStatColors().get(index));
		}
	      
	    ChartPanel cPanel = new ChartPanel(pieChart); 
	    getContainer().add(cPanel);
	   
	}
	
	public void initDonnee(HashMap<String, Integer> donnees) {
		this.donnees=donnees;
		initPie();
	}
	
	@Override
	public void reelStat() {
		if(getTitle().equals("Produits")) {
			HashMap<Produits, Integer> produit = GestionnaireStocks.getInstance().getProduits();
			HashMap<String, Integer> donnees = new HashMap<>();
			for(Produits produits : produit.keySet()) {
				donnees.put(produits.name(),produit.get(produits));
			}
			this.donnees=donnees;
			initPie();			
		}

	}
}
