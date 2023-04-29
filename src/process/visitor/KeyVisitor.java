package process.visitor;

import data.production.Produits;
import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.Employees;
import gui.gestionnaire.keys.Encloss;
import gui.gestionnaire.keys.Engins;
import gui.gestionnaire.keys.Graine;
import gui.gestionnaire.keys.Outils;
import gui.gestionnaire.keys.Structures;
import gui.gestionnaire.keys.Terrains;

public interface KeyVisitor<T> {

	T visit(Animals animal, int quantity);

	T visit(Graine graine, int quantity);

	T visit(Structures structure, int quantity);

	T visit(Outils outil, int quantity);

	T visit(Engins engin, int quantity);
	
	T visit(Terrains terrain, int quantity);
	
	T visit(Encloss enclos, int quantity);
	
	T visit(Employees employee, int quantity);
	
	T visit(Produits employee, int quantity);
	
}
