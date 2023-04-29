package gui.gestionnaire.keys;

import gui.gestionnaire.GestionnaireKey;
import process.visitor.KeyVisitor;

public enum Terrains implements Keys{

	TERRAIN(500,null); // Ajouter le terrain d'arbre ici peut-être
	
	private float prixAchat;
	private Graine graine;
	
	private Terrains(float prix, Graine graine) {
		prixAchat = prix;
		this.graine = graine;		
	}	
	
	@Override
	public float getPrixAchat() {
		return prixAchat;
	}
	
	@Override
	public float getPrixVente() {
		return 0;
	}
	
	public Graine getGraine() {
		return graine;
	}
	
	public void setGraine(Graine graine) {
		this.graine = graine;
	}

	@Override
	public <T> T accept(KeyVisitor<T> visitor, int quantity) {
		visitor.visit(this,quantity);
		return null;
	}

	@Override
	public GestionnaireKey getGestionnaireKey() {
		return GestionnaireKey.TERRAINS;
	}

}
