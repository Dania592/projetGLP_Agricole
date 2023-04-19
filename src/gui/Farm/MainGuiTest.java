package gui.Farm;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import data.configuration.GameConfiguration;
import data.planning.Activity;
import data.structure.hability.Actionnable;
import data.structure.hability.Productif;
import data.stucture_base.Element;
import data.stucture_base.Farm;
import process.action.TaskManager;
import process.action.task.Task;
import process.game.GameBuilder;

public class MainGuiTest  extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;

	private Farm farm ;
	private Board dashboard ;
	private Element selected ;
	private int index =0; 
	private int x ;
	private int y ;


	public MainGuiTest(String title , Farm farm ) {
		super(title);
		this.farm = farm ;
		init();
	}

	public void init() {

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		//farm=GameBuilder.buildinFarm();
		
		//System.out.println(farm.getTimeManager().getClock().getMinute().getValue());
		//SaveFarm save = new SaveFarm(farm);
		
		//farm = save.serializationRead(GameConfiguration.FILE_NAME_SAVE);
		//System.out.println(farm.getTimeManager().getClock().getMinute().getValue());

		//farm=GameBuilder.buildinFarm();

		selected= farm.getManager().getMapManager().get("fermier");	

		dashboard = new Board(farm, selected);

		contentPane.add(dashboard);

		dashboard.addMouseListener(new MouseControls());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setDefaultLookAndFeelDecorated(true);
		setExtendedState(this.MAXIMIZED_BOTH);
		setVisible(true);
		//setSize(GameConfiguration.WINDOW_WIDTH , GameConfiguration.WINDOW_HEIGHT);
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
			//dashboard.setSelected(selected);
			
			dashboard.getFarm().getEvolutionManager().UpdateEvolution();
			
			dashboard.repaint();
//			if(dashboard.getSelected() instanceof Actionnable){
//				Actionnable actionnableSelected = (Actionnable) dashboard.getSelected();
//				ArrayList<Task<?>> tasks = farm.getTaskManager().getPossibleTaskToPerform(actionnableSelected);
//				for (Task<?> task : tasks) {
//					System.out.println(tasks);
//				}
//			}
		}
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
					ArrayList<Task<?>> tasks = TaskManager.getInstance().getPossibleTaskToPerform(actionnable);
					dashboard.getHud().add_Actions(x, y , tasks);					
				}
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
		public void mouseEntered(MouseEvent e) {
			//System.out.println(x+" "+y);

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}




}
