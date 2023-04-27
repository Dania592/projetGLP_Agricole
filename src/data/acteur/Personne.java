package data.acteur;


import java.io.Serializable;

import data.map.Map;
import data.notion.Mortel;
import data.notion.Mortel.EtatSante;
import data.planning.WeeklyPlanner;
import data.stucture_base.Element;

public abstract class Personne extends Element implements Serializable {
	private String name;
	private WeeklyPlanner planning;
	private EtatSante etatSante;
	private boolean isFree = true;

	
	public static final int MAX_HOUR_OF_WORK_FARMER = 10;
	public static final int MAX_HOUR_OF_WORK_EMPLOYEE = 12;

	public Personne(String nom, int ligne, int colonne, String reference, Map map) {
		super(reference, false, 1, ligne, colonne, map);
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
