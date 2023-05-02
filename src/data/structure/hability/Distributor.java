package data.structure.hability;

import java.util.ArrayList;

import data.espece.characteristic.Transportable;


/**
 * Un Distributor est une {@link Structure} qui peut effectuer des transferts de {@link Transportable} 
 */
public interface Distributor<T extends Transportable> extends AbleToActOnInHabitant {
    boolean isEmpty(); 
    void addSpecialSenderElement(T specialSenderElement);
    void removeAll(ArrayList<T> transportableToRemoveList);

}
