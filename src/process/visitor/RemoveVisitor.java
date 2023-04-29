package process.visitor;

import data.acteur.Employee;
import data.espece.faune.Animal;
import data.flore.terrains.Terrain;
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
import data.structure.Enclos;
import data.structure.Structure;
import gui.gestionnaire.keys.Graine;

public class RemoveVisitor implements GestionVisitor<Void>{

	@Override
	public Void visit(Animal animal) {
		GestionnaireAnimaux.getInstance().remove(animal);
		return null;
	}
	
	@Override
	public Void visit(Graine graine) {
		GestionnaireStocks.getInstance().getGraines().remove(graine);
		return null;
	}

	@Override
	public Void visit(Structure structure) {
		GestionnaireStructures.getInstance().getStructures().remove(structure.getClass().getSimpleName(),structure);
		return null;
	}

	@Override
	public Void visit(Employee employee) {
		GestionnaireRH.getInstance().getEmployees().remove(employee.getReference(),employee);
		return null;
	}

	@Override
	public Void visit(Outil outil) {
		GestionnaireMateriel.getInstance().remove(outil);
		return null;
	}

	@Override
	public Void visit(Engin engin) {
		GestionnaireMateriel.getInstance().remove(engin);
		return null;
	}
	
	@Override
	public Void visit(Produit product) {
		GestionnaireStocks.getInstance().getProduits().remove(product, product);
		return null;
	}
	
	@Override
	public Void visit(Terrain terrain) {
		GestionnaireTerrains.getInstance().remove(terrain);
		return null;
	}

	@Override
	public Void visit(Enclos enclos) {
		GestionnaireEnclos.getInstance().remove(enclos);
		return null;
	}


	
}
