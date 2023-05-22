package data.espece.characteristic;

import data.production.Meat;
import gui.gestionnaire.keys.Animals;

/**
 * Les {@link DomesticSpecie} implémentant cette interface pourront être
 * envoyé à {@link Abattoire}.
 */
public interface Slaughtable extends Transportable{ 
    Animals getTypeOfAnimal();
    float getPoids();
    Meat getEquivalentInMeat();
}
