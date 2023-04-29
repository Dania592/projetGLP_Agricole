package process.action.visitor.place.transfert;

import java.util.ArrayList;
import java.util.Iterator;

import data.espece.faune.AnimalProducteur;
import data.espece.faune.Chevre;
import data.espece.faune.MilkProduceur;
import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;
import data.flore.terrains.Terrain;
import data.gestion.GestionnaireEnclos;
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
import data.structure.hability.Distributor;
import gui.gestionnaire.keys.Graine;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.place.PlaceVisitor;

public class EnclosureSenderVisitor implements PlaceVisitor<Void>{
    
    private int capacityLeft(Enclos enclos){
        return enclos.getCapacite()-enclos.getAnimals().size();
    }

    private ArrayList<Enclos> getAvalableEnclosureList(int numberOfTarget) throws UnableToMakeTheTransfertException{
        int numberOfPlacesToLookFor = numberOfTarget;
        Iterator<Enclos>enclosIter = GestionnaireEnclos.getInstance().getEnclos().iterator();
        ArrayList<Enclos> avalableEnclosures = new ArrayList<>();
        Enclos currentEnclosure;
        int currentEnclosureCapacity;
        while(enclosIter.hasNext() && numberOfPlacesToLookFor>0){
            currentEnclosure = enclosIter.next();
            currentEnclosureCapacity = capacityLeft(currentEnclosure);
            if(currentEnclosure.isStatique() && currentEnclosureCapacity>0){
                avalableEnclosures.add(currentEnclosure);
                numberOfPlacesToLookFor-= currentEnclosureCapacity;
            }
        }if(numberOfPlacesToLookFor>0){
            throw new UnableToMakeTheTransfertException("No enclosure left");
        }
        return avalableEnclosures;
    }

    private <T extends Distributor<E>, E extends AnimalProducteur> Void sendToEnclosure(T distributor, Iterator<E> iterator) throws UnableToMakeTheTransfertException{
        ArrayList<E> tranportableToRemove = new ArrayList<>(); 
        E currentTransportable;
        Enclos currentEnclosure;
        ArrayList<Enclos> enclosureIter = getAvalableEnclosureList(distributor.getNumberOfTarget());
        int indexEnclosure = 0;
        while(iterator.hasNext() && indexEnclosure<enclosureIter.size()){
            currentTransportable = iterator.next();
            tranportableToRemove.add(currentTransportable);
            currentEnclosure = enclosureIter.get(indexEnclosure);
            currentEnclosure.addSpecialSenderElement(currentTransportable);;
            if(capacityLeft(currentEnclosure)==0){
                indexEnclosure++;
            }
        }
        distributor.removeAll(tranportableToRemove);
        return null;
    }

    
@Override
    public Void action(Terrain terrain, Activity activity, Graine graine)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException,
            UnableToGenerateNewTaskException {
        return action(terrain, activity);
    }

    //TO DO trouver moyen de rassemnbler les type s!!
    private Void sendToEnclosure(SalleDeTraite salleDeTraite, Iterator<MilkProduceur> iterator) throws UnableToMakeTheTransfertException{
        ArrayList<MilkProduceur> tranportableToRemove = new ArrayList<>(); 
        MilkProduceur currentMilkProduceur;
        Enclos currentEnclosure;
        ArrayList<Enclos> enclosureIter = getAvalableEnclosureList(salleDeTraite.getMilkProduceur().size());
        int indexEnclosure = 0;
        while(iterator.hasNext() && indexEnclosure<enclosureIter.size()){
            currentMilkProduceur = iterator.next();
            tranportableToRemove.add(currentMilkProduceur);
            currentEnclosure = enclosureIter.get(indexEnclosure);
            currentEnclosure.addSpecialSenderElement(currentMilkProduceur);;
            if(capacityLeft(currentEnclosure)==0){
                indexEnclosure++;
            }
        }
        salleDeTraite.removeAll(tranportableToRemove);
        return null;
    }





    @Override
    public Void action(Etable etable) throws UnableToMakeTheTransfertException{
        Iterator<Vache> vacheIter = etable.getInHabitant().iterator();
        return sendToEnclosure(etable, vacheIter);
    }

    @Override
    public Void action(Poulallier poulallier) throws UnableToMakeTheTransfertException{
        Iterator<Poule> pouleIter = poulallier.getInHabitant().iterator();
        return sendToEnclosure(poulallier, pouleIter);
    }


    @Override
    public Void action(Enclos enclos) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException {
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
    public Void action(SalleDeTraite salleDeTraite) throws UnableToMakeTheTransfertException{
        Iterator<MilkProduceur>  milkPIterator = salleDeTraite.getMilkProduceur().iterator();
        return sendToEnclosure(salleDeTraite, milkPIterator);
    }


    @Override
    public Void action(Entrepot entrepot) throws UnableToPerformSuchActionWithCurrentActionnable {
        throw new UnableToPerformSuchActionWithCurrentActionnable(entrepot);
    }


    @Override
    public Void action(Terrain terrain)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
        throw new NotImplementYetException("Pas pour les terrains");
    }

    @Override
    public Void action(BergerieChevre bergerieChevre)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, UnableToMakeTheTransfertException {
        Iterator<Chevre> chevreIter = bergerieChevre.getInHabitant().iterator();
        return sendToEnclosure(bergerieChevre, chevreIter);
    }

    @Override
    public Void action(BergerieMouton bergerieMouton)
            throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException, UnableToMakeTheTransfertException {
        Iterator<Mouton> moutonIter = bergerieMouton.getInHabitant().iterator();
        return sendToEnclosure(bergerieMouton, moutonIter);
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
            throws UnableToPerformSuchActionWithCurrentActionnable, UnableToMakeTheTransfertException, NotImplementYetException, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException {
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
