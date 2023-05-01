package process.action.task.action;

import data.gestion.GestionnaireStocks;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.SpecialActionPerformer;
import gui.gestionnaire.keys.Graine;
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

    private Graine graine;
    
    public SpecialTask(Activity activity, SpecialActionPerformer actionnableTarget, SpecialActionVisitor visitor)
            throws UnableToGenerateNewTaskException {
        this(activity, actionnableTarget, Graine.AMARANTH_SEED, visitor);
    }

    public SpecialTask(Activity activity, SpecialActionPerformer actionnableTarget, Graine graine, SpecialActionVisitor visitor)
    throws UnableToGenerateNewTaskException {
        super(activity, actionnableTarget);
        this.visitor = visitor;
        this.graine = graine;
    }



    @Override
    protected void performAction()
            throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException,
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
        if(getActivity()==Activity.SHAVE_SHEEP){
            System.out.println("ICI c'est le bon message du gestionnaire : " + GestionnaireStocks.getInstance().toString());
        }if(getActivity()==Activity.SLAUGHTER){
            System.out.println("ICI c'est le bon message du gestionnaire : " + GestionnaireStocks.getInstance().toString());
        }if(getActivity()==Activity.MILK){
            System.out.println("ICI c'est le bon message du gestionnaire : " + GestionnaireStocks.getInstance().toString());
        }
    }
    
}
