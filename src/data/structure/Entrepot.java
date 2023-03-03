package data.structure;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import data.map.Map;
import data.production.Produit;


public class Entrepot extends Structure{

	private ArrayList<Produit> produits ;
	private int capacite =100  ; 
	private final static float PRIX_ACHAT = 50000 ;
	
	public Entrepot(int ligne_init, int colonne_init, String reference , Map map ) {
		super(ligne_init, colonne_init, PRIX_ACHAT , reference , map);
		this.produits = new ArrayList<>();
		setImage(new ImageIcon("src"+File.separator+"ressources"+File.separator+"minigrange.png"));
	}

	public ArrayList<Produit> getProduits() {
		return produits;
	}

	public void addProduit(Produit produit) {
		produits.add(produit);
	}
	public void removeProduit(Produit produit) {
		produits.remove(produit);
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

}
