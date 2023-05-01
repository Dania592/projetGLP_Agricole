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
import data.production.Produits;
import data.structure.Enclos;
import data.structure.Structure;
import gui.gestionnaire.keys.Graine;

public class AddVisitor implements GestionVisitor<Void>{

	@Override
	public Void visit(Animal animal) {
		System.out.println("Adiing animal");
		GestionnaireAnimaux.getInstance().add(animal);
		return null;
	}

	@Override
	public Void visit(Graine graine) {
		System.out.println("Adiing graine");
		GestionnaireStocks.getInstance().add(graine);
		return null;
	}

	@Override
	public Void visit(Structure structure) {
		System.out.println("Adiing struct");
		GestionnaireStructures.getInstance().add(structure);
		return null;
	}

	@Override
	public Void visit(Employee employee) {
		System.out.println("Adiing employee");
		GestionnaireRH.getInstance().getEmployees().put(employee.getNom(),employee);
		return null;
	}

	@Override
	public Void visit(Outil outil) {
		System.out.println("Adiing outil");
		GestionnaireMateriel.getInstance().add(outil);;
		return null;
	}

	@Override
	public Void visit(Engin engin) {
		System.out.println("Adiing engin");
		GestionnaireMateriel.getInstance().add(engin);
		return null;
	}
	
	@Override
	public Void visit(Produit product) {
		System.out.println("Adiing product");
		return visit(product.getType());

	}

	public Void visit(Produits productType) {
		System.out.println("Adiing productType");
		GestionnaireStocks.getInstance().add(productType);
		return null;
	}
	
	@Override
	public Void visit(Terrain terrain) {
		System.out.println("Adiing terrain");
		GestionnaireTerrains.getInstance().add(terrain);
		return null;
	}
	
	@Override
	public Void visit(Enclos enclos) {
		System.out.println("Adiing enclos");
		GestionnaireEnclos.getInstance().add(enclos);
		return null;
	}

//	@Override
//	public Void visit(Achat achat) {
//		GestionnaireFinancier.getInstance().getTransactions().put(achat.getReference(),achat);
//		return null;
//	}
//	
//	@Override
//	public Void visit(Vente vente) {
//		GestionnaireFinancier.getInstance().getTransactions().put(vente.getReference(),vente);
//		return null;
//	}
	
}
