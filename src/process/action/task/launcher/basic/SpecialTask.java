package process.action.task.launcher.basic;

import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.SpecialActionPerformer;
import gui.gestionnaire.keys.Graine;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
import process.action.visitor.place.SpecialActionVisitor;

public class SpecialTask extends Task<SpecialActionPerformer> {
    private SpecialActionVisitor visitor; 

    private Graine graine;
    
    public SpecialTask(Activity activity, SpecialActionPerformer actionnableTarget, SpecialActionVisitor visitor)
            throws UnableToGenerateNewTaskException {
        this(activity, actionnableTarget, null, visitor);
    }

    public SpecialTask(Activity activity, SpecialActionPerformer actionnableTarget, Graine graine, SpecialActionVisitor visitor)
    throws UnableToGenerateNewTaskException {
        super(activity, actionnableTarget);
        this.visitor = visitor;
        this.graine = graine;
    }



    public Graine getGraine() {
        return graine;
    }

    @Override
    protected void performAction()
            throws HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            UnableToPerformSuchActionWithCurrentActionnable, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
                try {
                    if(getActivity() == Activity.PLANT){
                        getActionnableTarget().launchAction(visitor, getActivity(), graine);
                    }else{
                        getActionnableTarget().launchAction(visitor);
                    }
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
