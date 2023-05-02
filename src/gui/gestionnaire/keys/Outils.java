package gui.gestionnaire.keys;

import process.gestion.visitor.KeyVisitor;

public enum Outils implements Keys{
	
	PELE(10, 12),
	ARROSOIR(10, 12),
	TRANSPLANTOIR(10, 12);

	private float prixAchat;
	private float prixVente;
	
	private Outils(float prixAchat, float prixVente) {
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
	}
	
	@Override
	public float getPrixAchat() {
		return prixAchat;
	}
	
	@Override
	public float getPrixVente() {
		return prixVente;
	}
	
	@Override
	public <T> T accept(KeyVisitor<T> visitor, int quantity) {
		visitor.visit(this, quantity);
		return null;
	}

	@Override
	public GestionnaireKey getGestionnaireKey() {
		return GestionnaireKey.OUTILS;
	}
}
