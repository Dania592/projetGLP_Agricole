package process.action.task.action;

import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.Productif;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.HaveNotProducedYetException;
import process.action.visitor.place.CollectVisitor;

public class CollectTask extends Task<Productif> {
    CollectVisitor visitor;

    public CollectTask(Activity activity, Productif producif, CollectVisitor collector) throws UnableToGenerateNewTaskException {
        super(activity, producif);
        this.visitor = collector;
    }

    @Override
    protected void performAction() throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException{
        try {
            getActionnableTarget().launchAction(visitor);
        } catch (UnableToPerformSuchActionWithCurrentActionnable e) {
            e.printStackTrace();
        }
    }

}
