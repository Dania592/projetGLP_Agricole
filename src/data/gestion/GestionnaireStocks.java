package data.gestion;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import data.production.Produits;
import gui.gestionnaire.keys.Graine;

public class GestionnaireStocks implements GestionnaireInterface, Serializable {

	private static final long serialVersionUID = 1L;
	// produits fruits et graines
	private HashMap<Graine, Integer> graines = new HashMap<Graine, Integer>();
	private HashMap<Produits, Integer> produits = new HashMap<>();

	private GestionnaireStocks() {
		for (Graine graine : Graine.values()) {
			graines.put(graine, 0);
		}
	}

	private static GestionnaireStocks instance = new GestionnaireStocks();

	public static GestionnaireStocks getInstance() {
		return instance;
	}

	public HashMap<Graine, Integer> getGraines() {
		return graines;
	}

	public Set<Graine> getAvailableGraines() {
		Set<Graine> set = new HashSet<>();
		for (Graine graine : graines.keySet()) {
			if (graines.get(graine) != 0) {
				set.add(graine);
			}
		}
		return set;
	}

	public HashMap<Produits, Integer> getProduits() {
		return produits;
	}

	public void add(Graine graine) {
		graines.put(graine, graines.get(graine) + 1);
	}

	public void add(Produits produit, int nombreToAddTo) {
		if(produits.containsKey(produit)){
			int oldPRoduction = produits.get(produit);
			produits.replace(produit, oldPRoduction+nombreToAddTo);
		}else{
			produits.put(produit, nombreToAddTo);
		}

	}


	public void add(Produits produit) {
		add(produit,1);
	}

	public void remove(Graine key, int quantity) {
		int i = 0;
		while (i < quantity) {
			remove(key);
		}
	}

	public int getNbSeedType() {
		int nbType = 0;
		for (int number : graines.values()) {
			if (number != 0) {
				nbType++;
			}
		}
		return nbType;
	}

	public void remove(Graine graine) {
		if (graines.containsKey(graine)) {
			graines.put(graine, graines.get(graine) - 1);
			if (graines.get(graine) == 0) {
				graines.remove(graine);
			}
		}
	}
	

	public void remove(Produits produit) {
		remove(produit,1);
	}

	public void remove(Produits key, int quantity) {
		int oldQuantity = produits.get(key);
		if(oldQuantity>0){
			produits.replace(key, oldQuantity-quantity);
		}
		
	}

	public String toString() {
		StringBuffer gestionnaire = new StringBuffer("\t" + this.getClass().getSimpleName());
		gestionnaire.append("\n\t\t Plantes :");
		for (Graine graine : graines.keySet()) {
			gestionnaire.append("\n\t\t\t" + graine.toString() + " : " + graines.get(graine));
		}
		gestionnaire.append("\n\t\t Produits :");
		gestionnaire.append(produits.toString());
		return gestionnaire.toString();
	}
}
