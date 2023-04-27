package gui.gestionnaire;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Asupprimer extends JFrame{
	public Asupprimer() {
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Dual Computer\\Desktop\\banque Image\\Structures\\eau.png"));
		lblNewLabel.setBounds(33, 29, 52, 56);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 128, 128));
		panel.setBounds(59, 60, 208, 252);
		getContentPane().add(panel);
		panel.setLayout(null);
	}

	private static final long serialVersionUID = 1L;
}
