package process.action.place.action;

import data.structure.Abatoire;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.SalleDeTraite;
import data.structure.hability.Fixable.FixableState;
import process.action.place.PlaceVisitor;
import process.action.place.UnableToPerformSuchActionWithCurrentActionnable;

public class FixVisitor implements PlaceVisitor<Void> {

    @Override
    public Void action(Etable etable) {
        etable.setState(FixableState.USABLE); 
        return null;
    }

    @Override
    public Void action(Poulallier poulallier) {
        poulallier.setState(FixableState.USABLE); 
        return null;
    }

    @Override
    public Void action(Enclos enclos) {
        enclos.setState(FixableState.USABLE); 
        return null;
    }

    @Override
    public Void action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable {
        abatoire.setState(FixableState.USABLE); 
        return null;
    }

    @Override
    public Void action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable {
        maison.setState(FixableState.USABLE); 
        return null;
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable {
        salleDeTraite.setState(FixableState.USABLE); 
        return null;
    }

    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        entrepot.setState(FixableState.USABLE); 
        return null;
    }
    
}
