package data.materiel;

import data.gestion.Stockage;
import data.map.Map;
import data.stucture_base.Element;
import gui.gestionnaire.keys.Engins;
import process.visitor.GestionVisitor;

public class Engin extends Element implements Stockage{
	
	private Engins type;
	
	public Engin(String reference, boolean statique, int nbCase, int ligne_init, int colonne_init , Map map, Engins type) {
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

	public Engins getType() {
		return type;
	}
	
	public String toString() {
		return "Engin : " + type + getReference();
	}
}
