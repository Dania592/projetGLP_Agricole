package data.materiel;

import data.gestion.Stockage;
import data.map.Map;
import data.stucture_base.Element;
import gui.gestionnaire.keys.Outils;
import process.visitor.GestionVisitor;

public class Outil extends Element implements Stockage{
	
	private Outils type;
	
	public Outil(String reference, boolean statique, int nbCase, int ligne_init, int colonne_init , Map map, Outils type) {
		super(reference, statique, nbCase, ligne_init, colonne_init , map);
		this.type = type;
	}

	@Override
	public <T> T accept(GestionVisitor<T> visitor) {
		visitor.visit(this);
		return null;
	}
	
	public void setStatique() {
		super.setStatique(true);
	}
	
	public Outils getType() {
		return type;
	}
	
	public String toString() {
		return "Outil : " + type + " " + getReference();
	}
}
