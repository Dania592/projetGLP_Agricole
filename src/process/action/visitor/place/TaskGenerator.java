package process.action.visitor.place;


import process.action.task.Task;
import process.action.task.action.CareTask;
import process.action.task.action.CollectTask;
import process.action.task.action.FeedingTask;
import process.action.task.action.FixTask;
import process.action.task.action.GiveWaterTask;
import process.action.task.action.HealTask;
import process.action.task.action.SpecialTask;
import process.action.task.action.transfert.SendBackHomeTask;
import process.action.task.action.transfert.SendToEnclosureTask;
import process.action.task.action.transfert.SendToProductifPlace;
import process.action.task.action.transfert.SendToProductifPlaceTask;
import process.action.task.action.transfert.SendToSlaugtherHouseTask;
import data.structure.Garage;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
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
import data.structure.hability.Fixable;
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
import process.action.visitor.place.transfert.EnclosureSenderVisitor;
import process.action.visitor.place.transfert.HomeSenderVisitor;
import process.action.visitor.place.transfert.ProductifPlaceSender;
import process.action.visitor.place.transfert.SendToSlaughterHouseVisitor;


public class TaskGenerator implements PlaceVisitor<Task<?>> {
    private static FeedVisitor feeder = new FeedVisitor();
    private static FixVisitor fixer = new FixVisitor();
    private static ProductionCollector collector = new ProductionCollector();
    private static HydrationVisitor hydrationVisitor = new HydrationVisitor();
    private static EnclosureSenderVisitor enclosureSender = new EnclosureSenderVisitor();
    private static ProductifPlaceSender placeSender  = new ProductifPlaceSender(); 
    private static HomeSenderVisitor homeSender = new HomeSenderVisitor();
    private static SendToSlaughterHouseVisitor slaughterHouseSender = new SendToSlaughterHouseVisitor();
    private static SpecialActionVisitor specialTaskVisitor = new SpecialActionVisitor();
    private static CareVisitor careVisitor = new CareVisitor();
    private static HealerVisitor healerVisitor = new HealerVisitor();
    private static ConditionTester conditionTester = new ConditionTester();

    @Override
    public Task<?> action(Poulallier poulallier)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException, NotImplementYetException {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Task<?> action(Enclos enclos)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException, NotImplementYetException {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Task<?> action(Abatoire abatoire)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Task<?> action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Task<?> action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable,
            NotImplementYetException, HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Task<?> action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Task<?> action(Terrain terrain)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Task<?> action(BergerieChevre bergerieChevre) throws UnableToPerformSuchActionWithCurrentActionnable,
            NotImplementYetException, UnableToMakeTheTransfertException {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Task<?> action(BergerieMouton bergerieMouton) throws UnableToPerformSuchActionWithCurrentActionnable,
            NotImplementYetException, UnableToMakeTheTransfertException {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Task<?> action(Puit puit) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Task<?> action(Garage garage) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Task<?> action(Grange grange) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    @Override
    public Task<?> action(Etable etable)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException {
        throw new IllegalArgumentException("Les conditions doivent être vérifié en passant l'activité.");
    }

    
    @Override
    public Task<?> action(Etable etable, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, NotImplementYetException, UnableToGenerateNewTaskException{ 
            if(conditionTester.action(etable, activity)){
                switch(activity){
                    case FIX_STRUCTURE:
                        return new FixTask(activity, etable, fixer);
                    case SEND_TO_ENCLOSURE:
                        return new SendToEnclosureTask(activity, etable, enclosureSender);
                    case SEND_TO_SEND_TO_SLAUGHTERHOUSE:
                        return new SendToSlaugtherHouseTask(activity, etable, slaughterHouseSender);
                    case SEND_TO_MILKING_PARLOUR:
                        return new SendToProductifPlaceTask(activity, etable, placeSender);
                    case HEAL :
                        return new HealTask(activity, etable, healerVisitor);
                    default:            
                        throw new UnableToPerformSuchActionWithCurrentActionnable(activity, etable);
                }
            }
            throw new UnableToGenerateNewTaskException(activity, etable);
    }

    @Override
    public Task<?> action(Poulallier poulallier, Activity activity)
        throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
            if(conditionTester.action(poulallier, activity)){
                switch(activity){
                    case FIX_STRUCTURE:
                        return new FixTask(activity, poulallier, fixer);
                    case COLLECT_EGG:  
                        return new CollectTask(activity, poulallier, collector);
                    case SEND_TO_ENCLOSURE:
                        return new SendToEnclosureTask(activity, poulallier, enclosureSender);
                    case SEND_TO_SEND_TO_SLAUGHTERHOUSE:
                        return new SendToSlaugtherHouseTask(activity, poulallier, slaughterHouseSender);
                    case HEAL:
                        return new HealTask(activity, poulallier, healerVisitor);
                    default:            
                        throw new UnableToPerformSuchActionWithCurrentActionnable(activity, poulallier);
                }
            }
            throw new UnableToGenerateNewTaskException(activity, poulallier);
        }

    @Override
    public Task<?> action(Enclos enclos, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
        if(conditionTester.action(enclos, activity)){
            switch(activity){
            case FIX_STRUCTURE:
            case FIX_ENCLOSURE:
                return new FixTask(Activity.FIX_ENCLOSURE, enclos, fixer);
            case FEED_ANIMAL_FROM_ENCLOSURE : 
                return new FeedingTask(activity, enclos, feeder);
            case COLLECT_EGG_FROM_ENCLOSURE : 
                return new CollectTask(activity, enclos, collector);
            case GIVE_WATER_TO_ANIMAL : 
                return new GiveWaterTask(activity, enclos, hydrationVisitor);
            case SHAVE_SHEEP : 
                return new SpecialTask(activity, enclos, specialTaskVisitor);
            case SEND_BACK_HOME_ANIMALS : 
                return new SendBackHomeTask(activity, enclos, homeSender);
            case TRANSFERT_TO_PRODUCTION_ROOM : 
                return new SendToProductifPlace(activity, enclos, placeSender);
            case HEAL_FROM_ENCLOSURE:
                return new HealTask(activity, enclos, healerVisitor);
            default:            
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, enclos);
            }
        }
        throw new UnableToGenerateNewTaskException(activity, enclos);
    }

    @Override
    public Task<?> action(Abatoire abatoire, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, UnableToGenerateNewTaskException{
        if(conditionTester.action(abatoire, activity)){
            switch(activity){
            case FIX_STRUCTURE:
                return new FixTask(activity, abatoire, fixer);
            case COLLECT_MEAT:
                return new CollectTask(activity, abatoire, collector); 
            case SLAUGHTER:
                return new SpecialTask(activity, abatoire, specialTaskVisitor);
            default:            
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, abatoire);
            }
        }
        throw new UnableToGenerateNewTaskException(activity, abatoire);
    }

    @Override
    public Task<?> action(Maison maison, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException {
        if(conditionTester.action(maison, activity)){
            switch(activity){
                case FIX_STRUCTURE:
                    return new FixTask(activity, maison, fixer);
                default:
                    throw new UnableToPerformSuchActionWithCurrentActionnable(activity, maison);
            }
        }
        throw new UnableToPerformSuchActionWithCurrentActionnable(activity, maison);
    }

    @Override
    public Task<?> action(SalleDeTraite salleDeTraite, Activity activity)
        throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException{
            if(conditionTester.action(salleDeTraite, activity)){
                switch(activity){
                case FIX_STRUCTURE:
                return new FixTask(activity, salleDeTraite, fixer);
                case COLLECT_MILK:
                    return new CollectTask(activity, salleDeTraite, collector);
                case MILK:
                    return new SpecialTask(activity, salleDeTraite, specialTaskVisitor);
                case SEND_TO_ENCLOSURE_FROM_PLACE_OF_ANIMAL_PRODUCTION:
                    return new SendToEnclosureTask(activity, salleDeTraite, enclosureSender);
                default:
                    throw new UnableToPerformSuchActionWithCurrentActionnable(activity, salleDeTraite);
                }
            }
        throw new UnableToGenerateNewTaskException(activity, salleDeTraite);
    }

    @Override
    public Task<?> action(Entrepot entrepot, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException {
        if(conditionTester.action(entrepot, activity)){
            switch(activity){
            case FIX_STRUCTURE:
                return new FixTask(activity, entrepot, fixer);
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, entrepot);
            }
        }
        throw new UnableToGenerateNewTaskException(activity, entrepot);
    }


    @Override
    public Task<?> action(Terrain terrain, Activity activity, Graine graine)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
            UnableToGenerateNewTaskException {
                if(conditionTester.action(terrain, activity)){
                    switch(activity){
                    case FIX_STRUCTURE:
                        return new FixTask(activity, terrain, fixer);
                    case DIG_OVER:
                        return new SpecialTask(activity, terrain, specialTaskVisitor);
                    case PLANT:
                        return new SpecialTask(activity, terrain, graine, specialTaskVisitor);
                    case TO_WATER:
                        return new GiveWaterTask(activity, terrain, hydrationVisitor);
                    case HARVEST:
                        return new CollectTask(activity, terrain, collector);
                    case REMOVE_ROTTEN_PLANT:
                        return new SpecialTask(activity, terrain, specialTaskVisitor);
                    case FERTILIZE_GROUND:
                        return new CareTask(activity, terrain, careVisitor);
                    case HEAL_FIELD:
                        return new HealTask(activity, terrain, healerVisitor);
                    default:
                        throw new UnableToPerformSuchActionWithCurrentActionnable(activity, terrain);
                    }
                }
                throw new UnableToGenerateNewTaskException(activity, terrain);
    }

    @Override
    public Task<?> action(BergerieChevre bergerieChevre, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException{
        if(conditionTester.action(bergerieChevre, activity)){
            switch(activity){
                case FIX_STRUCTURE:
                    return new FixTask(activity, bergerieChevre, fixer);
                case SEND_TO_ENCLOSURE:
                    return new SendToEnclosureTask(activity, bergerieChevre, enclosureSender);
                case HEAL:
                    return new HealTask(activity, bergerieChevre, healerVisitor); 
                case SEND_TO_SEND_TO_SLAUGHTERHOUSE:
                    return new SendToSlaugtherHouseTask(activity, bergerieChevre, slaughterHouseSender);
                default:
                    throw new UnableToPerformSuchActionWithCurrentActionnable(activity, bergerieChevre);
            }
        }
        throw new UnableToGenerateNewTaskException(activity, bergerieChevre);
    }

    @Override
    public Task<?> action(BergerieMouton bergerieMouton, Activity activity)
        throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException{
        if(conditionTester.action(bergerieMouton, activity)){
            switch(activity){
                case FIX_STRUCTURE:
                    return new FixTask(activity, bergerieMouton, fixer);
                case SEND_TO_ENCLOSURE:
                    return new SendToEnclosureTask(activity, bergerieMouton, enclosureSender);
                case HEAL :
                    return new HealTask(activity, bergerieMouton, healerVisitor);
                case SEND_TO_SEND_TO_SLAUGHTERHOUSE:
                    return new SendToSlaugtherHouseTask(activity, bergerieMouton, slaughterHouseSender);
                default:
                    throw new UnableToPerformSuchActionWithCurrentActionnable(activity, bergerieMouton);
            }
        }
        throw new UnableToGenerateNewTaskException(activity, bergerieMouton);
    }

    @Override
    public Task<?> action(Puit puit, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException {
        if(conditionTester.action(puit, activity)){
            switch(activity){
            case FIX_STRUCTURE:
                return new FixTask(activity, puit, fixer);
            case DRAW_WATER:
                return new SpecialTask(activity, puit, specialTaskVisitor);
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, puit);
            }
        }
        throw new UnableToGenerateNewTaskException(activity, puit);
    }

    @Override
    public Task<?> action(Garage garage, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException {
        if(conditionTester.action(garage, activity)){
            switch(activity){
                case FIX_STRUCTURE:
                    return new FixTask(activity, garage, fixer);
                default:
                    throw new UnableToPerformSuchActionWithCurrentActionnable(activity, garage);
            }
        }
        throw new UnableToGenerateNewTaskException(activity, garage);
    }

    @Override
    public Task<?> action(Grange grange, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToGenerateNewTaskException {
        if(conditionTester.action(grange, activity)){
            switch(activity){
            case FIX_STRUCTURE:
                return new FixTask(activity, grange, fixer);
            default:
                throw new UnableToPerformSuchActionWithCurrentActionnable(activity, grange);
            }
        }
        throw new UnableToGenerateNewTaskException(activity, grange);
    }

    @Override
    public Task<?> action(Terrain terrain, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable,
            NotImplementYetException, UnableToGenerateNewTaskException {
        return action(terrain, activity,Graine.AMARANTH_SEED);
    }
    

}
