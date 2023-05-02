package gui.Farm.farmer;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;

import data.acteur.Fermier;
import gui.Farm.MainGuiTest;
import gui.gestionnaire.GeneralPaintStrategy;
import gui.gestionnaire.RoundedPanel;
import gui.gestionnaire.contolleurs.WindowDispose;

public class FermierGui extends JFrame {
	private PanelFermier panelPrincipale ;
	private Fermier fermier ;
	private JFrame frame ; 
	
	public FermierGui(JFrame frame , Fermier fermier) {
		super("Mon fermier");
		this.frame = frame ;
		 addWindowListener(new WindowDispose(this, frame));
		 this.fermier = fermier;
		init();
	}
	
	public void init() {
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		panelPrincipale = new PanelFermier(fermier , frame , this);
		panelPrincipale.setBounds(5,5,920,700);
		contentPane.add(panelPrincipale );
		contentPane.setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		
		RoundedPanel buttonPane = new RoundedPanel(null,30 , GeneralPaintStrategy.MEDIUM_BROWN);
		buttonPane.setBounds(30,590,850,60);
		contentPane.add(buttonPane);
		GeneralPaintStrategy strategie = new GeneralPaintStrategy();
		JButton retour = strategie.paintButton(GeneralPaintStrategy.DARK_GREEN, GeneralPaintStrategy.LIGHT_BROWN, new Font(Font.SANS_SERIF , Font.BOLD, 18), "Retour au jeu");
		retour.setBounds(350, 7, 200, 40);
		retour.addActionListener(new Retour());
		buttonPane.add(retour);
		
		setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pack();
		setVisible(true);
		setSize(920 , 700);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	private class Retour implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(frame!=null) {
				frame.setVisible(true);				
			}
			FermierGui.this.dispose();
			
		}
		
	}

	public static void main(String[] args) {
		new FermierGui(null , new Fermier("Saadi", new Date(), " "));

	}
	
	


}
