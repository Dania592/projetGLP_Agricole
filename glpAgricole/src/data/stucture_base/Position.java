package data.stucture_base;

import data.map.Case;
import data.map.Map;

/**
 * cette classe est responsable de la creation et du regroupement des case d'un element dans un tableau a 2 dimension 
 * à partir de la ligne initial et de la colonne initial en prenant en compte le nombre de case à attribuer 
 * 
 * @author dania
 *
 */
public class Position {


	private Case[][] tabCase;
	private int nbCase;
	public final static int NB_CASE_MAX = 16;
	private int ligne_init;
	private int colonne_init;
	private int nbColonne ;
	private int nbLigne ;
	private Map map ;
	
	
	public Position(int nbCase , int ligne_init , int colonne_init , Map map) {
		
		this.map = map;
		this.nbCase = nbCase;
		this.ligne_init = ligne_init;
		this.colonne_init = colonne_init;
		
		// calcule du nombre de cases pour les lignes et les colonnes 
		 nbColonne = (int) Math.sqrt(nbCase);
		 nbLigne = nbColonne;
		
		tabCase = new Case[(int) Math.sqrt(NB_CASE_MAX)][(int) Math.sqrt(NB_CASE_MAX)];
		
		for(int indexligne =0 ;indexligne < nbLigne ;indexligne++) {
			for(int indexColone=0 ; indexColone < nbColonne; indexColone++ ) {
				
				tabCase[indexligne][indexColone]= map.getCase(ligne_init + indexligne , colonne_init + indexColone);
					
			}
		}
	}

	
	public Case[][] getTabCase() {
		return tabCase;
	}
	
	
	public void setTabCase(int new_ligne ,int  new_colonne) {
	
		int dligne = new_ligne - ligne_init ; 
		int dcolonne = new_colonne - colonne_init ;
		colonne_init = new_colonne ;
		ligne_init = new_ligne ;
		
		for(int indexligne =0 ;indexligne < nbLigne ;indexligne++) {
			for(int indexColone=0 ; indexColone < nbColonne; indexColone++ ) {
				
				Case block = tabCase[indexligne][indexColone];
				
				tabCase[indexligne][indexColone]= map.getCase(block.getLigne() + dligne, block.getColonne()+ dcolonne);
			
				map.getCase(block.getLigne() +dligne , block.getColonne()+dcolonne).setLibre(false);
				
			}
		}
	}
	
	
	public int getNbCase() {
		return nbCase;
	}
	
	
	public int getLigne_init() {
		return ligne_init;
	}
	
	
	public int getColonne_init() {
		return colonne_init;
	}
	
	

	public int getNbColonne() {
		return nbColonne;
	}


	public int getNbLigne() {
		return nbLigne;
	}

	@Override
	public String toString() {
		String res ="";
		for(int indexligne =0 ;indexligne < nbLigne ;indexligne++) {
			for(int indexColone=0 ; indexColone < nbColonne; indexColone++ ) {
				res+=tabCase[indexligne][indexColone];
			}
		}
		return res;
	}
	
	public boolean contains(Case block ) {
		for(int indexligne =0 ;indexligne < nbLigne ;indexligne++) {
			for(int indexColone=0 ; indexColone < nbColonne; indexColone++ ) {
				Case tabcase = tabCase[indexligne][indexColone];
				if(tabcase.equals(block)) {
					return true ;
				}
			}
		}
		
		return false ;
	}
	
}
