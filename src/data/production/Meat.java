package data.production;

import data.production.exception.NonExistantProduceException;
import gui.gestionnaire.keys.Animals;

public class Meat extends Produit {

    private final static float PRIX_VENTE = 100;

    public Meat(){
        super(Produits.MEAT, PRIX_VENTE);
    }

    
}
