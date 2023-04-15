package process.visitor;

import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.Engins;
import gui.gestionnaire.keys.Graine;
import gui.gestionnaire.keys.Outils;
import gui.gestionnaire.keys.Structures;

public interface KeyVisitor<T> {

	T visit(Animals animal, int quantity);

	T visit(Graine graine, int quantity);

	T visit(Structures structure, int quantity);

	T visit(Outils outil, int quantity);

	T visit(Engins engin, int quantity);
	
}
