package data.structure;

import data.map.Map;
import gui.gestionnaire.keys.Structures;

public class CagePoisson extends Refuge{

	private final static float PRIX_ACHAT = 50000 ;
	
	public CagePoisson(int ligne_init, int colonne_init, String reference , Map map) {
		super(ligne_init, colonne_init, PRIX_ACHAT, reference , map );
		
	}
	
	public Structures getKey() {
		return Structures.CAGE_POISSON;
	}

}
