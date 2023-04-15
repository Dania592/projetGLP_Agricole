package gui.Farm;


import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JLayeredPane;



public class ChoixPanel extends JLayeredPane {
	
	private static final long serialVersionUID = 1L;	
	// la clé de hashMap et le simple name de la classe 
	private HashMap<String, ElementCard> cards = new HashMap<>();
	
	public ChoixPanel( Board component  ) {
		super();
		cards=component.getChoix().getCards();
		init();
	}
	
	
	public void init() {
		setLayout(new GridLayout(1, 0, 0, 1));
		setOpaque(true);
		setBackground( Color.gray);
		addChoixPanel();		
	}

	public void addChoixPanel() {
		int x = 10 ;
		
		for(ElementCard card : cards.values()) {
			card.setBounds(x,5, card.getWidth(), card.getHeight());
			card.removePositionforEmpty();
			add(card);
			x+= card.getWidth()+10;
		}
	}	
}
