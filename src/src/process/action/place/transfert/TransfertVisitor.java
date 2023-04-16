package process.action.place.transfert;

import data.structure.Etable;
import data.structure.Poulallier;
import data.structure.hability.Actionnable;

public interface TransfertVisitor<T extends Actionnable>{
    void transfert(Etable etable, T actionnable);
    void transfert(Poulallier poulallier, T actionnable); 

}
