package process.visitor;

import java.util.ArrayList;

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

public class RemoveVisitor implements GestionVisitor<Void>{

	@Override
	public Void visit(Animal animal) {
		ArrayList<Animal> animals = GestionnaireAnimaux.getInstance().getAnimaux().get(animal.getClass().getSimpleName());
		if (animals != null ) {
			animals.remove(animal);
		}
		return null;
	}
	
	@Override
	public Void visit(TypeGraine graine) {
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
		GestionnaireMateriel.getInstance().getOutils().remove(outil.getType(),outil);
		return null;
	}

	@Override
	public Void visit(Engin engin) {
		GestionnaireMateriel.getInstance().getEngins().remove(engin.getType(),engin);
		return null;
	}
	
	@Override
	public Void visit(Produit product) {
		GestionnaireStocks.getInstance().getProduits().remove(product.getReference(),product);
		return null;
	}
	
	@Override
	public Void visit(Terrain terrain) {
		GestionnaireTerrains.getInstance().getTerrains().remove(terrain.getReference(),terrain);
		return null;
	}


	
}
