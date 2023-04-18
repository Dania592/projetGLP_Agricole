package data.structure;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import data.espece.FoodConsumer.HungerLevel;
import data.espece.faune.Animal;
import data.espece.faune.AnimalProducteur;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.map.Case;
import data.map.Map;
import data.structure.hability.Distributor;
import data.structure.hability.Feedable;
import data.structure.hability.Fixable;
import data.structure.hability.list.EnclosStorageStructure;
import data.structure.hability.Productif;
import data.stucture_base.Element;
import data.stucture_base.Position;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.HaveNotProducedYetException;
import process.action.visitor.place.PlaceVisitor;
import process.evolution.FullLevel;
import data.structure.hability.list.EnclosStorageStructure;;

public class Enclos extends Element implements Fixable, Feedable, Productif, Distributor{
	



	private int capacite ;
	private int lastDecrementation ; 
	private FullLevel niveauEau ; 
	private FullLevel niveauNourriture ;
	private int dimension ; 
	private FixableState state;
	private HungerLevel animalsHungerLevel ;
	private EnclosStorageStructure animalStorage = new EnclosStorageStructure();

	private HashMap<String, String > images = new HashMap<>();
	
	public Enclos(int ligne_init, int colonne_init, String reference, Map map ){
		super(reference, false, 49, ligne_init ,colonne_init ,map );
		//animalProducteurs = new ArrayList<>();
		state = FixableState.USABLE;
		capacite = 10 ;
		niveauEau = FullLevel.FULL ;
		niveauNourriture = FullLevel.FULL;
		dimension = 7 ;
		lastDecrementation = 0 ; 
		initImage();
		setImage(images.get("entier"));
		animalsHungerLevel = HungerLevel.FULL;
		
	}
	
	public EnclosStorageStructure getAnimalStorage() {
		return animalStorage;
	}
	

	public HungerLevel getAnimalsHungerLevel() {
		return animalsHungerLevel;
	}

	public void setAnimalsHungerLevel(HungerLevel animalsHungerLevel) {
		this.animalsHungerLevel = animalsHungerLevel;
		for(AnimalProducteur animal : getAnimals()) {
			animal.setHungerLevel(animalsHungerLevel);
		}
	}

	public int getLastDecrementation() {
		return lastDecrementation;
	}


	public void setLastDecrementation(int lastDecrementation) {
		this.lastDecrementation = lastDecrementation;
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
	
	public FullLevel getNiveauEau() {
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

	public ArrayList<AnimalProducteur> getAnimals() {
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
	public ArrayList<ActionnableKey> getActionnableKey() {
		ArrayList<ActionnableKey> actionnableKeys = new ArrayList<>();
		actionnableKeys.add(ActionnableKey.ENCLOS);
		return actionnableKeys;

	}

	@Override
	public boolean isNeedToBeFeed() {
		return animalsHungerLevel == HungerLevel.HUNGRY || animalsHungerLevel == HungerLevel.VERY_HUNGRY || animalsHungerLevel == HungerLevel.STARVING;
	}

	@Override
	public boolean isNeedToBeFixed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException {
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
	public ArrayList<?> getTarget() {
		return getAnimals();
	}

	@Override
	public boolean haveProduced() {
		Iterator<Poule> poulesIter = animalStorage.getPoules().iterator();
		boolean haveProduced = false;
		while(poulesIter.hasNext() && !haveProduced){
			haveProduced = poulesIter.next().haveProduced();
		}
		return haveProduced;
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

	

	
	


	

	

}
