package process.action.being;

import data.espece.faune.Chevre;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;

//POur l'instant que les animax mais on pourra utiliser pour les arbres, les terrains...
public interface ProductifSpeciesVisitor<T>{
    // T action(Chevre chevre) throws HaveNotProducedYetException; 
    // T action(Mouton mouton) throws HaveNotProducedYetException; 
    T action(Poule poule) throws HaveNotProducedYetException; 
    T action(Vache vache) throws HaveNotProducedYetException; 
    
}
