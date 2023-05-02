package data.espece.characteristic;

import data.notion.Mortel.EtatSante;
import data.structure.hability.AbleToActOnInHabitant;
import data.structure.hability.Actionnable;
import data.time.CyclicCounter;

/**
 * Les {@link DomesticSpecie} du jeu implémantant cette interface pourront être soignés
 */
public interface Healable{
    EtatSante getEtatSante();
    void setEtatSante(EtatSante etatSante);
}
