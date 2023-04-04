package process.action.place;


import data.structure.Abatoire;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.SalleDeTraite;

public interface PlaceVisitor<T>{
    T action(Etable etable);
    T action(Poulallier poulallier);
    T action(Enclos enclos);
    T action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable;
    T action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable;
    T action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable;
    T action(Entrepot entrepot)throws UnableToPerformSuchActionWithCurrentActionnable;

}
