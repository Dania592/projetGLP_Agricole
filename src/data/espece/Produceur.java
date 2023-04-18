package data.espece;

import data.production.Produit;

public interface Produceur extends DomesticSpecie{
        public enum ProductifState{
        UNABLE_TO_PRODUCE,
        PRODUCING,
        HAVE_PRODUCE,
        ;
        //TODO A absoluement faire QUAND L'EVOLUTION PASSE EN MODE ADULTE il faut se la productivité
        
    }

    public enum Type{
        BAD_PRODUCEUR(7),
        AVERAGE_PRODUCEUR(1),
        GOOD_PRODUCEUR(3),
        FAST_PRODUCEUR(2),
        DOPED_PRODUCEUR(1),
        ;

        private int productionTimeMultiplier;

        private Type(int productionMultiplier) {
            this.productionTimeMultiplier = productionMultiplier;
        }

        public int getProductionTimeMultiplier() {
            return productionTimeMultiplier;
        }



        public Type upgradeProduceurType(){
            switch(this){
                case BAD_PRODUCEUR : 
                    return AVERAGE_PRODUCEUR;
                case AVERAGE_PRODUCEUR : 
                    return GOOD_PRODUCEUR;
                case GOOD_PRODUCEUR : 
                    return FAST_PRODUCEUR;
                case FAST_PRODUCEUR : 
                case DOPED_PRODUCEUR : 
                default: 
                    return DOPED_PRODUCEUR;
            }
        }

        public Type downgradeProduceurType(){
            switch(this){
                case BAD_PRODUCEUR : 
                case AVERAGE_PRODUCEUR : 
                default :
                    return BAD_PRODUCEUR;
                case GOOD_PRODUCEUR : 
                    return AVERAGE_PRODUCEUR;
                case FAST_PRODUCEUR : 
                    return GOOD_PRODUCEUR;
                case DOPED_PRODUCEUR: 
                    return FAST_PRODUCEUR;
            }
        }
        
    }

	public enum TimeItTakes{
    CHEVRE(1000, Type.AVERAGE_PRODUCEUR),
		MOUTON(3300, Type.AVERAGE_PRODUCEUR),
		POULE(1000, Type.AVERAGE_PRODUCEUR),
		VACHE(100, Type.AVERAGE_PRODUCEUR),
        ;
		private int timeInSeconde;
        private Type produceurType;
        private TimeItTakes(int timeInSeconde, Type produceurType) {
            this.timeInSeconde = timeInSeconde;
            this.produceurType = produceurType;
        }

        public int getTimeInSeconde() {
            return timeInSeconde*produceurType.getProductionTimeMultiplier();
        }

        public Type getProduceurType() {
            return produceurType;
        }
        public void setProduceurType(Type produceurType) {
            this.produceurType = produceurType;
        }
		

	}
	
    boolean haveProduced();
    Produit collectProduction();
    ProductifState getProductifState();
    Type getProduceurType();
    TimeItTakes getTimeItTakes();
    int getTimeItTakesToProduceInSeconde();
    void setProductifState(ProductifState productifState);
     

}
