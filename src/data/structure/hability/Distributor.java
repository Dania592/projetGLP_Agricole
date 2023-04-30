package data.structure.hability;

import java.util.ArrayList;

import data.espece.Transportable;

public interface Distributor<T extends Transportable> extends AbleToActOnInHabitant {
    boolean isEmpty(); 
    boolean readyToSend(); //MEthode  will be called to check if ready to send to place not excluding enclosure
    void addSpecialSenderElement(T specialSenderElement);
    void removeAll(ArrayList<T> transportableToRemoveList);

}
