package data.structure.hability;

import data.notion.Mortel.EtatSante;
import data.time.CyclicCounter;

public interface Healable extends AbleToActOnInHabitant {
    EtatSante getGeneralHealthState();
    boolean isIll();
    CyclicCounter getIllCycle();
    
}
