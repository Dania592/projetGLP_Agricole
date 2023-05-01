package data.structure.hability;

import java.util.ArrayList;

import data.espece.Slaughtable;

public interface SlaughterHouseSender extends AbleToActOnInHabitant{
    boolean isReadyToSendToSlaughterHouse();
    void removeAll();
}
