package gui.gestionnaire;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import gui.gestionnaire.keys.Keys;

public class BillArticlePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 260;
	public static final int HEIGHT = 40;
	

	private JLabel nameLabel;
	private JSpinner quantitySpinner;
	
	public BillArticlePanel(Keys key, MarketGUI market, int w, int h, Font font) {
		super();
		//setBounds(x, y, w, h);
		Dimension dim = new Dimension(w, h);
		setLayout(new FlowLayout());
		setBackground(GestionnaireStocksGUI.LIGHT_BROWN);
		setPreferredSize(dim);
		setMaximumSize(dim);
		setMinimumSize(dim);
//		nameLabel = paintNameLabel(title, Gestionnaire.MIN_SPACE_BETWEEN, Gestionnaire.MIN_SPACE_BETWEEN, w/2, 30, font);
//		quantitySpinner = paintSpinner(2*Gestionnaire.MIN_SPACE_BETWEEN + (w/2),Gestionnaire.MIN_SPACE_BETWEEN, w/2 - (4*Gestionnaire.MIN_SPACE_BETWEEN),30,font);
		nameLabel = new JLabel();
		nameLabel.setText(GeneralPaintStrategy.getName(key));
		nameLabel.setPreferredSize(new Dimension(w/2, 30));
		quantitySpinner = new JSpinner();
		quantitySpinner.addChangeListener(new QuantityListener(quantitySpinner, key, market));   
		quantitySpinner.setValue(quantitySpinner.getNextValue());
		quantitySpinner.setPreferredSize(new Dimension(w/2 - (4*GestionnaireStocksGUI.MIN_SPACE_BETWEEN),30));
		add(nameLabel);
		add(quantitySpinner);
	}
	
	public JLabel paintNameLabel(String title, int x, int y, int w, int h, Font font) {
		JLabel label = new JLabel(title);
		//label.setBounds(x,y,w,h);
		//label.setFont(font);
		return label;
	}
	
	public JSpinner paintSpinner(int x, int y, int w, int h,Font font) {
		JSpinner spinner = new JSpinner();
		//spinner.setFont(font);
		//spinner.setBounds(x, y,w,h);
		return spinner;
	}
	
	public JLabel getNameLabel() {
		return nameLabel;
	}
	
	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}
	
	public JSpinner getQuantitySpinner() {
		return quantitySpinner;
	}
	
	public void setQuantitySpinner(JSpinner quantitySpinner) {
		this.quantitySpinner = quantitySpinner;
	}
	
	
}
