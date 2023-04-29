package process.action;

import java.util.ArrayList;
import java.util.Iterator;

import data.configuration.GameConfiguration;
import data.espece.ProductionManager;
import data.myExceptions.AskingToWorkAtIllegalHourException;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.myExceptions.UnknownActivityException;
import data.planning.Activity;
import data.planning.DailyPlanner;
import data.structure.hability.Actionnable;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.TaskCompleteException;
import process.action.exception.structure.TaskNotNeededToBePerform;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.TaskGenerator;
import process.time.TimeManager;

public class TaskManager{
    private ArrayList<Task<?>> taskToBeLaunched = new ArrayList<>();
    private ArrayList<Task<?>> inProcess = new ArrayList<>();
    private ArrayList<Task<?>> taskCompleted = new ArrayList<>();
    private int currentHour;
    // private static TaskFactory taskFactory = TaskFactory.getInstance();
    private TimeManager timeManager = TimeManager.getInstance();
    private static TaskGenerator taskGenerator = new TaskGenerator();

    private static TaskManager taskManager = new TaskManager();
    
    
    // public TaskManager(RessourcesManager ressourcesManager, TimeManager // Pour l'automatisation des tâches et leur attribution à un personnage!
    public  TaskManager(){
        this.timeManager =TimeManager.getInstance();
        currentHour = timeManager.getClock().getHour().getValue();

    }

    public static TaskManager getInstance() {
    	return taskManager ;
    }

    public void addNewTask(int hour, Activity activity, Actionnable actionnable)
            throws UnableToGenerateNewTaskException, NotImplementYetException, TaskNotNeededToBePerform, UnknownActivityException, AskingToWorkAtIllegalHourException, UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException {
        if(!isAnHourOfWork()){
            throw new AskingToWorkAtIllegalHourException(currentHour);
        }
        Task<?> generatedTask = actionnable.launchAction(taskGenerator, activity);
        taskToBeLaunched.add(generatedTask);
    }

    public void addNewTask(Activity activity, Actionnable actionnable) throws UnableToGenerateNewTaskException, NotImplementYetException, TaskNotNeededToBePerform, UnknownActivityException, AskingToWorkAtIllegalHourException, UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException{
        addNewTask(currentHour,activity, actionnable);
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


    public void addToTaskToBeLaunched(Task<?> task){
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

    public ArrayList<Task<?>> getPossibleTaskToPerform(Actionnable actionnable) throws AskingToWorkAtIllegalHourException{
        if(!isAnHourOfWork()){
            throw new AskingToWorkAtIllegalHourException(currentHour);
        }
        ArrayList<Activity> possibleActivityToPerform = Activity.getPossibleActivity(actionnable.getASetOfAllActionnableKey());
        ArrayList<Task<?>> possibleTaskToPerform= new ArrayList<>();
        Iterator<Activity>  activitiesIter = possibleActivityToPerform.iterator();
        Task<?> task;
        while(activitiesIter.hasNext()){
            try {
                task = actionnable.launchAction(taskGenerator,  activitiesIter.next());
                possibleTaskToPerform.add(task);
            } catch (Exception e) {
            }
        }
        return possibleTaskToPerform;
    }



    private boolean isAnHourOfWork(){
        return true;
        // return DailyPlanner.FIRST_HOUR_OF_WORK <= currentHour && currentHour <= DailyPlanner.LAST_HOUR_OF_WORK;
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
