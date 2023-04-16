package data.production;

import java.io.Serializable;

public abstract  class Produit implements Serializable{
	
	private float prixVente ;
	private String reference;
	


	public Produit(float prixVente) {
		this.prixVente=prixVente;
	}

	public float getPrixVente() {
		return prixVente;
	}
	
	public void setPrixVente(float prixVente) {
		this.prixVente = prixVente;
	}
	public String getReference() {
		return reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}
}
