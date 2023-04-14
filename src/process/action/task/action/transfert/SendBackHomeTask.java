package process.action.task.action.transfert;

import data.planning.Activity;
import data.structure.hability.Distributor;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.HaveNotProducedYetException;
import process.action.visitor.place.transfert.HomeSenderVisitor;
import data.myExceptions.UnableToGenerateNewTaskException;
public class SendBackHomeTask extends Task<Distributor> {
    HomeSenderVisitor visitor;

    
    public SendBackHomeTask (Activity activity, Distributor transportable, HomeSenderVisitor homeSender) throws UnableToGenerateNewTaskException {
        super(activity, transportable);
        this.visitor = homeSender;
    }

    @Override
    protected void performAction() throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException {
        getActionnableTarget().launchAction(visitor);
    }
    
}
