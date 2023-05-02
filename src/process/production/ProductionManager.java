package process.production;

import java.util.ArrayList;
import java.util.Iterator;

import data.espece.characteristic.Produceur;
import data.espece.characteristic.Produceur.ProductifState;
import data.structure.hability.ProductifPlace;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;

/**
 * La classe ProductionManager gère le processus de production en parcourant une
 * liste de lieux productifs.
 * @see ProductifPlaces
 * @see ProductionPerformer
 */
public class ProductionManager {

    private static ProductionManager instance;
    private static ProductionPerformer productionMaker = new ProductionPerformer();
    private static ArrayList<ProductifPlace> productifList = new ArrayList<>();
    private static Iterator<ProductifPlace> productifIter;

    private ProductionManager() {
    }

    public static ProductionManager getInstance() {
        if (instance == null) {
            instance = new ProductionManager();
        }
        return instance;
    }

    /**
     * Gère le processus de production d'une liste de lieux de productif
     */
    public void manageProduction() {
        if (productifList.size() > 0) {
            productifIter = productifList.iterator();
            ProductifPlace currentProductif;
            while (productifIter.hasNext()) {
                try {
                    currentProductif = productifIter.next();
                    if (!(currentProductif.isNeedToBeFixed())) {
                        currentProductif.launchAction(productionMaker);
                    }
                } catch (NeedToBeSendToSpecialProductionPlaceException | ProblemOccursInProductionException
                        | HaveNotProducedYetException | BeingCannotPerformSuchActionException
                        | UnableToPerformSuchActionWithCurrentActionnable | UnableToMakeTheTransfertException e) {
                }
            }
        }
    }

    
    public void addToProductifList(ProductifPlace productifPlace) {
        productifList.add(productifPlace);
    }

    /**
     * Une entité productive peut être incapable de produire parce qu'elle est malade ou qu'il est trop jeune.
     * Cette méthode permet de redémarrer son cycle productif.
     * @param produceur {@link Produceur}
     */
    public void setToAbleProduceur(Produceur produceur) {
        produceur.setProductifState(ProductifState.PRODUCING);
    }

    public ArrayList<ProductifPlace> getProductifList() {
        return productifList;
    }

}
