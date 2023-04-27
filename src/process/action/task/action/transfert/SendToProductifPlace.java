package process.action.task.action.transfert;

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
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.transfert.ProductifPlaceSender;

public class SendToProductifPlace extends Task<Distributor<?>> {
    ProductifPlaceSender visitor;

    // public SendToProductifPlace(Activity activity, Distributor<?> actionnableTarget, Personne personne)
    //         throws UnableToGenerateNewTaskException {
    //     super(activity, actionnableTarget, personne);
    // }

    public SendToProductifPlace(Activity activity, Distributor<?> actionnableTarget, ProductifPlaceSender productifSender)
            throws UnableToGenerateNewTaskException {
        super(activity, actionnableTarget);
        visitor = productifSender;
    }

    @Override
    protected synchronized void performAction() throws HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NotImplementYetException, UnableToPerformSuchActionWithCurrentActionnable,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException {
        try {
            getActionnableTarget().launchAction(visitor);
        } catch (UnableToMakeTheTransfertException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void performSpecialActionToInitTask() {
    }

    @Override
    protected void performSpecialActionToTerminateTask() {
    }
    
}
