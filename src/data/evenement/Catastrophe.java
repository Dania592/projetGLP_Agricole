package data.evenement;

import java.io.Serializable;
import java.util.ArrayList;

import data.stucture_base.Element;


public class Catastrophe implements Serializable{
	private int duree;
	private int debut;
	private boolean succes;
	
	private ArrayList<Element> cibles = new ArrayList<>();

	
	public Catastrophe(int duree, int debut, boolean succes, ArrayList<Element> cibles) {
		super();
		this.setDuree(duree);
		this.setDebut(debut);
		this.setSucces(succes);
		this.cibles = cibles;
	}

	public ArrayList<Element> getCibles() {
		return cibles;
	}

	public void addCible(Element cible) {
		cibles.add(cible);
	}
	
	public void removeCible(Element cible) {
		cibles.remove(cible);
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getDebut() {
		return debut;
	}

	public void setDebut(int debut) {
		this.debut = debut;
	}

	public boolean isSucces() {
		return succes;
	}

	public void setSucces(boolean succes) {
		this.succes = succes;
	}


	
}