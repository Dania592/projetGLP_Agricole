package gui.gestionnaire.keys;

import gui.gestionnaire.GestionnaireKey;
import process.visitor.KeyVisitor;

public enum Engins implements Keys{

	TRACTEUR(400),
	TONDEUSE(200);
	
	private float prixAchat;
	
	Engins(float prixAchat) {
		this.prixAchat = prixAchat;
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
