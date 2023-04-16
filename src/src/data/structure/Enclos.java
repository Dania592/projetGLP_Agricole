package data.structure;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import data.espece.FoodConsumer.HungerLevel;
import data.espece.faune.AnimalProducteur;
import data.map.Case;
import data.map.Map;
import data.structure.hability.Feedable;
import data.structure.hability.Fixable;
import data.structure.hability.list.EnclosStorageStructure;
import data.stucture_base.Element;
import data.stucture_base.Position;
import process.action.place.PlaceVisitor;
import process.action.place.UnableToPerformSuchActionWithCurrentActionnable;
import process.evolution.FullLevel;;

public class Enclos extends Element implements Fixable, Feedable{

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


	public HashMap<String, String> getImages(){
		return images ;
	}
	
	private void initImage() {
			images.put("bas_milieu","src"+File.separator+"ressources"+File.separator+"enclos"+File.separator+"bas_m.png");
			images.put("bas_gauche","src"+File.separator+"ressources"+File.separator+"enclos"+File.separator+"bas_g.png");
			images.put("bas_droit", "src"+File.separator+"ressources"+File.separator+"enclos"+File.separator+"bas_d.png");
			images.put("milieu", "src"+File.separator+"ressources"+File.separator+"enclos"+File.separator+"mm.png");
			images.put("entier","src"+File.separator+"ressources"+File.separator+"enclos"+File.separator+"entier.png");
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfTaget() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isNeedToBeFeed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNeedToBeFixed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setState(FixableState newState) {
		// TODO Auto-generated method stub
		
	}
	
	


	

	

}
