package data.gestion;

import java.io.Serializable;
import java.util.ArrayList;

import data.finance.Charge;
import process.transaction.Achat;
import process.transaction.Vente;

public class GestionnaireFinancier implements GestionnaireInterface, Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Achat> achats = new ArrayList<Achat>();
	private ArrayList<Vente> ventes = new ArrayList<Vente>();
	private ArrayList<Charge> charges = new ArrayList<Charge>();
	private static GestionnaireFinancier instance = new GestionnaireFinancier();
	
	public ArrayList<Achat> getAchats() {
		return achats;
	}

	public ArrayList<Vente> getVentes() {
		return ventes;
	}
	
	public ArrayList<Charge> getCharges() {
		return charges;
	}

	private GestionnaireFinancier() {}
	
	public static GestionnaireFinancier getInstance() {
		return instance;
	}
	
	public void reset() {
		achats.clear();
		ventes.clear();
		charges.clear();
	}
	
	public void add(Achat achat) {
		achats.add(achat);
	}
	
	public void add(Vente vente) {
		ventes.add(vente);
	}
	
	public void add(Charge charge) {
		charges.add(charge);
	}
	
	@Override
	public String toString() {
		StringBuffer transactionsString = new StringBuffer("\t" + getClass().getSimpleName());
		for (Achat achat : achats) {
			transactionsString.append("\n\t\t" + achat.toString());
		}
		for (Vente vente : ventes) {
			transactionsString.append("\n\t\t" + vente.toString());
		}
		return transactionsString.toString();
	}
	
}