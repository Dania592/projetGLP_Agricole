package process.action.task;

import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.Feedable;
import process.action.place.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.place.action.FeedVisitor;

public class FeedingTask extends Task<Feedable> {
    FeedVisitor visitor;

    public FeedingTask(Activity activity, Feedable actionnableTarget, FeedVisitor visitor)
    throws UnableToGenerateNewTaskException {
        super(activity, actionnableTarget);
        this.visitor = visitor;
    }

    @Override
    protected void performAction() {
        try {
            getActionnableTarget().launchAction(visitor);
        } catch (UnableToPerformSuchActionWithCurrentActionnable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }    
}
