package data.espece.faune;



import data.espece.Produceur;
import data.espece.Slaughtable;
import data.production.Produit;
import data.production.Produits;
import data.structure.Structure;
import data.time.CyclicCounter;
import gui.gestionnaire.keys.Animals;


/**
 * Class abstraite pour regrouper les animaux pouvant produire 
 *
 */
public abstract class AnimalProducteur extends Animal implements Produceur, Slaughtable, Healable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * la Quantité que l'animal va produire 
	 */
	private int quantiteProduction ;
	/**
	 * état de productivité 
	 */
	private ProductifState productifState;
	/**
	 * cycle de production de l'animal 
	 */
	private CyclicCounter productionCycle;
	/**
	 * type de producteur de l'animal 
	 */
	private Produceur.Type produceurType;
	/**
	 * le temps que l'animal prend pour produire 
	 */
	private Produceur.TimeItTakes timeItTakesToProduce;
	/**
	 * le produit de l'animal 
	 */
	private Produit produit;
	/**
	 * vérification de l'augmentation de la productivité 
	 */
	private boolean isDoped = false;
	/**
	 * constructeur nécéssaire au polymorphisme
	 * @param dureeVie : durée de vie 
	 * @param prixAchat : prix d'achat 
	 * @param naissance : date de naissance 
	 * @param poids : poids de l'animal 
	 * @param nom : nom de l'animal 
	 * @param sexe : sexe de l'animal 
	 * @param habitat : structure de l'animal 
	 * @param frequenceProduction : frequence de production
	 * @param quantiteProduction : quantité produite par l'animal 
	 * @param produit : produit de l'animal 
	 * @param reference : référence par rapport à la map 
	 * @param speedGrowth : vitesse d'évolution de l'animal 
	 */
	public AnimalProducteur(  int dureeVie, float prixAchat, int naissance, float poids, String nom, String sexe,
			Structure habitat, int frequenceProduction, int quantiteProduction,
			Produit produit , String reference  ,int speedGrowth ) {
		super(  dureeVie, prixAchat, naissance, poids, nom, 
				sexe, habitat ,reference  , speedGrowth);
		this.quantiteProduction = quantiteProduction;
		this.produit = produit;
		produceurType = Type.AVERAGE_PRODUCEUR; 
		productifState = ProductifState.UNABLE_TO_PRODUCE;
		productionCycle = new CyclicCounter(getTimeItTakesToProduceInSeconde());
	}

	/**
	 * retourne le temps que l'animal prend pour produire 
	 * @return
	 */
	public Produceur.TimeItTakes getTimeItTakesToProduce() {
		return timeItTakesToProduce;
	}

	/**
	 * retourne la quantité produite 
	 */
	public int getProcuedQuantity() {
		return quantiteProduction;
	}
	/**
	 * retourne le produit 
	 */
	public Produits collectProduction(){
		return produit.getType();
	}
	/**
	 * modifie l'état de productivité de l'animal 
	 */
	public void setProductifState(ProductifState productifState) {
		this.productifState = productifState;
	}
	/**
	 * modifie le type de producteur 
	 */
	public void setProduceurType(Produceur.Type produceurType) {
		this.produceurType = produceurType;
	}
	/**
	 * modifie l'état de production 
	 */
	public ProductifState getProductifState(){
		return productifState;
	}
	/**
	 * retourne le type de producteur
	 */
	public Type getProduceurType(){
		return produceurType;
	}

	/**
	 * retourne le temps que l'animal prend pour produire 
	 */
	public int getTimeItTakesToProduceInSeconde(){
		return getTimeItTakes().getTimeInSeconde();
	}
	/**
	 * retourne le cycle de prodution de l'animal
	 */
	public CyclicCounter getProductionCycle() {
		return productionCycle;
	}

	/**
	 * retourne la clé associé à l'animal 
	 */
	@Override
	public Animals getTypeOfAnimal() {
		return getKey();
	}

	/**
	 * vérifie si la productivité a été modifié 
	 */
	public boolean isDoped() {
		return isDoped;
	}
	/**
	 * modifie la productivité 
	 */
	@Override
	public void setDoped(boolean isDoped) {
		this.isDoped = isDoped;
	}


}
