package gui.Farm.messagerie;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import data.configuration.GameConfiguration;
import gui.Farm.Hud;
import gui.gestionnaire.GeneralPaintStrategy;
import gui.gestionnaire.RoundedPanel;

public class AlertPane extends RoundedPanel{

	public AlertPane() {
		super(null, 30, GeneralPaintStrategy.MEDIUM_BROWN);
		init();
		// TODO Auto-generated constructor stub
	}
	
	public void init() {
		JTextArea message = new JTextArea("Nouveau message!");
		message.setBounds(50,15,145,30);
		message.setEditable(false);
		message.setBackground(GeneralPaintStrategy.MEDIUM_BROWN);
		message.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		message.setForeground(GeneralPaintStrategy.DARK_BROWN);
		add(message);
		
		JLabel dessin = new JLabel(new ImageIcon(GameConfiguration.IMAGE_PATH+"alert.png"));
		dessin.setBounds(5,0,50,50);
		add(dessin);
		
		addMouseListener(new AlertMouse());
	}
	
	private class AlertMouse implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			Hud.alertMessage();
			
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
