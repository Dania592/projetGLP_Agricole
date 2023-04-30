package process.evolution;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import javax.imageio.ImageIO;

import data.configuration.GameConfiguration;
import data.espece.ProductionManager;
import data.espece.FoodConsumer.HungerLevel;
import data.espece.Produceur.ProductifState;
import data.espece.WaterConsumer.HydrationLevel;
import data.espece.faune.AnimalProducteur;
import data.flore.terrains.EvolutionTerrain;
import data.flore.terrains.Terrain;
import data.gestion.GestionnaireStocks;
import data.notification.Message;
import data.notification.Messagerie;
import data.notification.Message.BasicMessage;
import data.notion.Mortel.EtatSante;
import data.structure.Enclos;
import data.stucture_base.Element;
import data.time.Clock;
import data.time.CyclicCounter;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.ProductionPerformer;
import process.game.ElementManager;

public class EvolutionManager implements Serializable {

	private ProductionPerformer productionPerformer = new ProductionPerformer();
	private AnimalEvolution animalEvolution;
	private ElementManager elementManager;
	private Clock clock;
	private int deathIndex = 0;
	private ArrayList<AnimalProducteur> animalsToRemove = new ArrayList<>();

	// on ajoute l'evolution des terrains actions ...

	public EvolutionManager(ElementManager elementManager, Clock clock) {
		this.elementManager = elementManager;
		this.clock = clock;
		animalEvolution = new AnimalEvolution(elementManager, clock);

	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}

	public void UpdateEvolution() {
		animalEvolution.evoluate();
		terrainEvolution();
		enclosEvolution();
	}

	public void terrainEvolution() {
		Terrain terrain;
		for (Element element : elementManager.getMapManager().getElements().values()) {
			if (element instanceof Terrain) {
				// ((Terrain)element).evoluer();
				terrain = (Terrain) element;
				manageEvolutionTerrain(terrain);
			}

		}
	}

	private void manageEvolutionTerrain(Terrain terrain) {
		if(terrain.getEvolution() != EvolutionTerrain.VIERGE && terrain.getEvolution() != EvolutionTerrain.LABOURE
				&& terrain.getEvolution() != EvolutionTerrain.POURRI) {
			if (!(ProductionManager.getInstance().cointains(terrain))) {
				ProductionManager.getInstance().addToProductifList(terrain);
		}
		CyclicCounter hydrationCounter = terrain.getHydrationCounter();
		hydrationCounter.increment();
		if(hydrationCounter.getValue() == 0 &&(terrain.getEvolution()!=EvolutionTerrain.VIERGE || terrain.getEtatSante() != EtatSante.GRAVEMENT_MALADE)) {
			updateHealthState(terrain);
		}if(haveToUpdateProducingStateOfCurrentlyUnabledProduceur(terrain)){
			terrain.setProductifState(ProductifState.PRODUCING);
		}
		}
	}

	private boolean haveToUpdateProducingStateOfCurrentlyUnabledProduceur(Terrain terrain){
		return terrain.getEvolution() == EvolutionTerrain.PLANTE && terrain.getProductifState()==ProductifState.UNABLE_TO_PRODUCE &&
		terrain.getEtatSante() != EtatSante.GRAVEMENT_MALADE && terrain.getEtatSante() != EtatSante.MALADE;
	}

	private void updateHealthState(Terrain terrain){
		terrain.setHydrationLevel(terrain.getHydrationLevel().decrease());
		if (terrain.getHydrationLevel() == HydrationLevel.IN_DANGER) {
			Messagerie.getInstance().addMessage(new Message(BasicMessage.FIELD_NEED_WATER, clock.getHour().getValue(), clock.getMinute().getValue()));
			terrain.setEtatSante(EtatSante.MALADE);
		}else if (terrain.getHydrationLevel() == HydrationLevel.DESHYDRATED
		&& !(terrain.isCurrentlyUsedForAnotherTask())) {
			Messagerie.getInstance().addMessage(new Message(BasicMessage.FIELD_DESHYDRATE, clock.getHour().getValue(), clock.getMinute().getValue()));
			terrain.setEtatSante(EtatSante.GRAVEMENT_MALADE);
			terrain.getProduction().clear();
			terrain.setEvolution(EvolutionTerrain.POURRI);
		}
	}

	private void manageFoodLevelInEnclosure(Enclos enclos) {

		int delayNourriture = enclos.getAnimals().size() != 0
				? GameConfiguration.FREQUENCE_DECREMENTATION_ENCLOS_NOURRITURE / enclos.getAnimals().size()
				: GameConfiguration.FREQUENCE_DECREMENTATION_ENCLOS_NOURRITURE;
		int dhourNourriture = clock.getMinute().getValue() - enclos.getLastDecrementationNourriture();
		if (dhourNourriture >= delayNourriture && enclos.getAnimals().size() != 0) {
			if (enclos.getNiveauNourriture() != FullLevel.EMPTY) {
				enclos.setNiveauNourriture(enclos.getNiveauNourriture().getNextState(clock, "nourriture"));
				enclos.setLastDecrementationNourriture(clock.getMinute().getValue());
			} else {
				if (enclos.getAnimalsHungerLevel() != HungerLevel.STARVING) {
					enclos.setAnimalsHungerLevel(enclos.getAnimalsHungerLevel().decrease_1());
					enclos.setLastDecrementationNourriture(clock.getMinute().getValue());
				} else {
					animalsToRemove.add(enclos.getAnimals().get(0)); // TODO on peut en tue plusieurs d'un coup
					enclos.setLastDecrementationNourriture(clock.getMinute().getValue());
				}
				// x = 0;
		}
			// if(dhour==(delay+x) && enclos.getAnimals().size()!=0 &&
			// enclos.getNiveauEau()==FullLevel.EMPTY) {
			// Message message = new Message("Plus d'eau dans
			// l'enclos",clock.getHour().getValue(), clock.getMinute().getValue());
			// Messagerie.getInstance().addMessage(message);
			// }
			// if(dhour==(delay+x) && enclos.getAnimals().size()!=0 &&
			// enclos.getNiveauNourriture()==FullLevel.EMPTY) {
			// Message message = new Message("Plus d'nourriture dans
			// l'enclos",clock.getHour().getValue(), clock.getMinute().getValue());
			// Messagerie.getInstance().addMessage(message);
			// }
			// if(dhour==(delay+x) && enclos.getAnimals().size()!=0 &&
			// enclos.getAnimalsHungerLevel()!= HungerLevel.VERY_HUNGRY) {
			// Message message = new Message("les animaux vont biontôt
			// mourir",clock.getHour().getValue(), clock.getMinute().getValue());
			// Messagerie.getInstance().addMessage(message);
			// }
		}
	}

	private void manageWaterLevelInEnclosure(Enclos enclos) {
		int delayEau = enclos.getAnimals().size() != 0
				? GameConfiguration.FREQUENCE_DECREMENTATION_ENCLOS_EAU / enclos.getAnimals().size()
				: GameConfiguration.FREQUENCE_DECREMENTATION_ENCLOS_EAU;
		int dhourEau = clock.getMinute().getValue() - enclos.getLastDecrementationEau();
		if (dhourEau >= delayEau && enclos.getAnimals().size() != 0) {
			if (enclos.getNiveauEau() != FullLevel.EMPTY) {
				enclos.setNiveauEau(enclos.getNiveauEau().getNextState(clock, "eau"));
				enclos.setLastDecrementationEau(clock.getMinute().getValue());
			} else {
				if (enclos.getAnimalsHydrationLevel() != HydrationLevel.DESHYDRATED) {
					enclos.setAnimalHydrationLevel(enclos.getAnimalsHydrationLevel().decrease());
					enclos.setLastDecrementationEau(clock.getMinute().getValue());
				} else {
					animalsToRemove.add(enclos.getAnimals().get(0)); // TODO on peut en tue plusieurs d'un coup
					enclos.setLastDecrementationEau(clock.getMinute().getValue());
				}
				// x = 0;
			}
			// if(dhour==(delay+x) && enclos.getAnimals().size()!=0 &&
			// enclos.getNiveauEau()==FullLevel.EMPTY) {
			// Message message = new Message("Plus d'eau dans
			// l'enclos",clock.getHour().getValue(), clock.getMinute().getValue());
			// Messagerie.getInstance().addMessage(message);
			// }
			// if(dhour==(delay+x) && enclos.getAnimals().size()!=0 &&
			// enclos.getNiveauNourriture()==FullLevel.EMPTY) {
			// Message message = new Message("Plus d'nourriture dans
			// l'enclos",clock.getHour().getValue(), clock.getMinute().getValue());
			// Messagerie.getInstance().addMessage(message);
			// }
			// if(dhour==(delay+x) && enclos.getAnimals().size()!=0 &&
			// enclos.getAnimalsHungerLevel()!= HungerLevel.VERY_HUNGRY) {
			// Message message = new Message("les animaux vont biontôt
			// mourir",clock.getHour().getValue(), clock.getMinute().getValue());
			// Messagerie.getInstance().addMessage(message);
			// }
			// x++;
		}
	}

	public void enclosEvolution() {
		for (Enclos enclos : elementManager.getMapManager().getEnclosOnMap()) {
			manageFoodLevelInEnclosure(enclos);
			manageWaterLevelInEnclosure(enclos);
		}
		killAnimalToRemove();
	}

	private void killAnimalToRemove() {
		int i = 0;
		while (i < animalsToRemove.size()) {
			if (deathIndex == 5) {
				animalEvolution.killAnimal(animalsToRemove.get(0));
				Message message = new Message(
						"Un(e) " + animalsToRemove.get(0).getClass().getSimpleName() + " est mort \nde faim",
						Clock.getInstance().getHour().getValue(), Clock.getInstance().getMinute().getValue());
				Messagerie.getInstance().addMessage(message);
				animalsToRemove.remove(0);
				i = 0;
				deathIndex = 0;
			} else {
				deathIndex++;
				i++;
				String imagePath = GameConfiguration.IMAGE_PATH + "nuage" + deathIndex + ".png";
				animalsToRemove.get(0).setImage(imagePath);
				BufferedImage image;
			}
		}
	}

}
