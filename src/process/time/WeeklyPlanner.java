package process.time;

import java.io.Serializable;
import java.util.HashMap;

import data.planning.DailyPlanner;

public class WeeklyPlanner implements Serializable {
    public enum DayOfWeek {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY;

        public DayOfWeek next(){
            switch(this){
                case MONDAY:
                    return TUESDAY; 
                case TUESDAY: 
                    return WEDNESDAY;
                case WEDNESDAY: 
                    return THURSDAY;
                case THURSDAY: 
                    return FRIDAY;
                case FRIDAY: 
                    return SATURDAY;
                case SATURDAY: 
                    return SUNDAY;
                default:
                    return MONDAY;
            }

        }



    }

    public static final DayOfWeek[] days = { DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY };
    private HashMap<DayOfWeek, DailyPlanner> week = new HashMap<>();

    public WeeklyPlanner(int maxHourOfWorkPerDay) {
        for (int day = 0; day < days.length; day++) {
            week.put(days[day], new DailyPlanner(maxHourOfWorkPerDay));
        }
    }

    public HashMap<DayOfWeek, DailyPlanner> getWeek() {
        return week;
    }
    
    public String toString(){
        String weekText = "Week";
        for(DayOfWeek day:days){
            weekText+= "\n"+day+ week.get(day).toString();
        }
        return weekText;
    }
}
