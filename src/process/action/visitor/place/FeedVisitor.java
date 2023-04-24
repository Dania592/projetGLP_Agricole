package process.action.visitor.place;

import data.flore.terrains.Terrain;

import java.time.Clock;

import data.espece.FoodConsumer.HungerLevel;
import data.flore.terrains.EvolutionTerrain;
// import java.util.Iterator;

// import data.espece.faune.Poule;
// import data.espece.faune.Vache;
import data.structure.Abatoire;
import data.structure.BergerieChevre;
import data.structure.BergerieMouton;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Garage;
import data.structure.Grange;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.Puit;
import data.structure.SalleDeTraite;
import process.action.exception.NotImplementYetException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.evolution.FullLevel;

public class FeedVisitor implements PlaceVisitor<Void>{
    @Override
    public Void action(Etable etable) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(etable);
    }    

    @Override
    public Void action(Poulallier poulallier) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(poulallier);
    }

    @Override
    public Void action(Enclos enclos){
    	enclos.setNiveauNourriture(FullLevel.FULL);
    	enclos.setAnimalsHungerLevel(HungerLevel.FULL);
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
    public Void action(Terrain terrain) throws UnableToPerformSuchActionWithCurrentActionnable{
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(BergerieChevre bergerieChevre) throws UnableToPerformSuchActionWithCurrentActionnable,
            NotImplementYetException, UnableToMakeTheTransfertException {
        throw new UnableToPerformSuchActionWithCurrentActionnable();

    }

    @Override
    public Void action(BergerieMouton bergerieMouton) throws UnableToPerformSuchActionWithCurrentActionnable,
            NotImplementYetException, UnableToMakeTheTransfertException {
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(Puit puit) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(puit);
    }

    @Override
    public Void action(Garage garage) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(garage);
    }

    @Override
    public Void action(Grange grange) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(grange);
    }

}
