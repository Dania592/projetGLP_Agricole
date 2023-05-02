package data.espece.characteristic;



/**
 *  Les {@link data.espece.characteristic.DomesticSpecie} de la ferme implémentant cette interface devront recevoir de l' {@link data.production.Eau}.
 * @see Mouton 
 * @see Vache 
 * @see Chevre 
 * @see Poule 
 */
public interface WaterConsumer {
    public enum HydrationLevel{
        DEAD_FROM_DESHYDRATION,
        DESHYDRATED, 
        IN_DANGER,
        LOW,
        NORMAL, 
        FULLY_HYDRATED,
        ;

       /**
        * Cette fonction Java diminue le niveau d'hydratation d'un objet en fonction de son {@link data.espece.characteristic.WaterConsumer.HydrationLevel}
        * 
        * @return La méthode renvoie un nouveau {@link data.espece.characteristic.WaterConsumer.HydrationLevel} basé sur celui actuel. Le
        * niveau renvoyé est le niveau inférieur suivant en les différents {@link data.espece.characteristic.WaterConsumer.HydrationLevel}, à l'exception du
        * niveau le plus bas, qui renvoie DEAD_FROM_DESHYDRATION.
        */
        public HydrationLevel decrease(){
            switch(this){
                case IN_DANGER:
                    return DESHYDRATED;
                case LOW:
                    return IN_DANGER;
                case NORMAL:
                    return LOW;
                case FULLY_HYDRATED:
                    return NORMAL;
                default:
                case DESHYDRATED:
                        return DEAD_FROM_DESHYDRATION;
            }
        }

    }

    boolean isEnoughHydrated();
}
