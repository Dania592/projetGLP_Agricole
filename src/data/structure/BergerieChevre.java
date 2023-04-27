package data.structure;

import java.io.File;
import java.util.ArrayList;

import data.espece.Slaughtable;
import data.espece.faune.Chevre;
import data.map.Map;
import data.structure.hability.Distributor;
import data.structure.hability.SlaughterHouseSender;
import gui.gestionnaire.keys.Structures;
import process.action.exception.NotImplementYetException;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.transfert.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;

public class BergerieChevre extends Refuge<Chevre> implements  Distributor<Chevre>, SlaughterHouseSender{
    private ArrayList<Slaughtable> chevreToKill = new ArrayList<>();
    private final static float PRIX_ACHAT = 50000 ;
    private boolean isUsedForATask = false;

    public BergerieChevre(int ligne_init, int colonne_init, String reference , Map map ) {
		super(ligne_init, colonne_init, reference , map );
		
			setImage("src"+File.separator+"ressources"+File.separator+"minimoulin.png");
		
	}

    @Override
    protected int getMaxCapacity() {
        return MaxCapacity.MAX_CAPACITE_BERGERIE_CHEVRE.getCapacity();
    }

    @Override
    public Structures getKey() {
        return Structures.BERGERIE_CHEVRE;
    }


    @Override
    public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException, NotImplementYetException, UnableToMakeTheTransfertException {
        return visitor.action(this);
    }


    @Override
    public void addToSlaughter(Slaughtable slaughtable) {
        chevreToKill.add(slaughtable);
        getInHabitant().remove(slaughtable);
    }

    @Override
    public ArrayList<Slaughtable> getAnimalToSlaugther() {
        return chevreToKill;
    }

    @Override
    public boolean isEmpty() {
       return getInHabitant().isEmpty();
    }

    @Override
    public boolean readyToSend() {
       boolean haveProduced = false;
       int indexInhabitant = 0;
       ArrayList<Chevre> chevres = getInHabitant();
       while(indexInhabitant<chevres.size()){
            haveProduced = chevres.get(indexInhabitant).haveProduced();
       }
       return haveProduced;
    }

    @Override
    public void addSpecialSenderElement(Chevre specialSenderElement) {
        getInHabitant().add(specialSenderElement);
    }

    @Override
    public boolean isReadyToSendToSlaughterHouse() {
        return !chevreToKill.isEmpty();
    }

    @Override
    public void removeAll(ArrayList<Chevre> transportableToRemoveList) {
        getInHabitant().removeAll(transportableToRemoveList);
    }

    @Override
    public ArrayList<ActionnableKey> getASetOfAllActionnableKey(){
        ArrayList<ActionnableKey> actionnableKey = super.getActionnableKey();
		actionnableKey.add(getSpecificActionnableKey());
		return actionnableKey;
    }

    @Override
    public ActionnableKey getSpecificActionnableKey() {
        return  ActionnableKey.BERGERIE_CHEVRE;
    }

    @Override
    public boolean isCurrentlyUsedForAnotherTask() {
        return isUsedForATask;
    }

    @Override
    public void setStructureStatus(boolean isCurrentlyUsedForAnotherTask) {
        isUsedForATask = isCurrentlyUsedForAnotherTask;
    }

    @Override
    public int getNumberOfTarget() {    
        return getInHabitant().size();
    }
    
}