package data.materiel;

import data.gestion.Stockage;
import data.map.Map;
import data.stucture_base.Element;
import gui.gestionnaire.GestionnaireKey;
import gui.gestionnaire.keys.Keys;
import gui.gestionnaire.keys.Outils;
import process.transaction.Buyable;
import process.visitor.GestionVisitor;

public class Outil extends Element implements Buyable{
	
	private float prixAchat = 100;
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
		return "Outil : " + type + getReference();
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
		return GestionnaireKey.OUTILS;
	}
}
