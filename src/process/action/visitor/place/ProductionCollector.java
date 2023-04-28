package process.action.visitor.place;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import data.flore.terrains.EvolutionTerrain;
import data.flore.terrains.Terrain;
import data.gestion.GestionnaireStocks;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.Abatoire;
import data.structure.BergerieChevre;
import data.structure.BergerieMouton;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Garage;
import data.structure.Grange;
import data.production.Produits;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.Puit;
import data.structure.SalleDeTraite;
import data.structure.hability.ProductifPlace;
import gui.gestionnaire.keys.Graine;
import data.structure.hability.Actionnable.ActionnableKey;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;

public class ProductionCollector implements PlaceVisitor<Void> {

    public Void collectProduction(ProductifPlace productifPlace){
        if(haveProduced(productifPlace)){
            HashMap<Produits, Integer> production = productifPlace.getProduction();
            Set<Produits> productionsKey = production.keySet(); 
            Produits currentProduct;
            Iterator<Produits> produitsIter = productionsKey.iterator();
            while(produitsIter.hasNext()){
                currentProduct = produitsIter.next();
                GestionnaireStocks.getInstance().add(currentProduct, production.get(currentProduct));
            } 
            productifPlace.getProduction().clear();
        }
        return null;
    }


    public boolean haveProduced(ProductifPlace productifPlace){
        Iterator<Integer> productionIter = productifPlace.getProduction().values().iterator();
        Integer productionCounter = 0;
        if(productifPlace.getSpecificActionnableKey() == ActionnableKey.ENCLOS){
            if(productifPlace.getProduction().get(Produits.OEUF) != null){
                return productifPlace.getProduction().get(Produits.OEUF) != 0;
            }
            return false;
        }else{
            while(productionIter.hasNext()){
                productionCounter += productionIter.next();
            }
            return productionCounter != 0 ;
        }
    }

  


    @Override
    public Void action(Etable etable) throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        throw new UnableToPerformSuchActionWithCurrentActionnable(etable);
    }

    @Override
    public Void action(Poulallier poulallier) throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        return collectProduction(poulallier);
    }

    @Override
    public Void action(Enclos enclos) throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        return collectProduction(enclos);
    }

    @Override
    public Void action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException {
        return collectProduction(abatoire);
    }

    @Override
    public Void action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(maison);
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, HaveNotProducedYetException {
            return collectProduction(salleDeTraite); 
    }

    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(entrepot);
    }

    @Override
    public Void action(Terrain terrain)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        collectProduction(terrain);
        terrain.setEvolution(EvolutionTerrain.VIERGE);
        return null;
    }

    @Override
    public Void action(Terrain terrain, Activity activity, Graine graine)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
            UnableToGenerateNewTaskException {
        return action(terrain); 
    }


    @Override
    public Void action(BergerieChevre bergerieChevre) throws UnableToPerformSuchActionWithCurrentActionnable,
            NotImplementYetException, UnableToMakeTheTransfertException {
        throw new UnableToPerformSuchActionWithCurrentActionnable(bergerieChevre);
    }


    @Override
    public Void action(BergerieMouton bergerieMouton) throws UnableToPerformSuchActionWithCurrentActionnable,
            NotImplementYetException, UnableToMakeTheTransfertException {
        throw new UnableToPerformSuchActionWithCurrentActionnable(bergerieMouton);
    }


    @Override
    public Void action(Puit puit) throws UnableToPerformSuchActionWithCurrentActionnable {
        if(GestionnaireStocks.getInstance().getProduits().containsKey(Produits.WATER)){
        }
        collectProduction(puit); 
        if(GestionnaireStocks.getInstance().getProduits().containsKey(Produits.WATER)){
        }
        return null;
    }

    @Override
    public Void action(Garage garage) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(garage);
    }

    @Override
    public Void action(Grange grange) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(grange);
    }

    @Override
    public Void action(Etable etable, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException{
        return action(etable);
    }

    @Override
    public Void action(Poulallier poulallier, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException{
        return action(poulallier);
    }

    @Override
    public Void action(Enclos enclos, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException, UnableToMakeTheTransfertException, NotImplementYetException {
        return action(enclos);
    }

    @Override
    public Void action(Abatoire abatoire, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException {
            return action(abatoire);
    }

    @Override
    public Void action(Maison maison, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(maison);
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            UnableToMakeTheTransfertException {
        return action(salleDeTraite);
    }

    @Override
    public Void action(Entrepot entrepot, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(entrepot);
    }

    @Override
    public Void action(Terrain terrain, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        return action(terrain);
    }

    @Override
    public Void action(BergerieChevre bergerieChevre, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
            UnableToMakeTheTransfertException {
            return action(bergerieChevre);
    }

    @Override
    public Void action(BergerieMouton bergerieMouton, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
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
