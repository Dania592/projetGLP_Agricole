package data.acteur;

import gui.gestionnaire.keys.Employees;

/**
 * 
 * Classe de donnée des employés à recruter pour l'automatisation des actions 
 *
 */
public class Employee extends Personne  {
/**
 * Constante necéssaire pour hérité de Serialisable
 */
	private static final long serialVersionUID = 1L;
	/**
	 * Salaire des employés 
	 */
	private float salaire;
	/**
	 * Nom d'un des quatres employé 
	 */
	private Employees nom;
	/**
	 * Etat de recrutement
	 */
	private boolean recrute = false;

	/**
	 * constructeur de la class
	 * @param nom : nom del'employé 
	 * @param salaire : salaire de l'employé
	 * @param reference : reference pour le positionner sur la map
	 */
	public Employee(Employees nom,  float salaire, String reference) {
		super(nom.toString(), reference);
		this.salaire = salaire;

	}
	
	/**
	 * retourne l'état de recrutement de l'employé
	 * @return
	 */
	public boolean getRecrute() {
		return recrute;
	}
	/**
	 * modifie l'état de recrutement de l'employé 
	 * @param recrute : nouvel état 
	 */
	public void settRecrute(boolean recrute) {
		this.recrute = recrute;
	}
	/**
	 * retourne le salaire de l'employé 
	 * @return
	 */
	public float getSalaire() {
		return salaire;
	}
	/**
	 * retourne le nom de l'employé 
	 * @return
	 */
	public Employees getNom() {
		return nom;
	}

	/**
	 * modifie le salaire de l'employé 
	 * @param salaire
	 */
	public void setSalaire(float salaire) {
		this.salaire = salaire;
	}

	/**
	 * retourne le nombre d'heure maximum que peut travailler l'employé 
	 */
	@Override
	public int getMaxHourOfWork() {
		return Personne.MAX_HOUR_OF_WORK_EMPLOYEE;
	}

}