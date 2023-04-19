package gui.Farm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import data.configuration.GameConfiguration;

public class FermierGui extends JFrame {
	
	
	public FermierGui() {
		super("Mon fermier");
		init();
	}
	
	public void init() {
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(255, 231, 171));
		
		JLabel nom = new JLabel("fermier");
		nom.setBounds(200, 200, 100, 50);
		nom.setBackground(new Color(103, 148, 76));
		//nom.setIcon(new ImageIcon(GameConfiguration.IMAGE_PATH+"fermier_gui"+File.separator+"label_vert.png"));
		contentPane.add(nom);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setSize(500 , 500);
		setResizable(false);
	}

	public static void main(String[] args) {
		new FermierGui();

	}
	
	


}
