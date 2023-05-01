package gui.gestionnaire;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import gui.gestionnaire.contolleurs.QuantityListener;
import gui.gestionnaire.gestionnairesGUI.GestionnaireStocksGUI;
import gui.gestionnaire.gestionnairesGUI.MarketGUI;
import gui.gestionnaire.keys.Keys;
import gui.gestionnaire.keys.PaintKeys;
import gui.gestionnaire.keys.RemoveFromCart;

public class BillArticlePanel extends RoundedPanel{
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 260;
	public static final int HEIGHT = 40;
	public static final Font MAN_FONT = new Font("Monospaced", Font.BOLD, 16); 
	

	private JLabel nameLabel;
	private JSpinner quantitySpinner;
	
	public BillArticlePanel(Keys key, MarketGUI market, int w, int h, Font font, PaintKeys type) {
		super(20, GestionnaireStocksGUI.LIGHT_BROWN);
		Dimension dim = new Dimension(w, h);
		setLayout(new FlowLayout());
		setPreferredSize(dim);
		setMaximumSize(dim);
		setMinimumSize(dim);

		JLabel supprimer = new JLabel();
		supprimer.setPreferredSize(new Dimension(30,30));
		supprimer.setIcon(new ImageIcon("src"+File.separator+"ressources"+File.separator+"-.png"));
		supprimer.addMouseListener(new RemoveFromCart(key,market));
		
		nameLabel = paintNameLabel(GeneralPaintStrategy.getName(key));
		nameLabel.setPreferredSize(new Dimension(w/2 - 30, 30));
		quantitySpinner = paintSpinner(MAN_FONT);
		if (type.equals(PaintKeys.SELL)) {
			quantitySpinner.setModel(new SpinnerNumberModel(0, 0, (int) Integer.valueOf(GeneralPaintStrategy.getInformation(key, key.getGestionnaireKey())[2]), 1));
		}
		quantitySpinner.addChangeListener(new QuantityListener(quantitySpinner, key, market));   
		//quantitySpinner.setValue(quantitySpinner.getNextValue());
		quantitySpinner.setPreferredSize(new Dimension(w/2 - (4*GestionnaireStocksGUI.MIN_SPACE_BETWEEN),30));
		add(nameLabel);
		add(quantitySpinner);
		add(supprimer);
	}
	
	public JLabel paintNameLabel(String title) {
		JLabel label = new JLabel(title);
		return label;
	}
	
	public JSpinner paintSpinner(Font font) {
		JSpinner spinner = new JSpinner();
		spinner.setFont(font);
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
