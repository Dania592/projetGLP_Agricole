package gui.gestionnaire;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ValidationPanel extends JPanel{


	private static final long serialVersionUID = 1L;
	private JLabel totalCostLabel;
	private JButton validate;
	
	public ValidationPanel(int totalCost) {
		super();
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		totalCostLabel = new JLabel();
		totalCostLabel.setText(String.valueOf(totalCost));
		totalCostLabel.setPreferredSize(new Dimension(100,30));
		
		validate = new JButton("Valider");
		validate.setPreferredSize(new Dimension(100,30));
		add(totalCostLabel);
		add(validate);
	}
	
	public void riseTotalCost(float cost) {
		cost += Integer.valueOf(totalCostLabel.getText());
		totalCostLabel.setText(String.valueOf(cost));
	}
	
	public void reduceTotalCost(float cost) {
		cost -= Integer.valueOf(totalCostLabel.getText());
		totalCostLabel.setText(String.valueOf(cost));
	}

}
