package data.planning;


/**
 * A TimeSlot is a period of time between two hours, and has a duration and an activity.
 * This class is use to facilitate the addition of a task in a {@see core.planning.DailyPlanner}
 */
public class TimeSlot{

    private int startHour;
    private int endHour;
    private Activity activity;
    private boolean isSchedule;
    
    
    public TimeSlot(int startHour, Activity activity) {
        this(startHour, activity, false);
    }


    public TimeSlot(int startHour, Activity activity, boolean isSchedule) {
        this.startHour = startHour;
        this.activity = activity;
        this.endHour = startHour +
         activity.getDuration();
        this.isSchedule = isSchedule;
    }

    public int getStartHour() {
        return startHour;
    }
    
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }
    
    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public void setIsSchedule(boolean isSchedule){
        this.isSchedule = isSchedule;
    }
    
    public int getEndHour() {
        return endHour;
    }
    
    public boolean isSchedule() {
        return isSchedule;
    }

    public int getDuration(){
        return activity.getDuration();
    }

    public Activity getActivity(){
        return activity;
    }

    public void shiftByHour(int hour){
        setStartHour(startHour+hour);
        setEndHour(endHour+hour);
    }

    public void free(){
        activity = Activity.TO_REST;
    }

    @Override
    public String toString() {
        return "TimeSlot [startHour=" + startHour + ", endHour=" + endHour + ", activity=" + activity + ", isSchedule="
                + isSchedule + "]";
    }

}
