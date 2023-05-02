package data.structure.hability;

import java.util.HashMap;

import data.production.Produits;
/**
 * Une ProductifPlace est un {@link Actionnable} peut générer des {@link Produit}
 */
public interface ProductifPlace extends Actionnable{
    boolean canLaunchProduction();
    boolean needPlayerIntervention();
    HashMap<Produits, Integer> getProduction();
    
}
