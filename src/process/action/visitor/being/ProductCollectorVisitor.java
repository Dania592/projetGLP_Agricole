package process.action.visitor.being;

import data.espece.ProductionManager;
import data.espece.Produceur.ProductifState;
import data.espece.faune.AnimalProducteur;
import data.espece.faune.Chevre;
import data.espece.faune.Chien;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.production.Produit;
import process.action.exception.being.BeingCannotPerformSuchActionException;
//TODO il faudra vérifier que on a les bon outils pour pouvoir récolterp
public class ProductCollectorVisitor implements DomesticSpeciesVisitor<Produit>{
    
    public Produit getAnimalProduction(AnimalProducteur animal) throws HaveNotProducedYetException{
        if(animal.haveProduced()){
            animal.setProductifState(ProductifState.PRODUCING);
            ProductionManager.getInstance().removeProduceurFromHaveProducedProduceur(animal);
            return animal.collectProduction();
        }
        throw new HaveNotProducedYetException(animal);
    }

    @Override
    public Produit action(Poule poule) throws HaveNotProducedYetException {
        return getAnimalProduction(poule);
    }

    @Override
    public Produit action(Vache vache) throws HaveNotProducedYetException {
        return getAnimalProduction(vache);
    }

    @Override
    public Produit action(Chevre chevre) throws HaveNotProducedYetException {
        return getAnimalProduction(chevre);
    }

    @Override
    public Produit action(Chien chien) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
        throw new BeingCannotPerformSuchActionException();
    }

    @Override
    public Produit action(Mouton mouton) throws HaveNotProducedYetException {
        return getAnimalProduction(mouton);
    }

    // @Override
    // public Produit action(Terrain terrain) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'action'");
    // }
    
    
}