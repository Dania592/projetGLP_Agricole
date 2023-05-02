package data.evenement;

import java.util.ArrayList;

import data.acteur.ModeDefense;
import data.acteur.Predateur;
import data.notion.basic.Element;



public class Meteorite extends Catastrophe{

	public Meteorite(int duree, int debut, boolean succes,
			ArrayList<Element> cibles) {
		super(duree, debut, succes,cibles);
	}

}
