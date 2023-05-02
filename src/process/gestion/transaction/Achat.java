package process.gestion.transaction;

import java.util.HashMap;

import data.finance.Banque;
import data.gestion.GestionnaireFinancier;
import data.gestion.GestionnaireStocks;
import gui.gestionnaire.keys.Keys;
import process.gestion.visitor.AddKeyVisitor;

public class Achat extends Transaction{
	
	private HashMap<Keys,Integer> cart = new HashMap<Keys, Integer>();
	private AddKeyVisitor addKeyVisitor = new AddKeyVisitor();

	public HashMap<Keys, Integer> getCart() {
		return cart;
	}

	public void addToCart(Keys element, int quantity) {
		for (int i = 0; i < quantity; i++) {
			if (getTotalCost() + element.getPrixAchat() < Banque.getInstance().getCompte().getSolde()) {
				if (cart.containsKey(element)){
					incrementQuantity(element);
				} else {
					cart.put(element, 1);
				}
				setTotalCost(getTotalCost() + element.getPrixAchat());
			}	
		}
	}
	
	public void removeFromCart(Keys element, int quantity) {
		if (quantity < 0) {
			quantity = -(quantity);
		}
		for (int i = 0; i < quantity; i++) {
			if (cart.get(element) != 1){
				decrementQuantity(element);
			} else {
				cart.remove(element);
			}
			setTotalCost(getTotalCost() - element.getPrixAchat());
		}
	}
	
	public void addToCart(Keys element) {
		if (getTotalCost() + element.getPrixAchat() < Banque.getInstance().getCompte().getSolde()) {
			if (cart.containsKey(element)){
				incrementQuantity(element);
			} else {
				cart.put(element, 1);
			}
			setTotalCost(getTotalCost() + element.getPrixAchat());
		}	
	}
	
	public void removeFromCart(Keys element) {
		if (cart.get(element) != 1){
			decrementQuantity(element);
		} else {
			cart.remove(element);
		}
		setTotalCost(getTotalCost() - element.getPrixAchat());
	}

	public void validate() {
		if (cart!=null && cart.size()!=0) {
			setValidated(true);
			for (Keys key : cart.keySet()) {
			
				key.accept(addKeyVisitor, cart.get(key));
				}
			calculateTotalCost();
			Banque.getInstance().debiter(getTotalCost());
			GestionnaireFinancier.getInstance().getAchats().add(this);	
		}
	}
	
	@Override
	public void cancel() {
		cart.clear();
		setTotalCost(0);
	}
	
	public void incrementQuantity(Keys key) {
		cart.put(key, cart.get(key) + 1);
	}
	
	public void decrementQuantity(Keys key) {
		cart.put(key, cart.get(key) - 1);
	}
	
	public void calculateTotalCost() {
		setTotalCost(0);
		for(Keys key : cart.keySet()) {
			setTotalCost(getTotalCost() + (key.getPrixAchat()*cart.get(key)));
		}
	}
	
	public String toString() {
		StringBuffer achats = new StringBuffer("Votre achat : ");
		if (isValidated()) {
			achats.append("\n - montant : " + getTotalCost());
			for (Keys key : cart.keySet()) {
				achats.append("\n\t + "+ key.toString() + cart.get(key) );
			}
		} else {
			achats.append("n'a pas encore été validé");
		}
		return achats.toString();
	}


}