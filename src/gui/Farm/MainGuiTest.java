package gui.Farm;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import data.configuration.GameConfiguration;
import data.myExceptions.AskingToWorkAtIllegalHourException;
import data.notification.Message;
import data.notification.Messagerie;
import data.notion.basic.Element;
import data.notion.basic.Farm;
import data.planning.Activity;
import data.planning.DailyPlanner;
import data.structure.hability.Actionnable;
import gui.gestionnaire.gestionnairesGUI.GestionnaireStocksGUI;
import gui.gestionnaire.keys.Structures;
import process.action.task.Task;
import process.action.task.coordinator.TaskManager;
import process.time.TimeManager;

public class MainGuiTest  extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;

	private Farm farm ;
	private Board dashboard ;
	private Element selected ;
	private TaskManager taskManager ;
	//private int index =0; 
	private int x ;
	private int y ;


	public MainGuiTest(String title , Farm farm  , TaskManager taskManager) {
		super(title);
		this.farm = farm ;
		this.taskManager=taskManager;
		init();
	}

	public void init() {

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		selected= farm.getManager().getMapManager().get("fermier");	

		dashboard = new Board(farm, selected , taskManager, this);

		contentPane.add(dashboard);

		dashboard.addMouseListener(new MouseControls());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setDefaultLookAndFeelDecorated(true);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
		setResizable(false);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(GameConfiguration.GAME_SPEED);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			dashboard.repaint();
		}
	}

	public Farm getFarm() {
		return farm;
	}
	
	public Board getBoard() {
		return dashboard;
	}

	private class MouseControls implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			Element element = farm.getManager().search(x, y);
			if(element!=null) {
				//System.out.println(element.getReference());
				selected = element ;
				dashboard.setSelected(element);
				if(selected instanceof Actionnable) {
					Actionnable actionnable = (Actionnable)element;
					if(actionnable.isStatique() && !(actionnable.isCurrentlyUsedForAnotherTask())){
						ArrayList<Activity> activities;
						try {
							activities = taskManager.getPossibleTaskToPerform(actionnable);
							dashboard.getHud().add_Actions(x, y ,actionnable, activities);					
						} catch (AskingToWorkAtIllegalHourException e1) {
							Messagerie.getInstance().addMessage(new Message("Heures de travail légales \n"+DailyPlanner.FIRST_HOUR_OF_WORK+"  -  "+ DailyPlanner.LAST_HOUR_OF_WORK, TimeManager.getInstance().getClock().getHour().getValue(), TimeManager.getInstance().getClock().getMinute().getValue()));
						}
					}
				}

				if(selected.getClass().getSimpleName().equals("Entrepot")) {
					
					new GestionnaireStocksGUI("Gestionnaire Stock ", MainGuiTest.this,0);
					MainGuiTest.this.dispose();
				}

			}
			else {
				dashboard.getHud().remove_panels();

			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			x = e.getX() ;
			y = e.getY() ;	
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			int dx = e.getX() ;
			int dy = e.getY() ;
			farm.getManager().getMapManager().movingMap(dx - x, dy - y );
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

	}

}