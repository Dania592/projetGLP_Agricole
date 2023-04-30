package gui.gestionnaire;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import gui.gestionnaire.contolleurs.WindowDispose;
import gui.gestionnaire.gestionnairesGUI.GestionnaireStocksGUI;

public class InfosTransaction extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static Font font = new Font(Font.DIALOG, Font.BOLD, 20);

	public InfosTransaction(String info, JFrame frame) {
	
		Container container = getContentPane();
		container.setBackground(GestionnaireStocksGUI.LIGHT_BROWN);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel(info);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(60, 70, 370, 50);
		label.setFont(font);
		getContentPane().add(label);
		
		JButton gestFinancier = new JButton("Retour au jeu");
		gestFinancier.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (frame != null) {
					frame.setVisible(true);
				}
				//new RHManagerGUI(frame, 0);
				InfosTransaction.this.dispose();
			}
			
		});
		gestFinancier.setBounds(150, 220, 200, 30);
		
		getContentPane().add(gestFinancier);
		
		setSize(500,300);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		addWindowListener(new WindowDispose(this, frame));
	}
	
}