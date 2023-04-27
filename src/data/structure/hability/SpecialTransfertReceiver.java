package data.structure.hability;

import java.util.ArrayList;

public interface SpecialTransfertReceiver<T> extends SpecialActionPerformer {
    void addSpecialSenderElement(T specialSenderElement);
    void addSpecialSenderElement(ArrayList<T> specialSenderElement);
}
