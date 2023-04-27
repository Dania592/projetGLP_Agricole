package data.espece;

import data.myExceptions.DyingOfHungerException;
import data.notification.Messagerie;

public interface FoodConsumer extends DomesticSpecie{
    public enum HungerLevel{
    	STARVING,
		VERY_HUNGRY,
		HUNGRY, 
		NORMAL,
		FULL;

        public HungerLevel increase(){
            switch(this){
                case VERY_HUNGRY :
                    return HUNGRY;
                case HUNGRY:
                    return NORMAL;
                case NORMAL:
                    return FULL;
                case FULL:
                default:
                    return FULL;
            }
        }
        
        
        public HungerLevel decrease_1() {
        	switch(this) {
        	case STARVING : 
        		return STARVING;
        	case VERY_HUNGRY :
                return STARVING;
            case HUNGRY:
                return VERY_HUNGRY;
            case NORMAL:
                return HUNGRY;
            case FULL:
            default:
                return NORMAL;  
        	}
        }
    }

    HungerLevel getHungerLevel();
    boolean isHungry();

    
}
