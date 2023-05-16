package process.action.task.coordinator;

import java.util.ArrayList;
import java.util.Iterator;

import data.espece.flore.terrains.Terrain;
import data.myExceptions.AskingToWorkAtIllegalHourException;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.myExceptions.UnknownActivityException;
import data.planning.Activity;
import data.structure.hability.Actionnable;
import gui.gestionnaire.keys.Graine;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.TaskCompleteException;
import process.action.exception.structure.TaskNotNeededToBePerform;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
import process.production.ProductionManager;
import process.time.TimeManager;

public class TaskManager{
    private ArrayList<Task<?>> taskToBeLaunched = new ArrayList<>();
    private ArrayList<Task<?>> inProcess = new ArrayList<>();
    private ArrayList<Task<?>> taskCompleted = new ArrayList<>();
    private int currentHour;
    private TimeManager timeManager = TimeManager.getInstance();
    private static TaskGenerator taskGenerator = new TaskGenerator();
    private static ConditionTester conditionTester = new ConditionTester(); 
    private static TaskManager taskManager = new TaskManager();
    
    
    public  TaskManager(){
        this.timeManager =TimeManager.getInstance();
        currentHour = timeManager.getClock().getHour().getValue();

    }

    public static TaskManager getInstance() {
    	return taskManager ;
    }

    public void addNewTask(int hour, Activity activity, Actionnable actionnable)
            throws UnableToGenerateNewTaskException, TaskNotNeededToBePerform, UnknownActivityException, AskingToWorkAtIllegalHourException, UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException {
        Task<?> generatedTask = actionnable.launchAction(taskGenerator, activity);
        taskToBeLaunched.add(generatedTask);
    }

    public void addNewTask(Activity activity, Actionnable actionnable, Graine graine) throws UnableToGenerateNewTaskException, TaskNotNeededToBePerform, UnknownActivityException, AskingToWorkAtIllegalHourException, UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException{
        Task<?> currentTask;
        if(activity == Activity.PLANT){
            Terrain terrain = (Terrain)actionnable;
            terrain.setType(graine);
            currentTask = terrain.launchAction(taskGenerator, activity, graine);
        }else{
            currentTask = actionnable.launchAction(taskGenerator, activity);       
        }
        addToTaskToBeLaunched(currentTask);
    }

    public void addNewTask(Activity activity, Actionnable actionnable) throws UnableToGenerateNewTaskException, TaskNotNeededToBePerform, UnknownActivityException, AskingToWorkAtIllegalHourException, UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException{
        addNewTask(activity, actionnable, null);
    }
    private void addToTaskToinProcess(){ 
        Iterator<Task<?>> taskIterator = taskToBeLaunched.iterator();
        Task<?> currentTaskToBeLaunched;
        while (taskIterator.hasNext()){
            currentTaskToBeLaunched = taskIterator.next();
            inProcess.add(currentTaskToBeLaunched);
        }
        cleanTaskToBeLanched();
    }


    private void addToTaskToBeLaunched(Task<?> task){
        taskToBeLaunched.add(task);
    }

    private void cleanTaskToBeLanched() {
        Iterator<Task<?>> taskIterator = inProcess.iterator();
        while (taskIterator.hasNext()) {
            taskToBeLaunched.remove(taskIterator.next());
        }
    }

    private boolean hourHaveChanged() {
        if (currentHour != timeManager.getClock().getHour().getValue()) {
            return true;
        }
        return false;
    }

    public void managingTask(){
        ProductionManager.getInstance().manageProduction();
        if(hourHaveChanged()){
            currentHour = timeManager.getClock().getHour().getValue();
        }
        addToTaskToinProcess();
        processingTaskInProgess();
        removeCompletedTask();
    }


    private void processingTaskInProgess(){
        Iterator<Task<?>> taskIterator;
        Task<?> currentTask;
        taskIterator = inProcess.iterator();
        while(taskIterator.hasNext()){
            currentTask = taskIterator.next();
            try {
                currentTask.process();
            } catch (TaskCompleteException e) {
                taskCompleted.add(currentTask);
            }
        }
    }


    private void removeCompletedTask() {
        Iterator<Task<?>> taskToRemove = taskCompleted.iterator();
        Task<?> currentTask;
        while (taskToRemove.hasNext()) {
            currentTask = taskToRemove.next();
            inProcess.remove(currentTask);
        }
        taskCompleted.clear();
    }

    public ArrayList<Activity> getPossibleTaskToPerform(Actionnable actionnable) throws AskingToWorkAtIllegalHourException{
        ArrayList<Activity> possibleTaskToPerform= new ArrayList<>();
        if(actionnable.isNeedToBeFixed()){
            possibleTaskToPerform.add(Activity.FIX_STRUCTURE);
        }else{
            ArrayList<Activity> allPossibleActivitiesToPerform = Activity.getPossibleActivity(actionnable.getASetOfAllActionnableKey());
            Iterator<Activity>  activitiesIter = allPossibleActivitiesToPerform.iterator();
            Activity currentActivity;
            while(activitiesIter.hasNext()){
                try {
                    currentActivity = activitiesIter.next();
                    if(actionnable.launchAction(conditionTester, currentActivity)){
                        possibleTaskToPerform.add(currentActivity);
                    }
                } catch (Exception e) {
                }
            }
        }
        return possibleTaskToPerform;
    }



    public int getCurrentHour() {
        return currentHour;
    }

    public void removeTask(Task<?> task) {
        inProcess.remove(task);
    }

    public ArrayList<Task<?>> getTaskToBeLaunched() {
        return taskToBeLaunched;
    }

    public synchronized ArrayList<Task<?>> getinProcess() {
        return inProcess;
    }

}
