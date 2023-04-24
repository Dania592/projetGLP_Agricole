package process.action.task.action;

import data.gestion.GestionnaireStocks;
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
import process.action.visitor.place.ProductionCollector;


public class CollectTask extends Task<ProductifPlace> {
    ProductionCollector visitor;

    public CollectTask(Activity activity, ProductifPlace producif, ProductionCollector collector) throws UnableToGenerateNewTaskException {
        super(activity, producif);
        this.visitor = collector;
    }

    @Override
    protected synchronized void performAction() throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException{
        try {
            getActionnableTarget().launchAction(visitor);
        } catch (UnableToPerformSuchActionWithCurrentActionnable e) {
            e.printStackTrace();
        }
        System.out.println("Produits du gestionnaire : " + GestionnaireStocks.getInstance().toString());
    }

    @Override
    protected void performSpecialActionToInitTask() {
    }

    @Override
    protected void performSpecialActionToTerminateTask() {
    }


}
