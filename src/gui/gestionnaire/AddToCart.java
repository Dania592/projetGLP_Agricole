package gui.gestionnaire;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.gestionnaire.keys.Keys;
import process.transaction.Achat;

public class AddToCart implements ActionListener{
	
	public static int posY = 0;
	public static int WIDTH = 240;
	public static int HEIGHT = 40;

	private Achat achat;
	private Keys key;
	private MarketGUI market;
	
	public AddToCart(Keys key, Achat achat, MarketGUI market) {
		this.key = key;
		this.achat = achat;
		this.market = market;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		achat.addToCart(key);
		market.addToBill(key, WIDTH, HEIGHT);
		System.out.println(posY);
		System.out.println("Added\n : " + achat.getCart());
	}

}
