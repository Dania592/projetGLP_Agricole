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
import data.myExceptions.AskingToWorkAtIllegalHourException;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.myExceptions.UnknownActivityException;
import data.notification.Message;
import data.notification.Messagerie;
import data.planning.Activity;
import data.structure.hability.Actionnable;
import gui.Farm.Hud;
import gui.gestionnaire.keys.Graine;
import process.action.TaskManager;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.TaskNotNeededToBePerform;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.time.TimeManager;

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
	private Activity activity ;
	/**
	 * le hud du jeu 
	 */
	private Hud hud ;
	
	private Actionnable actionnable;
	/**
	 * constructeur pour instancier les panels de chaque tâches 
	 * @param task : la tâche à afficher 
	 * @param hud : hud du jeu 
	 */
	public  TachePane(Activity activity, Actionnable actionnable, Hud hud ) {
		this.activity=activity;
		this.hud = hud;
		this.actionnable = actionnable;
		init();
	}
	
	/**
	 * initialisation du panel
	 */
	public void init() {
		
		setLayout(new FlowLayout());
		String activity = this.activity.getLabel();
		setOpaque(false);
		String imagePath = GameConfiguration.IMAGE_PATH+"Taches"+File.separator+this.activity+".png";
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
		try {
			TaskManager.getInstance().addNewTask(activity, actionnable);
		} catch (UnableToGenerateNewTaskException | NotImplementYetException | TaskNotNeededToBePerform
				| UnknownActivityException | AskingToWorkAtIllegalHourException
				| UnableToPerformSuchActionWithCurrentActionnable | HaveNotProducedYetException
				| BeingCannotPerformSuchActionException | NeedToBeSendToSpecialProductionPlaceException
				| ProblemOccursInProductionException | UnableToMakeTheTransfertException e) {
			Messagerie.getInstance().addMessage(new Message("Impossible d'initer la tâche", TimeManager.getInstance().getClock().getHour().getValue(), TimeManager.getInstance().getClock().getMinute().getValue()));
		}
	}

	public void lunchTask(Graine graine){
		try {
			TaskManager.getInstance().addNewTask(activity, actionnable, graine);
		} catch (UnableToGenerateNewTaskException | NotImplementYetException | TaskNotNeededToBePerform
				| UnknownActivityException | AskingToWorkAtIllegalHourException
				| UnableToPerformSuchActionWithCurrentActionnable | HaveNotProducedYetException
				| BeingCannotPerformSuchActionException | NeedToBeSendToSpecialProductionPlaceException
				| ProblemOccursInProductionException | UnableToMakeTheTransfertException e) {
			Messagerie.getInstance().addMessage(new Message("Impossible d'initer la tâche", TimeManager.getInstance().getClock().getHour().getValue(), TimeManager.getInstance().getClock().getMinute().getValue()));
			
		}
	}

	/***
	 * 
	 * MouseListener adpater au lancement des tâches 
	 *
	 */
	private class MouseTask implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if (activity == Activity.PLANT) {
				//TODO afficher le choix de la graine
				//TODO IIIIIIIIIICIIIIIIIII à supprimer mettre le retour du xhoix de l'utilisateur
				Graine defaultGraine = Graine.BEET_SEED; 
				lunchTask(defaultGraine);
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
