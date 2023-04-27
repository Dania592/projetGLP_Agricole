package gui.Farm;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Profil extends JFrame{
	public Profil() {
		getContentPane().setBackground(new Color(255, 231, 171));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Dual Computer\\Desktop\\banque Image\\Structures\\av.png"));
		lblNewLabel.setBounds(80, 80, 81, 106);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Dual Computer\\Desktop\\banque Image\\Structures\\Group 24.png"));
		lblNewLabel_1.setBounds(10, 226, 853, 81);
		getContentPane().add(lblNewLabel_1);
	}
}
