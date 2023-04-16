package process.transaction;

import java.util.ArrayList;
import java.util.HashMap;

import data.gestion.GestionnaireFinancier;
import process.game.Game;
import process.visitor.AddVisitor;


public class Achat extends Transaction {
	private ArrayList<Buyable> cart = new ArrayList<>();

	
	public ArrayList<Buyable> getCart() {
		return cart;
	}

	public void addToCart(Buyable element) {
		cart.add(element);
		setTotalCost(getTotalCost() + element.getPrixAchat());
	}
	
	public void removeFromCart(Buyable element) {
		cart.remove(element);
	}
	
	public void validateOrder(Game game) {
		AddVisitor addVisitor = new AddVisitor();
		setValidated(true);
		System.out.println("Commande validée");
		for (Buyable element : cart) {
			element.accept(addVisitor);
		}
		calculateTotalCost();
		game.getBanque().debiter(getTotalCost());
		GestionnaireFinancier.getInstance().getAchats().add(this);
	}
	
	public void cancelOrder(Game game) {
		System.out.println("Commande annulée !");
		for (Buyable element : cart) {
			cart.remove(element);
		}
	}
	
	public void calculateTotalCost() {
		setTotalCost(0);
		for(Buyable element : cart) {
			setTotalCost(getTotalCost() + element.getPrixAchat());
		}
	}
	
	public String toString() {
		StringBuffer achats = new StringBuffer("Votre achat : ");
		if (isValidated()) {
			achats.append("\n - montant : " + getTotalCost());
			for (Buyable element : cart) {
				achats.append("\n\t + " + element.toString());
			}
		} else {
			achats.append("n'a pas encore été validé");
		}
		return achats.toString();
	}

}
