package process.action.task.launcher.basic;

import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.HealablePlace;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
import process.action.visitor.place.HealerVisitor;

public class HealTask extends Task<HealablePlace> {
    HealerVisitor visitor;

    public HealTask(Activity activity, HealablePlace actionnableTarget, HealerVisitor visitor) throws UnableToGenerateNewTaskException {
        super(activity, actionnableTarget);
        this.visitor = visitor;  
    }
    
    @Override
    protected void performAction()
            throws HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            UnableToPerformSuchActionWithCurrentActionnable, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
        getActionnableTarget().launchAction(visitor);
    }

    @Override
    protected void performSpecialActionToInitTask() {
    }

    @Override
    protected void performSpecialActionToTerminateTask() {
    }
    
}
