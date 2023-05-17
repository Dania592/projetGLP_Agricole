package data.structure;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import data.configuration.GameConfiguration;
import data.espece.flore.Saison;
import data.map.Map;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.myExceptions.UnknownActivityException;
import data.notion.basic.Farm;
import data.planning.Activity;
import data.production.Produits;
import data.structure.hability.Fixable;
import data.structure.hability.ProductifPlace;
import data.structure.hability.SpecialActionPerformer;
import gui.gestionnaire.keys.Graine;
import gui.gestionnaire.keys.Structures;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;
import process.gestion.transaction.Buyable;

public class Puit extends Structure implements SpecialActionPerformer, Buyable{
    private Seau seau = Seau.SEAU_BOIS;
    private int quantite = 100; //affectée par les saisons

    
    public Puit(String reference  ) {
		super( reference );
		setImage(GameConfiguration.IMAGE_PATH+Saison.PRINTEMPS+File.separator+"Structure"+File.separator+"Puit.png");	
	}

    public Seau getSeau() {
        return seau;
    }

    public void setSeau(Seau seau) {
        this.seau = seau;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public enum Seau{
        SEAU_BOIS(10),
        SEAU_TERRE_CUITE(20),
        SEAU_ARGENT(50),
        SEAU_OR(80);

        int capacite;
        private Seau(int capacite){
            this.capacite = capacite;
        }

        public int getCapacite(){
            return capacite;
        }
    }

    @Override
    public ArrayList<ActionnableKey> getASetOfAllActionnableKey() {
        ArrayList<ActionnableKey> actionnableKey = super.getASetOfAllActionnableKey();
		actionnableKey.add(getSpecificActionnableKey());
		return actionnableKey;
    }

    @Override
    public ActionnableKey getSpecificActionnableKey() {
        return ActionnableKey.PUIT;
    }

    @Override
    public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable,
            HaveNotProducedYetException, BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException {
        return visitor.action(this);
    }

    @Override
    public Structures getKey(){
        return Structures.PUIT;
    }

    @Override
    public <T> T launchAction(PlaceVisitor<T> visitor, Activity activity)
            throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException,
            BeingCannotPerformSuchActionException,
            NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException,
            UnableToMakeTheTransfertException, UnableToGenerateNewTaskException {
        return visitor.action(this, activity);
    }

    @Override
    public <T> T launchAction(PlaceVisitor<T> visitor, Activity activity, Graine graine) {
		throw new UnsupportedOperationException("Utilisé seulement par les terrains");
    }

    @Override
    public boolean canPerformSpecialAction(Activity activity) throws UnknownActivityException {
        return true; 
    }
    

}
