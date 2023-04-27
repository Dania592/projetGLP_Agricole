package process.action.task.action;

import data.acteur.Personne;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.SpecialActionPerformer;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.SpecialActionVisitor;

public class SpecialTask extends Task<SpecialActionPerformer> {
    private SpecialActionVisitor visitor; 

    // public SpecialTask(Activity activity, SpecialActionPerformer actionnableTarget, SpecialActionVisitor visitor, Personne personne)
    //         throws UnableToGenerateNewTaskException {
    //     super(activity, actionnableTarget, personne);
    //     this.visitor = visitor;
    // }

    
    public SpecialTask(Activity activity, SpecialActionPerformer actionnableTarget, SpecialActionVisitor visitor)
            throws UnableToGenerateNewTaskException {
        super(activity, actionnableTarget);
        this.visitor = visitor;
    }


    @Override
    protected void performAction()
            throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException,
            UnableToPerformSuchActionWithCurrentActionnable, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException {
                try {
                    getActionnableTarget().launchAction(visitor);
                } catch (UnableToPerformSuchActionWithCurrentActionnable e) {
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
