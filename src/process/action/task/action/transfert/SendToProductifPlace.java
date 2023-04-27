package process.action.task.action.transfert;

import data.espece.Transportable;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.Distributor;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;

public class SendToProductifPlace extends Task<Distributor<?>> {

    public SendToProductifPlace(Activity activity, Distributor<?> actionnableTarget)
            throws UnableToGenerateNewTaskException {
        super(activity, actionnableTarget);
    }

    @Override
    protected synchronized void performAction() throws HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NotImplementYetException, UnableToPerformSuchActionWithCurrentActionnable,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException {
        throw new UnsupportedOperationException("Unimplemented method 'performAction'");
    }

    @Override
    protected void performSpecialActionToInitTask() {
    }

    @Override
    protected void performSpecialActionToTerminateTask() {
    }
    
}
