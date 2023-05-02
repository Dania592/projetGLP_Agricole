package process.action.task.launcher.basic;

import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.Feedable;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
import process.action.visitor.place.FeedVisitor;

public class FeedingTask extends Task<Feedable> {
    FeedVisitor visitor;

    public FeedingTask(Activity activity, Feedable actionnableTarget, FeedVisitor visitor)
    throws UnableToGenerateNewTaskException {
        super(activity, actionnableTarget);
        this.visitor = visitor;
    }

    @Override
    protected synchronized void performAction() throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException {
        try {
            getActionnableTarget().launchAction(visitor);
        } catch (UnableToPerformSuchActionWithCurrentActionnable e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void performSpecialActionToInitTask() {
    }

    @Override
    protected void performSpecialActionToTerminateTask() {
    }


}