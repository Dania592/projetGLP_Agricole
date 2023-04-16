package data.espece;

import data.myExceptions.DyingOfHungerException;

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
        
        public HungerLevel decrease() throws DyingOfHungerException{
            switch(this){
                case VERY_HUNGRY :
                    throw new DyingOfHungerException();
                case HUNGRY:
                    return VERY_HUNGRY;
                case NORMAL:
                    return HUNGRY;
                case FULL:
                default:
                    return NORMAL;           
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
    void feed();
    boolean isHungry();

    
}
