package process.action;


import java.util.ArrayList;
import java.util.concurrent.Executor;

import data.acteur.Personne;
import data.gestion.RessourcesManager;
import process.action.task.Task;
import process.time.TimeManager;

public class TaskManager{
    private RessourcesManager ressourcesManager;
    private TimeManager timeManager;
    private ArrayList<Task> taskToBeLaunched; 
    private ArrayList<Task> inProgress;
    private Executor taskExecutor;
    private ArrayList<Personne> workers;

    public TaskManager(RessourcesManager ressourcesManager, TimeManager timeManager, ArrayList<Personne> workers){
        this.ressourcesManager = ressourcesManager;
        this.timeManager = timeManager;
        this.workers = workers;
    }

    




    


    



     

    
}
