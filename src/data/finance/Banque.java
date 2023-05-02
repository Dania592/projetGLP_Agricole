package data.finance;

import java.io.Serializable;
import java.util.HashMap;

import process.gestion.transaction.Transaction;

public class Banque implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Compte compte = Compte.getInstance();
	//private HashMap<String, Transaction> historique = new HashMap<>();
	
	private static Banque instance = new Banque();
	
	private Banque() {
	}
	
	public void reset() {
		compte.reset();
	}
	
	public static Banque getInstance() {
		return instance;
	}
	
	public Compte getCompte() {
		return compte;
	}

//	public HashMap<String, Transaction> getHistorique() {
//		return historique;
//	}
	
	public void accrediter(float montant) {
		compte.setSolde(compte.getSolde() + montant);
	}
	
	public void debiter(float montant) {
		if (compte.getSolde() >= montant) {
			compte.setSolde(compte.getSolde() - montant);
		}
	}
		
}
