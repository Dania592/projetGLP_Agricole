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
   // private static TimeManager timeManager = new TimeManager();

    
    
    // doit etre modifier pour prendre en compte l'etat de la ferme == jour , semaine ...
    public  TimeManager(Clock clock ){
        this.clock = clock ; 
        dayCounter= 0;
        isTimeRunning = true;
        day = DayOfWeek.MONDAY;
    }

    public Clock getClock() {
        return clock;
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
