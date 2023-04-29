package gui.gestionnaire;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.gestionnaire.keys.Keys;

public class AddToCart implements ActionListener{
	
	public static int posY = 0;
	public static int WIDTH = 280;
	public static int HEIGHT = 40;

	private Keys key;
	private MarketGUI market;
	private PaintKeys type;
	
	public AddToCart(Keys key, MarketGUI market,PaintKeys type) {
		this.key = key;
		this.market = market;
		this.type = type;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		market.addToBill(key, WIDTH, HEIGHT,type);
		//market.getGame().getAchat().addToCart(key);
	}

}
