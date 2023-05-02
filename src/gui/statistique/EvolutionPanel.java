package gui.statistique;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import data.gestion.GestionnaireFinancier;
import gui.gestionnaire.GeneralPaintStrategy;
import process.transaction.Achat;
import process.transaction.Vente;

public class EvolutionPanel extends StatPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private XYSeriesCollection dataset = new XYSeriesCollection();
	private ArrayList<Integer> donnees = new ArrayList<>();
	
	public EvolutionPanel(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}


	public void initEvolution() {

		XYSeries serie = new XYSeries(getTitle());
		for (int index = 0; index < donnees.size(); index++) {
			serie.add(index, donnees.get(index));
		}
		dataset.addSeries(serie);
		
	    JFreeChart barChart = ChartFactory.createXYLineChart(getTitle(), "Temps", "Nombre", dataset, PlotOrientation.VERTICAL, true, true, false);
		
	    XYPlot plot = (XYPlot) barChart.getPlot();
	    XYItemRenderer renderer1 = new StandardXYItemRenderer();
	    renderer1.setSeriesPaint(0, GeneralPaintStrategy.DARK_BROWN);
	    renderer1.setBaseItemLabelsVisible(false);
	    plot.setRenderer(renderer1);
	    
	    plot.setDomainGridlinesVisible(false);
	    plot.setBackgroundPaint(GeneralPaintStrategy.MEDIUM_BROWN);
	    plot.setOutlinePaint(GeneralPaintStrategy.DARK_BROWN);
	  
	        
	    ChartPanel cPanel = new ChartPanel(barChart); 
	    getContainer().add(cPanel);
	   
	}
	
	public void initDonnee(ArrayList<Integer> donnees) {
		this.donnees = donnees;
		initEvolution();
	}
	
	@Override
	public void reelStat() {
		if(getTitle().equals("Vente")) {
			ArrayList<Vente> ventes = GestionnaireFinancier.getInstance().getVentes();
			for(Vente vente : ventes) {
				donnees.add(new Integer((int) vente.getTotalCost()));
			}
		}
		else {
			if(getTitle().equals("Achat")) {
				ArrayList<Achat> achats = GestionnaireFinancier.getInstance().getAchats();
				for(Achat achat : achats) {
					donnees.add(new Integer((int) achat.getTotalCost()));
				}
			}
		}
		initEvolution();
	}
}
