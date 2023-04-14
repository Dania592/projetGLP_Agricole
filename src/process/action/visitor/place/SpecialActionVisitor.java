package process.action.visitor.place;

import data.flore.terrains.Terrain;
import data.structure.Abatoire;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.SalleDeTraite;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.exception.NotImplementYetException;
public class SpecialActionVisitor implements PlaceVisitor<Void> {

    @Override
    public Void action(Etable etable) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(Poulallier poulallier) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable();    }

    @Override
    public Void action(Enclos enclos) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable();    }

    @Override
    public Void action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable {
        System.out.println("En train d'abbatre les différents animaux");
        return null;
    }

    @Override
    public Void action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable {
        System.out.println("En train de traire les vaches");
        return null;
    }

    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(Terrain terrain) throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        throw new NotImplementYetException();
        // switch(terrain.getEvolution()){
        //     case VIERGE : 
        //         System.out.println("On laboure");
        //         break;
        //     case LABOURE : 
        //         System.out.println("On plante");
        //         break;
        //     default : 
        //         throw new UnableToPerformSuchActionWithCurrentActionnable(terrain);
        // }
        // return null;
    }

}
