package data.gestion;

import java.io.Serializable;

public class RessourcesManager implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private GestionnaireFinancier gestionnaireFinancier = GestionnaireFinancier.getInstance();
	private GestionnaireStructures gestionnaireStructure = GestionnaireStructures.getInstance();
	private GestionnaireAnimaux gestionnaireAnimaux = GestionnaireAnimaux.getInstance();
	private GestionnaireStocks gestionnaireStocks = GestionnaireStocks.getInstance();
	private GestionnaireMateriel gestionnaireMateriel = GestionnaireMateriel.getInstance();
	private GestionnaireTerrains gestionnaireTerrains = GestionnaireTerrains.getInstance();
	private GestionnaireEnclos gestionnaireEnclos = GestionnaireEnclos.getInstance();
	private GestionnaireRH gestionnaireRH = GestionnaireRH.getInstance();
	 
	private static RessourcesManager instance = new RessourcesManager();
	
	private RessourcesManager() {
	}
	
	public void reset() {
		gestionnaireAnimaux.reset();
		gestionnaireEnclos.reset();
		gestionnaireFinancier.reset();
		gestionnaireMateriel.reset();
		gestionnaireRH.reset();
		gestionnaireStocks.reset();
		gestionnaireStructure.reset();
		gestionnaireTerrains.reset();
	}
	
	public static RessourcesManager getInstance() {
		return instance;
	}

	public GestionnaireFinancier getGestionnaireFinancier() {
		return gestionnaireFinancier;
	}
	public GestionnaireRH getGestionnaireRH() {
		return gestionnaireRH;
	}
	public GestionnaireStocks getGestionnaireStocks() {
		return gestionnaireStocks;
	}
	public GestionnaireStructures getGestionnaireStructure() {
		return gestionnaireStructure;
	}
	public GestionnaireMateriel getGestionnaireMateriel() {
		return gestionnaireMateriel;
	}
	public GestionnaireTerrains getGestionnaireTerrains() {
		return gestionnaireTerrains;
	}
	public GestionnaireEnclos getGestionnaireEnclos() {
		return gestionnaireEnclos;
	}
	public GestionnaireAnimaux getGestionnaireAnimaux() {
		return gestionnaireAnimaux;
	}
	
	public String toString() {
		return "Gestionnaire : \n" + gestionnaireAnimaux.toString() + "\n" + gestionnaireStocks.toString() + "\n" + gestionnaireStructure.toString() 
		+"\n"+ gestionnaireMateriel.toString() + "\n"+ gestionnaireRH.toString()+"\n"+ gestionnaireTerrains.toString();
	}
}
