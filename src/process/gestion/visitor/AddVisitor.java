package process.gestion.visitor;

import data.acteur.Employee;
import data.espece.faune.Animal;
import data.espece.flore.terrains.Terrain;
import data.gestion.GestionnaireAnimaux;
import data.gestion.GestionnaireEnclos;
import data.gestion.GestionnaireMateriel;
import data.gestion.GestionnaireRH;
import data.gestion.GestionnaireStocks;
import data.gestion.GestionnaireStructures;
import data.gestion.GestionnaireTerrains;
import data.materiel.Engin;
import data.materiel.Outil;
import data.production.Produit;
import data.production.Produits;
import data.structure.Enclos;
import data.structure.Structure;
import gui.gestionnaire.keys.Graine;

public class AddVisitor implements GestionVisitor<Void>{

	@Override
	public Void visit(Animal animal) {
		GestionnaireAnimaux.getInstance().add(animal);
		return null;
	}

	@Override
	public Void visit(Graine graine) {
	
		GestionnaireStocks.getInstance().add(graine);
		return null;
	}

	@Override
	public Void visit(Structure structure) {
	
		GestionnaireStructures.getInstance().add(structure);
		return null;
	}

	@Override
	public Void visit(Employee employee) {
	
		GestionnaireRH.getInstance().getEmployees().put(employee.getNom(),employee);
		return null;
	}

	@Override
	public Void visit(Outil outil) {
		
		GestionnaireMateriel.getInstance().add(outil);;
		return null;
	}

	@Override
	public Void visit(Engin engin) {
	
		GestionnaireMateriel.getInstance().add(engin);
		return null;
	}
	
	@Override
	public Void visit(Produit product) {
		
		return visit(product.getType());

	}

	public Void visit(Produits productType) {
	
		GestionnaireStocks.getInstance().add(productType);
		return null;
	}
	
	@Override
	public Void visit(Terrain terrain) {
		
		GestionnaireTerrains.getInstance().add(terrain);
		return null;
	}
	
	@Override
	public Void visit(Enclos enclos) {
		GestionnaireEnclos.getInstance().add(enclos);
		return null;
	}


}
