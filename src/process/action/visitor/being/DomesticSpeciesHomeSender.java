package process.action.visitor.being;

import data.espece.faune.Chevre;
import data.espece.faune.Chien;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.gestion.GestionnaireStructures;
import process.action.exception.being.BeingCannotPerformSuchActionException;

public class DomesticSpeciesHomeSender implements DomesticSpeciesVisitor<Void>{
    GestionnaireStructures  gestionnaireStructure;

    @Override
    public Void action(Chien chien) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        System.out.println("On renvoie dans sa maison");
        return null;
    }
    
    @Override
    public Void action(Mouton mouton) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        System.out.println("On renvoie dans sa maison le "+ mouton);
        return null;
    }
    
    @Override
    public Void action(Poule poule) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        System.out.println("On renvoie dans sa maison la "+ poule);
        return null;
    }
    
    @Override
    public Void action(Vache vache) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        System.out.println("On renvoie dans sa maison la "+ vache);
        return null;
    }

    @Override
    public Void action(Chevre chevre) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        System.out.println("On renvoie dans sa maison la "+ chevre);
        return null;

    }

    
}
