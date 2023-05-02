package process.production;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

import data.espece.characteristic.MilkProduceur;
import data.espece.characteristic.Produceur;
import data.espece.characteristic.Slaughtable;
import data.espece.characteristic.Produceur.ProductifState;
import data.espece.characteristic.Produceur.Type;
import data.espece.faune.AnimalProducteur;
import data.espece.faune.Poule;
import data.espece.flore.terrains.Terrain;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.notion.Mortel.EtatSante;
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
import data.structure.hability.ProductifPlace;
import gui.gestionnaire.keys.Graine;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;

public class ProductionPerformer implements PlaceVisitor<Void>, Serializable{
    DomesticSpecieProductionVisitor producer = new DomesticSpecieProductionVisitor();
    
    private <T extends AnimalProducteur> Void performProduction(ProductifPlace productifPlace, Iterator<T> produceurIter){
        Produits production;
        Produceur currentProduceur;
        while(produceurIter.hasNext()){
            currentProduceur =  produceurIter.next(); 
            try {
                production = currentProduceur.launchAction(producer);
                addToProduction(productifPlace, production, currentProduceur.getProduceurType().getNumberOfProductPerProductifCycle());
            } catch (HaveNotProducedYetException  e) {
            } catch (BeingCannotPerformSuchActionException
                    | NeedToBeSendToSpecialProductionPlaceException | ProblemOccursInProductionException e) {
            } catch (UnableToMakeTheTransfertException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public void addToProduction(ProductifPlace productifPlace, Produits produit, int numberOfProducedProducts) {
        HashMap<Produits, Integer> production = productifPlace.getProduction();
		if(production.containsKey(produit)){
			int oldQuantity = production.get(produit); 
			production.replace(produit, oldQuantity+numberOfProducedProducts);
		}else{
			production.put(produit, numberOfProducedProducts);
		}
	}


    @Override
    public Void action(Etable etable) throws UnableToPerformSuchActionWithCurrentActionnable{
        throw new UnableToPerformSuchActionWithCurrentActionnable(etable);
    }
    
    @Override
    public Void action(Poulallier poulallier) throws BeingCannotPerformSuchActionException {
        Iterator<Poule> pouleIter = poulallier.getInHabitant().iterator();
        return performProduction(poulallier, pouleIter);
    }

    @Override
    public Void action(Enclos enclos){
        Iterator<AnimalProducteur> animalIterator = enclos.getAnimals().iterator();
        return performProduction(enclos, animalIterator);
    }

    @Override
    public Void action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable {
        Iterator<Slaughtable> slaughtablesIter = abatoire.getAnimaltoSlaughter().iterator(); 
        Slaughtable currentSlaughtable;
        while(slaughtablesIter.hasNext()){
            currentSlaughtable = slaughtablesIter.next();
            addToProduction(abatoire, currentSlaughtable.getEquivalentInMeat().getType(), 1);
        }
        abatoire.getAnimaltoSlaughter().clear();
        return null;

    }

    @Override
    public Void action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable{
        throw new UnableToPerformSuchActionWithCurrentActionnable(maison);
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable{
        Iterator<MilkProduceur> milkProduceurIter = salleDeTraite.getMilkProduceur().iterator();
        MilkProduceur currentMilkProduceur;
        while(milkProduceurIter.hasNext()){
            currentMilkProduceur = milkProduceurIter.next();
            currentMilkProduceur.setProductifState(ProductifState.PRODUCING);
            
        }
        return null;
    }

    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(entrepot);
    }

    @Override
    public Void action(Terrain terrain) throws UnableToPerformSuchActionWithCurrentActionnable {
        Produits production;
        if(terrain.canLaunchProduction()){
            try {
                production = terrain.launchAction(producer);
                addToProductionAccordingToProduceurType(terrain, production);
            } catch (HaveNotProducedYetException | BeingCannotPerformSuchActionException
                    | NeedToBeSendToSpecialProductionPlaceException | ProblemOccursInProductionException
                    | UnableToMakeTheTransfertException e) {
            }
        }
        return null;
    }

    private void addToProductionAccordingToProduceurType(Terrain terrain, Produits produit){
        Produceur.Type produceurType = terrain.getProduceurType();
        if(terrain.isDoped() && terrain.getEtatSante() == EtatSante.BONNE_SANTE){
            produceurType  = Type.DOPED_PRODUCEUR;
        }
        addToProduction(terrain, produit, produceurType.getNumberOfProductPerProductifCycle()*terrain.getProcuedQuantity());
    }


    @Override
    public Void action(Terrain terrain, Activity activity, Graine graine)
            throws UnableToPerformSuchActionWithCurrentActionnable,
            UnableToGenerateNewTaskException {
        return action(terrain, activity);
    }

    @Override
    public Void action(BergerieChevre bergerieChevre) throws UnableToPerformSuchActionWithCurrentActionnable,
     UnableToMakeTheTransfertException {
        throw new UnableToPerformSuchActionWithCurrentActionnable(bergerieChevre);
    }


    @Override
    public Void action(BergerieMouton bergerieMouton) throws UnableToPerformSuchActionWithCurrentActionnable,
     UnableToMakeTheTransfertException {
        throw new UnableToPerformSuchActionWithCurrentActionnable(bergerieMouton);
    }


    @Override
    public Void action(Puit puit) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(puit);
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
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException {
        return action(etable);
    }

    @Override
    public Void action(Poulallier poulallier, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException{
        return action(poulallier);
    }

    @Override
    public Void action(Enclos enclos, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException{
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
            throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException {
        return action(salleDeTraite);
    }

    @Override
    public Void action(Entrepot entrepot, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(entrepot);
    }

    @Override
    public Void action(Terrain terrain, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable {
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


    public DomesticSpecieProductionVisitor getProducer() {
        return producer;
    }

    
    
}
