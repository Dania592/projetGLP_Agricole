package data.structure.hability;

import java.util.ArrayList;

import data.espece.Transportable;

public interface Distributor<T extends Transportable> extends AbleToActOnInHabitant {
    boolean isEmpty(); 
    void addSpecialSenderElement(T specialSenderElement);
    void removeAll(ArrayList<T> transportableToRemoveList);

}
