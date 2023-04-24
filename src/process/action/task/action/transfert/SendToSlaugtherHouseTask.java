package process.action.task.action.transfert;

import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.SlaughterHouseSender;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.transfert.SendToSlaughterHouseVisitor;

public class SendToSlaugtherHouseTask extends Task<SlaughterHouseSender> {
    SendToSlaughterHouseVisitor visitor;

    public SendToSlaugtherHouseTask(Activity activity, SlaughterHouseSender slaughtable, SendToSlaughterHouseVisitor slaughterHouseSender ) throws UnableToGenerateNewTaskException {
        super(activity, slaughtable);
        this.visitor = slaughterHouseSender;
    }

    @Override
    protected synchronized void performAction() throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException {
        getActionnableTarget().launchAction(visitor);
    }

    @Override
    protected void performSpecialActionToInitTask() {

    }

    @Override
    protected void performSpecialActionToTerminateTask() {
    }
    
}
