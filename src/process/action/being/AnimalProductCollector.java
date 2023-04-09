package process.action.being;

import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.production.Produit;
//TODO il faudra vérifier que on a les bon outils pour pouvoir récolterp
public class AnimalProductCollector implements ProductifSpeciesVisitor<Produit>{

    // @Override
    // public Produit action(Chevre chevre) throws HaveNotProducedYetException {
    //     if(chevre.haveProduced()){
    //         return chevre.collectProduction();
    //     }
    //     throw new HaveNotProducedYetException(chevre);
    // }

    // @Override
    // public Produit action(Mouton mouton) throws HaveNotProducedYetException {
    //     if(mouton.haveProduced()){
    //         return mouton.collectProduction();
    //     }
    //     throw new HaveNotProducedYetException(mouton);
    // }

    @Override
    public Produit action(Poule poule) throws HaveNotProducedYetException {
        if(poule.haveProduced()){
            return poule.collectProduction();
        }
        throw new HaveNotProducedYetException(poule);    }

    @Override
    public Produit action(Vache vache) throws HaveNotProducedYetException {
        if(vache.haveProduced()){
            return vache.collectProduction();
        }
        throw new HaveNotProducedYetException(vache);    }
    
    
}