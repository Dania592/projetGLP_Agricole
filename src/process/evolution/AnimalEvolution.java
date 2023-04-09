package process.evolution;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import data.configuration.GameConfiguration;
import data.espece.faune.Animal;
import data.espece.faune.AnimalProducteur;
import data.flore.terrains.Terrain;
import data.myExceptions.MortException;
import data.structure.Enclos;
import data.stucture_base.Element;
import process.game.ElementManager;
import process.time.TimeManager;

public class AnimalEvolution {
	private ArrayList<AnimalProducteur> animals = new ArrayList<>();
	private ElementManager elementManager ;
	private TimeManager timeManager ;
	private int index = 0 ;

	public AnimalEvolution(ElementManager elementManager , TimeManager timeManager) {
		this.elementManager=elementManager;
		this.timeManager=timeManager;
		getAllAnimals();
	}
	
	
	public void evoluate() {
		getAllAnimals();
		randomMovement();
		terrainEvolution();
		growUp();
		
	}
	
	public void getAllAnimals() {
		animals.removeAll(animals);
		for(Enclos enclos : elementManager.getMapManager().getEnclosOnMap()) {
			animals.addAll(enclos.getAnimalProducteurs());
			
		}
	}

	public void killAnimal(AnimalProducteur animal ) {
		//animals.remove(animal); //=> cause un arret dans le thread 
		elementManager.remove(animal);
		for(Enclos enclos : elementManager.getMapManager().getEnclosOnMap()) {
			if(enclos.getAnimalProducteurs().contains(animal)) {
				enclos.removeAnimalProducteur(animal);
			}

		}
	}
	
	public void growUp() {
		for(AnimalProducteur animal : animals) {
			int gameHour = timeManager.getClock().getMinute().getValue();
			if((gameHour-animal.getLastEvolutionHour())> animal.getGrowthSpeed()*animal.getEvolution().getDureeEvolution()) {
				animal.setLastEvolutionHour(gameHour);
				try {
					animal.vieillir();
					String imagePath = GameConfiguration.IMAGE_PATH+animal.getClass().getSimpleName()
							+File.separator+animal.getEvolution()+File.separator+"STAND.png";
					BufferedImage image = ImageIO.read(new File(imagePath));
					animal.setImage(image);
				} catch (MortException | IOException e ) {
					System.out.println("animal mort : "+animal.getReference());
					killAnimal(animal);
				}
			}
		}
	}
	
	public void randomMovement() {
		if(index==GameConfiguration.ANIMAL_MOUVE_SPEED) {
			for(Animal animal : animals) {
				BufferedImage image;
				String imagePath;
				animal.setDirection(animal.getDirection().avancer());		
				imagePath =GameConfiguration.IMAGE_PATH +animal.getClass().getSimpleName()
						+File.separator+animal.getEvolution()+File.separator+animal.getDirection()+".png";
				index =0;
				switch(animal.getDirection()) {
				case UP_1,UP_2:
					elementManager.moveUp(animal);
				try {
					image = ImageIO.read(new File(imagePath));
					animal.setImage(image);
				} catch (IOException e) {

					e.printStackTrace();
				}
				break;
				case DOWN_1,DOWN_2 : 
					elementManager.moveDown(animal);	
				try {
					image = ImageIO.read(new File(imagePath));
					animal.setImage(image);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				case RIGHT_1,RIGHT_2 :
					elementManager.moveRight(animal);
				try {
					image = ImageIO.read(new File(imagePath));
					animal.setImage(image);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				case LEFT_1,LEFT_2 : 
					elementManager.moveLeft(animal);
				try {
					image = ImageIO.read(new File(imagePath));
					animal.setImage(image);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				default:
					break;
				}

			}

		}
		else {
			index ++;
		}
	}
		
	// n'a pas sa place ici doit etre gérér dans une class propre aux evolutions des terrains 
	public void terrainEvolution() {
		for (Element element : elementManager.getMapManager().getElements().values()) {
			if(element instanceof Terrain) {
				((Terrain)element).evoluer();
			}

		}
	}




}
