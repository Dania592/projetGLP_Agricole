package data.structure.hability;

import java.util.ArrayList;

import data.espece.Slaughtable;

public interface SlaughterHouseSender extends AbleToActOnInHabitant{
    <T extends Slaughtable> void addToSlaughter(T animalToAdd);
    ArrayList<?> getAnimalToSlaugther();
}
