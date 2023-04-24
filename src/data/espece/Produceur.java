package data.espece;

import data.notion.Mortel.EtatSante;
import data.production.Produits;
import data.time.CyclicCounter;


public interface Produceur extends DomesticSpecie{
        public enum ProductifState{
        UNABLE_TO_PRODUCE,
        PRODUCING,
        HAVE_PRODUCE,
        IN_WAIT_TO_BE_TRANSPORTED,
        IN_WAIT,
        ;
        //TODO A absoluement faire QUAND L'EVOLUTION PASSE EN MODE ADULTE il faut se la productivité
        
    }

    public enum Type{
        BAD_PRODUCEUR(1),
        AVERAGE_PRODUCEUR(2),
        GOOD_PRODUCEUR(4),
        FAST_PRODUCEUR(6),
        DOPED_PRODUCEUR(10),
        ;

        private int numberOfProductPerProductifCycle;

        private Type(int numberOfProductPerProductifCycle) {
            this.numberOfProductPerProductifCycle = numberOfProductPerProductifCycle;
        }

        public int getNumberOfProductPerProductifCycle() {
            return numberOfProductPerProductifCycle;
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
        CHEVRE(250, Type.AVERAGE_PRODUCEUR),
		MOUTON(300, Type.AVERAGE_PRODUCEUR),
		POULE(200, Type.AVERAGE_PRODUCEUR),
		VACHE(500, Type.AVERAGE_PRODUCEUR),
        TERRAIN(500, Type.AVERAGE_PRODUCEUR),

        ;
        
		private int timeInSeconde;
        private Type produceurType;
        private TimeItTakes(int timeInSeconde, Type produceurType) {
            this.timeInSeconde = timeInSeconde;
            this.produceurType = produceurType;
        }

        public int getTimeInSeconde() {
            return timeInSeconde;
        }

        public Type getProduceurType() {
            return produceurType;
        }
        public void setProduceurType(Type produceurType) {
            this.produceurType = produceurType;
        }

	}
	
    boolean haveProduced();
    Produits collectProduction();
    ProductifState getProductifState();
    Type getProduceurType();
    TimeItTakes getTimeItTakes();
    int getTimeItTakesToProduceInSeconde();
    void setProductifState(ProductifState productifState);
    boolean needSpecialPlaceToGetProduction();
    boolean needSpecialActionToGetProduction();
    CyclicCounter getProductionCycle();
    EtatSante getEtatSante();
    

}
