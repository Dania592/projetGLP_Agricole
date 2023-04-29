package gui.gestionnaire;

import java.util.ArrayList;

import data.gestion.GestionnaireAnimaux;
import data.gestion.GestionnaireEnclos;
import data.gestion.GestionnaireFinancier;
import data.gestion.GestionnaireMateriel;
import data.gestion.GestionnaireRH;
import data.gestion.GestionnaireStocks;
import data.gestion.GestionnaireStructures;
import data.gestion.GestionnaireTerrains;
import data.gestion.Market;

public enum GestionnaireKey {
	SEEDS(new ArrayList<>(GestionnaireStocks.getInstance().getAvailableGraines())),
	STRUCTURES(new ArrayList<>(GestionnaireStructures.getInstance().getStructures().keySet())),
	OUTILS(new ArrayList<>(GestionnaireMateriel.getInstance().getOutils().keySet())),
	ENGINS(new ArrayList<>(GestionnaireMateriel.getInstance().getEngins().keySet())),
	ANIMALS(new ArrayList<>(GestionnaireAnimaux.getInstance().getAnimaux().keySet())),
	ENCLOS(GestionnaireEnclos.getInstance().getEnclos()),
	TERRAINS(new ArrayList<>(GestionnaireTerrains.getInstance().getTerrains().keySet())),
	EMPLOYE(new ArrayList<>(GestionnaireRH.getInstance().getEmployees().keySet())),
	RECRUT(new ArrayList<>(GestionnaireRH.getInstance().getARecruter().keySet())),
	CHARGE(GestionnaireFinancier.getInstance().getCharges()),
	VENTE(GestionnaireFinancier.getInstance().getVentes()),
	ACHAT(GestionnaireFinancier.getInstance().getAchats()),
	PRODUIT(new ArrayList<>(GestionnaireStocks.getInstance().getProduits().keySet()));
	
	private ArrayList<?> elements;
	
	private GestionnaireKey(ArrayList<?> elements) {
		this.elements = elements;
	}
	
	public ArrayList<?> getElements(){
		return elements;
	}
	
	public ArrayList<?> getArticles(){
		return Market.getInstance().getArticles().get(this);
	}
}
