package data.gestion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import data.espece.faune.Animal;
import data.materiel.Engin;
import data.materiel.Engins;
import data.materiel.Outil;
import data.materiel.Outils;

public class GestionnaireMateriel implements Serializable {

	// On pourrait appliquer le même principe des graines
	
	private HashMap<Outils, ArrayList<Outil>> outils = new HashMap<>();
	private HashMap<Engins, ArrayList<Engin>> engins = new HashMap<>();
	
	private static GestionnaireMateriel instance = new GestionnaireMateriel();
	
	private GestionnaireMateriel(){}
	
	public HashMap<Outils, ArrayList<Outil>> getOutils() {
		return outils;
	}
	
	public HashMap<Engins, ArrayList<Engin>> getEngins() {
		return engins;
	}
	
	public static GestionnaireMateriel getInstance() {
		return instance;
	}
	
	public void add(Outil outil) {
		Outils type = outil.getType();
		if (outils.containsKey(type)) {
			outils.get(type).add(outil);
		} else {
			ArrayList<Outil> outils = new ArrayList<>();
			outils.add(outil);
			this.outils.put(type, outils);
		}
	}
	
	public void add(Engin engin) {
		Engins type = engin.getType();
		if (engins.containsKey(type)) {
			engins.get(type).add(engin);
		} else {
			ArrayList<Engin> engins = new ArrayList<>();
			engins.add(engin);
			this.engins.put(type, engins);
		}
	}
	
	public void remove(Outil outil) {
		Outils type = outil.getType();
		ArrayList<Outil> outils = this.outils.get(type);
		if (outils.size() == 1) {
			this.outils.remove(type);
		} else {
			outils.remove(outil);
		}
	}
	
	public void remove(Engin engin) {
		Engins type = engin.getType();
		ArrayList<Engin> engins = this.engins.get(type);
		if (engins.size() == 1) {
			this.engins.remove(type);
		} else {
			engins.remove(engin);
		}
	}
	
	public int getOutilsSize() {
		int size = 0;
		for (ArrayList<Outil> outils : this.outils.values()) {
			size += outils.size();
		}
		return size;
	}
	
	public int getEnginsSize() {
		int size = 0;
		for (ArrayList<Engin> engins : this.engins.values()) {
			size += engins.size();
		}
		return size;
	}

	public String toString() {
		StringBuffer gestionnaire = new StringBuffer("\t"+ this.getClass().getSimpleName());
		gestionnaire.append("\n\t\t Outils :");
		for (ArrayList<Outil> outils : outils.values()) {
			for (Outil outil : outils ) {
				gestionnaire.append("\n\t\t\t"+ outil.toString());
			}
		}
		gestionnaire.append("\n\t\t Engins :");
		for (ArrayList<Engin> engins : engins.values()) {
			for (Engin engin : engins) {
				gestionnaire.append("\n\t\t\t"+ engin.toString());	
			}
		}
		return gestionnaire.toString();
	}
}
