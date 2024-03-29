package process.action.task;

import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.Actionnable;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.TaskCompleteException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
/**
 * L'ensemble des {@link Activity} est lançable depuis des {@link Actionnable}. 
 * Les {@link Task} permettent de représenter les {@link Activity} de les lancer/exécuter depuis de {@link TaskManager}  
 */
public abstract class Task<T extends Actionnable> {

    public enum TaskState {
        WAITING_TO_BE_LANCHED(0f),
        JUST_BEGIN(1f),
        IN_PROCESS(2f),
        MORE_THAT_HALF(3f),
        ALMOST_DONE(4f),
        DONE(5f);

        private float stage;

        private TaskState(float stage) {
            this.stage = stage;
        }

        public TaskState update() {
            switch (this) {
                case WAITING_TO_BE_LANCHED:
                    return JUST_BEGIN;
                case JUST_BEGIN:
                    return IN_PROCESS;
                case IN_PROCESS:
                    return MORE_THAT_HALF;
                case MORE_THAT_HALF:
                    return ALMOST_DONE;
                case ALMOST_DONE:
                default:
                    return DONE;
            }
        }

        public float getStage() {
            return stage;
        }

        public static int getLastState() {
            return TaskState.values().length-2;
        }

        public static TaskState getMatchingStage(int index){
            return TaskState.values()[index];
        }

    }

    private TaskState state = TaskState.WAITING_TO_BE_LANCHED;
    private T actionnableTarget;
    private Activity activity; 
    private long totalTime;
    private long timeSpend = 0;

    public Task(Activity activity, T actionnableTarget) throws UnableToGenerateNewTaskException{
        this.actionnableTarget = actionnableTarget;
        totalTime = getTotalTimeInMilisFromTimeGivenInMinutes(activity.getDuration());
        this.activity = activity;
    }


    public synchronized Activity getActivity() {
        return activity;
    }
    
    public synchronized TaskState getState() {
        return state;
    }

    public synchronized T getActionnableTarget() {
        return actionnableTarget;
    }

    protected abstract void performAction() throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, UnableToPerformSuchActionWithCurrentActionnable, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException;
    protected abstract void performSpecialActionToInitTask();
    protected abstract void performSpecialActionToTerminateTask();

    public void process() throws TaskCompleteException{
        timeSpend+= 1000;
        if(state == TaskState.WAITING_TO_BE_LANCHED){
            getActionnableTarget().setStructureStatus(true);
            performSpecialActionToInitTask();
        }
        if(updateTaskStatus()){
            state = state.update();
        }if(state == TaskState.DONE){
            try {
                performAction();
            } catch (HaveNotProducedYetException | BeingCannotPerformSuchActionException 
        | UnableToPerformSuchActionWithCurrentActionnable | NeedToBeSendToSpecialProductionPlaceException | ProblemOccursInProductionException temp) {
                temp.printStackTrace();
            } catch (UnableToMakeTheTransfertException e) {
                e.printStackTrace();
            } catch (UnableToGenerateNewTaskException e) {
                e.printStackTrace();
            }
            getActionnableTarget().setStructureStatus(false);
            performSpecialActionToTerminateTask();
            throw new TaskCompleteException(this);
        }
    }


    private boolean updateTaskStatus(){
        return timeSpend >= (totalTime*(state.getStage()/(TaskState.getLastState())));
    }


    private long getTotalTimeInMilisFromTimeGivenInMinutes(int timeGivenInMinutes){
        return timeGivenInMinutes*60*1000;
    }

    @Override
    public String toString() {
        return activity.getLabel()+ "total : "+ activity.getDuration(); 
    }

    public long getTimeSpend() {
        return timeSpend;
    }

    @Override
    public synchronized boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task<?> other = (Task<?>) obj;
        if (actionnableTarget == null) {
            if (other.actionnableTarget != null)
                return false;
        } else if (!actionnableTarget.equals(other.actionnableTarget))
            return false;
        if (totalTime != other.totalTime)
            return false;
        return true;
    }

    public long getTotalTime() {
        return totalTime;
    }


}
