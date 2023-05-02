
package data.stucture_base;

import java.io.Serializable;

import data.acteur.Fermier;
import data.configuration.GameConfiguration;
import data.flore.Saison;
import data.gestion.RessourcesManager;
import data.notification.Messagerie;
import process.action.TaskManager;
import data.time.Clock;
import data.time.CyclicCounter;
import process.evolution.EvolutionManager;
import process.game.ElementManager;
import process.transaction.FinanceManager;

public class Farm implements Serializable {

	private static final long serialVersionUID = 1L;

	private int height = 22;
	private int width = 38;
	private int cptJour;
	public static Saison saisonActuelle ;
	private Fermier fermier ; 
	private RessourcesManager ressourcesManager ;	
	private int nbEtoile ;
	private ElementManager elementManager ;
	private Clock clock ; 
	private EvolutionManager evolutionManager ;
	private Messagerie messagerie ;
	private int ligne ; 
	private int colonne ; 
	private Boolean jour;
	private int lastCatastroph;
	
	public Farm( ElementManager manager, Fermier fermier ) {
		this.clock= Clock.getInstance();
		elementManager = manager;
		this.fermier = fermier;
		ressourcesManager = RessourcesManager.getInstance();
		nbEtoile = 0;
		cptJour = 0;
		lastCatastroph = 0;
		jour = true;
		saisonActuelle = Saison.PRINTEMPS;
		evolutionManager = new EvolutionManager(manager, clock);
		ligne = 2;
		colonne = 5;
	}

	public Boolean getJourMode() {
		return jour;
	}

	public int getLastCatastroph() {
		return lastCatastroph;
	}

	public ElementManager getElementManager() {
		return elementManager;
	}

	public EvolutionManager getEvolutionManager() {
		return evolutionManager;
	}

	public void setFermier(Fermier fermier) {
		this.fermier = fermier;
	}

	public Clock getClock() {
		return clock;
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

	public void setNbEtoile(int nbEtoile) {
		this.nbEtoile = nbEtoile;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setJourMode(Boolean switched) {
		jour = switched;
	}

	public int getWidth() {
		return width;
	}

	public void setWidht(int width) {
		this.width = width;
	}

	public int getCptJour() {
		return cptJour;
	}

	public void incrementCptJour() {
		if (cptJour == 19) {
			cptJour = 0;
		} else {
			cptJour++;
			FinanceManager.getInstance().incrementCounter();
		}
		setSaisonActuelle();
	}

	public Saison getSaisonActuelle() {
		return saisonActuelle;
	}

	public void setSaisonActuelle() {
		if (cptJour < 5) {
			saisonActuelle = Saison.ETE;
		} else {
			if (cptJour < 10) {
				saisonActuelle = Saison.HIVER;
			} else {
				if (cptJour < 15) {
					saisonActuelle = Saison.AUTOMNE;
				} else {
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

	public Boolean isOnborderFarm(int ligne, int colonne) {
		return (this.ligne == ligne) || (this.colonne == colonne) || (ligne == (this.ligne + height - 1))
				|| (colonne == (this.colonne + width - 1));
	}

	public void reservePlaceToFarm() {
		for (int ligneIndex = ligne; ligneIndex < height + ligne; ligneIndex++) {
			for (int colonneIndex = colonne; colonneIndex < width + colonne; colonneIndex++) {
				if (isOnborderFarm(ligneIndex, colonneIndex)) {
					elementManager.getMapManager().getMap().getCase(ligneIndex, colonneIndex).setLibre(false);
				}
			}
		}
	}
	
	public void etendre(int width, int height) {		
		for (int ligneIndex = ligne; ligneIndex < this.height + ligne; ligneIndex++) {
			for (int colonneIndex = colonne; colonneIndex < this.width + colonne; colonneIndex++) {
				if (isOnborderFarm(ligneIndex, colonneIndex)) {
					elementManager.getMapManager().getMap().getCase(ligneIndex, colonneIndex).setLibre(true);
				}
			}
		}
		this.height = height;
		this.width = width;
		for (int ligneIndex = ligne; ligneIndex < height + ligne; ligneIndex++) {
			for (int colonneIndex = colonne; colonneIndex < width + colonne; colonneIndex++) {
				if (isOnborderFarm(ligneIndex, colonneIndex)) {
					elementManager.getMapManager().getMap().getCase(ligneIndex, colonneIndex).setLibre(false);
				}
			}
		}
	}

}
