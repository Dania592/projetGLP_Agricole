package process.action.task.action.transfert;

import data.myExceptions.UnableToGenerateNewTaskException;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.HaveNotProducedYetException;
import process.action.visitor.place.transfert.EnclosureSenderVisitor;
import data.planning.Activity;
import data.structure.hability.Distributor;

public class SendToEnclosureTask extends Task<Distributor>{
    EnclosureSenderVisitor visitor;

    public SendToEnclosureTask(Activity activity, Distributor transportable, EnclosureSenderVisitor enclosureSender)
            throws UnableToGenerateNewTaskException {
        super(activity, transportable);
        this.visitor = enclosureSender;
    }

    @Override
    protected void performAction() throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException {
        getActionnableTarget().launchAction(visitor);
    }
    
}
