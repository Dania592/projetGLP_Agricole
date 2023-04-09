package process.action.task;


import data.configuration.GameConfiguration;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.Actionnable;


public abstract class Task<T extends Actionnable>{

    public enum TaskState{
        WAITING_TO_BE_LANCHED(0),
        JUST_BEGIN(1),
        IN_PROCESS(2),
        ALMOST_DONE(3),
        DONE(4);

        private int stage;
        private TaskState(int stage){
            this.stage = stage;
        }

        public TaskState update(){
            switch(this){
                case WAITING_TO_BE_LANCHED:
                    return JUST_BEGIN;
                case JUST_BEGIN:
                    return IN_PROCESS;
                case IN_PROCESS:
                    return ALMOST_DONE;
                case ALMOST_DONE:
                    return DONE;
                default :
                    return DONE;
            }
        }

        public int getStage() {
            return stage;
        }

        public static int getMaxStage(){
            return TaskState.values().length;
        }

    }
  
    private Activity activity;
    private TaskState state = TaskState.WAITING_TO_BE_LANCHED; 
    private T actionnableTarget;

    private long totalTime;


    public Task(Activity activity, T actionnableTarget) throws UnableToGenerateNewTaskException{
        this.activity = activity;
        totalTime = activity.getDuration()*GameConfiguration.GAME_SPEED*60000;
        this.actionnableTarget = actionnableTarget;
    }

    public Activity getActivity() {
        return activity;
    }

    public TaskState getState() {
        return state;
    }

    public T getActionnableTarget() {
        return actionnableTarget;
    }

    protected abstract void performAction();

    private long getTimeAStateTake(){
        return (long)(totalTime/TaskState.getMaxStage());
    }

    public void launch(){
        while(state!=TaskState.DONE){
            state.update();
            try {
                Thread.sleep(getTimeAStateTake());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        performAction();
    }

}
