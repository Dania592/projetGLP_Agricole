package process.evolution;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import data.configuration.GameConfiguration;
import data.espece.faune.Animal;
import data.espece.faune.AnimalProducteur;
import data.myExceptions.MortException;
import data.structure.Enclos;
import data.time.Clock;
import process.game.ElementManager;
import process.time.TimeManager;

public class AnimalEvolution implements Serializable {
	private ArrayList<AnimalProducteur> animals = new ArrayList<>();
	private ElementManager elementManager ;
	private Clock clock;
	private int index = 0 ;

	public AnimalEvolution(ElementManager elementManager , Clock clock) {
		this.elementManager=elementManager;
		this.clock=clock;
		getAllAnimals();
	}
	
	
	public void evoluate() {
		getAllAnimals();
		randomMovement();
		growUp();	
	}
	
	public void getAllAnimals() {
		animals.removeAll(animals);
		for(Enclos enclos : elementManager.getMapManager().getEnclosOnMap()) {
			animals.addAll(enclos.getAnimals());
			
		}
	}

	public void killAnimal(AnimalProducteur animal ) {
		//animals.remove(animal); //=> cause un arret dans le thread 
		for(Enclos enclos : elementManager.getMapManager().getEnclosOnMap()) {
			if(enclos.getAnimals().contains(animal)) {
				enclos.removeAnimal(animal);
			}
		}
		elementManager.remove(animal);
	}
	
	public void growUp() {
		for(AnimalProducteur animal : animals) {
			int gameHour = clock.getMinute().getValue();
			if((gameHour-animal.getLastEvolutionHour())> animal.getGrowthSpeed()*animal.getEvolution().getDureeEvolution()) {
				animal.setLastEvolutionHour(gameHour);
				try {
					animal.vieillir();
					String imagePath = GameConfiguration.IMAGE_PATH+animal.getClass().getSimpleName()
							+File.separator+animal.getEvolution()+File.separator+"STAND.png";
					BufferedImage image = ImageIO.read(new File(imagePath));
					animal.setImage(imagePath);
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
				String imagePath;
				animal.setDirection(animal.getDirection().avancer());		
				imagePath =GameConfiguration.IMAGE_PATH +animal.getClass().getSimpleName()
						+File.separator+animal.getEvolution()+File.separator+animal.getDirection()+".png";
				index =0;
				animal.setImage(imagePath);
				switch(animal.getDirection()) {
				case UP_1,UP_2:
					elementManager.moveUp(animal);
				
				break;
				case DOWN_1,DOWN_2 : 
					elementManager.moveDown(animal);	
			
				break;
				case RIGHT_1,RIGHT_2 :
					elementManager.moveRight(animal);
				
				break;
				case LEFT_1,LEFT_2 : 
					elementManager.moveLeft(animal);
				
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
		
	




}
