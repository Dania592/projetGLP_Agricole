package data.acteur;


import data.map.Map;
import data.notion.Mortel.EtatSante;
import data.planning.WeeklyPlanner;
import data.stucture_base.Element;

public abstract class Personne extends Element {
	private String name;
	private WeeklyPlanner planning;
	private EtatSante etatSante;

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

}
