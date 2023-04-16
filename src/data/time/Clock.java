package data.time;

import java.io.Serializable;

public class Clock implements Serializable{
    private static CyclicCounter hour ;
    private static CyclicCounter minute ;
    private static CyclicCounter second ;
    private static Clock reference = new Clock();
   
    private Clock(){
    	hour =  new CyclicCounter(0, 59, 0);
    	minute = new CyclicCounter(0, 59, 0);
    	second = new CyclicCounter(0, 59, 0);
    	//init(); 
    } 
	  
    // pourquoi le getInstance public ? 
    public static Clock getInstance(){
    	return reference ;
    }
	    
    public void increment() {
        second.increment();
	    if (second.getValue() == 0) {
	    	minute.increment();
	    	if (minute.getValue() == 0) {
	    		hour.increment();
	    	}
	    }
	}
        
    public CyclicCounter getMinute() {
        return minute;
    }
    public CyclicCounter getHour() {
        return hour;
    }

    public CyclicCounter getSecond() {
    return second;
    }

    public String toString() {
    	return hour.toString() + " : " + minute.toString() + " : " + second.toString();
    }


    private void init() {
        hour.setValue(0);
        minute.setValue(0);
        second.setValue(0);
    }

}  
    