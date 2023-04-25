package test;

import java.util.ArrayList;

import data.configuration.GameConfiguration;
import data.espece.Milieu;
import data.espece.faune.Poule;
import data.gestion.RessourcesManager;
import data.map.Map;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.Enclos;
import data.structure.Etable;
import data.structure.Poulallier;
import data.structure.hability.Actionnable;
import data.structure.hability.Fixable.FixableState;
import process.action.TaskManager;
import process.action.exception.NotImplementYetException;
import process.action.exception.structure.TaskNotNeededToBePerform;
import process.action.task.Task;
import process.time.TimeManager;

public class TestTaskManager {
    public static ArrayList<Task<?>> taskToAddToManager = new ArrayList<>();
    public static ArrayList<Actionnable> listOfActionnable = new ArrayList<>();
    public static Map testMap = Map.getInstance();
    public static TaskManager taskManager;
    public static TimeManager timeManager =TimeManager.getInstance();
    public static RessourcesManager ressourcesManager =  RessourcesManager.getInstance();

    public static Poulallier poulallier =   new Poulallier(0, 0, "poulepoule", testMap);
    public static Etable etable =   new Etable(0, 0, "vavache", testMap);
    public static Enclos enclos =   new Enclos(0, 0, "enclos", testMap);

    public static Poule poule1 = new Poule(0,0,Milieu.ESPACE, 4, "pape", "female", poulallier, "C'est ma ref", testMap );
    public static Poule poule2 = new Poule(0,0,Milieu.ESPACE, 4, "pape", "female", poulallier, "C'est ma ref", testMap );
    public static Poule poule3 = new Poule(0,0,Milieu.ESPACE, 4, "pape", "female", poulallier, "C'est ma ref", testMap );
    public static Poule poule4 = new Poule(0,0,Milieu.ESPACE, 4, "pape", "female", poulallier, "C'est ma ref", testMap );
    public static Poule poule5 = new Poule(0,0,Milieu.ESPACE, 4, "pape", "female", poulallier, "C'est ma ref", testMap );

    public static ArrayList<Task<?>> tasks  = new ArrayList<>();

    public static void main(String[] args) {
        timeManager.start();
        poulallier.addInHabitant(poule1);
        poulallier.addInHabitant(poule2);
        poulallier.addInHabitant(poule3);
        poulallier.addInHabitant(poule4);
        enclos.addAnimal(poule5);
        
        etable.setState(FixableState.DAMAGED);
        
        listOfActionnable.add(poulallier);
        listOfActionnable.add(etable);
        listOfActionnable.add(enclos);
        taskManager = TaskManager.getInstance();
        taskManager.start();


        ArrayList<Task<?>> tempTask;
        for (Actionnable actionnable : listOfActionnable) {
            System.out.println(
                    "------------------------ " + actionnable.getActionnableKey() + "------------------------ ");
            tempTask = taskManager.getPossibleTaskToPerform(actionnable);
            if(!tempTask.isEmpty()){
                tasks.addAll(tempTask);
            }
        }
        System.out.println(
                    "------------------------------ ON LANCE CES ACTIONS ------------------------------------------ ");
            
        for (Task<?> task : tasks) {
            System.out.println(task);
        }
        System.out.println(
            "------------------------------------------------------------------------ ");
        if(timeManager.getClock().getHour().getValue()>10){
            System.out.println("FIN");
        }
        
        System.out.println("//////////////////////////////-------------------- /////////////////////// TEST START //////////////////////////////-------------------- /////////////////////// ");
        for (Task<?> task : tasks) {
            taskManager.addToTaskToBeLaunched(task);
        }
    }



    public static int getRandomInt(int min, int max) {
        return min + (int) Math.random() * (max - 1 - min);
    }

    public static Actionnable getRandomActionnable() {
        int randomIndex = getRandomInt(0, listOfActionnable.size());
        return listOfActionnable.get(randomIndex);
    }

    public static Activity getRandomActivity(Actionnable actionnable) {
        ArrayList<Activity> possibleActivtiesToPerform = Activity.getPossibleActivity(actionnable.getActionnableKey());
        int randomTaskIndex = getRandomInt(0, possibleActivtiesToPerform.size());
        return possibleActivtiesToPerform.get(randomTaskIndex);
    }

    public static void addRandomTaskToTaskManager() throws UnableToGenerateNewTaskException, NotImplementYetException, TaskNotNeededToBePerform {
        Actionnable randomActionnable = getRandomActionnable();
        Activity randomActivity = getRandomActivity(randomActionnable);
        taskManager.addNewTask(randomActivity, randomActionnable);
    }

}
