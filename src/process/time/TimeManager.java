package process.time;
import data.configuration.GameConfiguration;
import data.notion.basic.Farm;
import data.planning.WeeklyPlanner.DayOfWeek;
import data.time.Clock;
import process.gestion.transaction.FinanceManager;
public class TimeManager extends Thread{
    private Clock clock = Clock.getInstance();
    private int dayCounter;
    

    private boolean gameOver = false;
    private boolean isTimeRunning;
    private DayOfWeek day;
    private int timeSpeed = 1; 
    private Farm farm;
    private static TimeManager timeManager = new TimeManager();

    // doit etre modifier pour prendre en compte l'etat de la ferme == jour , semaine ...
    private  TimeManager(){ 
        dayCounter= 0;
        isTimeRunning = true;
        day = DayOfWeek.MONDAY;
    }
    
    public DayOfWeek getDay() {
        return day;
    }
    
    public void setFarm(Farm farm) {
    	this.farm = farm ;
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
        		if (clock.getHour().getValue() == 24) {
        			farm.incrementCptJour();
        			dayCounter++;
        			FinanceManager.getInstance().incrementCounter();
        			clock.getMinute().setValue(0);
        			day = day.next();
        		}
        	}
            try {
                sleep(GameConfiguration.GAME_SPEED/timeSpeed);
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
    
    public void setTimeSpeed(int coef) {
    	timeSpeed=coef;
    }

}
