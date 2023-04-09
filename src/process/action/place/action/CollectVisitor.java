package process.action.place.action;

import java.util.ArrayList;
import java.util.Iterator;

import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.production.Produit;
import data.structure.Abatoire;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.SalleDeTraite;
import process.action.place.PlaceVisitor;
import process.action.place.UnableToPerformSuchActionWithCurrentActionnable;

public class CollectVisitor implements PlaceVisitor<ArrayList<Produit>>{
    ArrayList<Produit> products =  new ArrayList<>();


    @Override
    public ArrayList<Produit> action(Etable etable) {
        Iterator<Vache> vacheIter = etable.getAnimals().iterator();
        Vache currentVache;
        while(vacheIter.hasNext()){
            currentVache = vacheIter.next();
            if(currentVache.haveProduced()){
                products.add(currentVache.collectProduction());
            }
        }
        return products;
    }

    @Override
    public ArrayList<Produit> action(Poulallier poulallier) {
        Iterator<Poule> pouleIter = poulallier.getAnimals().iterator();
        Poule currentPoule;
        while(pouleIter.hasNext()){
            currentPoule = pouleIter.next();
            if(currentPoule.haveProduced()){
                products.add(currentPoule.collectProduction());
            }
        }
        return products;
    }

    @Override
    public ArrayList<Produit> action(Enclos enclos) {
        Iterator<Poule> poulesIter = enclos.getAnimalStorage().getPoules().iterator();
        Poule currentPoule;
        while(poulesIter.hasNext()){
            currentPoule = poulesIter.next();
            if(currentPoule.haveProduced()){
                products.add(currentPoule.collectProduction());
            }
        }
        return products;
    }

    @Override
    public ArrayList<Produit> action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(abatoire);
    }

    @Override
    public ArrayList<Produit> action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable{
        throw new UnableToPerformSuchActionWithCurrentActionnable(maison);
    }

    @Override
    public ArrayList<Produit> action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable{
        throw new UnableToPerformSuchActionWithCurrentActionnable(salleDeTraite);
    }

    @Override
    public ArrayList<Produit> action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(entrepot);
    }
    
}
