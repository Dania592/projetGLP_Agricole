package data.stucture_base;

import data.acteur.Fermier;
import data.configuration.GameConfiguration;
import data.flore.Saison;
import data.gestion.RessourcesManager;
import process.evolution.EvolutionManager;
import process.game.ElementManager;
import process.time.TimeManager;

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
	private ElementManager elementManager ;
	private TimeManager timeManager ; 
	private EvolutionManager evolutionManager ;
	private int ligne ; 
	private int colonne ; 
	
	public Farm( ElementManager manager, Fermier fermier , TimeManager timeManager) {
		this.timeManager=timeManager;
		elementManager = manager;
		this.fermier=fermier;
		ressourcesManager = new RessourcesManager();
		nbEtoile =0;
		cptJour = 0;
		saisonActuelle = Saison.PRINTEMPS;
		evolutionManager = new EvolutionManager(manager, timeManager);
		ligne = (GameConfiguration.NB_LIGNE - dimension )/2 ;
		colonne = (GameConfiguration.NB_COLONNE - dimension )/2 ;
	}
	
	public EvolutionManager getEvolutionManager() {
		return evolutionManager;
	}
	
	public TimeManager getTimeManager() {
		return timeManager;
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
		return elementManager;
	}
	
	public RessourcesManager getRessourcesManager() {
		return ressourcesManager;
	}

	public Fermier getFermier() {
		return fermier;
	}
	
	public Boolean isOnborderFarm(int ligne , int colonne  ) {
		return ( this.ligne ==ligne) || (this.colonne == colonne) || ( ligne == (this.ligne+dimension-1)) || (colonne == (this.colonne+dimension-1)) ;
	}
	public void reservePlaceToFarm() {
		for(int ligneIndex = ligne ; ligneIndex < dimension+ligne ; ligneIndex ++) {
			for(int colonneIndex = colonne ; colonneIndex < dimension+colonne; colonneIndex ++) {
				if(isOnborderFarm(ligneIndex, colonneIndex )) {
					elementManager.getMapManager().getMap().getCase(ligneIndex , colonneIndex).setLibre(false);
				}
			}
		}
	}
	
}
