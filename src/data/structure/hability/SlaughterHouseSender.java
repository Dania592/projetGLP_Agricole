package data.structure.hability;

import java.util.ArrayList;

import data.espece.Slaughtable;

public interface SlaughterHouseSender extends AbleToActOnInHabitant{
    void addToSlaughter(Slaughtable slaughtable);
    ArrayList<Slaughtable> getAnimalToSlaugther();
    boolean isReadyToSendToSlaughterHouse();
}
