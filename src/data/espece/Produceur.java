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
                    return DOPED_PRODUCEUR;
                case DOPED_PRODUCEUR :
                    return DOPED_PRODUCEUR;
                default:
                    return AVERAGE_PRODUCEUR;
            }
        }

        public Type downgradeProduceurType(){
            switch(this){
            case BAD_PRODUCEUR :
                return BAD_PRODUCEUR;
            case AVERAGE_PRODUCEUR :
                return BAD_PRODUCEUR;
            case DOPED_PRODUCEUR :
                return AVERAGE_PRODUCEUR;
            default:
                return AVERAGE_PRODUCEUR;

            }
        }
        
    }

	public enum TimeItTakes{
        CHEVRE(35, Type.AVERAGE_PRODUCEUR),
		MOUTON(200, Type.AVERAGE_PRODUCEUR),
		POULE(10, Type.AVERAGE_PRODUCEUR),
		VACHE(50, Type.AVERAGE_PRODUCEUR),
        TERRAIN(100, Type.AVERAGE_PRODUCEUR),

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
    int getProcuedQuantity();
	void setProduceurType(Type upgradeProduceurType);
    public boolean isDoped();
    public void setDoped(boolean isDoped);

}
