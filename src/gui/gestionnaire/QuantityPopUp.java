package gui.gestionnaire;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class QuantityPopUp extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel image = new RoundedPanel(157,26,200,200,null,200,Gestionnaire.DARK_BROWN);
	private JSpinner quantity = new JSpinner();
	private final JButton btnNewButton_1 = new JButton("Annuler");
	
	public QuantityPopUp() {
		setSize(530, 350);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		quantity.setBounds(148, 198, 217, 42);
		getContentPane().add(quantity);
		
		contentPane.add(image);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.setBounds(412, 275, 94, 28);
		getContentPane().add(btnNewButton);
		btnNewButton_1.setBounds(10, 275, 94, 28);
		
		getContentPane().add(btnNewButton_1);
		
	}
	
	public static void main(String[] args) {
		QuantityPopUp qpop = new QuantityPopUp();
	}
}
