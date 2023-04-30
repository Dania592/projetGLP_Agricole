package process.visitor;

import java.io.File;

import data.configuration.GameConfiguration;
import data.gestion.GestionnaireRH;
import data.gestion.GestionnaireStocks;
import data.gestion.GestionnaireStructures;
import data.gestion.RessourcesManager;
import data.materiel.Engin;
import data.materiel.Outil;
import data.production.Produits;
import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.Employees;
import gui.gestionnaire.keys.Encloss;
import gui.gestionnaire.keys.Engins;
import gui.gestionnaire.keys.Graine;
import gui.gestionnaire.keys.Outils;
import gui.gestionnaire.keys.Structures;
import gui.gestionnaire.keys.Terrains;

public class RemoveKeyVisitor implements KeyVisitor<Void>{

	private RessourcesManager ressourcesManager = RessourcesManager.getInstance();
	
	@Override
	public Void visit(Animals animal, int quantity) {
		for (int i=0; i<quantity; i++) {
			ressourcesManager.getGestionnaireAnimaux().remove(animal, quantity);
		}
		return null;
	}

	@Override
	public Void visit(Graine graine, int quantity) {
		for (int i=0; i<quantity; i++) {
			ressourcesManager.getGestionnaireStocks().remove(graine, quantity);
		}
		return null;
	}

	@Override
	public Void visit(Structures structure, int quantity) {
		for (int i=0; i<quantity; i++) {
			ressourcesManager.getGestionnaireStructure().remove(structure, quantity);
		}
		return null;
	}

	@Override
	public Void visit(Outils outil, int quantity) {
		Outil newOutil;
		for (int i=0; i<quantity; i++) {
			ressourcesManager.getGestionnaireMateriel().remove(outil, quantity);;
		}
		return null;
	}

	@Override
	public Void visit(Engins engin, int quantity) {
		Engin newEngin;
		for (int i=0; i<quantity; i++) {
			ressourcesManager.getGestionnaireMateriel().remove(engin, quantity);;
		}		
		if (ressourcesManager.getGestionnaireMateriel().getEnginsSize() == 0) {
			GestionnaireStructures.getInstance().getStructures().get(Structures.GARAGE).get(0).setImage(GameConfiguration.IMAGE_PATH+"Structure"+File.separator+"Garage.png");
		}
		return null;
	}

	@Override
	public Void visit(Terrains terrain, int quantity) {
		return null;
	}
	
	@Override
	public Void visit(Encloss terrain, int quantity) {
		return null;
	}

	@Override
	public Void visit(Employees employee, int quantity) {
		GestionnaireRH.getInstance().getEmployees().remove(employee);
		return null;
	}

	@Override
	public Void visit(Produits produit, int quantity) {
		GestionnaireStocks.getInstance().getProduits().remove(produit);
		return null;
	}

}
