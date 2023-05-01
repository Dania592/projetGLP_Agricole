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
	 * 
	 * @param nom
	 * @param reference
	 */
	public Personne(String nom, String reference) {
		super(reference, false, 1);
		this.name = nom;
		planning = new WeeklyPlanner(getMaxHourOfWork());
		this.etatSante = EtatSante.BONNE_SANTE;
	}

	public EtatSante getEtatSante() {
		return etatSante;
	}

	public String getName() {
		return name;
	}
	
	public void setStatique() {
		super.setStatique(true);
	}

	public void setEtatSante(EtatSante etatSante) {
		this.etatSante = etatSante;
	}
	
	public WeeklyPlanner getPlanning() {
		return planning;
	}
	
	public abstract int getMaxHourOfWork();
	
	public boolean isFree() {
		return isFree;
	}
	
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
	
}
