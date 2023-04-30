package data.structure;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import data.espece.FoodConsumer.HungerLevel;
import data.espece.WaterConsumer.HydrationLevel;
import data.espece.faune.Animal;
import data.espece.faune.AnimalProducteur;
import data.espece.faune.MilkProduceur;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.map.Case;
import data.map.Map;
import data.myExceptions.FullCapaciteException;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.notification.Message;
import data.notification.Messagerie;
import data.planning.Activity;
import data.production.Produits;
import data.structure.hability.Distributor;
import data.structure.hability.Feedable;
import data.structure.hability.Fixable;
import data.structure.hability.Hydratable;
import data.structure.hability.ProductifPlace;
import data.structure.hability.SpecialActionPerformer;
import data.structure.hability.list.EnclosStorageStructure;
import data.stucture_base.Element;
import data.stucture_base.Position;
import data.time.Clock;
import gui.gestionnaire.keys.Graine;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;
import process.evolution.FullLevel;
import process.visitor.GestionVisitor;

public class Enclos extends Element implements Fixable, Feedable, ProductifPlace, Distributor<AnimalProducteur>, Hydratable, SpecialActionPerformer{
	private int capacite ;
	private int lastDecrementationNourriture ; 
	private int lastDecrementationEau ;
	private int lastBirth ; 
	private FullLevel niveauEau ; 
	private FullLevel niveauNourriture ;
	private int dimension ; 
	private FixableState state;
	private HungerLevel animalsHungerLevel;
	private HydrationLevel animalsHydrationLevel;
	private EnclosStorageStructure animalStorage = new EnclosStorageStructure();
	private static boolean usedForAnAction = false;
	private HashMap<Produits, Integer> production = new HashMap<>();
	private HashMap<String, String > images = new HashMap<>();
	
	public Enclos(int ligne_init, int colonne_init, String reference, Map map ){
		super(reference, false, 49, ligne_init ,colonne_init ,map );
		//animalProducteurs = new ArrayList<>();
		state = FixableState.USABLE;
		capacite = 10;
		niveauEau = FullLevel.FULL ;
		niveauNourriture = FullLevel.FULL;
		dimension = 7 ;
		lastBirth = 0 ;
		lastDecrementationNourriture = 0 ; 
		lastDecrementationEau = 0 ; 
		initImage();
		setImage(images.get("entier"));
		animalsHungerLevel = HungerLevel.FULL;
		animalsHydrationLevel = HydrationLevel.FULLY_HYDRATED;
	}
	
	public int getLastBirth() {
		return lastBirth;
	}
	public void setLastBirth(int birth) {
		this.lastBirth=birth;
	}
	
	public EnclosStorageStructure getAnimalStorage() {
		return animalStorage;
	}
	

	public HydrationLevel getAnimalsHydrationLevel() {
		return animalsHydrationLevel;
	}

	public void setAnimalsHydrationLevel(HydrationLevel animalsHydrationLevel) {
		this.animalsHydrationLevel = animalsHydrationLevel;
	}

	public HungerLevel getAnimalsHungerLevel() {
		return animalsHungerLevel;
	}

	public void setAnimalsHungerLevel(HungerLevel animalsHungerLevel) {
		this.animalsHungerLevel = animalsHungerLevel;
		for(AnimalProducteur animal : getAnimals()) {
			animal.setHungerLevel(animalsHungerLevel);
		}
		if(animalsHungerLevel==HungerLevel.VERY_HUNGRY) {
			Message message = new Message("les animaux vont \n biontôt mourir" , Clock.getInstance().getHour().getValue() , Clock.getInstance().getMinute().getValue());
			Messagerie.getInstance().addMessage(message);
		}
	}

	public <T> T accept(GestionVisitor<T> visitor) {
		visitor.visit(this);
		return null;
	}
	
	public void setAnimalHydrationLevel(HydrationLevel animalsHydrationLevel) {
		this.animalsHydrationLevel = animalsHydrationLevel;
		for(AnimalProducteur animal : getAnimals()) {
			animal.setHydrationLevel(animalsHydrationLevel);
		}
	}


	public void setLastDecrementationNourriture(int lastDecrementationNourriture) {
		this.lastDecrementationNourriture = lastDecrementationNourriture;
	}

	public void setLastDecrementationEau(int lastDecrementationEau) {
		this.lastDecrementationEau = lastDecrementationEau;
	}

	public int getLastDecrementationNourriture() {
		return lastDecrementationNourriture;
	}

	public int getLastDecrementationEau() {
		return lastDecrementationEau;
	}

	public HashMap<String,  String> getImages(){
		return images ;
	}
	
	private void initImage() {
			images.put("bas_milieu","src"+File.separator+"ressources"+File.separator+"enclos"+File.separator+"bas_m.png");
			images.put("bas_gauche", "src"+File.separator+"ressources"+File.separator+"enclos"+File.separator+"bas_g.png");
			images.put("bas_droit", "src"+File.separator+"ressources"+File.separator+"enclos"+File.separator+"bas_d.png");
			images.put("milieu", "src"+File.separator+"ressources"+File.separator+"enclos"+File.separator+"mm.png");
			images.put("entier", "src"+File.separator+"ressources"+File.separator+"enclos"+File.separator+"entier.png");
		
	}

	public int getCapacite() {
		return capacite;
	}
	
	public synchronized FullLevel getNiveauEau() {
		return niveauEau;
	}

	public void setNiveauEau(FullLevel niveauEau) {
		this.niveauEau = niveauEau;
	}

	public FullLevel getNiveauNourriture() {
		return niveauNourriture;
	}

	public void setNiveauNourriture(FullLevel niveauNourriture) {
		this.niveauNourriture = niveauNourriture;
	}

	public int getDimension() {
		return dimension;
	}

	public synchronized ArrayList<AnimalProducteur> getAnimals() {
		return animalStorage.getAnimals();
	}

	
	public void removeAnimal(AnimalProducteur animal) {
		animalStorage.remove(animal);
	}
	
	public void addAnimal(AnimalProducteur animal ) {
		animalStorage.add(animal);
	}
	
	
	public ArrayList<Case> bordEnclos() {
		Position position = getPosition();
		ArrayList<Case> bords= new ArrayList<>();
		for(int indexl = 0 ; indexl < position.getNbLigne() ; indexl++) {
			for(int indexc = 0 ; indexc < position.getNbColonne() ; indexc++ ) {
				Case block = position.getTabCase()[indexl][indexc];
				if(isOnBorderEnclos(block.getLigne() , block.getColonne())) {
					bords.add(block);
				}
			}
		}	
		return bords;
	}

	
	
	public boolean isOnBorderEnclos(int ligne , int colonne) {
		Position position = getPosition();
		return ligne==position.getLigne_init() || ligne==(position.getLigne_init()+dimension-1) ||
				colonne==position.getColonne_init() || colonne==(position.getColonne_init()+dimension-1);
	}

	@Override
	public ArrayList<ActionnableKey> getASetOfAllActionnableKey() {
		ArrayList<ActionnableKey> actionnableKeys = new ArrayList<>();
		actionnableKeys.add(getSpecificActionnableKey());
		return actionnableKeys;
	}

	@Override
	public boolean isNeedToBeFeed() {
		return niveauNourriture == FullLevel.EMPTY || niveauNourriture== FullLevel.HALF_FULL || niveauNourriture == FullLevel.QUARTER_FULL;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, NotImplementYetException {
		return visitor.action(this);
	}


	public void addAnimal(Animal animal){
		if(animal instanceof Vache){
			animalStorage.add((Vache) animal);
		}else if(animal instanceof Poule){
			animalStorage.add((Poule) animal);
		}else if(animal instanceof Mouton){
			animalStorage.add((Mouton) animal);
		}
		
	}

	public void removeAnimal(Animal animal){
		if(animal instanceof Vache){
			animalStorage.getVaches().remove((Vache)animal);
		}else if(animal instanceof Poule){
			animalStorage.getPoules().remove((Poule)animal);
		}else if(animal instanceof Mouton){
			animalStorage.getMoutons().remove((Mouton)animal);
		}
	}

	@Override
	public boolean isEmpty() {
		return getAnimals().isEmpty();
	}

	@Override
	public void setState(FixableState newState) {
		state = newState;
	}

	@Override
	public FixableState getState() {
		return state;
	}

	@Override
	public boolean readyToSend() {
		return true;
	}

	@Override
	public boolean canLaunchProduction() {
		return true;
	}

	@Override
	public void addSpecialSenderElement(AnimalProducteur specialSenderElement) {
		getAnimalStorage().add(specialSenderElement);
	}

	public void addSpecialSenderElement(MilkProduceur specialSenderElement) {
		getAnimalStorage().add(specialSenderElement);
	}

	@Override
	public void removeAll(ArrayList<AnimalProducteur> transportableToRemoveList) {
		getAnimalStorage().removeAll(transportableToRemoveList);
	}

	@Override
	public ActionnableKey getSpecificActionnableKey() {
		return ActionnableKey.ENCLOS;
	}

	@Override
	public boolean isCurrentlyUsedForAnotherTask() {
		return usedForAnAction;
	}

	@Override
	public void setStructureStatus(boolean isCurrentlyUsedForAnotherTask) {
		usedForAnAction = isCurrentlyUsedForAnotherTask;
	}

	@Override
	public HashMap<Produits, Integer> getProduction() {
		return production;
	}

	@Override
	public boolean isNeedToBeHydrated() {
		return niveauEau != FullLevel.FULL;
	}

	@Override
	public String toString() {
		return "Enclos [lastDecrementationNourriture=" + lastDecrementationNourriture + ", lastDecrementationEau="
				+ lastDecrementationEau + ", niveauEau=" + niveauEau + ", niveauNourriture=" + niveauNourriture
				+ ", animalsHungerLevel=" + animalsHungerLevel + ", animalsHydrationLevel=" + animalsHydrationLevel
				+ "]";
	}

    public static boolean isUsedForAnAction() {
        return usedForAnAction;
    }

	@Override
	public boolean isNeedToBeFixed() {
		return state == FixableState.DAMAGED;
	}

	@Override
	public boolean canPerformSpecialAction(Activity activity) throws UnknownActivityException {
		if(activity == Activity.SHAVE_SHEEP){
			boolean haveProduced = false;
			Iterator<Mouton> moutons = getAnimalStorage().getMoutons().iterator();
			while(moutons.hasNext() && !haveProduced){
				haveProduced = moutons.next().getProductifState() == ProductifState.IN_WAIT;
			}
		return haveProduced;
		}
		throw new UnknownActivityException(activity);
	}

	@Override
	public int getNumberOfTarget() {
		return getAnimals().size();
	}

	@Override
	public boolean needPlayerIntervention() {
		return false;
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
	public <T> T launchAction(PlaceVisitor<T> visitor, Activity activity, Graine graine) {
		throw new UnsupportedOperationException("Utilisé seulement par les terrains");
	}
	




}
