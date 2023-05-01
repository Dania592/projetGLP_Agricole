package data.espece.faune;



import data.espece.Milieu;
import data.espece.Produceur;
import data.espece.Slaughtable;
import data.espece.Transportable;
import data.map.Map;
import data.production.Produit;
import data.production.Produits;
import data.structure.Structure;
import data.time.CyclicCounter;
import gui.gestionnaire.keys.Animals;



public abstract class AnimalProducteur extends Animal implements Produceur, Slaughtable, Healable{

	private static final long serialVersionUID = 1L;
	
	private int quantiteProduction ;
	private ProductifState productifState;
	private CyclicCounter productionCycle;
	private Produceur.Type produceurType;
	private Produceur.TimeItTakes timeItTakesToProduce;
	private Produit produit;
	private boolean isDoped = false;
	
	public Produceur.TimeItTakes getTimeItTakesToProduce() {
		return timeItTakesToProduce;
	}

	public AnimalProducteur( Milieu milieu, int dureeVie, float prixAchat, int naissance, float poids, String nom, Alimentation alimentation, String sexe,
			Structure habitat, int frequenceProduction, int quantiteProduction,
			Produit produit , String reference  ,int speedGrowth ) {
		super( milieu, dureeVie, prixAchat, naissance, poids, nom, alimentation,
				sexe, habitat ,reference  , speedGrowth);
		this.quantiteProduction = quantiteProduction;
		this.produit = produit;
		produceurType = Type.AVERAGE_PRODUCEUR; 
		productifState = ProductifState.UNABLE_TO_PRODUCE;
		productionCycle = new CyclicCounter(getTimeItTakesToProduceInSeconde());
	}

	@Override
	public String toString() {
		return "AnimalProducteur [quantiteProduction=" + quantiteProduction + ", productifState=" + productifState
				+ ", productionCycle=" + productionCycle + ", produceurType=" + produceurType + "nom :"+getNom()+"]";
	}

	public int getProcuedQuantity() {
		return quantiteProduction;
	}

	public Produits collectProduction(){
		return produit.getType();
	}

	public boolean haveProduced(){
		return productifState == ProductifState.IN_WAIT;
	}
	
	public void setProductifState(ProductifState productifState) {
		this.productifState = productifState;
	}

	public void setProduceurType(Produceur.Type produceurType) {
		this.produceurType = produceurType;
	}

	public ProductifState getProductifState(){
		return productifState;
	}
    public Type getProduceurType(){
		return produceurType;
	}

    public int getTimeItTakesToProduceInSeconde(){
		return getTimeItTakes().getTimeInSeconde();
	}

    public CyclicCounter getProductionCycle() {
        return productionCycle;
    }

	@Override
	public Animals getTypeOfAnimal() {
		return getKey();
	}

	public boolean isDoped() {
		return isDoped;
	}

    @Override
    public void setDoped(boolean isDoped) {
        this.isDoped = isDoped;
    }


}
