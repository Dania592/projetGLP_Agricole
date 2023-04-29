package data.notion;

import java.util.ArrayList;

import data.structure.Enclos;

public class Attack extends Event<Enclos> {

    public Attack(int lastDayHappening, Frequency frequency, ArrayList<Enclos> targets) {
        super(lastDayHappening, frequency, targets);
    }
    
}
