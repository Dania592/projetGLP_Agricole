package gui.gestionnaire.keys;

import gui.gestionnaire.GestionnaireKey;
import process.visitor.KeyVisitor;

public enum Animals implements Keys{

	VACHE(100),
	MOUTON(200),
	CHEVRE(300),
	CHIEN(100),
	POULE(100);
	
	private float prixAchat;
	
	Animals(float prix){
		prixAchat = prix;
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
	
}