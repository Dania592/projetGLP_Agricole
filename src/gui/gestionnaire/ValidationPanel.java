package gui.gestionnaire;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ValidationPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel totalCostLabel;
	private JButton validate;
	private MarketGUI market;
	
	public ValidationPanel(int totalCost, MarketGUI market) {
		super();
		this.market = market;
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		totalCostLabel = new JLabel();
		totalCostLabel.setText(String.valueOf(totalCost));
		totalCostLabel.setPreferredSize(new Dimension(100,30));
		
		validate = new JButton("Valider");
		validate.setPreferredSize(new Dimension(100,30));
		validate.addActionListener(new Validate());
		add(totalCostLabel);
		add(validate);
	}
	
	public void updateTotalCost(float cost) {
		cost += Float.valueOf(totalCostLabel.getText());
		totalCostLabel.setText(String.valueOf(cost));
	}
	
	private class Validate implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			market.getAchat().validateOrder();
			market.getBillPanel().removeAll();
			market.getBill().clear();
			totalCostLabel.setText("0");
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(totalCostLabel);
			String info;
			if (market.getAchat().isValidated()) {
				info = "Commande validée!";
			} else {
				info = "La commande ne peut pas être validée!";
			}
			new InfosTransaction(info, market.getFrame());
			frame.dispose();
		}
		
	}


}