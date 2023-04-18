package data.acteur;

import data.map.Map;

public class Employee extends Personne  {

	private float salaire;

	public Employee(String nom, int ligne, int colonne, float salaire, String reference, Map map) {
		super(nom, ligne, colonne, reference, map);
		this.salaire = salaire;

	}

	public float getSalaire() {
		return salaire;
	}

	public void setSalaire(float salaire) {
		this.salaire = salaire;
	}

	@Override
	public int getMaxHourOfWork() {
		return Personne.MAX_HOUR_OF_WORK_EMPLOYEE;
	}

}