
package process.production;

import data.espece.characteristic.Produceur.ProductifState;
import data.espece.faune.AnimalProducteur;
import data.espece.faune.Chevre;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.espece.flore.terrains.Terrain;
import data.production.Produit;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.DomesticSpeciesVisitor;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;

public class ProductCollectorVisitor implements DomesticSpeciesVisitor<Produit>{
    
    public Produit getAnimalToProduce(AnimalProducteur animal) throws HaveNotProducedYetException{
        if(animal.getProductifState() == ProductifState.HAVE_PRODUCE){
                animal.setProductifState(ProductifState.PRODUCING);
        }
        throw new HaveNotProducedYetException(animal);
    }

    @Override
    public Produit action(Poule poule) throws HaveNotProducedYetException {
        return getAnimalToProduce(poule);
    }

    @Override
    public Produit action(Vache vache) throws HaveNotProducedYetException {
        return getAnimalToProduce(vache);
    }

    @Override
    public Produit action(Chevre chevre) throws HaveNotProducedYetException {
        return getAnimalToProduce(chevre);
    }

    @Override
    public Produit action(Mouton mouton) throws HaveNotProducedYetException {
        return getAnimalToProduce(mouton);
    }

    @Override
    public Produit action(Terrain terrain) throws HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException{
        throw new BeingCannotPerformSuchActionException();
    }
    
    
}