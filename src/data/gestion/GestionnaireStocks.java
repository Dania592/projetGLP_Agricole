package data.gestion;


import java.util.ArrayList;
import java.util.HashMap;

import data.espece.faune.Animal;
import data.flore.terrains.TypeGraine;
import data.production.Produit;

public class GestionnaireStocks {

	// produits fruits et graines
	private ArrayList<TypeGraine> graines = new ArrayList<TypeGraine>();
	private HashMap<String, Produit> produits = new HashMap<>();
	
	private static GestionnaireStocks instance = new GestionnaireStocks();
	
	private GestionnaireStocks() {}
	
	public static GestionnaireStocks getInstance() {
		return instance;
	}
	
	public ArrayList<TypeGraine> getGraines(){
		return graines;
	}
	
	public HashMap<String, Produit> getProduits() {
		return produits;
	}

	public void add(TypeGraine graine) {
		if ( graines.contains(graine)) {
			graine.incrementQuantity();
		} else {
			graine.incrementQuantity();
			graines.add(graine);
		}
	}
	
	public void add(Produit produit) {
		produits.put(produit.getClass().getSimpleName(), produit);
	}
	
	public void remove(TypeGraine graine) {
		if (graines.contains(graine)) {
			graine.decrementQuantity();
			if (graine.getQuantity() == 0) {
				graines.remove(graine);
			}
		}
	}
	
	public void remove(Produit produit) {
		produits.remove(produit.getClass().getSimpleName(), produit);
	}
	
	
	public String toString() {
		StringBuffer gestionnaire = new StringBuffer("\t"+ this.getClass().getSimpleName());
		gestionnaire.append("\n\t\t Plantes :");
		for (TypeGraine graine : graines) {
			gestionnaire.append("\n\t\t\t"+ graine.toString());
		}
		gestionnaire.append("\n\t\t Produits :");
		for (Produit product : produits.values()) {
			gestionnaire.append("\n\t\t\t" + product.toString());
		}
		return gestionnaire.toString();
	}
}
