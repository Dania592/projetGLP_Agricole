package process.action.task;

import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.Fixable;
import process.action.place.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.place.action.FixVisitor;

public class FixTask extends Task<Fixable> {
    FixVisitor visitor;

    public FixTask(Activity activity, Fixable actionnableTarget, FixVisitor visitor) throws UnableToGenerateNewTaskException {
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
