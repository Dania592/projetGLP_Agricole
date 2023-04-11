package data.espece.faune;



import data.espece.Milieu;
import data.espece.Produceur;
import data.map.Map;
import data.production.Produit;
import data.structure.Structure;



public abstract class AnimalProducteur extends Animal implements Produceur{

	private int frequenceProduction ;
	private int quantiteProduction ;
	private ProductifState productifState;
	
	
	public AnimalProducteur(int ligne_init, int colonne_init, Milieu milieu, int dureeVie, float prixAchat, int naissance, float poids, String nom, Alimentation alimentation, String sexe,
			Structure habitat, int frequenceProduction, int quantiteProduction,
			Produit produit , String reference , Map map ,int speedGrowth ) {
		super(ligne_init, colonne_init, milieu, dureeVie, prixAchat, naissance, poids, nom, alimentation,
				sexe, habitat ,reference ,map , speedGrowth);
		this.frequenceProduction = frequenceProduction;
		this.quantiteProduction = quantiteProduction;
		productifState = ProductifState.UNABLE_TO_PRODUCE; //TODO Pour l'instant mais définir à partir de quand dans son évolution il est peut produire
	}


	public int getFrequenceProduction() {
		return frequenceProduction;
	}


	public void setFrequenceProduction(int frequenceProduction) {
		this.frequenceProduction = frequenceProduction;
	}


	public int getQuantiteProduction() {
		return quantiteProduction;
	}


	public void setQuantiteProduction(int quantiteProduction) {
		this.quantiteProduction = quantiteProduction;
	}

	public boolean haveProduced(){
		return productifState == ProductifState.HAVE_PRODUCE;
	}
	
	


	
}
