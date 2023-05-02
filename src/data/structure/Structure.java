package data.structure;

import java.io.Serializable;
import java.util.ArrayList;

import data.gestion.Stockage;
import data.map.Map;
import data.notion.basic.Element;
import data.structure.hability.Fixable;
import gui.gestionnaire.keys.GestionnaireKey;
import gui.gestionnaire.keys.Structures;
import process.gestion.transaction.Buyable;
import process.gestion.visitor.GestionVisitor;


/**
 * Les structures sont des {@link Actionnable} qui permmettent de lancer des {@link Task}
 */
public  abstract class Structure extends Element implements Buyable,Stockage, Fixable, Serializable{
	private boolean usedForAnAction = false;
	private static final long serialVersionUID = 1L;
	
	private float prixAchat ;
	private FixableState state ;
	private Charge[] charges ;
	
	public FixableState getState() {
		return state;
	}



	private final static int  NB_CASE = 16;
	
	public Structure( String reference   ) {
		super(reference ,false,NB_CASE);
		this.prixAchat = getKey().getPrixAchat();
		state = FixableState.USABLE;
		charges = new Charge[2];
	}


	public float getPrixAchat() {
		return prixAchat;
		}

	public void setPrixAchat(float prixAchat) {
		this.prixAchat = prixAchat;
	}

	
	public void setStatique() {
		super.setStatique(true);
	}



	public Charge[] getCharges() {
		return charges;
	}


	public void setCharges(Charge[] charges) {
		this.charges = charges;
	}

	@Override
	public <T> T accept(GestionVisitor<T> visitor) {
		visitor.visit(this);
		return null;
	}


	public ArrayList<ActionnableKey> getASetOfAllActionnableKey() {
		ArrayList<ActionnableKey> actionnableKeys = new ArrayList<>();
		actionnableKeys.add(ActionnableKey.STRUCTURE);
		return actionnableKeys;
	}
	
	public void setState(FixableState newState){
		state = newState;
	}
	
	public GestionnaireKey getGestionnaireKey() {
		return GestionnaireKey.STRUCTURES;
	}

	public boolean isNeedToBeFixed(){
		return state == FixableState.DAMAGED;
	}


	@Override
	public boolean isCurrentlyUsedForAnotherTask() {
		return usedForAnAction;
	}
	
	@Override
	public void setStructureStatus(boolean isCurrentlyUsedForAnotherTask) {
		usedForAnAction = isCurrentlyUsedForAnotherTask;
	}

	public abstract Structures getKey();

}
