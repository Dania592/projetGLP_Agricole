package gui.gestionnaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import data.configuration.GameConfiguration;
import data.gestion.GestionnaireStocks;
import gui.Farm.Hud;
import gui.Farm.actions.TachePane;
import gui.gestionnaire.UI.CustomizedScrollBar;
import gui.gestionnaire.gestionnairesGUI.GestionnaireStocksGUI;
import gui.gestionnaire.keys.Graine;

public class ChoixGraine extends RoundedPanel{

	private Hud hud;
	private static final long serialVersionUID = 1L;
	public static Color MARKET_CARD_COLOR = GestionnaireStocksGUI.LIGHT_BROWN;
	public final static Font LABEL_FONT = new Font("Monospaced", Font.PLAIN|Font.BOLD, 20);
	private JPanel grainesPanel = new JPanel();
	private BoxLayout boxLayout = new BoxLayout(grainesPanel, BoxLayout.Y_AXIS);
	private ArrayList<Graine> graines;
	private TachePane tachePane;
	
	public ChoixGraine(int x, int y, int w, int h, TachePane tachePane, Hud hud) {
		super(20,GeneralPaintStrategy.MEDIUM_BROWN, false);
		this.tachePane = tachePane;
		this.hud = hud;
		graines = new ArrayList<Graine>(GestionnaireStocks.getInstance().getAvailableGraines());
		setLayout(new BorderLayout());
		setBounds(x, y, w, h);
		paintChoixGraine(x,y, w-10, 40);
	}

	public void paintChoixGraine(int x, int y, int w, int h) {

		JLabel name = new JLabel();
		name.setText("Choix Graine");
		name.setFont(LABEL_FONT);
		name.setForeground(Color.WHITE);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		add(name,BorderLayout.NORTH);
		
		grainesPanel.setLayout(boxLayout);
		grainesPanel.setBackground(GestionnaireStocksGUI.MEDIUM_BROWN);
		grainesPanel.add(Box.createRigidArea(new Dimension(0,5)));
		JScrollPane scrollPane = new JScrollPane(grainesPanel);
		scrollPane.setVerticalScrollBar(new CustomizedScrollBar());
		scrollPane.setBorder(null);
		scrollPane.setBackground(GeneralPaintStrategy.MEDIUM_BROWN);
		scrollPane.setViewportView(grainesPanel);
		
		fillGraines(w, h);
		
		add(scrollPane, BorderLayout.CENTER);
	}
	
	public void fillGraines(int w, int h) {
		for (Graine graine : graines){
			grainesPanel.add(paintGrainePanel(graine, w, h));
		}
	}
	
	public Component paintGrainePanel(Graine graine, int w, int h) {
		JPanel panel = GeneralPaintStrategy.paintRoundedPanel(0, 0, w, h, boxLayout, 30, MARKET_CARD_COLOR);

		Dimension dim = new Dimension(w, h);
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(dim);
		panel.setMaximumSize(dim);
		panel.setMinimumSize(dim);
		
		JLabel nameLabel = new JLabel();
		nameLabel.setText(GeneralPaintStrategy.getName(graine));
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setForeground(GeneralPaintStrategy.DARK_BROWN);
		nameLabel.setPreferredSize(new Dimension(w, 30));
		nameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
	
		panel.add(nameLabel);
		
		panel.addMouseListener(new Choix(graine));
		return panel;
	}
	
	private class Choix implements MouseListener{

		private Graine graine;
		
		public Choix(Graine graine) {
			this.graine = graine;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			tachePane.lunchTask(graine);
			hud.removeChoixGraine();
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
}
