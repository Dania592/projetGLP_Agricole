package data.acteur;

import java.util.ArrayList;

import data.espece.characteristic.EtreVivant;
import data.map.Map;
import data.notion.basic.Element;


public class Predateur extends Element{
	
	private ArrayList<EtreVivant> proies ;
	private ModeDefense modeDefense ;

	public Predateur(  ModeDefense mode , ArrayList<EtreVivant> proies , String reference , Map map) {
		super( reference ,false, 1);
		this.proies = proies;
		modeDefense=mode;	
		
	}

	public ArrayList<EtreVivant> getProies() {
		return proies;
	}

	public void setProies(ArrayList<EtreVivant> proies) {
		this.proies = proies;
	}

	public ModeDefense getModeDefense() {
		return modeDefense;
	}

	public void setModeDefense(ModeDefense modeDefense) {
		this.modeDefense = modeDefense;
	}
	
	public void addProie(EtreVivant proie) {
		proies.add(proie);
	}
	
	public void removeProie(EtreVivant proie) {
		proies.remove(proie);
	}
	

}
