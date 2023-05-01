package data.evenement;

import java.util.ArrayList;

import data.acteur.ModeDefense;
import data.acteur.Predateur;
import data.stucture_base.Element;


public class MareeNoire extends Catastrophe {

	public MareeNoire(int duree, int debut, boolean succes,
			ArrayList<Element> cibles) {
		super(duree, debut, succes,cibles);
	}

}
