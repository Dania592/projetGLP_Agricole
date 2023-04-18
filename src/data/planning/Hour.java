package data.planning;

import java.io.Serializable;

public class Hour implements Serializable{
    private int hour;
    private Activity activity;

    public Hour(int hour) {
        this(hour, Activity.TO_REST);
    }

    public Hour(int hour, Activity activity){
        this.hour = hour;
        this.activity = activity; 
    }

    public int getHour() {
        return hour;
    }


    public String toString(){
        return "Hour : "+hour+ "Activity"+ activity;
    }

    public void setToCorrectHour(){
        if(getHour()== -1){
            hour = 23;
        }else if(hour ==  24){
            hour = 0;
        }
    }


        
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    
    public void setFree() {
        setActivity(Activity.TO_REST);        
    }
    
    public boolean isFreeOfWork() {
        return activity == Activity.TO_REST;        
    }

    public Activity getActivity() {
        return activity;
    }


}
