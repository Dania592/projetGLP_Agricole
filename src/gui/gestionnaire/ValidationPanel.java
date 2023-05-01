package gui.gestionnaire;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gui.gestionnaire.gestionnairesGUI.MarketGUI;
import process.transaction.FinanceManager;

public class ValidationPanel extends RoundedPanel{

	private static final long serialVersionUID = 1L;
	private JLabel totalCostLabel;
	private JButton validate;
	private JButton cancel;
	private MarketGUI market;
	
	public ValidationPanel(int totalCost, MarketGUI market) {
		super(20,GeneralPaintStrategy.MEDIUM_BROWN, false);
		this.market = market;
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		totalCostLabel = new JLabel();
		totalCostLabel.setForeground(Color.WHITE);
		totalCostLabel.setText(String.valueOf(totalCost));
		totalCostLabel.setPreferredSize(new Dimension(80,30));
		
		validate = new JButton("Valider");
		setButtonStyle(validate);
		validate.setPreferredSize(new Dimension(80,30));
		validate.addActionListener(new Validate());
		
		cancel = new JButton("Annuler");
		setButtonStyle(cancel);
		cancel.setPreferredSize(new Dimension(80,30));
		cancel.addActionListener(new Cancel());
		
		add(totalCostLabel);
		add(validate);
		add(cancel);
	}
	
	public void updateTotalCost(float cost) {
		cost += Float.valueOf(totalCostLabel.getText());
		totalCostLabel.setText(String.valueOf(cost));
	}
	
	public float getTotalCost() {
		return Float.valueOf(totalCostLabel.getText());
	}
	
	private class Validate implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			market.getTransaction().validate();
			market.getBillPanel().removeAll();
			market.getBill().clear();
			totalCostLabel.setText("0");
			String info;
			if (market.getTransaction().isValidated()) {
				info = "Transaction validée!";
			} else {
				info = "La transaction ne " + FinanceManager.EOL+ "peut pas être effectuée!";
			}
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(totalCostLabel);
			new InfosTransaction(info, market.getFrame());
			frame.dispose();
		}
		
	}
	
	private class Cancel implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			market.getTransaction().cancel();
			market.getBillPanel().removeAll();
			market.getBill().clear();
			totalCostLabel.setText("0");
			String info;
			if (market.getTransaction().isValidated()) {
				info = "Transaction annulée!" + market.getTransaction().getClass().getSimpleName();
			} else {
				info = "La transaction ne " + FinanceManager.EOL+ "peut pas être annulée!";
			}
			market.getFrame().setVisible(true);
			market.dispose();
		}
		
	}
	
	public void setButtonStyle(JButton button) {
		button.setBackground(GeneralPaintStrategy.DARK_GREEN);
		button.setForeground(Color.WHITE);
	}


}