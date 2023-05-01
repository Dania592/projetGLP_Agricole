package gui.Farm.actions;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.configuration.GameConfiguration;
import gui.Farm.Hud;
import process.action.TaskManager;
import process.action.task.Task;

/**
 * 
 * Panel associé à une seule action 
 *
 */
public class TachePane extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * la tâche à afficher 
	 */
	private Task<?> task ;
	/**
	 * le hud du jeu 
	 */
	private Hud hud ;
	
	/**
	 * constructeur pour instancier les panels de chaque tâches 
	 * @param task : la tâche à afficher 
	 * @param hud : hud du jeu 
	 */
	public  TachePane(Task<?> task , Hud hud ) {
		this.task=task;
		this.hud = hud;
		init();
	}
	
	/**
	 * initialisation du panel
	 */
	public void init() {
		
		setLayout(new FlowLayout());
		String activity = task.getActivity().getLabel();
		setOpaque(false);
		String imagePath = GameConfiguration.IMAGE_PATH+"Taches"+File.separator+task.getActivity()+".png";
		ImageIcon icon = new ImageIcon(imagePath);
		JLabel image = new JLabel(icon);
		image.setBackground(Color.black);
		addMouseListener(new MouseTask());
		add(image);
		
		JLabel labelImage = new JLabel(activity);
		add(labelImage);
	
	}
	
	/**
	 * lancement de la tâche 
	 */
	public void lunchTask() {
		TaskManager.getInstance().addToTaskToBeLaunched(task);
	}
	/***
	 * 
	 * MouseListener adpater au lancement des tâches 
	 *
	 */
	private class MouseTask implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if (task.getActivity().getLabel().equals("Planter")) {
				// afficher le chois de la graine
				lunchTask();
			} else {
				lunchTask();	
			}
			hud.removeActionPane();
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
	
}
