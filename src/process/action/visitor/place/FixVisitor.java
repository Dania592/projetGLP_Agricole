package process.action.visitor.place;

import data.flore.terrains.Terrain;
import data.structure.Abatoire;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.SalleDeTraite;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;

public class FixVisitor implements PlaceVisitor<Void> {

    @Override
    public Void action(Etable etable) {
        System.out.println("Réparation de mon étable " + etable);
        return null;
    }

    @Override
    public Void action(Poulallier poulallier) {
        System.out.println("Réparationde mon poulailler " + poulallier);
        return null;
    }

    @Override
    public Void action(Enclos enclos) {
        System.out.println("Réparation de mon enclos" + enclos);
        return null;
    }

    @Override
    public Void action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable {
        System.out.println("Réparation de mon abatoire "+ abatoire);
        return null;
    }

    @Override
    public Void action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable {
        System.out.println("Réparation de ma maison "+ maison);
        return null;
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable {
        System.out.println("Réparation de la salle des traite " + salleDeTraite);
        return null;
    }

    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        System.out.println("Réparation de l'entrepôt " + entrepot);
        return null;
    }

    @Override
    public Void action(Terrain terrain) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(terrain);
    }

}
