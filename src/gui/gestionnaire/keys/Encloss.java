package gui.gestionnaire.keys;

import gui.gestionnaire.GestionnaireKey;
import process.visitor.KeyVisitor;

public enum Encloss implements Keys{

	ENCLOS(500,null); // Ajouter le terrain d'arbre ici peut-être
	
	private float prixAchat;
	private Graine graine;
	
	private Encloss(float prix, Graine graine) {
		prixAchat = prix;
		this.graine = graine;		
	}	
	
	@Override
	public float getPrixAchat() {
		return prixAchat;
	}

	@Override
	public <T> T accept(KeyVisitor<T> visitor, int quantity) {
		visitor.visit(this,quantity);
		return null;
	}

	@Override
	public GestionnaireKey getGestionnaireKey() {
		return GestionnaireKey.ENCLOS;
	}
	
	@Override
	public float getPrixVente() {
		return 0;
	}
}
