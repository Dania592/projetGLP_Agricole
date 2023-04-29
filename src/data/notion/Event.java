package data.notion;

import java.util.ArrayList;

import data.time.CyclicCounter;

public class Event<T> {
    private int lastDayHappening = 10;
    private int DEFAULT_FIRST_EVENT = 10;
    private Frequency frequency;
    private ArrayList<T> targets;
     
    public enum Frequency{
        ILLNESS(1, 5),
        ATTACK(1, 3),
        CATASTROPHY(5,10),
        ;

        private int min;
        private int max;

        private Frequency(int min, int max){
            this.min = min;
            this.max = max;
        }
    }
    
    public Event(int lastDayHappening, Frequency frequency, ArrayList<T> targets) {
        this.lastDayHappening = lastDayHappening;
        this.frequency = frequency;
        this.targets = targets;
    }

}
