package process.transaction;

import java.io.Serializable;

import data.finance.Finance;

public abstract class Transaction implements Finance,Serializable{
	
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
	
}
