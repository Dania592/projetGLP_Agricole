package process.transaction;

import data.finance.Finance;
import gui.gestionnaire.keys.Keys;

public abstract class Transaction implements Finance{
	
	private boolean validated = false;
	private float totalCost;
	private String reference;
	
	public boolean isValidated() {
		return validated;
	}
	
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	
	public float getTotalCost() {	
		return totalCost;
	}
	
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	
	public String getReference() {
		return reference;
	}

	public abstract void validate();
	
	public abstract void cancel();
	
	public abstract void removeFromCart(Keys key, int quantity);

	public abstract void addToCart(Keys key,int quantity);
	
}
