package process.action.visitor.place;

import java.util.ArrayList;
import java.util.Iterator;

import data.espece.Produceur.ProductifState;
import data.espece.faune.Mouton;
import data.flore.terrains.EvolutionTerrain;
import data.flore.terrains.Terrain;
import data.gestion.GestionnaireStocks;
import data.production.Produit;
import data.production.Produits;
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
import gui.gestionnaire.keys.Graine;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.time.TimeManager;
import process.action.exception.NotImplementYetException;
public class SpecialActionVisitor implements PlaceVisitor<Void> {
    ProductionPerformer productionPerformer = new ProductionPerformer();

    @Override
    public Void action(Etable etable) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(Poulallier poulallier) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable();    
    }

    @Override
    public Void action(Enclos enclos) throws UnableToPerformSuchActionWithCurrentActionnable {
        Iterator<Mouton> moutonIter = enclos.getAnimalStorage().getMoutons().iterator();
        Mouton currentMouton;
        while(moutonIter.hasNext()){
            currentMouton = moutonIter.next();
            if(currentMouton.haveProduced()){
                productionPerformer.addToProduction(enclos, currentMouton.collectProduction(), currentMouton.getProduceurType().getNumberOfProductPerProductifCycle());
                currentMouton.setProductifState(ProductifState.PRODUCING);
                currentMouton.getProductionCycle().reset();
            }
        }
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
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(Terrain terrain) throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        switch(terrain.getEvolution()){
            case VIERGE : 
                terrain.setEvolution(EvolutionTerrain.LABOURE);
                break;
            case LABOURE : 
                terrain.setType(Graine.BLUEBERRY_SEED); 
                terrain.setEvolution(EvolutionTerrain.PLANTE);
                terrain.setProductifState(ProductifState.PRODUCING);
                break;
            case POURRI:
                terrain.setEvolution(EvolutionTerrain.VIERGE);
                
                break;
            default : 
                throw new UnableToPerformSuchActionWithCurrentActionnable(terrain);
        }
        return null;
    }


    public Void action(Terrain terrain, Graine type) throws UnableToPerformSuchActionWithCurrentActionnable{
        if(terrain.getEvolution() == EvolutionTerrain.LABOURE){
            terrain.setType(type); 
            terrain.setEvolution(EvolutionTerrain.PLANTE);
            terrain.setProductifState(ProductifState.PRODUCING);
        }else{
            throw new UnableToPerformSuchActionWithCurrentActionnable();
        }
        return null;
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
        if(puit.getProduction().containsKey(Produits.WATER)){
            System.out.println("Avant :"+puit.getProduction().get(Produits.WATER));
        }
        int capaciteSeau =  puit.getSeau().getCapacite();
        Integer alreadyCollectedWater = puit.getProduction().containsKey(Produits.WATER)? puit.getProduction().get(Produits.WATER) : 0;
        puit.setQuantite(puit.getQuantite()-1);
        puit.getProduction().put(Produits.WATER,alreadyCollectedWater+capaciteSeau);
        if(puit.getProduction().containsKey(Produits.WATER)){
            System.out.println("Après :"+puit.getProduction().get(Produits.WATER));
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

}
