package process.action.place.action;

import java.util.Iterator;

import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.structure.Abatoire;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.SalleDeTraite;
import process.action.place.PlaceVisitor;
import process.action.place.UnableToPerformSuchActionWithCurrentActionnable;

public class FeedVisitor implements PlaceVisitor<Void>{

    @Override
    public Void action(Etable etable) {
        Iterator<Vache> vacheIt = etable.getAnimals().iterator();
        Vache currentVache;
        while(vacheIt.hasNext()){
            currentVache =  vacheIt.next();
            if(currentVache.isHungry()){
                currentVache.feed();
            }
        }
        return null;
    }

    @Override
    public Void action(Poulallier poulallier) {
        Iterator<Poule> pouleIt = poulallier.getAnimals().iterator();
        Poule currentPoule;
        while(pouleIt.hasNext()){
            currentPoule =  pouleIt.next();
            if(currentPoule.isHungry()){
                currentPoule.feed();
            }
        }
        return null;
    }

    @Override
    public Void action(Enclos enclos){
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
    
}
