package gui.statistique;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import gui.gestionnaire.GeneralPaintStrategy;
import gui.gestionnaire.RoundedPanel;

public  abstract class StatPanel extends RoundedPanel{

	
	private JPanel container ;

	private String title ;
	
	public StatPanel(String title) {
		super(null, 30, GeneralPaintStrategy.DARK_BROWN);
		this.title=title;
		init();
	}
	
	
	public void init() {
		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBounds(15,15,370,350);
		add(container);
		//statTest();

		
	}


	public JPanel getContainer() {
		return container;
	}


	public void setContainer(JPanel container) {
		this.container = container;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public abstract void reelStat() ;
		
	

	
	
	
	
	
}
