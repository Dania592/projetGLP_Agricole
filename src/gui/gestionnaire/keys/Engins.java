package gui.gestionnaire.keys;

import process.gestion.visitor.KeyVisitor;

public enum Engins implements Keys{

	TRACTEUR(400, 420),
	TONDEUSE(200, 250);
	
	private float prixAchat;
	private float prixVente;
	
	Engins(float prixAchat, float prixVente) {
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
	}
	
	@Override
	public float getPrixVente() {
		return prixVente;
	}
	
	@Override
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
		return GestionnaireKey.ENGINS;
	}
	

	
}
