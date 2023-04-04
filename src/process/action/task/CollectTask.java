package process.action.task;

import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.Productif;
import process.action.place.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.place.action.CollectVisitor;

public class CollectTask extends Task<Productif> {
    CollectVisitor visitor;

    public CollectTask(Activity activity, Productif producif, CollectVisitor collector)
            throws UnableToGenerateNewTaskException {
        super(activity, producif);
        this.visitor = collector;
    }

    @Override
    protected void performAction(){
        try {
            getActionnableTarget().launchAction(visitor);
        } catch (UnableToPerformSuchActionWithCurrentActionnable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
