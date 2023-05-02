package data.acteur;


import java.io.Serializable;

import data.notion.Mortel.EtatSante;
import data.planning.WeeklyPlanner;
import data.stucture_base.Element;

/**
 * class abstraite regroupant le traitement de tous les acteurs du jeu 
 * @author dania
 *
 */
public abstract class Personne extends Element implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * nom de la personne
	 */
	private String name;
	/**
	 * planning de toute la semaine de l'acteur 
	 */
	private WeeklyPlanner planning;
	/**
	 * état de santé de l'acteur 
	 */
	private EtatSante etatSante;
	/**
	 * état d'occupation pour affectuer une action
	 */
	private boolean isFree = true;

	/**
	 * nombre d'heure maximum que peut faire un fermier  
	 */
	public static final int MAX_HOUR_OF_WORK_FARMER = 10;
	/**
	 * nombre d'heure maximum que peut faire un employé 
	 */
	public static final int MAX_HOUR_OF_WORK_EMPLOYEE = 12;
	/**
	 * constructeur nécessaire pour le polymorphisme 
	 * @param nom : nom de la personne 
	 * @param reference : référence sur la map 
	 */
	public Personne(String nom, String reference) {
		super(reference, false, 1);
		this.name = nom;
		planning = new WeeklyPlanner(getMaxHourOfWork());
		this.etatSante = EtatSante.BONNE_SANTE;
	}

	/**
	 * retourne l'état de santé du personnage 
	 * @return
	 */
	public EtatSante getEtatSante() {
		return etatSante;
	}
	/**
	 * retourne le nom du personnage 
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * fige l'élément sur la map 
	 */
	public void setStatique() {
		super.setStatique(true);
	}
	/**
	 * modifie l'état de santé de la personne 
	 * @param etatSante
	 */
	public void setEtatSante(EtatSante etatSante) {
		this.etatSante = etatSante;
	}
	/**
	 * retourne le le planning du personnage
	 * @return
	 */
	public WeeklyPlanner getPlanning() {
		return planning;
	}
	public abstract int getMaxHourOfWork();
	
	/**
	 * état occupation du personnage 
	 * @return
	 */
	public boolean isFree() {
		return isFree;
	}
	/**
	 * modifie l'état d'occupation du personnage 
	 * @param isFree
	 */
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
	
}
