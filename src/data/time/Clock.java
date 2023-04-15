package data.time;

public class Clock {
    private static CyclicCounter hour = new CyclicCounter(0, 59, 0);
    private static CyclicCounter minute = new CyclicCounter(0, 59, 0);
    private static CyclicCounter second = new CyclicCounter(0, 59, 0);
    private static Clock reference;
   
    private Clock(){
        init(); 
    } 
	  
    // pourquoi le getInstance public ? 
    public static Clock getInstance(){
	    if(reference==null){
	    	reference = new Clock();
	    }
	    return reference; 
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
    