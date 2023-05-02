package data.structure;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import data.configuration.GameConfiguration;
import data.espece.characteristic.MilkProduceur;
import data.espece.characteristic.Produceur.ProductifState;
import data.espece.faune.AnimalProducteur;
import data.espece.faune.Chevre;
import data.espece.faune.Vache;
import data.map.Map;
import data.myExceptions.UnableToGenerateNewTaskException;
import data.myExceptions.UnknownActivityException;
import data.notion.basic.Farm;
import data.planning.Activity;
import data.production.Produits;
import data.structure.hability.Distributor;
import data.structure.hability.SlaughterHouseSender;
import data.structure.hability.SpecialActionPerformer;
import gui.gestionnaire.keys.Graine;
import gui.gestionnaire.keys.Structures;
import process.action.exception.being.BeingCannotPerformSuchActionException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.task.launcher.basic.SpecialTask;
import process.action.visitor.being.exception.HaveNotProducedYetException;
import process.action.visitor.being.exception.NeedToBeSendToSpecialProductionPlaceException;
import process.action.visitor.being.exception.ProblemOccursInProductionException;
import process.action.visitor.being.exception.UnableToMakeTheTransfertException;
import process.action.visitor.place.PlaceVisitor;

/**
 * Une salle de traite permet de collecter le {@link Lait} des {@link MilkProduceur}
 */
public class SalleDeTraite extends StructureAction implements Distributor<MilkProduceur>{
	private static final long serialVersionUID = 1L;
	private ArrayList<Vache> vaches = new ArrayList<>();
	private ArrayList<Chevre> chevres = new ArrayList<>();
	private HashMap<Produits, Integer> production = new HashMap<>();


	public SalleDeTraite( String reference) {
		super( reference);
		setImage(GameConfiguration.IMAGE_PATH+Farm.saisonActuelle+File.separator+"Structure"+File.separator+"Salle_De_Traite.png");	
		
	}

	public ArrayList<ActionnableKey> getASetOfAllActionnableKey() {
		ArrayList<ActionnableKey> actionnableKey = super.getASetOfAllActionnableKey();
		actionnableKey.add(getSpecificActionnableKey());
		actionnableKey.add(ActionnableKey.PLACE_OF_ANIMAL_PRODUCTION);
		return actionnableKey;
	}

	public ArrayList<MilkProduceur> getMilkProduceur(){
		ArrayList<MilkProduceur> milkProducteurs = new ArrayList<>();
		milkProducteurs.addAll(vaches);
		milkProducteurs.addAll(chevres);
		return milkProducteurs;

	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable, HaveNotProducedYetException, BeingCannotPerformSuchActionException, NeedToBeSendToSpecialProductionPlaceException, ProblemOccursInProductionException, UnableToMakeTheTransfertException {
		return visitor.action(this);
	}

	public Structures getKey() {
		return Structures.SALLE_DE_TRAITE;
	}

	@Override
	public boolean canLaunchProduction() {
		return vaches.size()>0 || chevres.size()>0;
	}

	@Override
	public boolean isEmpty() {
		return vaches.isEmpty() && chevres.isEmpty();
	}

	@Override
	public void removeAll(ArrayList<MilkProduceur> transportableToRemoveList) {
		Iterator<MilkProduceur> milkIter =  transportableToRemoveList.iterator();
		MilkProduceur currentMilkProduceur;
		while(milkIter.hasNext()){
			currentMilkProduceur = milkIter.next();
			remove(currentMilkProduceur);
		}
	}	

	private void remove(MilkProduceur milkProduceur){
		switch(milkProduceur.getKey()){
			case CHEVRE:
				chevres.remove(milkProduceur);
				break;
			case VACHE:
				vaches.remove(milkProduceur);
				break;
			default :
				break;
		}
	}

	@Override
	public void addSpecialSenderElement(MilkProduceur specialSenderElement) {
		switch(specialSenderElement.getKey()){
			case CHEVRE:
				chevres.add((Chevre) specialSenderElement);
				break;
			case VACHE : 
				vaches.add((Vache) specialSenderElement);
				break;
			default:
				break;
		}
	}

	public ArrayList<Vache> getVaches() {
		return vaches;
	}

	public ArrayList<Chevre> getChevres() {
		return chevres;
	}

	@Override
	public ActionnableKey getSpecificActionnableKey() {
		return ActionnableKey.SALLE_TRAITE;
	}


	@Override
	public HashMap<Produits, Integer> getProduction() {
		return production;
	}

	@Override
	public int getNumberOfTarget() {
		return vaches.size()+chevres.size();
	}

	@Override
	public boolean needPlayerIntervention() {
		return true;
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
	public boolean canPerformSpecialAction(Activity activity) throws UnknownActivityException {
		return true;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor, Activity activity, Graine graine)
			throws UnableToPerformSuchActionWithCurrentActionnable,
			UnableToGenerateNewTaskException {
		throw new UnsupportedOperationException("Seulement pour les terrains ");
	}
	
}
