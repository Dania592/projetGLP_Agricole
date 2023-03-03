package data.stucture_base;

import data.acteur.Fermier;
import data.configuration.GameConfiguration;
import data.flore.Saison;
import data.gestion.RessourcesManager;
import process.game.ElementManager;

public class Farm {

	/**
	 * correspond à la dimension de la ferme = nombre de case par côté 
	 * 
	 */	
	private int dimension = 22; 
	private int cptJour ;
	private Saison saisonActuelle ;
	private Fermier fermier ; 
	private RessourcesManager ressourcesManager ;	
	private int nbEtoile ;
	private ElementManager ElementManager ; 
	private int ligne ; 
	private int colonne ; 
	
	public Farm( ElementManager manager, Fermier fermier ) {
		
		this.ElementManager = manager;
		this.fermier=fermier;
		ressourcesManager = new RessourcesManager();
		nbEtoile =0;
		cptJour = 0;
		saisonActuelle = Saison.PRINTEMPS;
		
		ligne = (GameConfiguration.NB_LIGNE - dimension )/2 ;
		colonne = (GameConfiguration.NB_COLONNE - dimension )/2 ;
	}
	

	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public int getNbEtoile() {
		return nbEtoile;
	}
	
	public void setNbEtoile(int nbEtoile ) {
		this.nbEtoile = nbEtoile;
	}
	public int getDimension() {
		return dimension;
	}
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	public int getCptJour() {
		return cptJour;
	}
	public void incrementCptJour() {
		if(cptJour == 19) {
			cptJour=0;
		}
		else{
			cptJour ++;
		}
		setSaisonActuelle();
	}
	
	public Saison getSaisonActuelle() {
		return saisonActuelle;
	}
	
	public void setSaisonActuelle() {
		if( cptJour <5 ) {
			saisonActuelle = Saison.ETE;	
		}
		else {
			if(cptJour < 10) {
				saisonActuelle = Saison.HIVER;
			}
			else {
				if(cptJour <15) {
					saisonActuelle = Saison.AUTOMNE;
				}
				else {
					saisonActuelle = Saison.PRINTEMPS;
				}
			}
		}
	}
	public ElementManager getManager() {
		return ElementManager;
	}
	
	public RessourcesManager getRessourcesManager() {
		return ressourcesManager;
	}

	public Fermier getFermier() {
		return fermier;
	}
	
	
}
