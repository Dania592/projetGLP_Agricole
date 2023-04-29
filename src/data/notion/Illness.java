package data.notion;

import java.util.ArrayList;

import data.structure.hability.Healable;

public class Illness extends Event<Healable> {

    public Illness(int lastDayHappening, Frequency frequency, ArrayList<Healable> targets) {
        super(lastDayHappening, frequency, targets);
    }

    
}
