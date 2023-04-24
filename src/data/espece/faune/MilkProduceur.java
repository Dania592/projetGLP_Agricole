package data.espece.faune;

import data.espece.Produceur;
import data.espece.Slaughtable;
import data.espece.Transportable;
import gui.gestionnaire.keys.Animals;

public interface MilkProduceur extends Transportable, Produceur, Slaughtable{
    Animals getKey();
}