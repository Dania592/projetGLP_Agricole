package data.structure;

import data.map.Map;
import data.structure.hability.Distributor;
import data.structure.hability.ProductifPlace;

public  abstract class StructureAction extends Structure implements ProductifPlace{

	public StructureAction(int ligne_init, int colonne_init, String reference , Map map) {
		super(ligne_init, colonne_init, reference , map);
	}



}
