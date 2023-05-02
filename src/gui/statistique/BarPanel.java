package gui.statistique;

import java.awt.GradientPaint;
import java.util.ArrayList;
import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import data.espece.faune.Animal;
import data.gestion.GestionnaireAnimaux;
import data.gestion.GestionnaireStocks;
import gui.gestionnaire.GeneralPaintStrategy;
import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.Graine;

public class BarPanel extends StatPanel{

	private HashMap<String, Integer> donnees = new HashMap<>();
	private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	
	public BarPanel(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	public void initBar() {

		int index = 1 ;
		for(String key : donnees.keySet()) {
			dataset.setValue(donnees.get(key), key, key);
			index ++;
		}
		
	    JFreeChart barChart = ChartFactory.createBarChart(getTitle(),"Etre vivant","Nombre", dataset, PlotOrientation.VERTICAL, true,true, false); 
		    

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(GeneralPaintStrategy.MEDIUM_BROWN);
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		
		renderer.setItemMargin(0);
		
	    GeneralPaintStrategy paintstrategie = new GeneralPaintStrategy();
	    for (int s=0; s<donnees.size(); s++){
			GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, paintstrategie.getStatColors().get(s),
					0.0f, 0.0f,  paintstrategie.getStatColors().get(s));
			renderer.setSeriesPaint(s, gp0);

		}
	        
	    ChartPanel cPanel = new ChartPanel(barChart); 
	    getContainer().add(cPanel);
	   
	}
	
	public void initDonnee(HashMap<String, Integer> donnees) {
		this.donnees = donnees;
		initBar();
	}
	
	@Override
	public void reelStat() {
		if(getTitle().equals("Animaux")) {
			HashMap<Animals, ArrayList<Animal>> animals = GestionnaireAnimaux.getInstance().getAnimaux();
			HashMap<String, Integer> donnees = new HashMap<>();
			for(Animals animal : animals.keySet()) {
				donnees.put(animal.name(),Integer.valueOf( animals.get(animal).size()));
			}
			this.donnees=donnees;
			initBar();			
		}
		else {
			if(getTitle().equals("Végétaux")) {
				
				HashMap<Graine, Integer> graines = GestionnaireStocks.getInstance().getGraines();
				HashMap<String, Integer> donnees = new HashMap<>();	
				for(Graine graine : graines.keySet()) {
					if(graines.get(graine)>0) {
						donnees.put(graine.name(),new Integer( graines.get(graine)));						
					}
				}
				this.donnees=donnees;
				initBar();
			}
			
		}
	}
	
	

	
	

}
