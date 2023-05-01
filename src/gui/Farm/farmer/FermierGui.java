package gui.Farm.farmer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.util.Date;

import javax.swing.JFrame;

import data.acteur.Fermier;
import gui.gestionnaire.GeneralPaintStrategy;
import gui.gestionnaire.contolleurs.WindowDispose;
import process.game.GameBuilder;

public class FermierGui extends JFrame {
	private PanelFermier panelPrincipale ;
	private Fermier fermier ;
	private JFrame frame ; 
	
	public FermierGui(JFrame frame , Fermier fermier) {
		super("Mon fermier");
		 addWindowListener(new WindowDispose(this, frame));
		 this.fermier = fermier;
		init();
	}
	
	public void init() {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		panelPrincipale = new PanelFermier(fermier , frame , this);
		contentPane.add(panelPrincipale );
	
		setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pack();
		setVisible(true);
		setSize(920 , 700);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	

	public static void main(String[] args) {
		new FermierGui(null , new Fermier("Saadi", new Date(), " "));

	}
	
	


}
