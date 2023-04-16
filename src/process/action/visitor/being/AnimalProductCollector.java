package process.action.visitor.being;

import data.espece.faune.Chevre;
import data.espece.faune.Chien;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.production.Produit;
import process.action.exception.being.BeingCannotPerformSuchActionException;
//TODO il faudra vérifier que on a les bon outils pour pouvoir récolterp
public class AnimalProductCollector implements DomesticSpeciesVisitor<Produit>{

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

    @Override
    public Produit action(Chevre chevre) throws HaveNotProducedYetException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'action'");
    }

    @Override
    public Produit action(Chien chien) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        throw new BeingCannotPerformSuchActionException();
    }

    @Override
    public Produit action(Mouton mouton) throws HaveNotProducedYetException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'action'");
    }
    
    
}