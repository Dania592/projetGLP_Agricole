package data.acteur;

import data.map.Map;
import gui.gestionnaire.keys.Employees;

public class Employee extends Personne  {

	private static final long serialVersionUID = 1L;
	private float salaire;
	private Employees nom;
	private boolean recrute = false;

	public Employee(Employees nom,  float salaire, String reference) {
		super(nom.toString(), reference);
		this.salaire = salaire;

	}
	
	public boolean getRecrute() {
		return recrute;
	}
	
	public void settRecrute(boolean recrute) {
		this.recrute = recrute;
	}
	
	public float getSalaire() {
		return salaire;
	}
	
	public Employees getNom() {
		return nom;
	}

	public void setSalaire(float salaire) {
		this.salaire = salaire;
	}

	@Override
	public int getMaxHourOfWork() {
		return Personne.MAX_HOUR_OF_WORK_EMPLOYEE;
	}

}