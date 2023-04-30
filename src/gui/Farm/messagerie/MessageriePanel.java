package gui.Farm.messagerie;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.configuration.GameConfiguration;
import data.notification.Message;
import data.notification.Messagerie;
import gui.Farm.Hud;
import gui.gestionnaire.GeneralPaintStrategy;
import gui.gestionnaire.RoundedPanel;

public class MessageriePanel extends RoundedPanel {
	
	private JLabel up ;
	private JLabel down ;
	private JLabel clear ;
	private static final long serialVersionUID = 1L;
	private Messagerie messagerie ;
	private CardLayout cardlayout;
	private JPanel panel ;
	private Hud hud ;
	
	public MessageriePanel(Hud hud) {
		super(null,30, GeneralPaintStrategy.LIGHT_BROWN);
		setLayout(null);
		this.hud=hud;
		messagerie = Messagerie.getInstance();
		setBounds(10,GameConfiguration.WINDOW_HEIGHT-380 , 200 , 300);	
		init();
	}
	
	public void init() {

		JLabel label = new JLabel(new ImageIcon(GameConfiguration.IMAGE_PATH+"label_message.png"));
		label.setBounds(10, 15, 180, 30);
		add(label);
		
		cardlayout = new CardLayout();
		panel = new JPanel(cardlayout);
		panel.setOpaque(false);
		panel.setBounds(5,10,190,255);
		add(panel);
		
		if(messagerie.getMessages().size()>0) {
			up = new JLabel(new ImageIcon(GameConfiguration.IMAGE_PATH+"haut.png"));
			up.setBounds(30 , 265 , 30,30);
			up.addMouseListener(new Change());
			add(up);
			
			clear = new JLabel(new ImageIcon(GameConfiguration.IMAGE_PATH+"corbeille.png"));
			clear.setToolTipText("vider la messagerie");
			clear.setBounds(80 , 260 , 30,40);
			clear.addMouseListener(new Change());
			add(clear);
			
			down = new JLabel(new ImageIcon(GameConfiguration.IMAGE_PATH+"bas.png"));	
			down.setBounds(130,265 , 30 ,30);
			down.addMouseListener(new Change());
			add(down);	
		}
		else {
			RoundedPanel liste = new RoundedPanel(null , 20 ,GeneralPaintStrategy.MEDIUM_BROWN );
			liste.setBounds(18, 70 , 170 , 200 );
			JLabel noMessage = new JLabel("Messagerie vide!!");
			noMessage.setBounds(30 , 50 ,200 , 50);
			liste.add(noMessage);
			add(liste);
		}
		
		addMessages();
	}
	
	
	public void addMessages () {
		int y = 50 ;
		int x = 10 ; 		
		int indexCard = 0 ;
		int i =1;
		RoundedPanel liste = new RoundedPanel(null , 20 ,GeneralPaintStrategy.MEDIUM_BROWN );
		
		for(int indice_message = messagerie.getMessages().size()-1 ; indice_message >=0 ; indice_message-- ) {
			Message message = messagerie.getMessages().get(indice_message);
			if(indexCard==3) {
				indexCard = 0;
				panel.add(liste);
				liste =  new RoundedPanel(null , 20 ,GeneralPaintStrategy.MEDIUM_BROWN );
				y = 50 ; 
			}
			MessagePane pane = new MessagePane(message , hud);
			pane.setBounds(x , y , 170 , 55);
			liste.add(pane);
			
			if(i== messagerie.getMessages().size()) {
				panel.add(liste);
			}
			y+=pane.getHeight()+16;
			indexCard++;
			i++;
		}
	}
	
	public CardLayout getCardLayout() {
		return cardlayout;
	}
	
	private class Change implements MouseListener{

		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(down)) {
				MessageriePanel.this.getCardLayout().next(panel);				
			}
			else {
				if(e.getSource().equals(up)) {
					MessageriePanel.this.getCardLayout().previous(panel);
				}
				else {
					if(e.getSource().equals(clear)) {
						messagerie.removeMessages();
						hud.removeMessagerie();
					}
				}
			}
			
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
