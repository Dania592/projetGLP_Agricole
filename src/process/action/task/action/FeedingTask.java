package process.action.task.action;

import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.Feedable;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.HaveNotProducedYetException;
import process.action.visitor.place.FeedVisitor;

public class FeedingTask extends Task<Feedable> {
    FeedVisitor visitor;

    public FeedingTask(Activity activity, Feedable actionnableTarget, FeedVisitor visitor)
    throws UnableToGenerateNewTaskException {
        super(activity, actionnableTarget);
        this.visitor = visitor;
    }

    @Override
    protected void performAction() throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException {
        try {
            getActionnableTarget().launchAction(visitor);
        } catch (UnableToPerformSuchActionWithCurrentActionnable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }    
}
