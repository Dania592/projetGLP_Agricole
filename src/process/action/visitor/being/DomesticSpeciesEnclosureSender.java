package process.action.visitor.being;

import data.espece.faune.Chevre;
import data.espece.faune.Chien;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.gestion.GestionnaireStructures;
import process.action.exception.being.BeingCannotPerformSuchActionException;

public class DomesticSpeciesEnclosureSender implements DomesticSpeciesVisitor<Void>{
    GestionnaireStructures gestionnaireStructure;

    @Override
    public Void action(Chien chien) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        throw new BeingCannotPerformSuchActionException(); 
    }

    @Override
    public Void action(Mouton mouton) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        System.out.println("On envoie à l'enclos: "+ mouton);
        return null;
    }

    @Override
    public Void action(Poule poule) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        System.out.println("On envoie à l'enclos: "+ poule);
        return null;
    }

    @Override
    public Void action(Vache vache) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        System.out.println("On envoie à l'enclos: "+ vache);
        return null;
    }

    @Override
    public Void action(Chevre chevre) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        System.out.println("On envoie à l'enclos: "+ chevre);
        return null;
    }

}
