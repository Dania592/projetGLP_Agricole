package gui.gestionnaire;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gui.gestionnaire.keys.Keys;

public class QuantityListener implements ChangeListener{

	private Integer lastValue;
	private JSpinner spinner;
	private Keys key;
	private MarketGUI market;
	
	public QuantityListener(JSpinner spinner, Keys key, MarketGUI market) {
		this.key = key;
		this.spinner = spinner;
		lastValue = Integer.valueOf(String.valueOf(spinner.getValue()));
		this.market = market;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		Integer newValue = Integer.valueOf(String.valueOf(spinner.getValue()));
		if (spinner.equals(e.getSource()) && lastValue != null && !newValue.equals(lastValue)) {
			if (newValue == 0) {
				market.getBillPanel().remove(market.getBill().get(key));
				market.getBill().remove(key);
				market.getAchat().removeFromCart(key);
			}else{
				market.getValidationPanel().updateTotalCost((newValue-lastValue)*key.getPrixAchat());
				if ((newValue-lastValue)>0) {
					market.getAchat().addToCart(key);
				}else {
					market.getAchat().removeFromCart(key);
				}
				//System.out.println(" Quantity listener : " + lastValue-newValue)*key.getPrixAchat());
				System.out.println("Added\n : " + market.getAchat().getCart());
			}
		}
		lastValue = newValue;
	}

}