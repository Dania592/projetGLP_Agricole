package process.evolution;

import process.game.ElementManager;
import process.time.TimeManager;

public class EvolutionManager {

	private AnimalEvolution animalEvolution ;
	
	// on ajoute l'evolution des terrains actions ... 
	
	public EvolutionManager(ElementManager elementManager , TimeManager timeManager) {
		animalEvolution = new AnimalEvolution(elementManager, timeManager);
		
	}
	
	public void UpdateEvolution() {
		animalEvolution.evoluate();
	}
}
