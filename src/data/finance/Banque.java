package data.finance;

import java.io.Serializable;
import java.util.HashMap;

import process.transaction.Transaction;

public class Banque implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Compte compte = Compte.getInstance();
	private HashMap<String, Transaction> historique = new HashMap<>();
	
	private static Banque instance = new Banque();
	
	private Banque() {
	}
	
	public static Banque getInstance() {
		return instance;
	}
	
	public Compte getCompte() {
		return compte;
	}

	public HashMap<String, Transaction> getHistorique() {
		return historique;
	}
	
	public void accrediter(float montant) {
		compte.setSolde(compte.getSolde() + montant);
	}
	
	public void debiter(double d) {
		compte.setSolde(compte.getSolde() - d);
	}
		
}
