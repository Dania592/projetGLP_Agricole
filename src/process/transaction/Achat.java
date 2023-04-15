package process.transaction;

import java.util.HashMap;

import data.gestion.GestionnaireFinancier;
import gui.gestionnaire.keys.Keys;
import process.game.Game;
import process.visitor.AddKeyVisitor;

public class Achat extends Transaction{
	
	private HashMap<Keys,Integer> cart = new HashMap<Keys, Integer>();
	private AddKeyVisitor addKeyVisitor = new AddKeyVisitor();

	public HashMap<Keys, Integer> getCart() {
		return cart;
	}

	public void addToCart(Keys element) {
		if (cart.containsKey(element)){
			incrementQuantity(element);
		} else {
			cart.put(element, 1);
		}
		setTotalCost(getTotalCost() + element.getPrixAchat());
	}
	
	public void removeFromCart(Buyable element) {
		cart.remove(element.getKey());
	}

	public void validateOrder(Game game) {
		setValidated(true);
		System.out.println("Commande validée");
		for (Keys key : cart.keySet()) {
			addToGestionnaire(key,cart.get(key));
		}
		calculateTotalCost();
		game.getBanque().debiter(getTotalCost());
		GestionnaireFinancier.getInstance().getAchats().add(this);
	}
	
	public void addToGestionnaire(Keys key, Integer entier) {
		key.accept(addKeyVisitor, entier);
	}
	
	public void cancelOrder(Game game) {
		System.out.println("Commande annulée !");
		for (Keys key : cart.keySet()) {
			cart.remove(key);
		}
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
			setTotalCost(getTotalCost() + key.getPrixAchat());
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