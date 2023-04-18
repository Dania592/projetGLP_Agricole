package process.action; 

import java.util.ArrayList;

import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.planning.Activity.Type;
import data.structure.hability.Actionnable;
import data.structure.hability.Distributor;
import data.structure.hability.Feedable;
import data.structure.hability.Fixable;
import data.structure.hability.Productif;
import data.structure.hability.SlaughterHouseSender;
import data.structure.hability.Actionnable.ActionnableKey;
import process.action.exception.NotImplementYetException;
import process.action.exception.structure.TaskNotNeededToBePerform;
import process.action.task.Task;
import process.action.task.action.CollectTask;
import process.action.task.action.FeedingTask;
import process.action.task.action.FixTask;
import process.action.task.action.transfert.SendBackHomeTask;
import process.action.task.action.transfert.SendToEnclosureTask;
import process.action.task.action.transfert.SendToSlaugtherHouseTask;
import process.action.visitor.being.ProductCollectorVisitor;
import process.action.visitor.being.DomesticSpeciesEnclosureSender;
import process.action.visitor.being.DomesticSpeciesHomeSender;
import process.action.visitor.place.CollectVisitor;
import process.action.visitor.place.FeedVisitor;
import process.action.visitor.place.FixVisitor;
import process.action.visitor.place.transfert.EnclosureSenderVisitor;
import process.action.visitor.place.transfert.HomeSenderVisitor;
import process.action.visitor.place.transfert.SendToSlaughterHouseVisitor;

public class TaskFactory{
    private CollectVisitor collector = new CollectVisitor(new ProductCollectorVisitor());
    private FeedVisitor feeder = new FeedVisitor();
    private FixVisitor fixer = new FixVisitor();
    private SendToSlaughterHouseVisitor slaughterHouseSender = new SendToSlaughterHouseVisitor();
    private EnclosureSenderVisitor enclosureSender = new EnclosureSenderVisitor(new DomesticSpeciesEnclosureSender());
    private HomeSenderVisitor homeSender = new HomeSenderVisitor(new DomesticSpeciesHomeSender()); 
    private static TaskFactory instance; 

     

    private TaskFactory() {}


    public static TaskFactory getInstance(){
        if(instance==null){
            instance = new TaskFactory();
        }
        return instance;
    }


    public Task<?> newTask(Activity activity, Actionnable actionnable) throws UnableToGenerateNewTaskException, NotImplementYetException, TaskNotNeededToBePerform{
        if(!isActionPossibleToPerformFrom(activity,actionnable)){
            throw new UnableToGenerateNewTaskException(activity, actionnable);
        }
        Type activityType = activity.getType();
        if(activityType == Type.GET_PRODUCTION && actionnable instanceof Productif){
            Productif productif = (Productif)actionnable; 
            return newCollectTask(activity, productif);
        }else if(activityType == Type.FEED && actionnable instanceof Feedable){
            Feedable feedable = (Feedable)actionnable;
            return newFeedingTask(activity, feedable);
        }else if(activityType == Type.FIX && actionnable instanceof Fixable){
            Fixable fixable = (Fixable)actionnable;
            return newFixTask(activity, fixable);
        }else if((activityType == Type.SEND_TO_ENCLOSURE || activityType == Type.GO_BACK_HOME )&& actionnable instanceof Distributor){
            Distributor distributor = (Distributor)actionnable;
            if(activityType == Type.SEND_TO_ENCLOSURE){
                return newSendToEnclosureTask(activity, distributor);
            }else{
                return newSendBackHomeTask(activity, distributor);
            }
        }else if(activityType == Type.SEND_TO_SLAUGHTERHOUSE && actionnable instanceof SlaughterHouseSender){
            SlaughterHouseSender slaughtable = (SlaughterHouseSender)actionnable;
            throw new NotImplementYetException(activity);
            // return newSendToSlaughterHouseTask(activity, slaughtable);
        }else{
            throw new NotImplementYetException(activity); 
        }
    }

  


    /*
     * INSTANCIATION OF THE DIFFERENTS TASKS
     */
     //TODO if there are some conditions regarding the launch of task we will implement them here ! : The need of an action to be perform (milk cow if they've produced milk is already handle when we get the actionnable keys); 
    private CollectTask newCollectTask(Activity activity, Productif productif) throws UnableToGenerateNewTaskException, TaskNotNeededToBePerform{
        if(productif.haveProduced()){
            return new CollectTask(activity, productif, collector); 
        }
        throw new TaskNotNeededToBePerform(activity, productif); 
    }

    private SendBackHomeTask newSendBackHomeTask(Activity activity, Distributor distributor) throws UnableToGenerateNewTaskException, TaskNotNeededToBePerform{
        if(!(distributor.isEmpty())){
            return new SendBackHomeTask(activity, distributor, homeSender);
        }
        throw new TaskNotNeededToBePerform(activity, distributor);
    }

    private SendToEnclosureTask newSendToEnclosureTask(Activity activity, Distributor distributor) throws UnableToGenerateNewTaskException, TaskNotNeededToBePerform{
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
        ArrayList<ActionnableKey> actionnableKey = actionnable.getActionnableKey();
        ArrayList<Activity> possibleActivityToPerform = Activity.getPossibleActivity(actionnableKey);
        return possibleActivityToPerform.contains(activity);
    }


}
