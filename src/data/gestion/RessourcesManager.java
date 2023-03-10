package data.gestion;

public class RessourcesManager {
	private GestionnaireFinancier gestionnaireFinancier = GestionnaireFinancier.getInstance();
	private GestionnaireRH gestionnaireRH = GestionnaireRH.getInstance();
	private GestionnaireStocks gestionnaireStocks = GestionnaireStocks.getInstance();
	private GestionnaireStructures gestionnaireStructure = GestionnaireStructures.getInstance();
	private GestionnaireMateriel gestionnaireMateriel = GestionnaireMateriel.getInstance();
	private GestionnaireTerrains gestionnaireterrains = GestionnaireTerrains.getInstance();
	
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
		return gestionnaireterrains;
	}
	
	public String toString() {
		return "Gestionnaire : \n" + gestionnaireStocks.toString() + "\n" + gestionnaireMateriel.toString() 
		+"\n"+ gestionnaireStructure.toString() +"\n"+ gestionnaireFinancier.toString() +"\n"+ gestionnaireRH.toString()+"\n"+ gestionnaireterrains.toString();
	}
}
