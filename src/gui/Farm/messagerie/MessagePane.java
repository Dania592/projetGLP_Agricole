package gui.Farm.messagerie;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import data.configuration.GameConfiguration;
import data.notification.Message;
import data.notification.Messagerie;
import gui.Farm.Hud;
import gui.gestionnaire.GeneralPaintStrategy;
import gui.gestionnaire.RoundedPanel;

public class MessagePane extends RoundedPanel{

	private Message message ;
	private JLabel supp;
	private JLabel time;
	private JTextArea text ;
	private Hud hud ;
	
	public MessagePane(Message message , Hud hud) {
		super(null, 30, GeneralPaintStrategy.LIGHT_BROWN);
		this.hud=hud;
		this.message = message ;
		init();
	}
	
	public void init() {
		text = new JTextArea(message.getContenu());
		text.setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		text.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,  13));
		text.setBounds(10, 3, 130, 35);
		text.setEditable(false);
		add(text);
		
		String heure = (message.getHeure()/10==0)? "0"+message.getHeure() : ""+message.getHeure();
		String minute = (message.getMinute()/10==0)? "0"+message.getMinute() : ""+message.getMinute();
		time = new JLabel(heure+":"+minute);
		time.setBounds(125,37,40,10);
		add(time);
		
		supp = new JLabel(new ImageIcon(GameConfiguration.IMAGE_PATH+"supp.png"));
		supp.setBounds(139,3,30, 30);
		supp.addMouseListener(new MouseMessage());
		add(supp);
		
	}
	
	private class MouseMessage implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			Messagerie.getInstance().getMessages().remove(message);
			hud.removeMessagerie();		
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
