package data.espece;

import data.notion.Mortel.EtatSante;

public interface WaterConsumer {

    public enum HydrationLevel{
        DESHYDRATED, 
        IN_DANGER, //TODO set to sick; => UNABLE_TO_PRODUCED => quand on soigne et que l'on est adulte il faut penser au fait de remettre l'animal à  PRODUCING  
        LOW,
        NORMAL, 
        FULLY_HYDRATED,
        ;

        public HydrationLevel decrease(){
            switch(this){
                default:
                case DESHYDRATED:
                    return DESHYDRATED;
                case IN_DANGER:
                    return DESHYDRATED;
                case LOW:
                    return IN_DANGER;
                case NORMAL:
                    return LOW;
                case FULLY_HYDRATED:
                    return NORMAL;
            }
        }

    }

    boolean isEnoughHydrated();
    
    
    
}
