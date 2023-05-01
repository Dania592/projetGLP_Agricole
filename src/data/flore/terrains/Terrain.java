package data.flore.terrains;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import data.espece.Produceur;
import data.espece.WaterConsumer;
import data.espece.faune.Healable;
import data.map.Map;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.myExceptions.UnknownActivityException;
import data.notion.Mortel.EtatSante;
import data.planning.Activity;
import data.production.Produits;
import data.structure.hability.Fixable;
import data.structure.hability.HealablePlace;
import data.structure.hability.Hydratable;
import data.structure.hability.ProductifPlace;
import data.structure.hability.SpecialActionPerformer;
import data.stucture_base.Element;
import data.time.CyclicCounter;
import gui.gestionnaire.keys.GestionnaireKey;
import gui.gestionnaire.keys.Graine;
import gui.gestionnaire.keys.Keys;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.DomesticSpeciesVisitor;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;
import process.transaction.Buyable;
import process.visitor.GestionVisitor;

public class Terrain extends Element implements Buyable, Produceur, ProductifPlace, WaterConsumer, Fixable, SpecialActionPerformer, Hydratable, Healable, HealablePlace{
	private static final long serialVersionUID = 1L;
	
	private boolean isUsedForATask = false;
	// private static int SPEED = 10;// vitesse d'évolution
	private static int DIMENSION = 16; // C'est un carré donc une seule dimension
	private static float PRIX_ACHAT = 100;
	private ProductifState productifState = ProductifState.UNABLE_TO_PRODUCE; 
	private HashMap<Produits, Integer> production = new HashMap<>();
	private HydrationLevel hydrationLevel = HydrationLevel.FULLY_HYDRATED;
	public void setEtatSante(EtatSante etatSante) {
		this.etatSante = etatSante;
	}

	private Produceur.Type produceurType = Type.AVERAGE_PRODUCEUR;
	private static Produceur.TimeItTakes timeItTakesToProduce = TimeItTakes.TERRAIN;
	private CyclicCounter productifCycle = new CyclicCounter(timeItTakesToProduce.getTimeInSeconde());
	private FixableState fixableState = FixableState.USABLE;
	private static int DEFAULT_PRODUCED_QUANTITY = 25;
	private EvolutionTerrain evolution;
	private Graine type;
	private HashMap<EvolutionTerrain, String> images = new HashMap<>();
	private EtatSante etatSante = EtatSante.BONNE_SANTE;
	public final static int DEFAUT_MAX_HYDRATION_COUNTER = timeItTakesToProduce.getTimeInSeconde()/3;
	private CyclicCounter hydrationCounter = new CyclicCounter(DEFAUT_MAX_HYDRATION_COUNTER); 
	private boolean isDoped = false;


	public Terrain(String reference, boolean statique ,Graine type) {
		super(reference, statique, DIMENSION);
		evolution = EvolutionTerrain.VIERGE;
		this.type = type;
		if (type != null) {
			images = ImagesTerrains.getInstance().getImages().get(type);
			setImage(images.get(evolution));
		} else {
			images.put(EvolutionTerrain.VIERGE, "src"+File.separator+"ressources"+File.separator+"Terrain"+File.separator+"terrain.png");
			setImage("src"+File.separator+"ressources"+File.separator+"Terrain"+File.separator+"terrain.png");
		}
		// timeItTakesToProduce = 
		// randomQuantity();
	}
	
	public void setType(Graine type) {
		this.type = type;
	}
	

	public void evoluer(){
		evolution = evolution.evolue();
		setImage(images.get(evolution));
	}


	// public void nextEvolution() {
	// 	evolution = evolution.evolue();
	// 	setImage(images.get(evolution));
	// 	count++;
	// }
	
	public EvolutionTerrain getEvolution() {
		return evolution;
	}

	public Graine getType() {
		return type;
	}
	
	public void setEvolution(EvolutionTerrain evolution) {
		this.evolution = evolution;
		setImage(images.get(evolution));
	}
	
	// public void randomQuantity() {
	// 	quantiteProduction = ThreadLocalRandom.current().nextInt(10,20);
	// }
	
	public String getCurrentImage() {
		return getImage();
	}
	
	@Override
	public float getPrixAchat() {
		return PRIX_ACHAT;
	}
	
	
	public HydrationLevel getHydrationLevel() {
		return hydrationLevel;
	}

	public void setHydrationLevel(HydrationLevel hydrationLevel) {
		this.hydrationLevel = hydrationLevel;
	}

	@Override
	public <T> T accept(GestionVisitor<T> visitor) {
		visitor.visit(this);
		return null;
	}

	@Override
	public String toString() {
		return "Terrain [isUsedForATask=" + isUsedForATask + ", productifState=" + productifState + ", production="
				+ production + ", hydrationLevel=" + hydrationLevel + ", produceurType=" + produceurType
				+ ", timeItTakesToProduce=" + timeItTakesToProduce.getTimeInSeconde() + ", productifCycle=" + productifCycle.getValue()
				+ ", fixableState=" + fixableState + ", evolution=" + evolution + ", type=" + type + ", etatSante="
				+ etatSante + ", CycleHydration = " +hydrationCounter+"]";
	}

	public CyclicCounter getHydrationCounter() {
		return hydrationCounter;
	}

	public void setHydrationCounter(CyclicCounter hydrationCounter) {
		this.hydrationCounter = hydrationCounter;
	}

	@Override
	public Keys getKey() {
		return type;
	}

	@Override
	public GestionnaireKey getGestionnaireKey() {
		return null;
	}

	@Override
	public ArrayList<ActionnableKey> getASetOfAllActionnableKey() {
		ArrayList<ActionnableKey> actionnableKeys = new ArrayList<>();
		actionnableKeys.add(getSpecificActionnableKey());
		return actionnableKeys;
	}

	@Override
	public ActionnableKey getSpecificActionnableKey() {
		return ActionnableKey.TERRAIN;
	}

	@Override
	public boolean isCurrentlyUsedForAnotherTask() {
		return isUsedForATask; 
	}

	@Override
	public void setStructureStatus(boolean isCurrentlyUsedForAnotherTask) {
		isUsedForATask = isCurrentlyUsedForAnotherTask;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable,
			HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException,
			NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
			UnableToMakeTheTransfertException {
			return visitor.action(this);
	}

	@Override
	public boolean isNeedToBeFixed() {
		return false;
	}

	@Override
	public boolean isEnoughHydrated() {
		return hydrationLevel == HydrationLevel.FULLY_HYDRATED;
	}

	@Override
	public boolean canLaunchProduction() {
		return productifState == ProductifState.PRODUCING || productifState == ProductifState.HAVE_PRODUCE;
	}

	@Override()
	public boolean haveProduced() {
		return evolution ==  EvolutionTerrain.PLANTE_5;
	}

	@Override
	public HashMap<Produits, Integer> getProduction() {
		return production;
	}

	@Override
	public Produits collectProduction() {
		return type.getProduits();
	}

	@Override
	public ProductifState getProductifState() {
		return productifState;
	}

	@Override
	public Type getProduceurType() {
		return produceurType;
	}

	@Override
	public TimeItTakes getTimeItTakes() {
		return timeItTakesToProduce;
	}

	@Override
	public int getTimeItTakesToProduceInSeconde() {
		throw new UnsupportedOperationException("Unimplemented method 'getTimeItTakesToProduceInSeconde'");
	}

	@Override
	public void setProductifState(ProductifState productifState) {
		this.productifState = productifState;
	}

	@Override
	public boolean needSpecialPlaceToGetProduction() {
		return false;
	}

	@Override
	public CyclicCounter getProductionCycle() {
		return productifCycle;
	}

	@Override
	public <T> T launchAction(DomesticSpeciesVisitor<T> visitor) throws HaveNotProducedYetException,
			BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException,
			ProblemOccursInProductionException, UnableToMakeTheTransfertException, NotImplementYetException {
		return visitor.action(this);
	}

	@Override
	public boolean needSpecialActionToGetProduction() {
		return false;	
	}

	@Override
	public EtatSante getEtatSante() {
		return etatSante;
	}

	public void setProduceurType(Produceur.Type produceurType) {
		this.produceurType = produceurType;
	}

	@Override
	public void setState(FixableState newState) {
		fixableState = newState;
	}

	@Override
	public FixableState getState() {
		return fixableState;
	}

	@Override
	public boolean canPerformSpecialAction(Activity activity) throws UnknownActivityException {
		if(activity == Activity.DIG_OVER){
			return evolution == EvolutionTerrain.VIERGE;
		}else if(activity == Activity.PLANT){
			return evolution == EvolutionTerrain.LABOURE && hydrationLevel == HydrationLevel.FULLY_HYDRATED;
		}else if(activity == Activity.REMOVE_ROTTEN_PLANT){
			return evolution == EvolutionTerrain.POURRI;
		}throw new UnknownActivityException(activity);
	}

	@Override
	public boolean isNeedToBeHydrated() {
		return !isEnoughHydrated();
	}

	@Override
	public int getNumberOfTarget() {
		return 1;
	}

	@Override
	public boolean needPlayerIntervention() {
		return true;
	}

	@Override
	public int getProcuedQuantity() {
		return DEFAULT_PRODUCED_QUANTITY;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor, Activity activity)
			throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
			BeingCannotPerformSuchActionException, NotImplementYetException,
			NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
			UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
		return visitor.action(this, activity);
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor, Activity activity, Graine graine) throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, UnableToGenerateNewTaskException {
		return visitor.action(this, activity, graine);
	}

	@Override
	public boolean isDoped() {
		return isDoped;
	}

    @Override
    public void setDoped(boolean isDoped) {
        this.isDoped = isDoped;
    }

	



	

	

		
}