package process.action.task.launcher.transfert;

import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.Distributor;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
import process.action.visitor.place.transfert.EnclosureSenderVisitor;

public class SendToEnclosureTask extends Task<Distributor<?>>{
    EnclosureSenderVisitor visitor;

    public SendToEnclosureTask(Activity activity, Distributor<?> transportable, EnclosureSenderVisitor enclosureSender)
    throws UnableToGenerateNewTaskException {
        super(activity, transportable);
        this.visitor = enclosureSender;
        }

    @Override
    protected synchronized void performAction() throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, 
    BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException {
        getActionnableTarget().launchAction(visitor);
    }

    @Override
    protected void performSpecialActionToInitTask() {
    }

    @Override
    protected void performSpecialActionToTerminateTask() {
    }
    
}
