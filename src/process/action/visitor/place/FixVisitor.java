package process.action.visitor.place;

import data.espece.flore.terrains.Terrain;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.notion.Mortel.EtatSante;
import data.notion.evolution.EvolutionTerrain;
import data.planning.Activity;
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
import data.structure.hability.Fixable;
import data.structure.hability.Fixable.FixableState;
import gui.gestionnaire.keys.Graine;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;

public class FixVisitor implements PlaceVisitor<Void> {

    private Void fixStructure(Fixable fixable) {
        if (fixable.isNeedToBeFixed()) {
            fixable.setState(FixableState.USABLE);
        }
        return null;
    }

    @Override
    public Void action(Etable etable) {
        return fixStructure(etable);
    }

    @Override
    public Void action(Poulallier poulallier) {
        return fixStructure(poulallier);
    }

    @Override
    public Void action(Enclos enclos) {
        return fixStructure(enclos);
    }

    @Override
    public Void action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable {
        return fixStructure(abatoire);
    }

    @Override
    public Void action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable {
        return fixStructure(maison);
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable {
        return fixStructure(salleDeTraite);
    }

    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        return fixStructure(entrepot);
    }

    @Override
    public Void action(Terrain terrain) throws UnableToPerformSuchActionWithCurrentActionnable {
        fixStructure(terrain);
        terrain.setEvolution(EvolutionTerrain.VIERGE);
        terrain.setEtatSante(EtatSante.BONNE_SANTE);;
        return null;
    }

    @Override
    public Void action(BergerieChevre bergerieChevre) throws UnableToPerformSuchActionWithCurrentActionnable, 
        UnableToMakeTheTransfertException {
        return fixStructure(bergerieChevre);
    }

    @Override
    public Void action(BergerieMouton bergerieMouton) throws UnableToPerformSuchActionWithCurrentActionnable,
        UnableToMakeTheTransfertException {
        return fixStructure(bergerieMouton);
    }

    @Override
    public Void action(Puit puit) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(puit);
    }

    @Override
    public Void action(Garage garage) throws UnableToPerformSuchActionWithCurrentActionnable {
        return fixStructure(garage);
    }

    @Override
    public Void action(Grange grange) throws UnableToPerformSuchActionWithCurrentActionnable {
        return fixStructure(grange);
    }


    @Override
    public Void action(Terrain terrain, Activity activity, Graine graine)
            throws UnableToPerformSuchActionWithCurrentActionnable,
            UnableToGenerateNewTaskException {
        return action(terrain);
    }

    @Override
    public Void action(Etable etable, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException {
        return action(etable);
    }

    @Override
    public Void action(Poulallier poulallier, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException {
        return action(poulallier);
    }

    @Override
    public Void action(Enclos enclos, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException{
        return action(enclos);
    }

    @Override
    public Void action(Abatoire abatoire, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable {
            return action(abatoire);
    }

    @Override
    public Void action(Maison maison, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(maison);
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable,
            UnableToMakeTheTransfertException {
        return action(salleDeTraite);
    }

    @Override
    public Void action(Entrepot entrepot, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(entrepot);
    }

    @Override
    public Void action(Terrain terrain, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable{
        return action(terrain);
    }

    @Override
    public Void action(BergerieChevre bergerieChevre, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable,
            UnableToMakeTheTransfertException {
            return action(bergerieChevre);
    }

    @Override
    public Void action(BergerieMouton bergerieMouton, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable,
            UnableToMakeTheTransfertException {
        return action(bergerieMouton);
    }

    @Override
    public Void action(Puit puit, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(puit);
    }

    @Override
    public Void action(Garage garage, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(garage);
    }

    @Override
    public Void action(Grange grange, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(grange);
    }

}
