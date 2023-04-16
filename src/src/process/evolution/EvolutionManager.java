package process.evolution;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import data.configuration.GameConfiguration;
import data.espece.FoodConsumer.HungerLevel;
import data.espece.faune.AnimalProducteur;
import data.flore.terrains.Terrain;
import data.structure.Enclos;
import data.stucture_base.Element;
import data.time.Clock;
import process.game.ElementManager;
import process.time.TimeManager;

public class EvolutionManager implements Serializable{

	private AnimalEvolution animalEvolution ;
	private ElementManager elementManager ;
	private Clock clock;
	private int deathIndex = 0;
	private ArrayList<AnimalProducteur> animalsToRemove = new ArrayList<>();


	// on ajoute l'evolution des terrains actions ... 

	public EvolutionManager(ElementManager elementManager , Clock clock) {
		this.elementManager=elementManager;
		this.clock=clock;
		animalEvolution = new AnimalEvolution(elementManager, clock);

	}

	public void UpdateEvolution() {
		animalEvolution.evoluate();
		terrainEvolution();
		enclosEvolution();
	}


	public void terrainEvolution() {
		for (Element element : elementManager.getMapManager().getElements().values()) {
			if(element instanceof Terrain) {
				((Terrain)element).evoluer();
			}

		}
	}


	public void enclosEvolution() {
		for(Enclos enclos : elementManager.getMapManager().getEnclosOnMap()) {

			int delay = enclos.getAnimals().size()!=0 ? GameConfiguration.FREQUENCE_DECREMENTATION_ENCLOS/enclos.getAnimals().size() : GameConfiguration.FREQUENCE_DECREMENTATION_ENCLOS;
			int dhour = clock.getMinute().getValue()- enclos.getLastDecrementation();

			if(dhour >= delay && enclos.getAnimals().size()!=0) {
				if(enclos.getNiveauEau()!=FullLevel.EMPTY || enclos.getNiveauNourriture()!=FullLevel.EMPTY) {
					enclos.setNiveauEau(enclos.getNiveauEau().getNextState());
					enclos.setNiveauNourriture(enclos.getNiveauNourriture().getNextState());
					enclos.setLastDecrementation( clock.getMinute().getValue());
				}
				else {
					if(enclos.getAnimalsHungerLevel()!= HungerLevel.STARVING) {
						enclos.setAnimalsHungerLevel(enclos.getAnimalsHungerLevel().decrease_1());
						enclos.setLastDecrementation( clock.getMinute().getValue());
					}
					else {
						// on peut tuer plusieurs d'un coup 
						animalsToRemove.add(enclos.getAnimals().get(0));
						enclos.setLastDecrementation(clock.getMinute().getValue());
					}
				}
			}
		}
		int i = 0;
		while(i < animalsToRemove.size()) {
			if(deathIndex == 5) {
				animalEvolution.killAnimal(animalsToRemove.get(0));
				animalsToRemove.remove(0);
				i = 0;
				deathIndex = 0;
			}
			else {
				deathIndex ++;
				i++;
				String imagePath =GameConfiguration.IMAGE_PATH +"nuage"+deathIndex+".png";
				animalsToRemove.get(0).setImage(imagePath);
							
			}
		}



	}


}
