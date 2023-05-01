package data.espece.faune;


import data.espece.Milieu;
import data.map.Map;
import data.structure.Structure;



public abstract class AnimalCompagnie extends Animal  {

	private static final long serialVersionUID = 1L;

	public AnimalCompagnie( Milieu milieu, int dureeVie, float prixAchat , int naissance, float poids, String nom,
			Alimentation alimentation, String sexe ,Structure habitat , String reference   , int speedGrowth) {
		super( milieu, dureeVie, prixAchat , naissance, poids, nom, alimentation, sexe,
				habitat,reference  ,speedGrowth );
	}

}
