package data.structure.hability;

import java.util.ArrayList;

import data.espece.Slaughtable;

public interface SlaughterHouseSender<T extends Slaughtable> extends AbleToActOnInHabitant{
    ArrayList<T> getAnimalToSlaugther();
    void removeAll();
    
}
