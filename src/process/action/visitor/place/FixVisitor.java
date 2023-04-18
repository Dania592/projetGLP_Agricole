package process.action.visitor.place;

import data.flore.terrains.Terrain;
import data.structure.Abatoire;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.SalleDeTraite;
import data.structure.hability.Fixable;
import data.structure.hability.Fixable.FixableState;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;

public class FixVisitor implements PlaceVisitor<Void> {

    private Void fixStructure(Fixable fixable){
        if(fixable.getState() != FixableState.DESTROYED){
            fixable.setState(FixableState.USABLE);
        }
        return null;
    }


    @Override
    public Void action(Etable etable) {
        fixStructure(etable);
        return null;
    }

    @Override
    public Void action(Poulallier poulallier) {
        fixStructure(poulallier);
        return null;
    }

    @Override
    public Void action(Enclos enclos) {
        fixStructure(enclos);
        return null;
    }

    @Override
    public Void action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable {
        fixStructure(abatoire);
        return null;
    }

    @Override
    public Void action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable {
        fixStructure(maison);
        return null;
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable {
        fixStructure(salleDeTraite);
        return null;
    }

    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        fixStructure(entrepot);
        return null;
    }

    @Override
    public Void action(Terrain terrain) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(terrain);
    }

}
