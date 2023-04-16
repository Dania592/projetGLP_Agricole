package data.flore;

import data.espece.EtreVivant;
import data.espece.Milieu;
import data.espece.evolution.EvolutionPlante;
import data.gestion.Stockage;
import data.map.Map;
import data.myExceptions.MortException;
import process.visitor.GestionVisitor;

public  abstract class Culture extends EtreVivant {
	

	private Engrais engrais;
	private EvolutionPlante evolution;
	
	public Culture(int nbCase, int ligne_init, int colonne_init, Milieu milieu, int dureeVie, float prixAchat,  String reference , Map map ) {
		super(nbCase, ligne_init, colonne_init, milieu, dureeVie, prixAchat, reference , map);
		this.engrais = new Engrais();
		evolution = EvolutionPlante.GRAINE;
	}

	public Engrais getEngrais() {
		return engrais;
	}

	public void setEngrais(Engrais engrais) {
		this.engrais = engrais;
	}

	@Override
	public void vieillir() throws MortException {
		// TODO Auto-generated method stub
	}
		
	public EvolutionPlante getEvolution() {
		return evolution;
	}

}
