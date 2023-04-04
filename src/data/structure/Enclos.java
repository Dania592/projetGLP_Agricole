package data.structure;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import data.espece.faune.Animal;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.map.Case;
import data.map.Map;
import data.structure.hability.Feedable;
import data.structure.hability.Fixable;
import data.stucture_base.Element;
import data.stucture_base.Position;
import process.action.place.PlaceVisitor;
import process.action.place.UnableToPerformSuchActionWithCurrentActionnable;
import data.structure.hability.list.EnclosStorageStructure;;

public class Enclos extends Element implements Fixable, Feedable{

	private int capacite ;
	private int niveauEau ; 
	private int niveauNourriture ;
	private int dimension ; 
	private FixableState state;
	private EnclosStorageStructure animalStorage = new EnclosStorageStructure();
	private HashMap<String, BufferedImage > images = new HashMap<>();
	
	public Enclos(int ligne_init, int colonne_init, String reference, Map map ){
		super(reference, false, 49, ligne_init ,colonne_init ,map );
		state = FixableState.USABLE;
		capacite = 10 ;
		niveauEau = 100 ;
		niveauNourriture = 100;
		dimension = 7 ;
		initImage();
		setImage(images.get("entier"));
		
	}
	
	public HashMap<String,  BufferedImage> getImages(){
		return images ;
	}
	
	private void initImage() {
		try {
			images.put("bas_milieu",ImageIO.read(new File("src"+File.separator+"ressources"+File.separator+"enclos"+File.separator+"bas_m.png")));
			images.put("bas_gauche", ImageIO.read(new File("src"+File.separator+"ressources"+File.separator+"enclos"+File.separator+"bas_g.png")));
			images.put("bas_droit", ImageIO.read(new File("src"+File.separator+"ressources"+File.separator+"enclos"+File.separator+"bas_d.png")));
			images.put("milieu", ImageIO.read(new File("src"+File.separator+"ressources"+File.separator+"enclos"+File.separator+"mm.png")));
			images.put("entier", ImageIO.read(new File("src"+File.separator+"ressources"+File.separator+"enclos"+File.separator+"entier.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCapacite() {
		return capacite;
	}
	
	public int getNiveauEau() {
		return niveauEau;
	}

	public void setNiveauEau(int niveauEau) {
		this.niveauEau = niveauEau;
	}

	public int getNiveauNourriture() {
		return niveauNourriture;
	}

	public void setNiveauNourriture(int niveauNourriture) {
		this.niveauNourriture = niveauNourriture;
	}

	public int getDimension() {
		return dimension;
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
		return ligne==position.getLigne_init() || ligne==(position.getLigne_init()+dimension-1) || colonne==position.getColonne_init() || colonne==(position.getColonne_init()+dimension-1);
	}
	

	public ArrayList<Animal> getAnimals(){
		ArrayList<Animal> animals = new ArrayList<>();
		animals.addAll(animalStorage.getVaches());
		animals.addAll(animalStorage.getMoutons());
		animals.addAll(animalStorage.getPoules());
		return animals;
	}

	public ArrayList<ActionnableKey> getActionnableKey(){
		ArrayList<ActionnableKey> actionnableKey = new ArrayList<>();
		actionnableKey.add(ActionnableKey.ENCLOS);
		return actionnableKey; 
	}


	public EnclosStorageStructure getAnimalStorage() {
		return animalStorage;
	}

	@Override
	public boolean isNeedToBeFeed() {
		throw new UnsupportedOperationException("Unimplemented method 'isNeedToBeFeed'");
	}


	@Override
	public boolean isNeedToBeFixed() {
		return state == FixableState.DAMAGED;
	}

	public void setState(FixableState newState){
		state = newState;
	}

	@Override
	public int getNumberOfTaget() {
		int numberOfTarget = 0;
		numberOfTarget += animalStorage.getVaches().size();
		numberOfTarget += animalStorage.getPoules().size();
		numberOfTarget += animalStorage.getMoutons().size();
		return numberOfTarget;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable {
		return visitor.action(this);
	}


	public void addAnimal(Animal animal){
		if(animal instanceof Vache){
			animalStorage.addToVaches((Vache) animal);
		}else if(animal instanceof Poule){
			animalStorage.addToPoules((Poule) animal);
		}else if(animal instanceof Mouton){
			animalStorage.addToMoutons((Mouton) animal);
		}
		
	}

	


	

	

}
