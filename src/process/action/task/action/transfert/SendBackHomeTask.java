package process.action.task.action.transfert;

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
import process.action.visitor.place.transfert.HomeSenderVisitor;
import data.acteur.Personne;
import data.myExceptions.UnableToGenerateNewTaskException;

public class SendBackHomeTask extends Task<Distributor<?>> {
    HomeSenderVisitor visitor;
    
    // public SendBackHomeTask (Activity activity, Distributor<?> transportable, HomeSenderVisitor homeSender, Personne personne) throws UnableToGenerateNewTaskException {
    //     super(activity, transportable, personne);
    //     this.visitor = homeSender;
    // }

    public SendBackHomeTask (Activity activity, Distributor<?> transportable, HomeSenderVisitor homeSender) throws UnableToGenerateNewTaskException {
        super(activity, transportable);
        this.visitor = homeSender;
    }



    @Override
    protected synchronized void performAction() throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException {
        getActionnableTarget().launchAction(visitor);
    }

    @Override
    protected void performSpecialActionToInitTask() {
    }

    @Override
    protected void performSpecialActionToTerminateTask(){

    }

}
