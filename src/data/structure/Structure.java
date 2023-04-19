package data.structure;

import java.io.Serializable;
import java.util.ArrayList;

import data.gestion.Stockage;
import data.map.Map;
import data.structure.hability.Fixable;
import data.stucture_base.Element;
import gui.gestionnaire.GestionnaireKey;
import gui.gestionnaire.keys.Structures;
import process.transaction.Buyable;
import process.visitor.GestionVisitor;

public  abstract class Structure extends Element implements Buyable,Stockage, Fixable,Serializable{

	private static final long serialVersionUID = 1L;
	
	private float prixAchat ;
	private FixableState state ;
	private Charge[] charges ;
	
	public FixableState getState() {
		return state;
	}



	private final static int  NB_CASE = 16;
	
	public Structure( int ligne_init, int colonne_init, float prixAchat , String reference , Map map  ) {
		super(reference ,false,NB_CASE, ligne_init, colonne_init , map);
		this.prixAchat = prixAchat;
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

	public boolean isNeedToBeFixed(){
		return state == FixableState.DAMAGED;
	}


	public ArrayList<ActionnableKey> getActionnableKey() {
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
	
	public abstract Structures getKey();

}
