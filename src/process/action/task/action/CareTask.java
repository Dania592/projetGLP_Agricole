package process.action.task.action;

import data.espece.Produceur;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.ProductifPlace;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.CareVisitor;

public class CareTask extends Task<ProductifPlace> {
    private CareVisitor visitor;
    
    public CareTask(Activity activity, ProductifPlace actionnableTarget, CareVisitor careVisitor) throws UnableToGenerateNewTaskException {
        super(activity, actionnableTarget);
        visitor = careVisitor;
    }
    
    @Override
    protected void performAction()
            throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException,
            UnableToPerformSuchActionWithCurrentActionnable, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
        getActionnableTarget().launchAction(visitor);
    }

    @Override
    protected void performSpecialActionToInitTask() {
    }

    @Override
    protected void performSpecialActionToTerminateTask() {
    }
    
}
