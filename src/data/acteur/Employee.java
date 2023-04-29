package data.acteur;

import data.gestion.GestionnaireRH;
import data.map.Map;
import gui.gestionnaire.keys.Employees;

public class Employee extends Personne  {

	private float salaire;
	private Employees nom;
	private boolean recrute = false;

	public Employee(Employees nom, int ligne, int colonne, float salaire, String reference, Map map) {
		super(nom.toString(), ligne, colonne, reference, map);
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