package data.structure.hability;



/**
 * Un Fixable est un {@link Actionnable} qui peut être Réparer.
 */
public interface Fixable extends Actionnable{
    
    public enum FixableState {
        USABLE,
        DAMAGED,
        DESTROYED;
        
        FixableState worsten() throws Exception{
            switch(this){
                case USABLE: 
                    return DAMAGED;
                case DAMAGED: 
                    return DESTROYED;  
                case DESTROYED:        
                default :
                throw new Exception("Unknown Structure State");
            }
        }
    
    
        FixableState better() throws Exception{
            switch(this){
                case USABLE:
                    throw new Exception("Current structure is already usable.");
                case DAMAGED: 
                        return USABLE; 
                case DESTROYED:
                    throw new Exception("Current structure is destroyed : it is no more usable!");
                default :
                throw new Exception("Unknown Structure State.");
            }
    
        }
        
    }
    boolean isNeedToBeFixed();
    void setState(FixableState newState);
    FixableState getState();

}