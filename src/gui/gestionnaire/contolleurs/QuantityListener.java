package gui.gestionnaire.contolleurs;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import data.finance.Banque;
import gui.gestionnaire.InfosTransaction;
import gui.gestionnaire.gestionnairesGUI.MarketGUI;
import gui.gestionnaire.keys.Keys;
import process.gestion.transaction.Transaction;

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
		Transaction transaction = market.getTransaction();
		String type = transaction.getClass().getSimpleName();
		Integer newValue = Integer.valueOf(String.valueOf(spinner.getValue()));
		if (spinner.equals(e.getSource()) && lastValue != null && !newValue.equals(lastValue)) {
			if (newValue == 0) {
				market.getBillPanel().remove(market.getBill().get(key));
				market.getBill().remove(key);
				transaction.removeFromCart(key,(newValue-lastValue));
				market.getBillPanel().revalidate();
				market.getBillPanel().repaint();
			}else{
				if ((newValue-lastValue)>0) {
					if(type.equals("Achat")) {
						if (Banque.getInstance().getCompte().getSolde() >= (market.getValidationPanel().getTotalCost()+((newValue-lastValue)*key.getPrixAchat()))) {
							transaction.addToCart(key,(newValue-lastValue));
						} else {
							new InfosTransaction("Solde insuffisant", market.getFrame());
							market.dispose();
							newValue = lastValue;
						}
					} else {
						transaction.addToCart(key,(newValue-lastValue));
					}
				}else {
					transaction.removeFromCart(key,(newValue-lastValue));
				}
			}
			if(type.equals("Achat")) {
				market.getValidationPanel().updateTotalCost((newValue-lastValue)*key.getPrixAchat());
			} else {
				market.getValidationPanel().updateTotalCost((newValue-lastValue)*key.getPrixVente());
			}
		}
		lastValue = newValue;
	}

}