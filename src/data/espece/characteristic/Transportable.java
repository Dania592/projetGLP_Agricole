package data.espece.characteristic;


import data.espece.faune.Animal;
import data.structure.Refuge;
import gui.gestionnaire.keys.Structures;

/**
 * Les {@link data.espece.characteristic.DomesticSpecie} implémentant cette interface pourront être
 * transférés.
 * @see data.structure.Enclos
 * @see data.structure.hability.SpecialActionPerformer
 * @see data.structure.hability.ProductifPlace
 */
public interface Transportable extends DomesticSpecie{
    public Structures getHomeLabel();
}
