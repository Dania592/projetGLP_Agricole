package data.espece.characteristic;

import gui.gestionnaire.keys.Animals;
/**
 * Les {@link DomesticSpecie} implementant cette interface pourront être envoyés à 
 * {@link SalleDeTraite} pour être trait.
 */
public interface MilkProduceur extends Produceur, Slaughtable{
    Animals getKey();
}