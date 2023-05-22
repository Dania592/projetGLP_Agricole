package data.finance;

import java.io.Serializable;

public class Compte implements Serializable{

	private static final long serialVersionUID = 1L;
	public static float CAPITAL_INITIAL = 200000;
	
	private float solde = CAPITAL_INITIAL;
	
	public static Compte instance = new Compte();
	
	private Compte() {};
	
	public void reset() {
		solde = CAPITAL_INITIAL;
	}
	
	public static Compte getInstance() {
		return instance;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float d) {
		this.solde = d;
	}
	
}