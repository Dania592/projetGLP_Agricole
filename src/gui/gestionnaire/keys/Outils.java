package gui.gestionnaire.keys;

import gui.gestionnaire.GestionnaireKey;
import process.visitor.KeyVisitor;

public enum Outils implements Keys{
	
	PELE,
	PLANTOIR,
	TRANSPLANTOIRE,;

	private float prixAchat;
	
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
		return GestionnaireKey.OUTILS;
	}
}
