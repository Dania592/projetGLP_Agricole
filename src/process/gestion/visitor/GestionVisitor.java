package process.gestion.visitor;

import java.io.Serializable;

import data.acteur.Employee;
import data.espece.faune.Animal;
import data.espece.flore.terrains.Terrain;
import data.materiel.Engin;
import data.materiel.Outil;
import data.production.Produit;
import data.structure.Enclos;
import data.structure.Structure;
import gui.gestionnaire.keys.Graine;


public interface GestionVisitor<T> extends Serializable {

		T visit(Animal animal);

		T visit(Graine graine);

		T visit(Structure structure);

		T visit(Employee employee);
		
		T visit(Outil outil);

		T visit(Engin engin);
		
		T visit(Produit product);
		
		T visit(Terrain terrain);
		
		T visit(Enclos enclos);
		
}