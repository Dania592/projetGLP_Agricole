package process.action.task.action;

import data.espece.WaterConsumer;
import data.gestion.GestionnaireStocks;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.production.Produits;
import data.structure.hability.Hydratable;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.Task;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.HydrationVisitor;

public class GiveWaterTask extends Task<Hydratable>{
    HydrationVisitor visitor;

    public GiveWaterTask(Activity activity, Hydratable actionnableTarget, HydrationVisitor visitor) throws UnableToGenerateNewTaskException {
        super(activity, actionnableTarget);
        this.visitor = visitor;
    }

    @Override
    protected void performAction()
            throws HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException,
            UnableToPerformSuchActionWithCurrentActionnable, NeedToBeSendToSpecialProductionPlaceException,
            ProblemOccursInProductionException, UnableToMakeTheTransfertException {
        getActionnableTarget().launchAction(visitor);
    }

    @Override
    protected void performSpecialActionToInitTask(){}

    @Override
    protected void performSpecialActionToTerminateTask(){
        Integer oldWaterQuantity =GestionnaireStocks.getInstance().getProduits().get(Produits.WATER); 
        GestionnaireStocks.getInstance().getProduits().replace(Produits.WATER, oldWaterQuantity-1);
    }

    
}
