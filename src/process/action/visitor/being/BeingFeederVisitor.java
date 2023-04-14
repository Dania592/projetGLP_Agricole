package process.action.visitor.being;

import data.espece.faune.Chevre;
import data.espece.faune.Chien;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
//TODO Il faudra vérifier que l'on a la bonne nourriture
public class BeingFeederVisitor implements DomesticSpeciesVisitor<Void> {

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

    @Override
    public Void action(Chevre chevre) throws HaveNotProducedYetException {
        System.out.println("On nourris "+ chevre);
        return null;
    }

    @Override
    public Void action(Chien chien) throws HaveNotProducedYetException {
        System.out.println("On nourris "+ chien);
        return null;
    }

    @Override
    public Void action(Mouton mouton) throws HaveNotProducedYetException {
        System.out.println("On nourris "+ mouton);
        return null;
    }

    
}
