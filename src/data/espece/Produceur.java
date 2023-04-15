package data.espece;

import data.production.Produit;

public interface Produceur extends DomesticSpecie{
    public enum ProductifState{
        UNABLE_TO_PRODUCE,
        PRODUCING,
        HAVE_PRODUCE;

        public ProductifState evolve(){
            switch(this){
                case UNABLE_TO_PRODUCE:
                    return PRODUCING;
                case PRODUCING:
                    return HAVE_PRODUCE;
                case HAVE_PRODUCE:
                    return PRODUCING;
                default : 
                    return PRODUCING; 
            }
        }

    }

    boolean haveProduced();
    Produit collectProduction(); 
}
