package process.action.visitor.place.transfert;

import java.util.ArrayList;
import java.util.Iterator;

import data.espece.Slaughtable;
import data.flore.terrains.Terrain;
import data.gestion.GestionnaireStructures;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.planning.Activity;
import data.structure.Abatoire;
import data.structure.BergerieChevre;
import data.structure.BergerieMouton;
import data.structure.Enclos;
import data.structure.Entrepot;
import data.structure.Etable;
import data.structure.Garage;
import data.structure.Grange;
import data.structure.Maison;
import data.structure.Poulallier;
import data.structure.Puit;
import data.structure.SalleDeTraite;
import data.structure.Structure;
import data.structure.hability.SlaughterHouseSender;
import gui.gestionnaire.keys.Graine;
import gui.gestionnaire.keys.Structures;
import process.action.exception.NotImplementYetException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;

public class SendToSlaughterHouseVisitor implements PlaceVisitor<Void> {

    private Abatoire getAvalablAbatoire() throws UnableToMakeTheTransfertException{
        ArrayList<Structure> abatoirePossible = GestionnaireStructures.getInstance().getStructures().get(Structures.ABATTOIRE);
        int index= 0;
        boolean isAvalable = false;
        while(index<abatoirePossible.size() && !isAvalable) {
            isAvalable = abatoirePossible.get(index).isStatique();
            index++;
        }if(index >= abatoirePossible.size()){
            throw new UnableToMakeTheTransfertException(" No Abatoir Avalable");
        }
        return (Abatoire)abatoirePossible.get(index);
    } 
    
    
    @Override
    public Void action(Terrain terrain, Activity activity, Graine graine)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
            UnableToGenerateNewTaskException {
        return action(terrain, activity);
    }


    private Void sendToSlaugtherHouse(SlaughterHouseSender structure, Iterator<Slaughtable> iterator) throws UnableToMakeTheTransfertException{
        ArrayList<Slaughtable> slaughtablesToRemove = new ArrayList<>(); 
        Slaughtable currentSlaughtable;
        Abatoire abatoire = getAvalablAbatoire();
        while(iterator.hasNext()){
            currentSlaughtable = iterator.next();
            abatoire.addSpecialSenderElement(currentSlaughtable);
            slaughtablesToRemove.add(currentSlaughtable);
        }
        structure.getAnimalToSlaugther().removeAll(slaughtablesToRemove);
        return null;
    }



    @Override
    public Void action(Etable etable) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException {
        return sendToSlaugtherHouse(etable,etable.getAnimalToSlaugther().iterator());
    }

    @Override
    public Void action(Poulallier poulallier) throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException {
        return sendToSlaugtherHouse(poulallier,poulallier.getAnimalToSlaugther().iterator());
    }

    @Override
    public Void action(Enclos enclos) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(enclos);
    }

    @Override
    public Void action(Abatoire abatoire) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(abatoire);
    }

    @Override
    public Void action(Maison maison) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(maison);
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(salleDeTraite);
    }

    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(entrepot);
    }

    @Override
    public Void action(Terrain terrain) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(terrain);
    }

    @Override
    public Void action(BergerieChevre bergerieChevre)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, UnableToMakeTheTransfertException {
        return sendToSlaugtherHouse(bergerieChevre,bergerieChevre.getAnimalToSlaugther().iterator());
    }

    @Override
    public Void action(BergerieMouton bergerieMouton)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, UnableToMakeTheTransfertException {
        return sendToSlaugtherHouse(bergerieMouton,bergerieMouton.getAnimalToSlaugther().iterator());
    }

    @Override
    public Void action(Puit puit) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(puit);
    }

    @Override
    public Void action(Garage garage) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(garage);
    }

    @Override
    public Void action(Grange grange) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(grange);
    }



    @Override
    public Void action(Etable etable, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException {
        return action(etable);
    }

    @Override
    public Void action(Poulallier poulallier, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException, NotImplementYetException {
        return action(poulallier);
    }

    @Override
    public Void action(Enclos enclos, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException, NotImplementYetException {
        return action(enclos);
    }

    @Override
    public Void action(Abatoire abatoire, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable {
            return action(abatoire);
    }

    @Override
    public Void action(Maison maison, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(maison);
    }

    @Override
    public Void action(SalleDeTraite salleDeTraite, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
            UnableToMakeTheTransfertException {
        return action(salleDeTraite);
    }

    @Override
    public Void action(Entrepot entrepot, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(entrepot);
    }

    @Override
    public Void action(Terrain terrain, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        return action(terrain);
    }

    @Override
    public Void action(BergerieChevre bergerieChevre, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
            UnableToMakeTheTransfertException {
            return action(bergerieChevre);
    }

    @Override
    public Void action(BergerieMouton bergerieMouton, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
            UnableToMakeTheTransfertException {
        return action(bergerieMouton);
    }

    @Override
    public Void action(Puit puit, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(puit);
    }

    @Override
    public Void action(Garage garage, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(garage);
    }

    @Override
    public Void action(Grange grange, Activity activity) throws UnableToPerformSuchActionWithCurrentActionnable {
        return action(grange);
    }


    
}
