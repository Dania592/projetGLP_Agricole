package data.finance;

import java.io.Serializable;

public class Compte implements Serializable{
	
	private float solde = 1000;

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}
	
}