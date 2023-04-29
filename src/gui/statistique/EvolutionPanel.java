package gui.statistique;

import java.awt.Color;
import java.awt.GradientPaint;
import java.util.ArrayList;
import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import gui.gestionnaire.GeneralPaintStrategy;

public class EvolutionPanel extends StatPanel {

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
	
}
