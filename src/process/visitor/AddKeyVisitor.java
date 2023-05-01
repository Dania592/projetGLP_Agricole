package process.visitor;

import java.io.File;

import data.acteur.Employee;
import data.configuration.GameConfiguration;
import data.espece.faune.Animal;
import data.flore.terrains.Terrain;
import data.gestion.GestionnaireRH;
import data.gestion.GestionnaireStocks;
import data.gestion.GestionnaireStructures;
import data.map.Map;
import data.materiel.Engin;
import data.materiel.Outil;
import data.production.Produits;
import data.structure.Enclos;
import data.structure.Structure;
import gui.gestionnaire.keys.*;
import process.GestionnaireFactory;

public class AddKeyVisitor implements KeyVisitor<Void>{
	
	private Map map = Map.getInstance();
	private AddVisitor addVisitor = new AddVisitor();

	@Override
	public Void visit(Animals animal, int quantity) {
		Animal newAnimal;
		for (int i=0; i<quantity; i++) {
			newAnimal = GestionnaireFactory.createElement(animal, map);
			newAnimal.accept(addVisitor);
		}
		return null;
	}

	@Override
	public Void visit(Graine graine, int quantity) {
		for (int i=0; i<quantity; i++) {
			graine.accept(addVisitor);
		}
		return null;
	}

	@Override
	public Void visit(Structures structure, int quantity) {
		Structure newStructure;
		for (int i=0; i<quantity; i++) {
			newStructure = GestionnaireFactory.createElement(structure, map);
			newStructure.accept(addVisitor);
		}
		return null;
	}

	@Override
	public Void visit(Outils outil, int quantity) {
		Outil newOutil;
		for (int i=0; i<quantity; i++) {
			newOutil = GestionnaireFactory.createElement(outil, map);
			newOutil.accept(addVisitor);
		}
		return null;
	}

	@Override
	public Void visit(Engins engin, int quantity) {
		Engin newEngin;
		for (int i=0; i<quantity; i++) {
			newEngin = GestionnaireFactory.createElement(engin, map);
			newEngin.accept(addVisitor);
		}
		GestionnaireStructures.getInstance().getStructures().get(Structures.GARAGE).get(0).setImage(GameConfiguration.IMAGE_PATH+"Structure"+File.separator+"GarageFilled.png");
		return null;
	}

	@Override
	public Void visit(Terrains terrain, int quantity) {
		Terrain newTerrain;
		for (int i=0; i<quantity; i++) {
			newTerrain = GestionnaireFactory.createElement(terrain, map);
			newTerrain.accept(addVisitor);
		}		
		return null;
	}
	
	@Override
	public Void visit(Encloss enclos, int quantity) {
		Enclos newEnclos;
		for (int i=0; i<quantity; i++) {
			newEnclos = GestionnaireFactory.createElement(enclos, map);
			newEnclos.accept(addVisitor);
		}		
		return null;
	}

	@Override
	public Void visit(Employees employee, int quantity) {
		Employee employe = new Employee(employee, quantity, null);
		GestionnaireRH.getInstance().getEmployees().put(employee, employe);
		return null;
	}

	@Override
	public Void visit(Produits produit, int quantity) {
		GestionnaireStocks.getInstance().add(produit, quantity);
		return null;
	}
	
	
	
}
