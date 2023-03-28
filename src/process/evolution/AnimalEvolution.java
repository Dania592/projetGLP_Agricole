package process.evolution;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import data.espece.faune.Animal;
import data.myExceptions.MortException;
import data.structure.Enclos;
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
			}
		}
		
		public void evoluate() {
			getAllAnimals();
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
		
		
		
		
	
	
}
