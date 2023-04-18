package process.action.visitor.place;

import java.util.ArrayList;
// import java.util.Iterator;
import java.util.Iterator;

import data.espece.faune.Poule;
import data.flore.terrains.Terrain;
// import data.espece.faune.Poule;
// import data.espece.faune.Vache;
import data.production.Produit;
import data.structure.Abatoire;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.SalleDeTraite;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.ProductCollectorVisitor;
import process.action.visitor.being.HaveNotProducedYetException;

// public class CollectVisitor implements PlaceVisitor<ArrayList<Produit>>{
//     ArrayList<Produit> products =  new ArrayList<>();


//     @Override
//     public ArrayList<Produit> action(Etable etable) {
//         Iterator<Vache> vacheIter = etable.getAnimals().iterator();
//         Vache currentVache;
//         while(vacheIter.hasNext()){
//             currentVache = vacheIter.next();
//             if(currentVache.haveProduced()){
//                 products.add(currentVache.collectProduction());
//             }
//         }
//         return products;
//     }

//     @Override
//     public ArrayList<Produit> action(Poulallier poulallier) {
//         Iterator<Poule> pouleIter = poulallier.getAnimals().iterator();
//         Poule currentPoule;
//         while(pouleIter.hasNext()){
//             currentPoule = pouleIter.next();
//             if(currentPoule.haveProduced()){
//                 products.add(currentPoule.collectProduction());
//             }
//         }
//         return products;
//     }

//     @Override
//     public ArrayList<Produit> action(Enclos enclos) {
//         Iterator<Poule> poulesIter = enclos.getAnimalStorage().getPoules().iterator();
//         Poule currentPoule;
//         while(poulesIter.hasNext()){
//             currentPoule = poulesIter.next();
//             if(currentPoule.haveProduced()){
//                 products.add(currentPoule.collectProduction());
//             }
//         }
//         return products;
//     }

//     @Override
//     public ArrayList<Produit> action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable {
//         throw new UnableToPerformSuchActionWithCurrentActionnable(abatoire);
//     }

//     @Override
//     public ArrayList<Produit> action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable{
//         throw new UnableToPerformSuchActionWithCurrentActionnable(maison);
//     }

//     @Override
//     public ArrayList<Produit> action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable{
//         throw new UnableToPerformSuchActionWithCurrentActionnable(salleDeTraite);
//     }

//     @Override
//     public ArrayList<Produit> action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
//         throw new UnableToPerformSuchActionWithCurrentActionnable(entrepot);
//     }
    
// }

public class CollectVisitor implements PlaceVisitor<Void>{
    ProductCollectorVisitor collector;
    ArrayList<Produit> products =  new ArrayList<>();


    
    public CollectVisitor(ProductCollectorVisitor collector) {
        this.collector = collector;
    }

    @Override
    public Void action(Etable etable) throws UnableToPerformSuchActionWithCurrentActionnable{
        throw new UnableToPerformSuchActionWithCurrentActionnable(etable);
    }
    
    @Override
    public Void action(Poulallier poulallier) throws BeingCannotPerformSuchActionException {
        Iterator<Poule> pouleIter = poulallier.getInHabitant().iterator();
        while(pouleIter.hasNext()){
            try {
                pouleIter.next().launchAction(collector);
            } catch (HaveNotProducedYetException e) {
                System.out.println("N'a pas encore produit");
            }   
        }
        return null;
    }

    @Override
    public Void action(Enclos enclos) {
        Iterator<Poule> pouleIter = enclos.getAnimalStorage().getPoules().iterator();
        while(pouleIter.hasNext()){
            try {
                pouleIter.next().launchAction(collector);
            } catch (HaveNotProducedYetException | BeingCannotPerformSuchActionException e) {
                System.out.println("N'a pas encore produit");
            }
        }
        return null;
    }

    @Override
    public Void action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(abatoire);
    }

    @Override
    public Void action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable{
        throw new UnableToPerformSuchActionWithCurrentActionnable(maison);
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable{
        System.out.println("Collecte les produit de l'enclos : "+ salleDeTraite);
        return null;
    }

    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(entrepot);
    }

    @Override
    public Void action(Terrain terrain) throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        System.out.println("Collecte les produit de du terrain : "+ terrain);
        return null;
    }
    
}
