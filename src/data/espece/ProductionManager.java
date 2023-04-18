package data.espece;

import java.util.ArrayList;
import java.util.Iterator;

import data.espece.Produceur.ProductifState;
import data.espece.faune.Animal;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.visitor.being.HaveNotProducedYetException;
import process.action.visitor.being.ProduceVisitor;


public class ProductionManager {

    private static ProductionManager instance;
    private static ArrayList<Produceur> producingProduceurs = new ArrayList<>();
    private static ArrayList<Produceur> haveProducedProduceurs = new ArrayList<>();
    private static Iterator<Produceur> produceurIt;
    private static ProduceVisitor productionMaker =  new ProduceVisitor(); 

    private ProductionManager(){}

    public static ProductionManager getInstance() {
        if(instance == null){
            instance = new ProductionManager();
        }
        return instance;
    }
    
    public ArrayList<Produceur> getProducingProduceurs() {
        return producingProduceurs;
    }

    public ArrayList<Produceur> getHaveProducedProduceurs() {
        return haveProducedProduceurs;
    }

    public void addProduceur(Produceur produceur){
        if(produceur.getProductifState() == ProductifState.UNABLE_TO_PRODUCE){
            produceur.setProductifState(ProductifState.PRODUCING);
        }
        producingProduceurs.add(produceur);
    }

    public boolean contains(Produceur produceur){
        return producingProduceurs.contains(produceur) || haveProducedProduceurs.contains(produceur);
    }

    public void removeFromProductionManager(Produceur produceur){
        producingProduceurs.remove(produceur);
        haveProducedProduceurs.remove(produceur);
    }

    public void manageProduction(){
        produceurIt = producingProduceurs.iterator();
        Produceur currentProduceur;
        while(produceurIt.hasNext()){
            try {
                currentProduceur = produceurIt.next(); 
                currentProduceur.launchAction(productionMaker);
                if(currentProduceur.haveProduced()){
                    haveProducedProduceurs.add(currentProduceur);
                }
            } catch (HaveNotProducedYetException | BeingCannotPerformSuchActionException e) {
                System.err.println(e.getMessage());
            } 
            removeHaveProducedProducteurFromProducingProducteur();
        }
    }

    private void removeHaveProducedProducteurFromProducingProducteur(){
        produceurIt = haveProducedProduceurs.iterator();
        while(produceurIt.hasNext()){
            producingProduceurs.remove(produceurIt.next());
        }
    }

    public void removeProduceurFromHaveProducedProduceur(Produceur produceur){
        haveProducedProduceurs.remove(produceur);
        addProduceur(produceur);
    }




    

}
