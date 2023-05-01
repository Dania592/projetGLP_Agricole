package data.espece.faune;

import data.notion.Mortel.EtatSante;
import data.structure.hability.AbleToActOnInHabitant;
import data.structure.hability.Actionnable;
import data.time.CyclicCounter;

public interface Healable{
    EtatSante getEtatSante();
    void setEtatSante(EtatSante etatSante);
}
