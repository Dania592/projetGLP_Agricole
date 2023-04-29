package process.action; 

import java.util.ArrayList;

import data.flore.terrains.Terrain;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.myExceptions.UnknownActivityException;
import data.planning.Activity;
import data.planning.Activity.Type;
import data.structure.hability.Actionnable;
import data.structure.hability.Distributor;
import data.structure.hability.Feedable;
import data.structure.hability.Fixable;
import data.structure.hability.Hydratable;
import data.structure.hability.ProductifPlace;
import data.structure.hability.SlaughterHouseSender;
import data.structure.hability.SpecialActionPerformer;
import data.structure.hability.Actionnable.ActionnableKey;
import process.action.exception.NotImplementYetException;
import process.action.exception.structure.TaskNotNeededToBePerform;
import process.action.task.Task;
import process.action.task.action.CollectTask;
import process.action.task.action.FeedingTask;
import process.action.task.action.FixTask;
import process.action.task.action.GiveWaterTask;
import process.action.task.action.SpecialTask;
import process.action.task.action.transfert.SendBackHomeTask;
import process.action.task.action.transfert.SendToEnclosureTask;
import process.action.visitor.place.FeedVisitor;
import process.action.visitor.place.FixVisitor;
import process.action.visitor.place.HydrationVisitor;
import process.action.visitor.place.ProductionCollector;
import process.action.visitor.place.SpecialActionVisitor;
import process.action.visitor.place.transfert.EnclosureSenderVisitor;
import process.action.visitor.place.transfert.HomeSenderVisitor;
import process.action.visitor.place.transfert.ProductifPlaceSender;
import process.action.visitor.place.transfert.SendToSlaughterHouseVisitor;

public class TaskFactory{
    private static FeedVisitor feeder = new FeedVisitor();
    private static FixVisitor fixer = new FixVisitor();
    private static ProductionCollector collector = new ProductionCollector();
    private static HydrationVisitor hydrationVisitor = new HydrationVisitor();
    private static EnclosureSenderVisitor enclosureSender = new EnclosureSenderVisitor();
    private static ProductifPlaceSender placeSender  = new ProductifPlaceSender(); 
    private static HomeSenderVisitor homeSender = new HomeSenderVisitor();
    private static SendToSlaughterHouseVisitor slaughterHouseSender = new SendToSlaughterHouseVisitor();
    private static SpecialActionVisitor specialTaskVisitor = new SpecialActionVisitor();
    private static TaskFactory instance; 

     

    private TaskFactory() {}


    public static TaskFactory getInstance(){
        if(instance==null){
            instance = new TaskFactory();
        }
        return instance;
    }


    public Task<?> newTask(Activity activity, Actionnable actionnable) throws UnableToGenerateNewTaskException, NotImplementYetException, TaskNotNeededToBePerform, UnknownActivityException{
        if(actionnable.isCurrentlyUsedForAnotherTask()){
            throw new UnableToGenerateNewTaskException(activity, actionnable);
        }if(!isActionPossibleToPerformFrom(activity,actionnable)){
            throw new UnableToGenerateNewTaskException(activity, actionnable);
        }if(activity.getType() != Type.FIX && actionnable.isNeedToBeFixed()){
            throw new UnableToGenerateNewTaskException(activity, actionnable, "Need to be fixed");
        }if(!actionnable.isStatique()){
            throw new UnableToGenerateNewTaskException(activity, actionnable, "Actionnable place not even set");
        }
        Type activityType = activity.getType();
        if(activityType == Type.GET_PRODUCTION && actionnable instanceof ProductifPlace){
            ProductifPlace productif = (ProductifPlace)actionnable; 
            return newCollectTask(activity, productif);
        }else if(activityType == Type.FEED && actionnable instanceof Feedable){
            Feedable feedable = (Feedable)actionnable;
            return newFeedingTask(activity, feedable);
        }else if(activityType == Type.FIX && actionnable instanceof Fixable){
            Fixable fixable = (Fixable)actionnable;
            return newFixTask(activity, fixable);
        }else if((activityType == Type.SEND_TO_ENCLOSURE || activityType == Type.GO_BACK_HOME )&& actionnable instanceof Distributor){
            Distributor<?> distributor = (Distributor<?>)actionnable;
            if(activityType == Type.SEND_TO_ENCLOSURE){
                return newSendToEnclosureTask(activity, distributor);
            }else{
                return newSendBackHomeTask(activity, distributor);
            }
        }else if(activityType == Type.SEND_TO_SLAUGHTERHOUSE && actionnable instanceof SlaughterHouseSender){
            SlaughterHouseSender slaughtable = (SlaughterHouseSender)actionnable;
            throw new NotImplementYetException(activity);
            // return newSendToSlaughterHouseTask(activity, slaughtable);
        }else if(activityType == Type.GIVE_WATER && actionnable instanceof Hydratable){
            System.out.println("Activité"+ activity+ "Actionnable sur qui perform : "+ actionnable.getSpecificActionnableKey());
            Hydratable hydratablePlace = (Hydratable)actionnable;
            return newGiveWaterTask(activity, hydratablePlace);
        }else if(activityType == Type.SPECIAL && actionnable instanceof SpecialActionPerformer){
            SpecialActionPerformer specialActionnable = (SpecialActionPerformer)actionnable;
            return newSpecialAction(activity, specialActionnable);
        }else{
            throw new NotImplementYetException(activity); 
        }
    }



    private SpecialTask newSpecialAction(Activity activity, SpecialActionPerformer specialActionnable) throws UnknownActivityException, UnableToGenerateNewTaskException {
        if(specialActionnable.canPerformSpecialAction(activity)){
            return new SpecialTask(activity, specialActionnable, specialTaskVisitor);
        }
        throw new UnableToGenerateNewTaskException();
    }


    /*
     * INSTANCIATION OF THE DIFFERENTS TASKS
     */
     //TODO if there are some conditions regarding the launch of task we will implement them here ! : The need of an action to be perform (milk cow if they've produced milk is already handle when we get the actionnable keys); 
    private CollectTask newCollectTask(Activity activity, ProductifPlace productifPlace) throws UnableToGenerateNewTaskException, TaskNotNeededToBePerform{
        if(collector.haveProduced(productifPlace)){
            return new CollectTask(activity, productifPlace, collector); 
        }
        throw new TaskNotNeededToBePerform(activity, productifPlace); 
    }

    private SendBackHomeTask newSendBackHomeTask(Activity activity, Distributor<?> distributor) throws UnableToGenerateNewTaskException, TaskNotNeededToBePerform{
        if(!(distributor.isEmpty())){
            return new SendBackHomeTask(activity, distributor, homeSender);
        }
        throw new TaskNotNeededToBePerform(activity, distributor);
    }

    private GiveWaterTask newGiveWaterTask(Activity activity, Hydratable hydratablePlace) throws UnableToGenerateNewTaskException, TaskNotNeededToBePerform{
        if(hydrationVisitor.needToBeHydrated(hydratablePlace)){
            return new GiveWaterTask(activity, hydratablePlace, hydrationVisitor);
        }
        throw new TaskNotNeededToBePerform(activity, hydratablePlace);
    }

    private SendToEnclosureTask newSendToEnclosureTask(Activity activity, Distributor<?> distributor) throws UnableToGenerateNewTaskException, TaskNotNeededToBePerform{
        if(!(distributor.isEmpty())){
            return new SendToEnclosureTask(activity, distributor, enclosureSender);
        }
        throw new TaskNotNeededToBePerform(activity, distributor);
    }
    
    // private SendToSlaugtherHouseTask newSendToSlaughterHouseTask(Activity activity, SlaughterHouseSender slaughtable) throws UnableToGenerateNewTaskException, TaskNotNeededToBePerform, NotImplementYetException{
    //     throw new NotImplementYetException();
    // }

    private FeedingTask newFeedingTask(Activity activity, Feedable feedable) throws UnableToGenerateNewTaskException, TaskNotNeededToBePerform{
        if(feedable.isNeedToBeFeed()){
            return new FeedingTask(activity, feedable, feeder);
        }
        throw new TaskNotNeededToBePerform(activity, feedable);

    }     
    private FixTask newFixTask(Activity activity, Fixable fixable) throws TaskNotNeededToBePerform, UnableToGenerateNewTaskException{
        if(fixable.isNeedToBeFixed()){
            return new FixTask(activity, fixable, fixer);
        }
        throw new TaskNotNeededToBePerform(activity, fixable);
    }     


    public boolean isActionPossibleToPerformFrom(Activity activity, Actionnable actionnable) throws TaskNotNeededToBePerform{
        ArrayList<ActionnableKey> actionnableKey = actionnable.getASetOfAllActionnableKey();
        ArrayList<Activity> possibleActivityToPerform = Activity.getPossibleActivity(actionnableKey);
        return possibleActivityToPerform.contains(activity);
    }


}
