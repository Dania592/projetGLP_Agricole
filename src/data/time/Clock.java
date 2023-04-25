package data.time;

import java.io.Serializable;

public class Clock implements Serializable{
    private  CyclicCounter hour  ;
    private  CyclicCounter minute ;
    private  CyclicCounter  second ;
    //private static Clock reference = new Clock();
   
    public Clock( CyclicCounter hour , CyclicCounter minute , CyclicCounter second ){
    	this.hour = hour;
    	this.minute = minute ;
    	this.second = second ;
    } 
	  
    // pourquoi le getInstance public ? 
//    public static Clock getInstance(){
//    	return reference ;
//    }
	    
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
    