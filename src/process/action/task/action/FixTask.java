package process.action.task.action;

import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.Fixable;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.FixVisitor;

public class FixTask extends Task<Fixable> {
    FixVisitor visitor;

    // public FixTask(Activity activity, Fixable actionnableTarget, FixVisitor visitor, Personne personne) throws UnableToGenerateNewTaskException {
    //     super(activity, actionnableTarget, personne);
    //     this.visitor = visitor;
    // }

    public FixTask(Activity activity, Fixable actionnableTarget, FixVisitor visitor) throws UnableToGenerateNewTaskException {
            super(activity, actionnableTarget);
            this.visitor = visitor;
    }
 
    @Override
    protected synchronized void performAction() throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException {
        try {
            getActionnableTarget().launchAction(visitor);
        } catch (UnableToPerformSuchActionWithCurrentActionnable e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void performSpecialActionToInitTask() {}

    @Override
    protected void performSpecialActionToTerminateTask() {
    }

    
}
