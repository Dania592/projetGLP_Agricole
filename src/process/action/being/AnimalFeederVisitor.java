package process.action.being;

import data.espece.faune.Poule;
import data.espece.faune.Vache;
//TODO Il faudra vérifier que l'on a la bonne nourriture
public class AnimalFeederVisitor implements ProductifSpeciesVisitor<Void> {

    // @Override
    // public Void action(Chevre chevre) throws HaveNotProducedYetException {
    //     chevre.feed();    
    //     return null;
    // }

    // @Override
    // public Void action(Mouton mouton) throws HaveNotProducedYetException {
    //     mouton.feed();
    //     return null;
    // }

    @Override
    public Void action(Poule poule) throws HaveNotProducedYetException {
        poule.feed();
        return null;
    }

    @Override
    public Void action(Vache vache) throws HaveNotProducedYetException {
        vache.feed();
        return null;
    }

    
}
