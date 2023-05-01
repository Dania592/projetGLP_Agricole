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
import data.structure.BergerieChevre;
import data.structure.BergerieMouton;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Garage;
import data.structure.Grange;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.Puit;
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
				return new Vache(0, 0, 0, null, null, null, reference, map);
			case MOUTON:
				return new Mouton(0, 0, 0, null, null, null, reference, map);
			case POULE:
				return new Poule(0, 0, null, 0, null, null, null, reference, map);
			case CHEVRE:
				return new Chevre(0, 0, 0, null, null, null, reference, map);
			case CHIEN:
				return new Chien(0, 0, null, 0, null, null, null, reference, map);
			default:
				return null;
		}
	}
	
	public static Structure createElement(Structures key, Map map) {
		String reference = generateReference(key);
		switch (key) {
			case ABATTOIRE:
				return new Abatoire(0, 0, reference, map);
			case ENTREPOT:
				return new Entrepot(0, 0, reference, map);
			case ETABLE:
				return new Etable(0, 0, reference, map);
			case MAISON:
				return new Maison(0, 0, reference, map);
			case POULAILLER:
				return new Poulallier(0, 0, reference, map);
			case SALLE_DE_TRAITE:
				return new SalleDeTraite(0, 0, reference, map);
			case GARAGE:
				return new Garage(0, 0, reference, map);
			case GRANGE:
				return new Grange(0, 0, reference, map);
			case BERGERIE_MOUTON:
				return new BergerieMouton(0, 0, reference, map);
			case BERGERIE_CHEVRE:
				return new BergerieChevre(0, 0, reference, map);
			case PUIT:
				return new Puit(0, 0, reference, map);
			default:
				return null;
		}
	}
	
	public static Terrain createElement(Terrains terrain, Map map) {
		String reference = generateReference(terrain);
		return new Terrain(reference, false, 0, 0, map, null);
	}
	
	public static Outil createElement(Outils outil, Map map) {
		String reference = generateReference(outil);
		return new Outil(reference, false, 0, 0, 0, map, outil);
	}
	
	public static Engin createElement(Engins engin, Map map) {
		String reference = generateReference(engin);
		return new Engin(reference, false, 0, 0, 0, map, engin);
	}
	
	public static Enclos createElement(Encloss enclos, Map map) {
		String reference = generateReference(enclos);
		return new Enclos(0, 0, reference, map);
	}
	
	public static String generateReference(Keys key) {
		String reference = key.name()+counter;
		counter++;
		return reference;
		
	}
}
