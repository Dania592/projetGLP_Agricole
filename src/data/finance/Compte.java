package data.finance;

import java.io.Serializable;

public class Compte implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private float solde = 1000;
	
	public static Compte instance = new Compte();
	
	private Compte() {};
	
	public static Compte getInstance() {
		return instance;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}
	
}