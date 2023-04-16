package process.action.task.action;

import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.Fixable;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.HaveNotProducedYetException;
import process.action.visitor.place.FixVisitor;

public class FixTask extends Task<Fixable> {
    FixVisitor visitor;

    public FixTask(Activity activity, Fixable actionnableTarget, FixVisitor visitor) throws UnableToGenerateNewTaskException {
        super(activity, actionnableTarget);
        this.visitor = visitor;
    }

    @Override
    protected void performAction() throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException {
        try {
            getActionnableTarget().launchAction(visitor);
        } catch (UnableToPerformSuchActionWithCurrentActionnable e) {
            e.printStackTrace();
        }
    }
    
}
