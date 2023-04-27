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
import process.action.task.Task;

public class TachePane extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private Task task ;
	private Hud hud ;
	
	public  TachePane(Task task , Hud hud ) {
		this.task=task;
		this.hud = hud;
		init();
	}
	
	public void init() {
		
		setLayout(new FlowLayout());
		String activity = task.getActivity().getLabel();
		
		String imagePath = GameConfiguration.IMAGE_PATH+"Taches"+File.separator+task.getActivity()+".png";
		ImageIcon icon = new ImageIcon(imagePath);
		JLabel image = new JLabel(icon);
		image.setBackground(Color.black);
		image.addMouseListener(new MouseTask());
		add(image);
		
		JLabel label_image = new JLabel(activity);
		label_image.addMouseListener(new MouseTask());
		add(label_image);
	
	}
	
	public void lunchTask() {
		//TaskManager.getInstance().addToTaskToBeLaunched(task);
	}
	
	private class MouseTask implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			//lunchTask();
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
