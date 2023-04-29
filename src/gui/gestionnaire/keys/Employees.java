package gui.gestionnaire.keys;

import gui.gestionnaire.GestionnaireKey;
import process.visitor.KeyVisitor;

public enum Employees implements Keys{
	
	PAUL(100),
	JULIE(100),
	HARRY(100),
	JEAN(100);

	private float salaire;
	
	private Employees(float salaire) {
		this.salaire = salaire;
	}
	
	@Override
	public float getPrixAchat() {
		return salaire;
	}

	@Override
	public <T> T accept(KeyVisitor<T> visitor, int quantity) {
		visitor.visit(this, 1);
		return null;
	}

	@Override
	public GestionnaireKey getGestionnaireKey() {
		return GestionnaireKey.EMPLOYE;
	}
	
	public String toString() {
		return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
	}
	
	@Override
	public float getPrixVente() {
		return 0;
	}

}
