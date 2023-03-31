package process.evolution;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import data.espece.faune.Animal;
import data.flore.terrains.Terrain;
import data.myExceptions.MortException;
import data.structure.Enclos;
import data.stucture_base.Element;
import process.game.ElementManager;
import process.time.TimeManager;

public class AnimalEvolution {
		private ArrayList<Animal> animals = new ArrayList<>();
		private ElementManager elementManager ;
		private TimeManager timeManager ;
		
		public AnimalEvolution(ElementManager elementManager , TimeManager timeManager) {
			this.elementManager=elementManager;
			this.timeManager=timeManager;
			getAllAnimals();
		}
		
		public void getAllAnimals() {
			for(Enclos enclos : elementManager.getMapManager().getEnclosOnMap()) {
				animals.addAll(enclos.getAnimals());
				randomMovement(enclos);
			}
		}
		
		public void evoluate() {
			getAllAnimals();
			terrainEvolution();
			for(Animal animal : animals) {
				int gameHour = timeManager.getClock().getMinute().getValue();
				// ici il faudra isolé frequence l'evolution de chaque animal 
				if((gameHour-animal.getLastEvolutionHour())>2) {
					animal.setLastEvolutionHour(gameHour);
					try {
						animal.vieillir();
						updateImage(animal);
						
					} catch (MortException e) {
						elementManager.remove(animal);
						System.out.println("L'animal "+animal.getClass().getSimpleName()+" est mort ");
						for(Enclos enclos : elementManager.getMapManager().getEnclosOnMap()) {
							if(enclos.getAnimals().contains(animal)) {
								enclos.removeAnimal(animal);
							}
						}
					}
				}
			}
			
			
		}
		
		public void updateImage(Animal animal) {
			try {
				String imagePath = "src"+File.separator+"ressources"+File.separator+animal.getClass().getSimpleName()
						+File.separator+animal.getEvolution()+File.separator+"stand.png";
				BufferedImage image = ImageIO.read(new File(imagePath));
				animal.setImage(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void randomMovement(Enclos enclos) {
			for(Animal animal : enclos.getAnimals()) {
				int mouvement = (int)(Math.random()*20);
				BufferedImage image;
				String imagePath;
				switch(mouvement) {
				case 1 :
					elementManager.moveUp(animal);
					imagePath = "src"+File.separator+"ressources"+File.separator+animal.getClass().getSimpleName()
							+File.separator+animal.getEvolution()+File.separator+"up.png";
					try {
						image = ImageIO.read(new File(imagePath));
						animal.setImage(image);
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					break;
				case 2 : 
					elementManager.moveDown(animal);
					imagePath = "src"+File.separator+"ressources"+File.separator+animal.getClass().getSimpleName()
							+File.separator+animal.getEvolution()+File.separator+"down.png";
					
					try {
						image = ImageIO.read(new File(imagePath));
						animal.setImage(image);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 3 :
					elementManager.moveRight(animal);
					imagePath = "src"+File.separator+"ressources"+File.separator+animal.getClass().getSimpleName()
							+File.separator+animal.getEvolution()+File.separator+"right.png";
					
					try {
						image = ImageIO.read(new File(imagePath));
						animal.setImage(image);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 4 : 
					elementManager.moveLeft(animal);
					imagePath = "src"+File.separator+"ressources"+File.separator+animal.getClass().getSimpleName()
							+File.separator+animal.getEvolution()+File.separator+"left.png";
					
					try {
						image = ImageIO.read(new File(imagePath));
						animal.setImage(image);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
					
				default:
					imagePath = "src"+File.separator+"ressources"+File.separator+animal.getClass().getSimpleName()
					+File.separator+animal.getEvolution()+File.separator+"stand.png";
					try {
						image = ImageIO.read(new File(imagePath));
						animal.setImage(image);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break ;
				}
				
			}
		}
		public void terrainEvolution() {
			for (Element element : elementManager.getMapManager().getElements().values()) {
				if(element instanceof Terrain) {
					((Terrain)element).evoluer();
				}
				
			}
		}
		
		
	
	
}
