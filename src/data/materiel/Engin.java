package data.materiel;

import data.gestion.Stockage;
import data.map.Map;
import data.notion.basic.Element;
import gui.gestionnaire.keys.Engins;
import process.gestion.visitor.GestionVisitor;

public class Engin extends Element implements Stockage{
	
	private Engins type;
	
	public Engin(String reference, boolean statique, int nbCase, Engins type) {
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

	public Engins getType() {
		return type;
	}
	
	public String toString() {
		return "Engin : " + type  + " " +  getReference();
	}
}
