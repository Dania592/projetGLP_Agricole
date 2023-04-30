package process.action.visitor.place;

import java.util.Iterator;

import data.espece.faune.Chevre;
import data.espece.faune.Mouton;
import data.espece.faune.Vache;
import data.espece.Produceur.ProductifState;
import data.espece.WaterConsumer.HydrationLevel;
import data.flore.terrains.EvolutionTerrain;
import data.flore.terrains.Terrain;
import data.gestion.GestionnaireStocks;
import data.myExceptions.UnableToGenerateNewTaskException;
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
import data.structure.hability.Actionnable;
import data.structure.hability.Hydratable;
import data.structure.hability.ProductifPlace;
import gui.gestionnaire.keys.Graine;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;

public class ConditionTester implements PlaceVisitor<Boolean>{

    private boolean needToFix(Actionnable actionnable){
        return actionnable.isNeedToBeFixed();
    }
    
    
    
    
    @Override
    public Boolean action(Poulallier poulallier)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException, NotImplementYetException {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Boolean action(Enclos enclos)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException, NotImplementYetException {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Boolean action(Abatoire abatoire)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Boolean action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Boolean action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable,
            NotImplementYetException, HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Boolean action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Boolean action(Terrain terrain)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Boolean action(BergerieChevre bergerieChevre) throws UnableToPerformSuchActionWithCurrentActionnable,
            NotImplementYetException, UnableToMakeTheTransfertException {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Boolean action(BergerieMouton bergerieMouton) throws UnableToPerformSuchActionWithCurrentActionnable,
            NotImplementYetException, UnableToMakeTheTransfertException {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Boolean action(Puit puit) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Boolean action(Garage garage) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Boolean action(Grange grange) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Boolean action(Etable etable)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    

    
    @Override
    public Boolean action(Terrain terrain, Activity activity, Graine graine)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
            UnableToGenerateNewTaskException {
        return action(terrain, activity);
    }




    @Override
    public Boolean action(Etable etable, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, NotImplementYetException{ 
            switch(activity){
                case FIX_STRUCTURE:
                    return needToFix(etable);
                case SEND_TO_ENCLOSURE:
                case SEND_TO_SEND_TO_SLAUGHTERHOUSE:
                case SEND_TO_MILKING_PARLOUR:
                    throw new NotImplementYetException(activity);
                default:            
                    throw new UnableToPerformSuchActionWithCurrentActionnable(activity, etable);
            }
    }

    @Override
    public Boolean action(Poulallier poulallier, Activity activity)
        throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException {
            switch(activity){
                case FIX_STRUCTURE:
                    return needToFix(poulallier);
                case COLLECT_EGG:
                case SEND_TO_ENCLOSURE:
                case SEND_TO_SEND_TO_SLAUGHTERHOUSE:
                    throw new NotImplementYetException(activity);
                default:            
                    throw new UnableToPerformSuchActionWithCurrentActionnable(activity, poulallier);
            }
    }

    @Override
    public Boolean action(Enclos enclos, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException {
        switch(activity){
            case FIX_ENCLOSURE:
                return needToFix(enclos);
            case FEED_ANIMAL_FROM_ENCLOSURE : 
                return enclos.isNeedToBeFeed();
            case COLLECT_EGG_FROM_ENCLOSURE : 
                return canCollectEgg(enclos);
            case GIVE_WATER_TO_ANIMAL : 
                return basicConditionForHydration(enclos);
            case SHAVE_SHEEP : 
                return canShaveSheep(enclos);
            case SEND_BACK_HOME_ANIMALS : 
            case TRANSFERT_TO_PRODUCTION_ROOM : 
                throw new NotImplementYetException(activity);
            default:            
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, enclos);
        }    
    }

    private boolean basicConditionForHydration(Hydratable hydratablePlace){
        return  hydratablePlace.isNeedToBeHydrated() &&  playerHaveWater();
    }

    private boolean playerHaveWater(){
        return GestionnaireStocks.getInstance().getProduits().get(Produits.WATER) != null;
    }

    private boolean canCollectEgg(Enclos enclos){
        if(enclos.getProduction().get(Produits.OEUF) != null){
            return enclos.getProduction().get(Produits.OEUF) != 0;
        }
        return false;
    }


    private boolean canShaveSheep(Enclos enclos){
        boolean haveProduced = false;
		Iterator<Mouton> moutons = enclos.getAnimalStorage().getMoutons().iterator();
			while(moutons.hasNext() && !haveProduced){
				haveProduced = moutons.next().getProductifState() == ProductifState.IN_WAIT;
			}
		return haveProduced;
    } 

    @Override
    public Boolean action(Abatoire abatoire, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException{
        switch(activity){
            case FIX_STRUCTURE:
                return needToFix(abatoire);
            case COLLECT_MEAT:
                return isInProductifPlaceProduction(abatoire, Produits.MEAT); 
            case SLAUGHTER:
                return isThereSlaughtableSpecies(abatoire);
            default:            
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, abatoire);
        }
    }

    private boolean isThereSlaughtableSpecies(Abatoire abatoire){
        return abatoire.getAnimaltoSlaughter().size()>0;
    }

    @Override
    public Boolean action(Maison maison, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        switch(activity){
            case FIX_STRUCTURE:
                return needToFix(maison);
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, maison);
        }
    }

    @Override
    public Boolean action(SalleDeTraite salleDeTraite, Activity activity)
        throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException{
            switch(activity){
                case FIX_STRUCTURE:
                return needToFix(salleDeTraite);
                case COLLECT_MILK:
                    return isInProductifPlaceProduction(salleDeTraite, Produits.WATER);
                case MILK:
                    return haveMilkProducedProduced(salleDeTraite);
                case SEND_TO_ENCLOSURE:
                    throw new NotImplementYetException(activity);
                default:
                    throw new UnableToPerformSuchActionWithCurrentActionnable(activity, salleDeTraite);
            }
    }

    private boolean haveMilkProducedProduced(SalleDeTraite salleDeTraite){
        Iterator<Vache> vacheIter = salleDeTraite.getVaches().iterator();
        Iterator<Chevre> chevreIter = salleDeTraite.getChevres().iterator();
		boolean haveProduced = false;
		while(vacheIter.hasNext() && !haveProduced){
			haveProduced = vacheIter.next().haveProduced(); 
		}while(chevreIter.hasNext() && !haveProduced){
			haveProduced = chevreIter.next().haveProduced(); 
		}
		return haveProduced;
    }


    @Override
    public Boolean action(Entrepot entrepot, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        switch(activity){
            case FIX_STRUCTURE:
                return needToFix(entrepot);
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, entrepot);
        }
    }

    @Override
    public Boolean action(Terrain terrain, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        switch(activity){
            case FIX_STRUCTURE:
                return needToFix(terrain);
            case DIG_OVER:
                return canDigOver(terrain);
            case PLANT:
                return canPlant(terrain);
            case TO_WATER:
                return canWaterField(terrain);
            case HARVEST:
                return defaultConditionForCheckingProduction(terrain);
            case REMOVE_ROTTEN_PLANT:
                return canRemoveRottenPlant(terrain);
            case FERTILIZE_GROUND:
                // return;
                throw new NotImplementYetException(activity);
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, terrain);
        }
    }

    private boolean canWaterField(Terrain terrain){
        return terrain.getProductifState() == ProductifState.PRODUCING && basicConditionForHydration(terrain);
    }
    private boolean defaultConditionForCheckingProduction(ProductifPlace productifPlace){
        Iterator<Integer> productionIter = productifPlace.getProduction().values().iterator();
        Integer productionCounter = 0;
        while(productionIter.hasNext()){
            productionCounter += productionIter.next();
        }
        return productionCounter != 0 ;
    }

    private boolean canDigOver(Terrain terrain){
        return terrain.getEvolution() == EvolutionTerrain.VIERGE;
    }

    private boolean canPlant(Terrain terrain){
        return terrain.getEvolution() == EvolutionTerrain.LABOURE && terrain.getHydrationLevel() == HydrationLevel.FULLY_HYDRATED;
    }

    private boolean canRemoveRottenPlant(Terrain terrain){
        return terrain.getEvolution() == EvolutionTerrain.POURRI;
    }


    @Override
    public Boolean action(BergerieChevre bergerieChevre, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, UnableToMakeTheTransfertException{
        switch(activity){
            case FIX_STRUCTURE:
                return needToFix(bergerieChevre);
            case SEND_TO_ENCLOSURE:
                throw new NotImplementYetException(activity);
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, bergerieChevre);
        }
    }

    @Override
    public Boolean action(BergerieMouton bergerieMouton, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, UnableToMakeTheTransfertException{
        switch(activity){
            case FIX_STRUCTURE:
                return needToFix(bergerieMouton);
            case SEND_TO_ENCLOSURE:
                throw new NotImplementYetException(activity);
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, bergerieMouton);
        }
    }

    @Override
    public Boolean action(Puit puit, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        switch(activity){
            case FIX_STRUCTURE:
                return needToFix(puit);
            case DRAW_WATER:
                return haveWaterInWell(puit);
            case COLLECT_WATER:
                return isInProductifPlaceProduction(puit, Produits.WATER);
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, puit);
        }
    }

    private boolean haveWaterInWell(Puit puit){
        return puit.getQuantite()>=puit.getSeau().getCapacite();
    }

    private boolean isInProductifPlaceProduction(ProductifPlace productifPlace, Produits produit){
        if(productifPlace.getProduction().get(produit) != null){
            return productifPlace.getProduction().get(produit) > 0;
        }
        return false;
    }

    @Override
    public Boolean action(Garage garage, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        switch(activity){
            case FIX_STRUCTURE:
                return needToFix(garage);
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, garage);
        }
    }

    @Override
    public Boolean action(Grange grange, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        switch(activity){
            case FIX_STRUCTURE:
                return needToFix(grange);
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, grange);
        }
    }


}
