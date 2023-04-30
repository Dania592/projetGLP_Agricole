package data.espece;

import data.production.Meat;
import gui.gestionnaire.keys.Animals;

public interface Slaughtable extends Transportable{ 
    Animals getTypeOfAnimal();
    float getPoids();
    Meat getEquivalentInMeat();
    
}
