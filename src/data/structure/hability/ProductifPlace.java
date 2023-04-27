package data.structure.hability;

import java.util.HashMap;

import data.production.Produits;

public interface ProductifPlace extends Actionnable{
    boolean canLaunchProduction();
    boolean needPlayerIntervention();
    HashMap<Produits, Integer> getProduction();
    
}
