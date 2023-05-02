package process.gestion.transaction;

import java.util.HashMap;

import data.finance.Banque;
import data.gestion.GestionnaireFinancier;
import gui.gestionnaire.keys.Keys;
import process.gestion.visitor.AddKeyVisitor;
import process.gestion.visitor.RemoveKeyVisitor;
import process.gestion.visitor.RemoveVisitor;

public class Vente extends Transaction {
	
	private HashMap<Keys,Integer> cart = new HashMap<Keys, Integer>();
	private RemoveKeyVisitor removeVisitor = new RemoveKeyVisitor();

	public HashMap<Keys, Integer> getCart() {
		return cart;
	}
	
	public void addToCart(Keys element, int quantity) {
		for (int i = 0; i < quantity; i++) {
			if (cart.containsKey(element)){
				incrementQuantity(element);
			} else {
				cart.put(element, 1);
			}
			setTotalCost(getTotalCost() + element.getPrixVente());
		}
	}
	
	public void removeFromCart(Keys element, int quantity) {
		if (quantity < 0) {
			quantity = -(quantity);
		}
		for (int i =0; i < quantity; i++) {
			if (cart.get(element) != 1){
				decrementQuantity(element);
			} else {
				cart.remove(element);
			}
			setTotalCost(getTotalCost() - element.getPrixVente());
		}
	}
	
	public void addToCart(Keys element) {
		if (cart.containsKey(element)){
			incrementQuantity(element);
		} else {
			cart.put(element, 1);
		}
		setTotalCost(getTotalCost() + element.getPrixVente());
	}
	
	public void removeFromCart(Keys element) {
		if (cart.get(element) != 1){
			decrementQuantity(element);
		} else {
			cart.remove(element);
		}
		setTotalCost(getTotalCost() - element.getPrixVente());
	}
	
	public void incrementQuantity(Keys key) {
		cart.put(key, cart.get(key) + 1);
	}
	
	public void decrementQuantity(Keys key) {
		cart.put(key, cart.get(key) - 1);
	}
	
	
	public void validate() {
		if (cart!=null && cart.size()!=0) {
			setValidated(true);
			for (Keys key : cart.keySet()) {
				System.out.println("enlever : " + key.name());
				key.accept(removeVisitor,cart.get(key));
			}
			calculateTotalCost();
			Banque.getInstance().accrediter(getTotalCost());
			GestionnaireFinancier.getInstance().getVentes().add(this);	
		}
	}
	
	public void calculateTotalCost() {
		setTotalCost(0);
		for(Keys key : cart.keySet()) {
			setTotalCost(getTotalCost() + (key.getPrixVente()*cart.get(key)));
		}
	}
	
	public String toString() {
		StringBuffer vente = new StringBuffer("Votre vente : ");
		if (isValidated()) {
			vente.append("\n + montant : " + getTotalCost());
			for (Keys element : cart.keySet()) {
				vente.append("\n\t - " + element.toString());
			}
		} else {
			vente.append("n'a pas encore été validé");
		}
		return vente.toString();
	}


	@Override
	public void cancel() {
		cart.clear();
		setTotalCost(0);
	}

}
