package data.production;

import java.io.Serializable;

public abstract class Produit implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Produits type;
	private int quantity;
	private float prixVente;
		
	
	public Produit(Produits type, float prixVente) {
		this.type = type;
		this.prixVente = prixVente;
		quantity = 0;
	}
	
	public Produit(float prixVente) {
		this.prixVente=prixVente;
	}

	public float getPrixVente() {
		return prixVente;
	}
	
	public void setPrixVente(float prixVente) {
		this.prixVente = prixVente;
	}
	
	public Produits getType() {
		return type;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void incrementQuantity() {
		quantity++;
	}
	
	public void decrementQuantity() {
		if (quantity > 0) {
			quantity--;
		}
	}
	
}
