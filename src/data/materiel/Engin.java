package data.materiel;

import data.map.Map;
import data.stucture_base.Element;
import gui.gestionnaire.GestionnaireKey;
import gui.gestionnaire.keys.Engins;
import gui.gestionnaire.keys.Keys;
import process.transaction.Buyable;
import process.visitor.GestionVisitor;

public class Engin extends Element implements Buyable{
	
	private float prixAchat = 500;
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass().getSimpleName().equals(this.getClass().getSimpleName())) {
			return true;
		}
		return false;
	}

	@Override
	public float getPrixAchat() {
		return prixAchat;
	}

	@Override
	public Keys getKey() {
		return type;
	}
	
	@Override
	public GestionnaireKey getGestionnaireKey() {
		return GestionnaireKey.ENGINS;
	}
	
}
