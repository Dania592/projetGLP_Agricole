package process.action.visitor.being;

import data.espece.faune.Chevre;
import data.espece.faune.Chien;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import process.action.exception.being.BeingCannotPerformSuchActionException;

public interface DomesticSpeciesVisitor<T>{
    T action(Chien chien) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException; 
    T action(Mouton mouton) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException; 
    T action(Poule poule) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException; 
    T action(Vache vache) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException;
    T action(Chevre chevre) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException;
    
}
