package data.myExceptions;

import data.planning.Activity;
import data.planning.TimeSlot;

public class UnAvalableTimeSlot extends Exception {
    
    public UnAvalableTimeSlot(TimeSlot timeSlot){
        super("Time slot" +timeSlot.getStartHour() +"H - "+timeSlot.getEndHour()  + "H is unavalable!");
    }
    public UnAvalableTimeSlot(Activity activity){
        super("is Unavalable for "+ activity);
    }

}
