package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import data.configuration.GameConfiguration;
import data.notion.basic.Farm;
import gui.gestionnaire.GeneralPaintStrategy;
import process.game.GameBuilder;
import process.game.Jeu;
import process.game.SaveFarm;
import process.time.TimeManager;
import javax.swing.JEditorPane;

public class Accueil extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel lastFarm;
	private JLabel newFarm;

	public Accueil() {

			
		JEditorPane editorPane = new JEditorPane();
		
		
		editorPane.setBounds(192, 131, 493, 365);
		getContentPane().add(editorPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,700);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	private class ReadFarmAction implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(lastFarm)) {
				SaveFarm save = new SaveFarm();
				Farm farm = save.serializationRead(GameConfiguration.FILE_NAME_SAVE);
				Jeu jeu = new Jeu(farm, "Reprenez votre monde où vous l'avez laissé !");
				TimeManager.getInstance().setFarm(farm);
				Thread gameThread = new Thread(jeu);
				gameThread.start();
			} else {
				if(e.getSource().equals(newFarm)) {
					Farm farm=GameBuilder.buildinFarm();	
					Jeu jeu = new Jeu(farm, "Nouvelle partie");
					TimeManager.getInstance().setFarm(farm);
					Thread game = new Thread(jeu);
					game.start();						
				}
			}
			Accueil.this.dispose();

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
		new Accueil();
	}
}
