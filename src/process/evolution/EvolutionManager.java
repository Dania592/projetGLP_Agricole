package process.evolution;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import data.configuration.GameConfiguration;
import data.espece.characteristic.FoodConsumer.HungerLevel;
import data.espece.characteristic.Produceur.ProductifState;
import data.espece.characteristic.Produceur.Type;
import data.espece.characteristic.WaterConsumer.HydrationLevel;
import data.espece.faune.Animal;
import data.espece.faune.AnimalProducteur;
import data.espece.faune.Chevre;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.espece.flore.terrains.Terrain;
import data.gestion.GestionnaireAnimaux;
import data.map.Case;
import data.map.Map;
import data.notification.Message;
import data.notification.Messagerie;
import data.notion.Mortel.EtatSante;
import data.notion.basic.Element;
import data.notion.evolution.EvolutionAnimal;
import data.notion.evolution.EvolutionTerrain;
import data.structure.Enclos;
import data.time.Clock;
import data.time.CyclicCounter;
import gui.gestionnaire.keys.Animals;
import process.game.ElementManager;
import process.production.ProductionManager;
import process.production.ProductionPerformer;

public class EvolutionManager implements Serializable {

	private ProductionPerformer productionPerformer = new ProductionPerformer();
	private AnimalEvolution animalEvolution ;
	private ElementManager elementManager ;
	private Clock clock;
	private int deathIndex = 0;
	private ArrayList<AnimalProducteur> animalsToRemove = new ArrayList<>();

	public EvolutionManager(ElementManager elementManager , Clock clock) {
		this.elementManager=elementManager;
		this.clock=clock;
		animalEvolution = new AnimalEvolution(elementManager , clock);

	}
	
	public void setClock(Clock clock) {
		this.clock=clock;
	}

	public void UpdateEvolution() {
		animalEvolution.evoluate();
		terrainEvolution();
		enclosEvolution();
	}


	public void terrainEvolution() {
		Terrain terrain;
		for (Element element : elementManager.getMapManager().getElements().values()) {
			if(element instanceof Terrain) {
				// ((Terrain)element).evoluer();
				terrain = (Terrain)element;
				manageEvolutionTerrain(terrain);
			}

		}
	}

	private void manageEvolutionTerrain(Terrain terrain) {
		if(terrain.getEvolution() != EvolutionTerrain.VIERGE && terrain.getEvolution() != EvolutionTerrain.LABOURE
				&& terrain.getEvolution() != EvolutionTerrain.POURRI) {
			if (!(ProductionManager.getInstance().getProductifList().contains(terrain))) {
				ProductionManager.getInstance().addToProductifList(terrain);
			}
			if(!(terrain.isCurrentlyUsedForAnotherTask())){
				CyclicCounter hydrationCounter = terrain.getHydrationCounter();
				hydrationCounter.increment();
				if(hydrationCounter.getValue() == 0 &&(terrain.getEvolution()!=EvolutionTerrain.VIERGE )) {
					updateHealthState(terrain);
				}if(haveToUpdateProducingStateOfCurrentlyUnabledProduceur(terrain)){
					terrain.setProductifState(ProductifState.PRODUCING);
				}

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
			Messagerie.getInstance().addMessage(new Message("A besoin d'eau", clock.getHour().getValue(), clock.getMinute().getValue()));
			terrain.setEtatSante(EtatSante.MALADE);
		}else if (terrain.getHydrationLevel() == HydrationLevel.DESHYDRATED) {
			Messagerie.getInstance().addMessage(new Message(" Est deshydraté", clock.getHour().getValue(), clock.getMinute().getValue()));
			terrain.setEtatSante(EtatSante.GRAVEMENT_MALADE);
		}else if(terrain.getHydrationLevel() == HydrationLevel.DEAD_FROM_DESHYDRATION){
			terrain.getProduction().clear();
			terrain.setEvolution(EvolutionTerrain.POURRI);
			terrain.setProductifState(ProductifState.UNABLE_TO_PRODUCE);
		}
	}

	private void manageFoodLevelInEnclosure(Enclos enclos){
	
		int delayNourriture = enclos.getAnimals().size()!=0 ? GameConfiguration.FREQUENCE_DECREMENTATION_ENCLOS_NOURRITURE/enclos.getAnimals().size() : GameConfiguration.FREQUENCE_DECREMENTATION_ENCLOS_NOURRITURE;
		int dhourNourriture = clock.getMinute().getValue()- enclos.getLastDecrementationNourriture();
		if(dhourNourriture >= delayNourriture && enclos.getAnimals().size()!=0) {
			if(enclos.getNiveauNourriture()!=FullLevel.EMPTY) {
				enclos.setNiveauNourriture(enclos.getNiveauNourriture().getNextState(clock, "nourriture"));
				enclos.setLastDecrementationNourriture( clock.getMinute().getValue());
			}
			else {
				if(enclos.getAnimalsHungerLevel()!= HungerLevel.STARVING) {
					enclos.setAnimalsHungerLevel(enclos.getAnimalsHungerLevel().decrease_1());
					enclos.setLastDecrementationNourriture( clock.getMinute().getValue());
				}
				else {
					animalsToRemove.add(enclos.getAnimals().get(0)); //TODO on peut en tue plusieurs d'un coup
					enclos.setLastDecrementationNourriture(clock.getMinute().getValue());
				}
				// x = 0;
			}
			// if(dhour==(delay+x) && enclos.getAnimals().size()!=0 && enclos.getNiveauEau()==FullLevel.EMPTY) {
			// 	Message message = new Message("Plus d'eau dans l'enclos",clock.getHour().getValue(), clock.getMinute().getValue());
			// 	Messagerie.getInstance().addMessage(message);
			// }
			// if(dhour==(delay+x) && enclos.getAnimals().size()!=0 && enclos.getNiveauNourriture()==FullLevel.EMPTY) {
			// 	Message message = new Message("Plus d'nourriture dans l'enclos",clock.getHour().getValue(), clock.getMinute().getValue());
			// 	Messagerie.getInstance().addMessage(message);
			// }
			// if(dhour==(delay+x) && enclos.getAnimals().size()!=0 && enclos.getAnimalsHungerLevel()!= HungerLevel.VERY_HUNGRY) {
			// 	Message message = new Message("les animaux vont biontôt mourir",clock.getHour().getValue(), clock.getMinute().getValue());
			// 	Messagerie.getInstance().addMessage(message);
			// }
		}
	}
	
	
	public void animalReproduction(Enclos enclos ) {
		int delay = Clock.getInstance().getMinute().getValue() - enclos.getLastBirth() ;
		if(delay >= GameConfiguration.FREQUENCE_ANIMAL_BIRTH_ENCLOS && enclos.getAnimals().size()>0) {
			Random newBirth = new Random();
			Animals target =null;
			Animal baby = null ;
			if(newBirth.nextBoolean() && birthConditions(enclos)) {
				int indice_animal = (int)(Math.random() * (4));
				switch (indice_animal) {
				case 0:					
					if(enclos.getAnimalStorage().getVaches().size()>= 2) {
						int nbAdult = 0;
						for(Vache vache :enclos.getAnimalStorage().getVaches()) {
							if(vache.getEvolution()== EvolutionAnimal.ADULTE) {
								nbAdult++;
							}
						}
						if(nbAdult>=2) {
							baby = new Vache(Clock.getInstance().getMinute().getValue(),null, null, null, ""+enclos.getAnimals().size());
							target =  Animals.VACHE ;
							
						}
					}
					break;				
				case 1 :
					if(enclos.getAnimalStorage().getChevres().size()>= 2) {
						int nbAdult=0;
						for(Chevre chevre :enclos.getAnimalStorage().getChevres()) {
							if(chevre.getEvolution()== EvolutionAnimal.ADULTE) {
								nbAdult++;
							}
						}
						if(nbAdult>=2) {
							target = Animals.CHEVRE;
							baby = new Chevre(Clock.getInstance().getMinute().getValue(),null, null, null, ""+enclos.getAnimals().size());							
						}
					}
					break;
				case 2:
					if(enclos.getAnimalStorage().getMoutons().size()>= 2) {
						int nbAdult=0;
						for(Mouton mouton :enclos.getAnimalStorage().getMoutons()) {
							if(mouton.getEvolution()== EvolutionAnimal.ADULTE) {
								nbAdult++;
							}
						}
						if(nbAdult>=2) {
							baby = new Mouton(Clock.getInstance().getMinute().getValue(),null, null, null, ""+enclos.getAnimals().size());
							target = Animals.MOUTON ;							
						}
					}
					break;
				case 3 :
					if(enclos.getAnimalStorage().getPoules().size()>= 2) {
						int nbAdult =0;
						for(Poule poule :enclos.getAnimalStorage().getPoules()) {
							if(poule.getEvolution()== EvolutionAnimal.ADULTE) {
								nbAdult++;
							}
						}
						if(nbAdult>=2) {
							target = Animals.POULE ;
							baby = new Poule(Clock.getInstance().getMinute().getValue(), null, null, null, ""+enclos.getAnimals().size());							
						}
					}
					break;
				default:
					target = null ;
				}
				
				if(target!=null) {	
					
					
					Case randomCase = randomPosition(baby , enclos);
					baby.setPosition(randomCase.getLigne(), randomCase.getColonne());		
					enclos.addAnimal(baby);
					elementManager.add(baby);
					GestionnaireAnimaux.getInstance().getAnimaux().get(target).add(baby);
					Message message = new Message("Un "+target+" est né",Clock.getInstance().getHour().getValue(), Clock.getInstance().getMinute().getValue());
					Messagerie.getInstance().addMessage(message);
				
				}
				
			}
			
			enclos.setLastBirth(Clock.getInstance().getMinute().getValue());
		}
	}
	
	public boolean birthConditions(Enclos enclos) {
		return enclos.getAnimalsHungerLevel()==HungerLevel.FULL 
				&& enclos.getAnimalsHydrationLevel()==HydrationLevel.FULLY_HYDRATED 
				&& enclos.getCapacite()>enclos.getAnimals().size();				
	}

	private void manageWaterLevelInEnclosure(Enclos enclos){
		int delayEau = enclos.getAnimals().size()!=0 ? GameConfiguration.FREQUENCE_DECREMENTATION_ENCLOS_EAU/enclos.getAnimals().size() : GameConfiguration.FREQUENCE_DECREMENTATION_ENCLOS_EAU;
		int dhourEau = clock.getMinute().getValue()- enclos.getLastDecrementationEau();
		if(dhourEau >= delayEau && enclos.getAnimals().size()!=0) {
			if(enclos.getNiveauEau()!=FullLevel.EMPTY) {
				enclos.setNiveauEau(enclos.getNiveauEau().getNextState(clock, "eau"));
				enclos.setLastDecrementationEau(clock.getMinute().getValue());
			}
			else {
				if(enclos.getAnimalsHydrationLevel() != HydrationLevel.DESHYDRATED) {
					enclos.setAnimalHydrationLevel(enclos.getAnimalsHydrationLevel().decrease());
					enclos.setLastDecrementationEau(clock.getMinute().getValue());
				}
				else {
					animalsToRemove.add(enclos.getAnimals().get(0)); //TODO on peut en tue plusieurs d'un coup
					enclos.setLastDecrementationEau(clock.getMinute().getValue());
				}
				// x = 0;
			}
			// if(dhour==(delay+x) && enclos.getAnimals().size()!=0 && enclos.getNiveauEau()==FullLevel.EMPTY) {
			// 	Message message = new Message("Plus d'eau dans l'enclos",clock.getHour().getValue(), clock.getMinute().getValue());
			// 	Messagerie.getInstance().addMessage(message);
			// }
			// if(dhour==(delay+x) && enclos.getAnimals().size()!=0 && enclos.getNiveauNourriture()==FullLevel.EMPTY) {
			// 	Message message = new Message("Plus d'nourriture dans l'enclos",clock.getHour().getValue(), clock.getMinute().getValue());
			// 	Messagerie.getInstance().addMessage(message);
			// }
			// if(dhour==(delay+x) && enclos.getAnimals().size()!=0 && enclos.getAnimalsHungerLevel()!= HungerLevel.VERY_HUNGRY) {
			// 	Message message = new Message("les animaux vont biontôt mourir",clock.getHour().getValue(), clock.getMinute().getValue());
			// 	Messagerie.getInstance().addMessage(message);
			// }
			// x++;
		}
	}




	public void enclosEvolution() {
		for(Enclos enclos : elementManager.getMapManager().getEnclosOnMap()) {
			manageFoodLevelInEnclosure(enclos);
			manageWaterLevelInEnclosure(enclos);
		}
		killAnimalToRemove();
	}


	private void killAnimalToRemove(){
		int i = 0;
		while(i < animalsToRemove.size()) {
			if(deathIndex == 5) {
				animalEvolution.killAnimal(animalsToRemove.get(0));
				Message message = new Message("Un(e) "+animalsToRemove.get(0).getClass().getSimpleName()+" est mort \nde faim" , Clock.getInstance().getHour().getValue() , Clock.getInstance().getMinute().getValue());
				Messagerie.getInstance().addMessage(message);
				animalsToRemove.remove(0);
				i = 0;
				deathIndex = 0;
			}
			else {
				deathIndex ++;
				i++;
				String imagePath =GameConfiguration.IMAGE_PATH +"nuage"+deathIndex+".png";
				animalsToRemove.get(0).setImage(imagePath);
				BufferedImage image;		
			}
		}
	}
	
	public Case randomPosition(Element element ,Enclos enclos ) {
        Case block = new Case(true , 0 , 0);
        Boolean libre = false ;
        while( !libre) {
            int ligneAleatoire =  enclos.getPosition().getLigne_init() + (int)(Math.random() * (enclos.getDimension()-2));
            int colonneAleatoire = enclos.getPosition().getColonne_init() + (int)(Math.random() * (enclos.getDimension()-2));
            block = new Case(true, ligneAleatoire, colonneAleatoire);
           libre = Map.getInstance().getCase(ligneAleatoire, colonneAleatoire).isLibre();
        }
        return block;
    }

}
