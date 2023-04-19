package process.action.visitor.place;

import data.flore.terrains.Terrain;
import data.espece.FoodConsumer.HungerLevel;
import data.flore.terrains.EvolutionTerrain;
// import java.util.Iterator;

// import data.espece.faune.Poule;
// import data.espece.faune.Vache;
import data.structure.Abatoire;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.SalleDeTraite;
import process.action.exception.NotImplementYetException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.evolution.FullLevel;

public class FeedVisitor implements PlaceVisitor<Void>{
    @Override
    public Void action(Etable etable) {
        System.out.println("Noussissant les vaches de mon étable "+etable);
        return null;
    }    

    @Override
    public Void action(Poulallier poulallier) {

        return null;
    }

    @Override
    public Void action(Enclos enclos){
    	enclos.setNiveauEau(FullLevel.FULL);
    	enclos.setNiveauNourriture(FullLevel.FULL);
    	enclos.setAnimalsHungerLevel(HungerLevel.FULL);
        System.out.println("Nourissant les animaux de mon enclos "+ enclos);
        return null;
    }

    @Override
    public Void action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(entrepot);
    }

    @Override
    public Void action(Terrain terrain) throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        if(canFeedTerrain(terrain)){
            System.out.println("Nourissant les animaux de mon enclos "+ terrain);
        }
        return null;
    }

    private boolean canFeedTerrain(Terrain terrain){
        return terrain.getEvolution() == EvolutionTerrain.PLANTE|
            terrain.getEvolution() == EvolutionTerrain.PLANTE_1|
            terrain.getEvolution() == EvolutionTerrain.PLANTE_2|
            terrain.getEvolution() == EvolutionTerrain.PLANTE_3|
            terrain.getEvolution() == EvolutionTerrain.PLANTE_4;
    }

}
