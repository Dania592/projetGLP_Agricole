package data.espece;

import java.util.ArrayList;
import java.util.Iterator;

import data.espece.Produceur.ProductifState;
import data.structure.Enclos;
import data.structure.hability.ProductifPlace;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.ProduceVisitor;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.ProductionPerformer;


public class ProductionManager {

    private static ProductionManager instance;
    private static ProductionPerformer productionMaker =  new ProductionPerformer(); 
    private static ArrayList<ProductifPlace> productifList = new ArrayList<>(); 
    private static Iterator<ProductifPlace> productifIter; 
    

    private ProductionManager(){}

    public static ProductionManager getInstance() {
        if(instance == null){
            instance = new ProductionManager();
        }
        return instance;
    }

    public void manageProduction(){
        if(productifList.size()>0){
            productifIter = productifList.iterator();
            ProductifPlace currentProductif;
            while(productifIter.hasNext()){
                try {
                    currentProductif =  productifIter.next();
                    if(!(currentProductif.isNeedToBeFixed())){
                        currentProductif.launchAction(productionMaker);
                    }
                } catch (NeedToBeSendToSpecialProductionPlaceException | ProblemOccursInProductionException | 
                HaveNotProducedYetException | BeingCannotPerformSuchActionException e) {
                    System.err.println(e.getMessage());
                } catch (UnableToPerformSuchActionWithCurrentActionnable e) {
                    e.printStackTrace();
                } catch (NotImplementYetException e) {
                    e.printStackTrace();
                } catch (UnableToMakeTheTransfertException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
            }
        }
    }


    public void addToProductifList(ProductifPlace productifPlace){
        productifList.add(productifPlace);
    }

    public void addToProductifList(Enclos enclos){
        productifList.add(enclos);
    }

    public void setToAbleProduceur(Produceur produceur){
        produceur.setProductifState(ProductifState.PRODUCING);
    }

    public ArrayList<ProductifPlace> getProductifList() {
        return productifList;
    }

}
