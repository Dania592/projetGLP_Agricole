package process.action;

import java.util.ArrayList;
import java.util.Iterator;

import data.configuration.GameConfiguration;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.Actionnable;
import process.action.exception.NotImplementYetException;
import process.action.exception.structure.TaskCompleteException;
import process.action.exception.structure.TaskNotNeededToBePerform;
import process.action.task.Task;
import process.time.TimeManager;

public class TaskManager extends Thread {
    private ArrayList<Task<?>> taskToBeLaunched = new ArrayList<>();
    private ArrayList<Task<?>> inProcess = new ArrayList<>();
    private ArrayList<Task<?>> taskCompleted = new ArrayList<>();
    private int currentHour;
    private static TaskFactory taskFactory = TaskFactory.getInstance();
    private TimeManager timeManager;
    // public TaskManager(RessourcesManager ressourcesManager, TimeManager // Pour l'automatisation des tâches et leur attribution à un personnage!
    public TaskManager(TimeManager timeManager){
        this.timeManager = timeManager;
        currentHour = timeManager.getClock().getHour().getValue();

    }

    public void addNewTask(int hour, Activity activity, Actionnable actionnable)
            throws UnableToGenerateNewTaskException, NotImplementYetException, TaskNotNeededToBePerform {
        Task<?> generatedTask = taskFactory.newTask(activity, actionnable);
        taskToBeLaunched.add(generatedTask);
    }

    public void addNewTask(Activity activity, Actionnable actionnable) throws UnableToGenerateNewTaskException, NotImplementYetException, TaskNotNeededToBePerform{
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

    public void run(){
        while(true){
            if(hourHaveChanged()){
                currentHour = timeManager.getClock().getHour().getValue();
            }
            addToTaskToinProcess();
            processingTaskInProgess();
            removeCompletedTask();
            try {
                Thread.sleep(GameConfiguration.GAME_SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

    public ArrayList<Task<?>> getPossibleTaskToPerform(Actionnable actionnable){
        ArrayList<Activity> possibleActivityToPerform = Activity.getPossibleActivity(actionnable.getActionnableKey());
        ArrayList<Task<?>> possibleTaskToPerform= new ArrayList<>();
        Iterator<Activity>  activitiesIter = possibleActivityToPerform.iterator();
        Task<?> task;
        while(activitiesIter.hasNext()){
            try {
                task = taskFactory.newTask(activitiesIter.next(), actionnable);
                possibleTaskToPerform.add(task);
            } catch (Exception e) {
                //TODO
                // System.err.println(e);
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

    public ArrayList<Task<?>> getinProcess() {
        return inProcess;
    }

}
