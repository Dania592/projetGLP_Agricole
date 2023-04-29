package gui.gestionnaire;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Home extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private RoundedPanel panel;
	private JLabel acheter;
	private JLabel vendre;
	private JLabel stocks;
	private JLabel finances;
	private JLabel recruter;
	private GeneralPaintStrategy generalPaintStrategy = new GeneralPaintStrategy(); 
	
	public Home(JFrame frame) {
		Action action = new Action();
		
		getContentPane().setLayout(null);
		
		JLabel avatar = new JLabel();
		avatar.setIcon(new ImageIcon("src"+File.separator+"ressources"+File.separator+"personnage2.png"));
		avatar.setBounds(39, 10, 127, 170);
		getContentPane().add(avatar);
		
		JLabel message = generalPaintStrategy.printImageLabel("Message par défaut à modifier", 10, 167, 215, 100, "src"+File.separator+"ressources"+File.separator+"homeMessage.png");
		getContentPane().add(message);
		
		panel = new RoundedPanel(30, GeneralPaintStrategy.MEDIUM_BROWN);
		panel.setBounds(221, 15, 190, 240);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		int width = 187;
		int height = 35;
		int gap = GeneralPaintStrategy.MIN_SPACE_BETWEEN;
		int cpt = 0;
		acheter = generalPaintStrategy.printImageLabel("Acheter", 10, gap + (cpt * (gap + height)), width, height, "src"+File.separator+"ressources"+File.separator+"acheter.png");
		acheter.addMouseListener(action);
		cpt++;
		vendre = generalPaintStrategy.printImageLabel("Vendre", 10, gap + (cpt * (gap + height)), width, height, "src"+File.separator+"ressources"+File.separator+"vendre.png");
		vendre.addMouseListener(action);
		cpt++;
		stocks = generalPaintStrategy.printImageLabel("Consulter le stock", 10, gap + (cpt * (gap + height)), width, height, "src"+File.separator+"ressources"+File.separator+"stocks.png");
		stocks.addMouseListener(action);
		cpt++;
		finances = generalPaintStrategy.printImageLabel("Gérer les finances", 10, gap + (cpt * (gap + height)), width, height, "src"+File.separator+"ressources"+File.separator+"finances.png");
		finances.addMouseListener(action);
		cpt++;
		recruter = generalPaintStrategy.printImageLabel("Recruter", 10, gap + (cpt * (gap + height)), width, height, "src"+File.separator+"ressources"+File.separator+"vendre.png");
		recruter.addMouseListener(action);
		
		panel.add(vendre);
		panel.add(acheter);
		panel.add(stocks);
		panel.add(finances);
		panel.add(recruter);
		
		getContentPane().setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		
		setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		setSize(449,303);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		addWindowListener(new WindowDispose(this, frame));
	}
	
	private class Action implements MouseListener{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource().equals(acheter)) {
				new MarketGUI(Home.this, PaintKeys.BUY, 0);
			}else if (e.getSource().equals(vendre)) {
				new MarketGUI(Home.this, PaintKeys.SELL, 0);
			} else if (e.getSource().equals(stocks)) {
				new GestionnaireStocksGUI("Gestionnaire de stocks", Home.this, 0);
			} else if (e.getSource().equals(finances)) {
				new GestionnaireFinancierGUI(Home.this, 1);
			} else {
				new RHManagerGUI(Home.this, 1);
			}
			Home.this.dispose();
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
	
	public static void main(String[] args) {
		new Home(null);
	}
}
