package process.action.visitor.place;

import data.flore.terrains.EvolutionTerrain;
import data.flore.terrains.Terrain;
import data.notion.Mortel.EtatSante;
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
import process.action.exception.NotImplementYetException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;

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
            NotImplementYetException, UnableToMakeTheTransfertException {
        return fixStructure(bergerieChevre);
    }

    @Override
    public Void action(BergerieMouton bergerieMouton) throws UnableToPerformSuchActionWithCurrentActionnable,
            NotImplementYetException, UnableToMakeTheTransfertException {
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

}
