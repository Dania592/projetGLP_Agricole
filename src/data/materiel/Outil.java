package data.materiel;

import data.gestion.Stockage;
import data.map.Map;
import data.notion.basic.Element;
import gui.gestionnaire.keys.Outils;
import process.gestion.visitor.GestionVisitor;

public class Outil extends Element implements Stockage{
	
	private Outils type;
	
	public Outil(String reference, boolean statique, int nbCase, Outils type) {
		super(reference, statique, nbCase);
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
