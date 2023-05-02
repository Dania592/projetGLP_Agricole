package gui.gestionnaire;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data.notion.basic.Farm;
import process.game.GameBuilder;
import process.game.Jeu;
import process.time.TimeManager;

public class GameOver extends JFrame{
	
	private JButton nouvellePartie;
	private JButton quitter;
	private Jeu jeu;

	private static final long serialVersionUID = 1L;

	public GameOver(Jeu jeu) {
		this.jeu = jeu;
		getContentPane().setLayout(null);
		getContentPane().setBackground(GeneralPaintStrategy.MEDIUM_BROWN);
		
		JPanel gameOverPanel = new JPanel();
		gameOverPanel.setSize(400,200);
		gameOverPanel.setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		gameOverPanel.setLayout(null);
		
		JLabel gameOver = new JLabel("Game Over");
		gameOver.setHorizontalAlignment(SwingConstants.CENTER);
		gameOver.setFont(GeneralPaintStrategy.font);
		gameOver.setBounds(25, 20, 334, 77);
		gameOverPanel.add(gameOver);
		
		nouvellePartie = new JButton("Rejouer");
		nouvellePartie.setBackground(GeneralPaintStrategy.DARK_BROWN);
		nouvellePartie.setForeground(GeneralPaintStrategy.LIGHT_BROWN);
		nouvellePartie.setFont(GeneralPaintStrategy.font);
		nouvellePartie.setBounds(239, 107, 120, 29);
		nouvellePartie.addActionListener(new Action());
		gameOverPanel.add(nouvellePartie);
		
		quitter = new JButton("Quitter");
		quitter.setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		quitter.setForeground(GeneralPaintStrategy.MEDIUM_BROWN);
		quitter.setFont(GeneralPaintStrategy.font);
		quitter.setBounds(35, 107, 120, 29);
		quitter.addActionListener(new Action());
		gameOverPanel.add(quitter);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(400,200);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	private class Action implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(nouvellePartie)) {
				if (jeu != null) {
					jeu.restart();
					GameOver.this.dispose();
				} 
			} else {
				System.exit(0);
			}
		}
	}
	
	
	public static void main(String[] args) {
		new GameOver(null);
	}

	
}
