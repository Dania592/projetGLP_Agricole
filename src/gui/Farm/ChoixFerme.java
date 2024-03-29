package gui.Farm;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import data.configuration.GameConfiguration;
import data.notion.basic.Farm;
import process.game.GameBuilder;
import process.game.Jeu;
import process.game.SaveFarm;


/**
 * servira pour le test de l'enregistement du jeu 
 * @author dania
 *
 */
public class ChoixFerme extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel lastFarme ;
	private JLabel newFarme ;
	public ChoixFerme() {
		super("choisir la ferme");
		init();
	}
	
	public void init() {

		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(255, 231, 171));
		
		File file = new File(GameConfiguration.FILE_NAME_SAVE);
		ImageIcon icon ;
		if(file.exists()) {			
			lastFarme = new JLabel();
			icon = new ImageIcon(GameConfiguration.IMAGE_PATH+"lastFarm.png");
			lastFarme.setIcon(icon);
			lastFarme.setBounds(100, 100, 200, 50);
			lastFarme.addMouseListener(new ReadFarmAction());
			contentPane.add(lastFarme);	
		}
		
		newFarme = new JLabel();
		newFarme.setBounds(100, 200, 200, 50);
		ImageIcon newicon = new ImageIcon(GameConfiguration.IMAGE_PATH+"newFarm.png");
		newFarme.setIcon(newicon);
		newFarme.addMouseListener(new ReadFarmAction() );
		contentPane.add(newFarme);	
	
		JLabel personnage = new JLabel();
		personnage.setBounds(450,0,200 , 400);
		ImageIcon persoicon = new ImageIcon(GameConfiguration.IMAGE_PATH+"personnage2.png");
		personnage.setIcon(persoicon);
		contentPane.add(personnage);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setSize(GameConfiguration.WINDOW_WIDTH/2 , GameConfiguration.WINDOW_HEIGHT/2);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	

	
	private class ReadFarmAction implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(lastFarme)) {
				SaveFarm save = new SaveFarm();
				Farm farm = save.serializationRead(GameConfiguration.FILE_NAME_SAVE);
				Jeu jeu = new Jeu(farm, "Reprenez votre monde où vous l'avez laissé !");
				Thread gameThread = new Thread(jeu);
				gameThread.start();				
				ChoixFerme.this.dispose();
			}
			else {
				if(e.getSource().equals(newFarme)) {
					Farm farm=GameBuilder.buildinFarm();	
					Jeu jeu = new Jeu(farm, "Nouvelle partie");
					Thread game = new Thread(jeu);
					game.start();						
				}
			}
			ChoixFerme.this.dispose();
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	public static void main(String[]args) {
		new ChoixFerme();
	}

}

