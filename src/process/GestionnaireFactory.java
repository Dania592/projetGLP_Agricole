package process;

import data.espece.faune.Animal;
import data.espece.faune.Chevre;
import data.espece.faune.Chien;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.map.Map;
import data.materiel.Engin;
import data.materiel.Outil;
import data.structure.Abatoire;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.SalleDeTraite;
import data.structure.Structure;
import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.Engins;
import gui.gestionnaire.keys.Outils;
import gui.gestionnaire.keys.Structures;

public class GestionnaireFactory {
	
	public GestionnaireFactory() {
	}
	
	public Animal createElement(Animals key, Map map) {
		switch (key) {
			case VACHE:
				return new Vache(0, 0, 0, null, null, null, null, map);
			case MOUTON:
				return new Mouton(0, 0, 0, null, null, null, null, map);
			case POULE:
				return new Poule(0, 0, null, 0, null, null, null, null, map);
			case CHEVRE:
				return new Chevre(0, 0, 0, null, null, null, null, map);
			case CHIEN:
				return new Chien(0, 0, null, 0, null, null, null, null, map);
			default:
				return null;
		}
	}
	
	public Structure createElement(Structures key, Map map) {
		switch (key) {
			case ABATTOIRE:
				return new Abatoire(0, 0, null, map);
			case ENTREPOT:
				return new Entrepot(0, 0, null, map);
			case ETABLE:
				return new Etable(0, 0, null, map);
			case MAISON:
				return new Maison(0, 0, null, map);
			case POULAILLER:
				return new Poulallier(0, 0, null, map);
			case SALLE_DE_TRAITE:
				return new SalleDeTraite(0, 0, null, map);
			case GARAGE:
				return null;
			case GRANGE:
				return null;
			default:
				return null;
		}
	}

	public Outil createElement(Outils outil, Map map) {
		return null;
	}

	public Engin createElement(Engins engin, Map map) {
		// TODO Auto-generated method stub
		return null;
	}
}
