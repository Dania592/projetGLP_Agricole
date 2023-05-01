package process;

import data.espece.faune.Animal;
import data.espece.faune.Chevre;
import data.espece.faune.Chien;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.flore.terrains.Terrain;
import data.map.Map;
import data.materiel.Engin;
import data.materiel.Outil;
import data.structure.Abatoire;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Garage;
import data.structure.Grange;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.SalleDeTraite;
import data.structure.Structure;
import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.Encloss;
import gui.gestionnaire.keys.Engins;
import gui.gestionnaire.keys.Keys;
import gui.gestionnaire.keys.Outils;
import gui.gestionnaire.keys.Structures;
import gui.gestionnaire.keys.Terrains;

public class GestionnaireFactory {
	
	private static int counter = 0;
	
	public GestionnaireFactory() {
	}
	
	public static Animal createElement(Animals key, Map map) {
		String reference = generateReference(key);
		switch (key) {
			case VACHE:
				return new Vache(0, null, null, null, reference);
			case MOUTON:
				return new Mouton(0, null, null, null, reference);
			case POULE:
				return new Poule(null, 0, null, null, null, reference);
			case CHEVRE:
				return new Chevre(0, null, null, null, reference);
			case CHIEN:
				return new Chien(null, 0, null, null, null, reference);
			default:
				return null;
		}
	}
	
	public static Structure createElement(Structures key, Map map) {
		String reference = generateReference(key);
		switch (key) {
			case ABATTOIRE:
				return new Abatoire(reference);
			case ENTREPOT:
				return new Entrepot(reference);
			case ETABLE:
				return new Etable(reference);
			case MAISON:
				return new Maison(reference);
			case POULAILLER:
				return new Poulallier(reference);
			case SALLE_DE_TRAITE:
				return new SalleDeTraite(reference);
			case GARAGE:
				return new Garage(reference);
			case GRANGE:
				return new Grange(reference);
			default:
				return null;
		}
	}
	
	public static Terrain createElement(Terrains terrain, Map map) {
		String reference = generateReference(terrain);
		return new Terrain(reference, false, null);
	}
	
	public static Outil createElement(Outils outil, Map map) {
		String reference = generateReference(outil);
		return new Outil(reference, false, 0, outil);
	}
	
	public static Engin createElement(Engins engin, Map map) {
		String reference = generateReference(engin);
		return new Engin(reference, false, 0, engin);
	}
	
	public static Enclos createElement(Encloss enclos, Map map) {
		String reference = generateReference(enclos);
		return new Enclos(reference);
	}
	
	public static String generateReference(Keys key) {
		String reference = key.name()+counter;
		counter++;
		return reference;
		
	}
}
