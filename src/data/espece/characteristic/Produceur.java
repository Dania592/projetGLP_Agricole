package data.espece.characteristic;

import data.notion.Mortel.EtatSante;
import data.production.Produits;
import data.time.CyclicCounter;


/**
 * Les espèces domestiquées implémentant cette interface seront générateurs de {@link Produceur}
 * Les {@link Produceur} sont caractérisés par un cycle de production de durée relative à leur espèce.
 * Durant ce dernier, ils passent par différents {@link ProductifState} et peuvent changer de {@link Type}. 
 * @see data.espece.faune.Vache
 * @see data.espece.faune.Mouton
 * @see data.espece.faune.Chevre
 * @see data.espece.faune.Poule
 * @see data.espece.faune.Terrain
 * @see data.production.Produits
 */
public interface Produceur extends DomesticSpecie{  
    /**  Il s'agit d'une énumération qui représente les différents états dans lesquels un objet
    * {@link Produceur} peut se trouver au cours de son cycle de production. Ces dernniers
    * indiquent si le {@link Produceur} produit actuellement, a produit, est en attente d'être 
    * transféré vers une {@link data.structure.hability.ProductifPlace} ou en attente de l'intervention du joueur.
    */
    public enum ProductifState{
        UNABLE_TO_PRODUCE,
        PRODUCING,
        HAVE_PRODUCE,
        IN_WAIT_TO_BE_TRANSPORTED,
        IN_WAIT,
        ;
    }
    /**
     * Le Type de {@link Produceur} permet de déterminer la quantité produite de {@link Produits}
     */
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


        /**
         * Cette fonction met à jour le type d'un {@link Produceur} positivement
         * @return retourne {@link Produceur.Type}
         */
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
        
        /**
         * Cette fonction met à jour le type d'un {@link Produceur} négativement
         * @return retourne {@link Produceur.Type}
         */
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

    /**
     * Cette enumération permet de déterminer la durée d'un cycle de production d'un {@link data.espece.characteristic.Produceur} 
     */
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
