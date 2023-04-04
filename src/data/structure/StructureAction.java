package data.structure;


import data.map.Map;



public  abstract class StructureAction extends Structure {

	public StructureAction(int ligne_init, int colonne_init, float prixAchat , String reference , Map map) {
		super(ligne_init, colonne_init, prixAchat , reference , map);
	}



}
