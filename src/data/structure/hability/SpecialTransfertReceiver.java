package data.structure.hability;

import java.util.ArrayList;

public interface SpecialTransfertReceiver<T> extends SpecialActionnable {
    void addSpecialSenderElement(T specialSenderElement);
    void addSpecialSenderElement(ArrayList<T> specialSenderElement);
}
