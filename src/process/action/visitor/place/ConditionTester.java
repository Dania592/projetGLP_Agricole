package process.action.visitor.place;

import java.util.ArrayList;
import java.util.Iterator;

import data.espece.faune.AnimalProducteur;
import data.espece.faune.Chevre;
import data.espece.faune.Healable;
import data.espece.faune.Mouton;
import data.espece.faune.Vache;
import data.evenement.Avalanche;
import data.espece.Transportable;
import data.espece.Produceur.ProductifState;
import data.espece.WaterConsumer.HydrationLevel;
import data.flore.terrains.EvolutionTerrain;
import data.flore.terrains.Terrain;
import data.gestion.GestionnaireEnclos;
import data.gestion.GestionnaireStocks;
import data.gestion.GestionnaireStructures;
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
import data.structure.Refuge;
import data.structure.SalleDeTraite;
import data.structure.Structure;
import data.structure.hability.Actionnable;
import data.structure.hability.Hydratable;
import data.structure.hability.ProductifPlace;
import data.structure.hability.Actionnable.ActionnableKey;
import gui.gestionnaire.keys.Graine;
import gui.gestionnaire.keys.Structures;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.action.CollectTask;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;

public class ConditionTester implements PlaceVisitor<Boolean> {

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
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException, NotImplementYetException {
        switch (activity) {
            case FIX_STRUCTURE:
                return etable.isNeedToBeFixed();
            case SEND_TO_ENCLOSURE:
                return isThereFreeEnclosure() && !(etable.getInHabitant().isEmpty());
            case SEND_TO_SEND_TO_SLAUGHTERHOUSE:
                return isThereFreeSlaugtherHouse() && !(etable.getInHabitant().isEmpty());
            case SEND_TO_MILKING_PARLOUR:
                return isThereFreeMilkHouse();
            case HEAL:
                return canHeal(etable.getInHabitant().iterator());
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, etable);
        }
    }

    @Override
    public Boolean action(Poulallier poulallier, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException {
        switch (activity) {
            case FIX_STRUCTURE:
                return poulallier.isNeedToBeFixed();
            case COLLECT_EGG:
                return isInProductifPlaceProduction(poulallier, Produits.OEUF);
            case SEND_TO_ENCLOSURE:
                System.out.println("Des free enclosure : " + isThereFreeEnclosure());
                return isThereFreeEnclosure() && !(poulallier.getInHabitant().isEmpty());
            case SEND_TO_SEND_TO_SLAUGHTERHOUSE:
                return isThereFreeSlaugtherHouse() && !(poulallier.getInHabitant().isEmpty());
            case HEAL:
                return canHeal(poulallier.getInHabitant().iterator());
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, poulallier);
        }
    }

    /**
     * This function checks if there are available homes for animal producers in a
     * given enclosure.
     * 
     * @param enclos an object of type Enclos, which presumably represents an animal
     *               enclosure or
     *               habitat.
     * @return The method is returning a boolean value.
     */
    private boolean isThereAvalablesHomes(Enclos enclos) {
        Iterator<AnimalProducteur> animalProduceurIter = enclos.getAnimals().iterator();
        boolean isThereAvalable = false;
        while (animalProduceurIter.hasNext() && !isThereAvalable) {
            isThereAvalable = isThereAvalableHomes(animalProduceurIter.next());
        }
        return isThereAvalable;
    }

    public <T extends Transportable> boolean isThereAvalableHomes(T animalProducteur) {
        ArrayList<Structure> possibleHomeForAnimalProduceur = GestionnaireStructures.getInstance().getStructures()
                .get(animalProducteur.getHomeLabel());
        if (possibleHomeForAnimalProduceur != null) {
            return areStructureAvalables(possibleHomeForAnimalProduceur);
        }
        return false;
    }

    private boolean isThereFreeMilkHouse() {
        ArrayList<Structure> salleDeTraiteList = GestionnaireStructures.getInstance().getStructures()
                .get(Structures.SALLE_DE_TRAITE);
        boolean isThereAvalable = false;
        if (salleDeTraiteList != null) {
            Iterator<Structure> salleDeTraite = salleDeTraiteList.iterator();
            while (salleDeTraite.hasNext() && !isThereAvalable) {
                isThereAvalable = basicConditionForActionnableToBeFree(salleDeTraite.next());
            }
        }
        return isThereAvalable;
    }

    private boolean isThereFreeSlaugtherHouse() {
        ArrayList<Structure> abattoireList = GestionnaireStructures.getInstance().getStructures()
                .get(Structures.ABATTOIRE);
        boolean isThereAvalable = false;
        if (abattoireList != null) {
            Iterator<Structure> abattoire = abattoireList.iterator();
            while (abattoire.hasNext() && !isThereAvalable) {
                isThereAvalable = basicConditionForActionnableToBeFree(abattoire.next());
            }
        }
        return isThereAvalable;
    }

    private boolean isThereFreeEnclosure() {
        boolean isTherePossibleEnclosure = false;
        ArrayList<Enclos> possibleAvalableEnclosure = GestionnaireEnclos.getInstance().getEnclos();
        if (possibleAvalableEnclosure != null) {
            Iterator<Enclos> enclosureIter = possibleAvalableEnclosure.iterator();
            while (enclosureIter.hasNext() && !isTherePossibleEnclosure) {
                isTherePossibleEnclosure = basicConditionForActionnableToBeFree(enclosureIter.next());
            }
        }
        return isTherePossibleEnclosure;
    }

    private boolean areStructureAvalables(ArrayList<Structure> structures) {
        Iterator<Structure> structureIter = structures.iterator();
        boolean areAvalable = false;
        while (structureIter.hasNext() && !areAvalable) {
            areAvalable = basicConditionForActionnableToBeFree(structureIter.next());
        }
        return areAvalable;
    }

    private boolean basicConditionForActionnableToBeFree(Actionnable actionnable) {
        if(actionnable.getSpecificActionnableKey() == ActionnableKey.SALLE_TRAITE){
            System.out.println(actionnable.getSpecificActionnableKey()+ " is !DAMAGED  : " + !(actionnable.isNeedToBeFixed()) + "&& static : "
            + actionnable.isStatique());
        }
        return !(actionnable.isNeedToBeFixed()) && actionnable.isStatique();
    }

    @Override
    public Boolean action(Enclos enclos, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException {
        switch (activity) {
            case FIX_STRUCTURE:
            case FIX_ENCLOSURE:
                return enclos.isNeedToBeFixed();
            case FEED_ANIMAL_FROM_ENCLOSURE:
                return enclos.isNeedToBeFeed();
            case COLLECT_EGG_FROM_ENCLOSURE:
                return canCollectEgg(enclos);
            case GIVE_WATER_TO_ANIMAL:
                return basicConditionForHydration(enclos);
            case SHAVE_SHEEP:
                return canShaveSheep(enclos);
            case SEND_BACK_HOME_ANIMALS:
                return isThereAvalablesHomes(enclos);
            case TRANSFERT_TO_PRODUCTION_ROOM:
                return isThereFreeMilkHouse() && !(enclos.getAnimalStorage().getChevres().isEmpty() && enclos.getAnimalStorage().getVaches().isEmpty());
            case HEAL_FROM_ENCLOSURE:
                return canHeal(enclos.getAnimals().iterator());
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, enclos);
        }
    }

    private boolean basicConditionForHydration(Hydratable hydratablePlace) {
        return hydratablePlace.isNeedToBeHydrated() && playerHaveWater();
    }

    private boolean playerHaveWater() {
        return GestionnaireStocks.getInstance().getProduits().get(Produits.WATER) != null;
    }

    private boolean canCollectEgg(Enclos enclos) {
        if (enclos.getProduction().get(Produits.OEUF) != null) {
            return enclos.getProduction().get(Produits.OEUF) != 0;
        }
        return false;
    }

    private boolean canShaveSheep(Enclos enclos) {
        boolean haveProduced = false;
        Iterator<Mouton> moutons = enclos.getAnimalStorage().getMoutons().iterator();
        Mouton mouton;
        while (moutons.hasNext() && !haveProduced) {
            mouton = moutons.next();
            haveProduced = mouton.getProductifState() == ProductifState.IN_WAIT;
        }
        return haveProduced;
    }

    @Override
    public Boolean action(Abatoire abatoire, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException {
        switch (activity) {
            case FIX_STRUCTURE:
                return abatoire.isNeedToBeFixed();
            case COLLECT_MEAT:
                return isInProductifPlaceProduction(abatoire, Produits.MEAT);
            case SLAUGHTER:
                return isThereSlaughtableSpecies(abatoire);
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, abatoire);
        }
    }

    private boolean isThereSlaughtableSpecies(Abatoire abatoire) {
        return abatoire.getAnimaltoSlaughter().size() > 0;
    }

    @Override
    public Boolean action(Maison maison, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        switch (activity) {
            case FIX_STRUCTURE:
                return maison.isNeedToBeFixed();
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, maison);
        }
    }

    @Override
    public Boolean action(SalleDeTraite salleDeTraite, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException {
        switch (activity) {
            case FIX_STRUCTURE:
                return salleDeTraite.isNeedToBeFixed();
            case COLLECT_MILK:
                return isInProductifPlaceProduction(salleDeTraite, Produits.WATER);
            case MILK:
                return haveMilkProducedProduced(salleDeTraite);
            case SEND_TO_ENCLOSURE_FROM_PLACE_OF_ANIMAL_PRODUCTION:
                System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                return isThereFreeEnclosure() && (salleDeTraite.getVaches().size()+salleDeTraite.getChevres().size()>0);
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, salleDeTraite);
        }
    }

    private boolean haveMilkProducedProduced(SalleDeTraite salleDeTraite) {
        System.out.println("est Vide : "+(salleDeTraite.getVaches().size()+salleDeTraite.getChevres().size()==0));
        Iterator<Vache> vacheIter = salleDeTraite.getVaches().iterator();
        Iterator<Chevre> chevreIter = salleDeTraite.getChevres().iterator();
        boolean haveProduced = false;
        while (vacheIter.hasNext() && !haveProduced) {
            System.out.println();
            haveProduced = vacheIter.next().getProductifState()==ProductifState.HAVE_PRODUCE;
        }
        while (chevreIter.hasNext() && !haveProduced) {
            haveProduced = chevreIter.next().getProductifState()==ProductifState.HAVE_PRODUCE;
        }
        return haveProduced;
    }

    @Override
    public Boolean action(Entrepot entrepot, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        switch (activity) {
            case FIX_STRUCTURE:
                return entrepot.isNeedToBeFixed();
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, entrepot);
        }
    }

    @Override
    public Boolean action(Terrain terrain, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        switch (activity) {
            case DIG_OVER:
                return canDigOver(terrain);
            case PLANT:
                return canPlant(terrain);
            case TO_WATER:
                return canWaterField(terrain);
            case HARVEST:
                return canCollectPlant(terrain);
            case REMOVE_ROTTEN_PLANT:
                return canRemoveRottenPlant(terrain);
            case FERTILIZE_GROUND:
                return canFertilise(terrain);
            case HEAL_FIELD:
                return canHeal(terrain);
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, terrain);
        }
    }

    private boolean needToHeal(Healable healable) {
        return healable.getEtatSante() == EtatSante.MALADE || healable.getEtatSante() == EtatSante.MOURANT ||
                healable.getEtatSante() == EtatSante.GRAVEMENT_MALADE;
    }

    private boolean canHeal(Terrain terrain) {
        return needToHeal(terrain) && terrain.getProductifState() != ProductifState.IN_WAIT
                && terrain.getProductifState() != ProductifState.PRODUCING;
    }// TODO doit on acheter les médicaments

    private <E extends AnimalProducteur> boolean canHeal(Iterator<E> animalProduceurIterator) {
        boolean needToHeal = false;
        while (animalProduceurIterator.hasNext() && !needToHeal) {
            needToHeal = needToHeal(animalProduceurIterator.next());
        }
        return needToHeal;
    }// TODO doit on acheter les médicaments

    private boolean canCollectPlant(Terrain terrain) {
        return defaultConditionForCheckingProduction(terrain) && terrain.getEvolution() == EvolutionTerrain.PLANTE_5;
    }

    private boolean canFertilise(Terrain terrain) {
        return terrain.getProductifState() == ProductifState.PRODUCING
                && !(terrain.getEtatSante() == EtatSante.GRAVEMENT_MALADE) &&
                !(terrain.getEtatSante() == EtatSante.MOURANT) && terrain.isDoped() == false
                && terrain.getHydrationLevel() != HydrationLevel.IN_DANGER
                && terrain.getHydrationLevel() != HydrationLevel.DESHYDRATED;
        // TODO on doit acheter donc vérifier qu'il y en a dans le gestionnaire
    }

    private boolean canWaterField(Terrain terrain) {
        return basicConditionForHydration(terrain) && terrain.getEvolution() != EvolutionTerrain.POURRI;
    }

    private boolean defaultConditionForCheckingProduction(ProductifPlace productifPlace) {
        Iterator<Integer> productionIter = productifPlace.getProduction().values().iterator();
        Integer productionCounter = 0;
        while (productionIter.hasNext()) {
            productionCounter += productionIter.next();
        }
        return productionCounter != 0;
    }

    private boolean canDigOver(Terrain terrain) {
        return terrain.getEvolution() == EvolutionTerrain.VIERGE;
    }

    private boolean canPlant(Terrain terrain) {
        return terrain.getEvolution() == EvolutionTerrain.LABOURE
                && terrain.getHydrationLevel() == HydrationLevel.FULLY_HYDRATED &&
                terrain.getEtatSante() != EtatSante.MALADE && terrain.getEtatSante() != EtatSante.GRAVEMENT_MALADE;
    }

    private boolean canRemoveRottenPlant(Terrain terrain) {
        return terrain.getEvolution() == EvolutionTerrain.POURRI;
    }

    @Override
    public Boolean action(BergerieChevre bergerieChevre, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
            UnableToMakeTheTransfertException {
        switch (activity) {
            case FIX_STRUCTURE:
                return bergerieChevre.isNeedToBeFixed();
            case SEND_TO_ENCLOSURE:
                return isThereFreeEnclosure() && !(bergerieChevre.getInHabitant().isEmpty());
            case HEAL:
                return canHeal(bergerieChevre.getInHabitant().iterator());
            case SEND_TO_SEND_TO_SLAUGHTERHOUSE:
                return isThereFreeSlaugtherHouse() && !(bergerieChevre.getInHabitant().isEmpty());
            case SEND_TO_MILKING_PARLOUR:
                return isThereFreeMilkHouse();
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, bergerieChevre);
        }
    }

    @Override
    public Boolean action(BergerieMouton bergerieMouton, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
            UnableToMakeTheTransfertException {
        switch (activity) {
            case FIX_STRUCTURE:
                return bergerieMouton.isNeedToBeFixed();
            case SEND_TO_ENCLOSURE:
                return isThereFreeEnclosure() && !(bergerieMouton.getInHabitant().isEmpty());
            case HEAL:
                return canHeal(bergerieMouton.getInHabitant().iterator());
            case SEND_TO_SEND_TO_SLAUGHTERHOUSE:
                return isThereFreeSlaugtherHouse() && !(bergerieMouton.getInHabitant().isEmpty());
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, bergerieMouton);
        }
    }

    @Override
    public Boolean action(Puit puit, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        switch (activity) {
            case FIX_STRUCTURE:
                return puit.isNeedToBeFixed();
            case DRAW_WATER:
                return haveWaterInWell(puit);
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, puit);
        }
    }

    private boolean haveWaterInWell(Puit puit) {
        return puit.getQuantite() >= puit.getSeau().getCapacite();
    }

    private boolean isInProductifPlaceProduction(ProductifPlace productifPlace, Produits produit) {
        if (productifPlace.getProduction().get(produit) != null) {
            return productifPlace.getProduction().get(produit) > 0;
        }
        return false;
    }

    @Override
    public Boolean action(Garage garage, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        switch (activity) {
            case FIX_STRUCTURE:
                return garage.isNeedToBeFixed();
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, garage);
        }
    }

    @Override
    public Boolean action(Grange grange, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        switch (activity) {
            case FIX_STRUCTURE:
                return grange.isNeedToBeFixed();
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, grange);
        }
    }

}
