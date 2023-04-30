package gui.gestionnaire.keys;

import process.visitor.KeyVisitor;

public enum Animals implements Keys{

	VACHE(100,150),
	MOUTON(70,100),
	CHEVRE(70,100),
	CHIEN(50,70),
	POULE(20,30);
	
	private float prixAchat;
	private float prixVente;
	
	Animals(float prix, float prixVente){
		prixAchat = prix;
		this.prixVente = prixVente;
	}
	
	public float getPrixAchat() {
		return prixAchat;
	}
	
	@Override
	public <T> T accept(KeyVisitor<T> visitor, int quantity) {
		visitor.visit(this, quantity);
		return null;
	}

	@Override
	public GestionnaireKey getGestionnaireKey() {
		return GestionnaireKey.ANIMALS;
	}

	@Override
	public float getPrixVente() {
		return prixVente;
	}
	
}