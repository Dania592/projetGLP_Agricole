package process.action.visitor.place;

import java.util.HashMap;
// import java.util.Iterator;
import java.util.Iterator;
import java.util.Set;

import data.espece.Produceur;
import data.espece.Slaughtable;
    import data.espece.Produceur.ProductifState;
import data.espece.faune.AnimalProducteur;
import data.espece.faune.MilkProduceur;
import data.espece.faune.Poule;
import data.flore.terrains.EvolutionTerrain;
import data.flore.terrains.Terrain;
import data.production.Produits;
import data.production.exception.NonExistantProduceException;
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
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.ProduceVisitor;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.transfert.EnclosureSenderVisitor;

public class ProductionPerformer implements PlaceVisitor<Void>{
    ProduceVisitor producer = new ProduceVisitor();
    
    private <T extends AnimalProducteur> Void performProduction(ProductifPlace productifPlace, Iterator<T> produceurIter) throws NotImplementYetException{
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
    public Void action(Poulallier poulallier) throws BeingCannotPerformSuchActionException, NotImplementYetException {
        Iterator<Poule> pouleIter = poulallier.getInHabitant().iterator();
        return performProduction(poulallier, pouleIter);
    }

    @Override
    public Void action(Enclos enclos) throws NotImplementYetException {
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
            if(currentMilkProduceur.haveProduced()){
                addToProduction(salleDeTraite, currentMilkProduceur.collectProduction(), currentMilkProduceur.getProduceurType().getNumberOfProductPerProductifCycle());
                currentMilkProduceur.setProductifState(ProductifState.PRODUCING);
                
            }
        }
        return null;
    }

    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(entrepot);
    }

    @Override
    public Void action(Terrain terrain) throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        Produits production;
        if(terrain.canLaunchProduction()){
            try {
                production = terrain.launchAction(producer);
                addToProduction(terrain, production, terrain.getProduceurType().getNumberOfProductPerProductifCycle());
            } catch (HaveNotProducedYetException | BeingCannotPerformSuchActionException
                    | NeedToBeSendToSpecialProductionPlaceException | ProblemOccursInProductionException
                    | UnableToMakeTheTransfertException e) {
            }
        }
        return null;
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
    
    
}
