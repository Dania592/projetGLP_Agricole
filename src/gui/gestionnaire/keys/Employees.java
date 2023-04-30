package gui.gestionnaire.keys;

import gui.gestionnaire.GestionnaireKey;
import process.visitor.KeyVisitor;

public enum Employees implements Keys{
	
	PAUL(50),
	JULIE(50),
	HARRY(50),
	JEAN(50);

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
	
	public static float getTotalSalaryToPay() {
		float montant = 0;
		for (Employees employee : values()) {
			montant += employee.getPrixAchat();
		}
		return montant;
	}

}
