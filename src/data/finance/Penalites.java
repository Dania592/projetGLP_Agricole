package data.finance;

import java.io.Serializable;

import process.gestion.transaction.FinanceManager;

public abstract class Penalites implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private float montant;
	private int delais;
	private boolean paid;
	
	public Penalites() {
		this.montant = (int) ((Math.random() * (350 - 100)) + 100);
		this.delais = (int) ((Math.random() * (3 - 1)) + 3);;
		paid = false;
	}
	
	public float getMontant() {
		return montant;
	}
	
	public void setMontant(float montant) {
		this.montant = montant;
	}
	
	public int getDelais() {
		return delais;
	}
	
	public void setDelais(int delais) {
		this.delais = delais;
	}
	
	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
	public void payer() {
		Banque.getInstance().debiter(montant);
		paid = true;
	}
}
