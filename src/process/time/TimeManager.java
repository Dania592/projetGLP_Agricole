package process.time;
import data.configuration.GameConfiguration;
import data.planning.WeeklyPlanner.DayOfWeek;
import data.time.Clock;
import process.transaction.FinanceManager;
public class TimeManager extends Thread{
    private Clock clock = Clock.getInstance();
    private int dayCounter;
    public DayOfWeek getDay() {
        return day;
    }

    private boolean gameOver = false;
    private boolean isTimeRunning;
    private DayOfWeek day;
    private int timeSpeed = 1; 
    private static TimeManager timeManager = new TimeManager();

    // doit etre modifier pour prendre en compte l'etat de la ferme == jour , semaine ...
    private  TimeManager(){ 
        dayCounter= 0;
        isTimeRunning = true;
        day = DayOfWeek.MONDAY;
    }
    
    public static TimeManager getInstance() {
    	return timeManager;
    }

    public void gameOver(boolean gameOver) {
    	this.gameOver = gameOver;
    }
    
    public Clock getClock() {
        return clock;
    }
    public void setClock(Clock clock) {
    	this.clock=clock;
    }
    
    public void setTimeRunning(boolean isTimeRunning) {
        this.isTimeRunning = isTimeRunning;
    }

    public void run() {
        while (isTimeRunning){
        	if (!gameOver) {
        		clock.increment();
        		if (clock.getMinute().getValue() == 1) {
        			dayCounter++;
        			FinanceManager.getInstance().incrementCounter();
        			clock.getMinute().setValue(0);
        			day = day.next();
        		}
        	}
            try {
                sleep(GameConfiguration.GAME_SPEED*timeSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void reset() {
    	clock.init();
        dayCounter= 0;
        isTimeRunning = true;
        day = DayOfWeek.MONDAY;
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
