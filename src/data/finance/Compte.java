package data.finance;

import java.io.Serializable;

public class Compte implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private double solde = 1000;
	
	public static Compte instance = new Compte();
	
	private Compte() {};
	
	public static Compte getInstance() {
		return instance;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double d) {
		this.solde = d;
	}
	
}