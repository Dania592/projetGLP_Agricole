package gui.gestionnaire;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import data.finance.Banque;
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
				if ((newValue-lastValue)>0) {
					if(Banque.getInstance().getCompte().getSolde() >= market.getValidationPanel().getTotalCost()+((newValue-lastValue)*key.getPrixAchat())) {
						market.getAchat().addToCart(key);
					} else {
						new InfosTransaction("Solde insuffisant", market.getFrame());
						market.dispose();
						newValue = lastValue;
					}
				}else {
					market.getAchat().removeFromCart(key);
				}
				market.getValidationPanel().updateTotalCost((newValue-lastValue)*key.getPrixAchat());
			}
		}
		lastValue = newValue;
	}

}