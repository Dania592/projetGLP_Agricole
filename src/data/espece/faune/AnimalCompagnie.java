package data.espece.faune;


import data.structure.Structure;


/**
 * Class abstraite regroupant les animaux de compagnie  
 *
 */
public abstract class AnimalCompagnie extends Animal  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * constructeur des animaux de production à utiliser pour le polymorphisme 
	 * @param dureeVie : durée de vie de l'animal 
	 * @param prixAchat : prix d'achat de l'animal
	 * @param naissance : heure de naissance de l'animal
	 * @param poids : poids de l'animal
	 * @param nom : nom de l'animal
	 * @param sexe : sexe de l'animal
	 * @param habitat : structure habitat de l'animal 
	 * @param reference : reference par rapport à la map 
	 * @param speedGrowth : vitesse d'évolution de l'animal
	 */
	public AnimalCompagnie( int dureeVie, float prixAchat , int naissance, float poids, String nom, String sexe ,Structure habitat , String reference   , int speedGrowth) {
		super( dureeVie, prixAchat , naissance, poids, nom, sexe,
				habitat,reference  ,speedGrowth );
	}

}
