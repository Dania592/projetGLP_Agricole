package process.action.visitor.place.transfert;

import java.util.Iterator;

import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.flore.terrains.Terrain;
import data.structure.Abatoire;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.SalleDeTraite;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.DomesticSpeciesHomeSender;
import process.action.visitor.being.HaveNotProducedYetException;
import process.action.visitor.place.PlaceVisitor;

public class HomeSenderVisitor implements PlaceVisitor<Void>{
    DomesticSpeciesHomeSender homeSender;

    public HomeSenderVisitor(DomesticSpeciesHomeSender homeSender) {
        this.homeSender = homeSender;
    }

    @Override
    public Void action(Etable etable) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(etable);
    }

    @Override
    public Void action(Poulallier poulallier) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(poulallier);
    }

    @Override
    public Void action(Enclos enclos) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException {
//        Iterator<Poule> pouleIter = enclos.getAnimalStorage().getPoules().iterator();
//        Iterator<Vache> vacheIter = enclos.getAnimalStorage().getVaches().iterator();
//        Iterator<Mouton> moutonIter = enclos.getAnimalStorage().getMoutons().iterator();
//        while(pouleIter.hasNext()){
//            pouleIter.next().launchAction(homeSender);
//        }
//        while(vacheIter.hasNext()){
//            vacheIter.next().launchAction(homeSender);
//        }
//        while(moutonIter.hasNext()){
//            moutonIter.next().launchAction(homeSender);
//        }
        throw new UnableToPerformSuchActionWithCurrentActionnable(enclos);

    }

    @Override
    public Void action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(abatoire);
    }

    @Override
    public Void action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(maison);
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(salleDeTraite);
    }

    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(entrepot); 
    }

    @Override
    public Void action(Terrain terrain)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        throw new UnableToPerformSuchActionWithCurrentActionnable(terrain); 
    } 

}
