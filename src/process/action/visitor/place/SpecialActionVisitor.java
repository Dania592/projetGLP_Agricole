package process.action.visitor.place;

import java.util.ArrayList;
import java.util.Iterator;

import data.espece.characteristic.MilkProduceur;
import data.espece.characteristic.Slaughtable;
import data.espece.characteristic.Produceur.ProductifState;
import data.espece.faune.Animal;
import data.espece.faune.Mouton;
import data.espece.flore.terrains.Terrain;
import data.gestion.GestionnaireAnimaux;
import data.gestion.GestionnaireStocks;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.notion.evolution.EvolutionTerrain;
import data.planning.Activity;
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
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
import process.production.ProductionPerformer;


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
           
            if(currentMouton.getProductifState()==ProductifState.IN_WAIT){
                currentMouton.setProductifState(ProductifState.HAVE_PRODUCE);
                try {
                    Produits production = currentMouton.launchAction(productionPerformer.getProducer());
                    GestionnaireStocks.getInstance().add(production, currentMouton.getProcuedQuantity());
                    currentMouton.setProductifState(ProductifState.PRODUCING);
                    currentMouton.getProductionCycle().reset();
                } catch (HaveNotProducedYetException | BeingCannotPerformSuchActionException
                        | NeedToBeSendToSpecialProductionPlaceException | ProblemOccursInProductionException
                        | UnableToMakeTheTransfertException e) {
                }
            }
        }
        return null;
    }

    @Override
    public Void action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable {
        Iterator<Slaughtable> slaughtableIter = abatoire.getAnimaltoSlaughter().iterator();
        Slaughtable currentSlaughtable;
        while(slaughtableIter.hasNext()){
            currentSlaughtable = slaughtableIter.next();
            GestionnaireStocks.getInstance().add(Produits.VIANDE, 1);
            GestionnaireAnimaux.getInstance().remove((Animal)currentSlaughtable);
        }
        abatoire.getAnimaltoSlaughter().clear();
        return null;
    }

    @Override
    public Void action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable {
        Iterator<MilkProduceur> milkProduceurIter = salleDeTraite.getMilkProduceur().iterator();
        MilkProduceur milkProduceur;
        while(milkProduceurIter.hasNext()){
            milkProduceur = milkProduceurIter.next();
            if(milkProduceur.getProductifState() == ProductifState.HAVE_PRODUCE){
                try {
                    Produits production = milkProduceur.launchAction(productionPerformer.getProducer());
                    GestionnaireStocks.getInstance().add(production, 1);
                    milkProduceur.setProductifState(ProductifState.PRODUCING);
                    milkProduceur.getProductionCycle().reset();
                } catch (HaveNotProducedYetException | BeingCannotPerformSuchActionException
                        | NeedToBeSendToSpecialProductionPlaceException | ProblemOccursInProductionException
                        | UnableToMakeTheTransfertException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(Terrain terrain) throws UnableToPerformSuchActionWithCurrentActionnable, ProblemOccursInProductionException {
        switch(terrain.getEvolution()){
            case VIERGE : 
                terrain.setEvolution(EvolutionTerrain.LABOURE);
                break;
            case POURRI:
                terrain.setEvolution(EvolutionTerrain.VIERGE);
                break;
            case LABOURE : 
                throw new ProblemOccursInProductionException(terrain);
            default : 
                throw new UnableToPerformSuchActionWithCurrentActionnable(terrain);
        }
        return null;
    }

    @Override
    public Void action(BergerieChevre bergerieChevre) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException {
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(BergerieMouton bergerieMouton) throws UnableToPerformSuchActionWithCurrentActionnable,
     UnableToMakeTheTransfertException {
        throw new UnableToPerformSuchActionWithCurrentActionnable();
    }

    @Override
    public Void action(Puit puit) throws UnableToPerformSuchActionWithCurrentActionnable {
        int capaciteSeau =  puit.getSeau().getCapacite();
        Integer alreadyCollectedWater = GestionnaireStocks.getInstance().getProduits().get(Produits.WATER) == null?0: GestionnaireStocks.getInstance().getProduits().get(Produits.WATER); 
        if(alreadyCollectedWater>0){
            GestionnaireStocks.getInstance().getProduits().replace(Produits.WATER, alreadyCollectedWater+capaciteSeau);
        }else{
            GestionnaireStocks.getInstance().getProduits().put(Produits.WATER, capaciteSeau);
        }
    
        puit.setQuantite(puit.getQuantite()-1);
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
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException {
        return action(enclos);
    }

    @Override
    public Void action(Abatoire abatoire, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable{
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
            throws UnableToPerformSuchActionWithCurrentActionnable,ProblemOccursInProductionException {
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

    @Override
    public Void action(Terrain terrain, Activity activity, Graine graine)
            throws UnableToPerformSuchActionWithCurrentActionnable,
            UnableToGenerateNewTaskException, ProblemOccursInProductionException {
            switch(terrain.getEvolution()){
                case LABOURE : 
                    if(graine == null){
                        graine = Graine.GRAINE_TOMATE;
                    }
                    terrain.setType(graine);
                    terrain.setEvolution(EvolutionTerrain.PLANTE);
                    terrain.setProductifState(ProductifState.PRODUCING);
                    Integer oldQauntity = GestionnaireStocks.getInstance().getGraines().get(graine);
                    GestionnaireStocks.getInstance().getGraines().replace(graine, oldQauntity, oldQauntity-1);
                    break;
                case VIERGE : 
                case POURRI:
                    action(terrain);
                    break;
                default : 
                    throw new UnableToPerformSuchActionWithCurrentActionnable(terrain);
            }
        return null;
        
    }

    
}
