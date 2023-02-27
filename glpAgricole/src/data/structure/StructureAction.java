package data.structure;

import java.util.ArrayList;

import data.map.Map;
import data.travail.Tache;



public  abstract class StructureAction extends Structure{
	
	private ArrayList<Tache> taches ;

	public StructureAction(int ligne_init, int colonne_init, float prixAchat , String reference , Map map) {
		super(ligne_init, colonne_init, prixAchat , reference , map);
		this.taches =  new ArrayList<>();;
	}

	public ArrayList<Tache> getTaches() {
		return taches;
	}

	public void addTache(Tache tache) {
		taches.add(tache);
	}
	
	public void removeTache(Tache tache) {
		taches.remove(tache);
	}

}
