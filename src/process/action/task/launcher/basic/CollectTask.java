package process.action.task.launcher.basic;

import data.gestion.GestionnaireStocks;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.hability.ProductifPlace;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
import process.production.ProductionCollector;


public class CollectTask extends Task<ProductifPlace> {
    ProductionCollector visitor;

    public CollectTask(Activity activity, ProductifPlace producif, ProductionCollector collector) throws UnableToGenerateNewTaskException {
        super(activity, producif);
        this.visitor = collector;
    }

    
    // public CollectTask(Activity activity, ProductifPlace producif, ProductionCollector collector, Personne personne) throws UnableToGenerateNewTaskException {
    //     super(activity, producif, personne);
    //     this.visitor = collector;
    // }

    @Override
    protected synchronized void performAction() throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException{
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
