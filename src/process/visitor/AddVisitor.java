package process.visitor;

import data.acteur.Employee;
import data.espece.faune.Animal;
import data.flore.Culture;
import data.flore.terrains.Terrain;
import data.flore.terrains.TypeGraine;
import data.gestion.GestionnaireAnimaux;
import data.gestion.GestionnaireMateriel;
import data.gestion.GestionnaireRH;
import data.gestion.GestionnaireStocks;
import data.gestion.GestionnaireStructures;
import data.gestion.GestionnaireTerrains;
import data.materiel.Engin;
import data.materiel.Outil;
import data.production.Produit;
import data.structure.Structure;

public class AddVisitor implements GestionVisitor<Void>{

	@Override
	public Void visit(Animal animal) {
		GestionnaireAnimaux.getInstance().add(animal);
		return null;
	}

	@Override
	public Void visit(TypeGraine graine) {
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
		GestionnaireRH.getInstance().getEmployees().put(employee.getReference(),employee);
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
		GestionnaireStocks.getInstance().getProduits().put(product.getClass().getSimpleName(),product);
		return null;
	}
	
	@Override
	public Void visit(Terrain terrain) {
		GestionnaireTerrains.getInstance().add(terrain);
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
