package gui.gestionnaire;

import java.util.ArrayList;
import data.gestion.GestionnaireAnimaux;
import data.gestion.GestionnaireInterface;
import data.gestion.GestionnaireMateriel;
import data.gestion.GestionnaireStocks;
import data.gestion.GestionnaireStructures;
import gui.gestionnaire.keys.Keys;
import data.gestion.Market;

public enum GestionnaireKey {
	SEEDS(new ArrayList<>(GestionnaireStocks.getInstance().getAvailableGraines()), GestionnaireStocks.getInstance()),
	STRUCTURES(new ArrayList<>(GestionnaireStructures.getInstance().getStructures().keySet()), GestionnaireStructures.getInstance()),
	OUTILS(new ArrayList<>(GestionnaireMateriel.getInstance().getOutils().keySet()), GestionnaireMateriel.getInstance()),
	ENGINS(new ArrayList<>(GestionnaireMateriel.getInstance().getEngins().keySet()), GestionnaireMateriel.getInstance()),
	ANIMALS(new ArrayList<>(GestionnaireAnimaux.getInstance().getAnimaux().keySet()), GestionnaireAnimaux.getInstance());
	
	private ArrayList<Keys> elements;
	
	private GestionnaireKey(ArrayList<Keys> elements, GestionnaireInterface gestionnaire) {
		this.elements = elements;
	}
	
	public ArrayList<Keys> getElements(){
		return (ArrayList<Keys>) elements;
	}
	
	public ArrayList<Keys> getArticles(){
		return Market.getInstance().getArticles().get(this);
	}
}
