package process.time;

import data.configuration.GameConfiguration;
import data.planning.WeeklyPlanner.DayOfWeek;
import data.time.Clock;

public class TimeManager extends Thread {
    private Clock clock;
    private int dayCounter;
    private boolean isTimeRunning;
    private DayOfWeek day;
    private int timeSpeed = 1; 

    public Clock getClock() {
        return clock;
    }

    public TimeManager(){
        clock = Clock.getInstance();
        dayCounter= 0;
        isTimeRunning = true;
        day = DayOfWeek.MONDAY;
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
            //System.out.println(clock);
        }
    }

    @Override
    public String toString() {
        return "TimeManager [clock=" + clock + ", dayCounter=" + dayCounter + ", isTimeRunning=" + isTimeRunning + "]";
    }

    public void setIsTimeRunnning(boolean isTimeRunning) {
        this.isTimeRunning = isTimeRunning;
    }
    
    public int getTimeSpeed() {
    	return timeSpeed;
    }
    public void setTimeSpeed(int speed) {
    	timeSpeed = speed;
    }

}
