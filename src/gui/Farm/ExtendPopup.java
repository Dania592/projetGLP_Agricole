package gui.Farm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import data.configuration.GameConfiguration;
import data.finance.Banque;
import data.stucture_base.Farm;
import gui.gestionnaire.GeneralPaintStrategy;
import gui.gestionnaire.InfosTransaction;
import gui.gestionnaire.RoundedPanel;
import gui.gestionnaire.contolleurs.WindowDispose;

public class ExtendPopup extends JFrame {

	private static final long serialVersionUID = 1L;

	public static int PRIX_PARCELLE = 5;
	private GeneralPaintStrategy generalPaintStrategy;
	private JButton valider;
	private JButton annuler;
	private JFrame frame;
	private Farm farm;
	private JLabel costLabel;
	private JLabel personnageLabel;
	private JPanel settingPanel;
	private JSpinner widthSpinner;
	private JSpinner heightSpinner;
	private JLabel height;
	private JLabel width;
	public static Font font = new Font(Font.SANS_SERIF,  Font.BOLD, 16);

	private int widthExtension = 0;
	private int heightExtension = 0;

	public ExtendPopup(JFrame frame, Farm farm) {
		generalPaintStrategy  = new GeneralPaintStrategy();
		this.frame = frame;
		this.farm = farm;
		getContentPane().setLayout(null);
		getContentPane().setBackground(GeneralPaintStrategy.LIGHT_BROWN);

		settingPanel = new RoundedPanel(105, 146, 240, 40,null,30,GeneralPaintStrategy.LIGHT_BROWN);
		getContentPane().add(settingPanel);

		int maxHeight = GameConfiguration.NB_LIGNE*GameConfiguration.CASE_DIMENSION - farm.getHeight();
		int maxWidth = GameConfiguration.NB_COLONNE*GameConfiguration.CASE_DIMENSION - farm.getWidth();

		widthSpinner = new JSpinner(new SpinnerNumberModel(0, 0, maxWidth, 1));
		widthSpinner.setBounds(160, 5, 62, 30);
		widthSpinner.addChangeListener(new Extend(widthSpinner));
		settingPanel.add(widthSpinner);

		heightSpinner = new JSpinner(new SpinnerNumberModel(0,0,maxHeight,1));
		heightSpinner.setBounds(45, 5, 54, 30);
		heightSpinner.addChangeListener(new Extend(heightSpinner));
		settingPanel.add(heightSpinner);

		height = generalPaintStrategy.printImageLabel("", 10, 5, 30, 40, GameConfiguration.IMAGE_PATH+"height.png",null);
		settingPanel.add(height);

		width = generalPaintStrategy.printImageLabel("", 120, 5, 40, 30, GameConfiguration.IMAGE_PATH+"width.png",null);
		settingPanel.add(width);

		RoundedPanel panel = new RoundedPanel(null,500,GeneralPaintStrategy.DARK_BROWN);
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(145, 10, 160, 150);
		getContentPane().add(panel);

		personnageLabel = generalPaintStrategy.printImageLabel("", 40, 20, 100, 130, GameConfiguration.IMAGE_PATH+"Employee"+File.separator+"Jean.png",null);
		personnageLabel.setHorizontalAlignment(SwingConstants.LEADING);
		panel.add(personnageLabel);

		annuler = generalPaintStrategy.paintButton(GeneralPaintStrategy.DARK_GREEN, Color.WHITE, font, "Annuler");
		annuler.setBounds(35, 260, 100, 30);
		annuler.addActionListener(new Action());
		getContentPane().add(annuler);

		valider = generalPaintStrategy.paintButton(GeneralPaintStrategy.DARK_GREEN, Color.WHITE, font, "Valider");
		valider.setBounds(300, 260, 100, 30);
		valider.addActionListener(new Action());
		getContentPane().add(valider);

		costLabel = generalPaintStrategy.printImageLabel("0", 95, 190, 260, 55, GameConfiguration.IMAGE_PATH+"solde.png",font);
		getContentPane().add(costLabel);

		setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		setSize(450,340);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		addWindowListener(new WindowDispose(this, frame));
	}

	public float getTotalCost() {
		return Float.valueOf(costLabel.getText());
	}

	public void setTotalCost(float cost) {
		costLabel.setText(String.valueOf(cost));
	}

	public String extendFarm() {
		String title = null;
		if (widthExtension>=0 && ((farm.getWidth()+widthExtension)<=GameConfiguration.NB_COLONNE)){
			farm.setWidht(farm.getWidth() + widthExtension);
		} else {
			title = "Extension en largeur depasse la limite!";
		}
		if (heightExtension>=0 && ((farm.getHeight()+heightExtension)<=GameConfiguration.NB_LIGNE)) {
			farm.setHeight(farm.getHeight() + heightExtension);
		} else {
			title = "Extension en longueur depasse la limite!";
		}
		Banque.getInstance().debiter(Float.valueOf(costLabel.getText()));
		return title;
	}

	private class Extend implements ChangeListener{

		private Integer lastValue;
		private JSpinner spinner;

		public Extend(JSpinner spinner) {
			this.spinner = spinner;
			lastValue = Integer.valueOf(String.valueOf(spinner.getValue()));
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			Integer newValue = Integer.valueOf(String.valueOf(spinner.getValue()));
			if (lastValue != null && !newValue.equals(lastValue)) {
				if (newValue > 0 && newValue > lastValue) {
					if (Banque.getInstance().getCompte().getSolde() >= (ExtendPopup.this.getTotalCost() + ((newValue-lastValue) *PRIX_PARCELLE))){
						ExtendPopup.this.setTotalCost(ExtendPopup.this.getTotalCost() + ((newValue-lastValue) *PRIX_PARCELLE));
					} else {
						new InfosTransaction("Solde insuffisant", ExtendPopup.this);
						ExtendPopup.this.dispose();
						newValue = lastValue;
					}
				}
			}
			if (e.getSource().equals(widthSpinner)) {
				ExtendPopup.this.widthExtension = newValue;
			} else {
				ExtendPopup.this.heightExtension = newValue;
			}
			lastValue = newValue;
		}

	}

	private class Action implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(valider)) {
				if (widthExtension != 0 || heightExtension != 0) {
					String title = extendFarm();
					if (title == null ) {
						title = "Votre monde grandit!";
					}
					new InfosTransaction(title, frame);
				}
				ExtendPopup.this.dispose();
			} else {
				frame.setVisible(true);
				ExtendPopup.this.dispose();
			}
		}

	}

	//	public static void main(String[] args) {
	//		new ExtendPopup(null,null);
	//	}
}
