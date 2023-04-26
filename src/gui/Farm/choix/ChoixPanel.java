package gui.Farm.choix;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import data.configuration.GameConfiguration;
import gui.Farm.Board;
import gui.gestionnaire.GeneralPaintStrategy;




public class ChoixPanel extends JLayeredPane {

	private static final long serialVersionUID = 1L;	
	// la clé de hashMap et le simple name de la classe 
	private HashMap<String, ElementCard> cards = new HashMap<>();
	private CardLayout cardlayout ;
	private JLabel previous ;
	private JLabel next ;

	public ChoixPanel( Board component  ) {
		super();
		cards=component.getChoix().getCards();
		init();
	}

	public CardLayout getCardLayout() {
		return cardlayout;
	}
	
	
	public void init() {
		setLayout(null);
		setOpaque(true);
		setBackground(GeneralPaintStrategy.LIGHT_BROWN);
		addChoixPanel();		
	}

	public void addChoixPanel() {
		
		cardlayout = new CardLayout();
		JPanel panel = new JPanel(cardlayout);
		panel.setBounds(55, 5, GameConfiguration.WINDOW_WIDTH-280, 150 );
		add(panel);

		previous = new JLabel(new ImageIcon(GameConfiguration.IMAGE_PATH+"previous.png"));
		previous.setBounds(5 , 50 , 50 ,50);
		previous.addMouseListener(new Change(panel));
		add(previous);


		next = new JLabel(new ImageIcon(GameConfiguration.IMAGE_PATH+"next.png"));
		next.addMouseListener(new Change(panel));
		next.setBounds(GameConfiguration.WINDOW_WIDTH-225, 50 , 50 ,50);
		add(next);
		
		
		ArrayList<ElementCard> listCards = new ArrayList<>();
		for(ElementCard card : cards.values()) {
			//System.out.println(card.getElements().size());
			listCards.add(card);
		}
		int nb = GameConfiguration.NB_CARD_CHOIX;
		for(int index = 0 ; index <= listCards.size()/nb ; index++) {
			JPanel liste = new JPanel();
			liste.setBackground(GeneralPaintStrategy.MEDIUM_BROWN);
			int x = 50 ;
			liste.setLayout(null);
			for(int i = index*nb ; i <= index*nb+nb && i< listCards.size() ; i++) {	
				ElementCard card = listCards.get(i);
				card.setBounds(x,5, card.getWidth(), card.getHeight());
				card.removePositionforEmpty();
				liste.add(card);
				x+= card.getWidth()+50;
			}
			panel.add(liste);
		}
		
	}
	
	
	
	private class Change implements MouseListener{

		JPanel parent ; 
		Change(JPanel panel ){
			parent = panel ;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(next)) {
				ChoixPanel.this.getCardLayout().next(parent);				
			}
			else {
				if(e.getSource().equals(previous)) {
					ChoixPanel.this.getCardLayout().previous(parent);
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
