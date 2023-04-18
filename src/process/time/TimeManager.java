package process.time;

import data.configuration.GameConfiguration;
import data.planning.WeeklyPlanner.DayOfWeek;
import data.time.Clock;
public class TimeManager extends Thread{
    private Clock clock;
    private int dayCounter;
    private boolean isTimeRunning;
    private DayOfWeek day;
    private int timeSpeed = 1; 

    public Clock getClock() {
        return clock;
    }
    public TimeManager(boolean running){
        clock = Clock.getInstance();
        dayCounter= 0;
        isTimeRunning = running;
        day = DayOfWeek.MONDAY;
    }

    public TimeManager(){
        this(true);
    }


    public void setTimeRunning(boolean isTimeRunning) {
        this.isTimeRunning = isTimeRunning;
    }

    public void run() {
        while (isTimeRunning) {
            clock.increment();
            if (clock.getHour().getValue() == 24) {
                dayCounter++;
                clock.getHour().setValue(0);
                //day = day.next();
            }
            try {
                sleep(GameConfiguration.GAME_SPEED*timeSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isTimeRunning() {
        return isTimeRunning;
    }

    @Override
    public String toString() {
        return "TimeManager [clock=" + clock + ", dayCounter=" + dayCounter + ", isTimeRunning=" + isTimeRunning + "]";
    }

    public void stopTime(){
        isTimeRunning = false;
    }

}
