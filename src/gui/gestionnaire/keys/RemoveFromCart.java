package gui.gestionnaire.keys;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gui.gestionnaire.MarketGUI;

public class RemoveFromCart implements MouseListener{

	public static int posY = 0;
	public static int WIDTH = 280;
	public static int HEIGHT = 40;

	private Keys key;
	private MarketGUI market;
	
	public RemoveFromCart(Keys key, MarketGUI market) {
		this.key = key;
		this.market = market;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		market.removeFromBill(key);
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
