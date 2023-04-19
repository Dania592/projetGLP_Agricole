package process.visitor;

import data.espece.faune.Animal;
import data.map.Map;
import data.materiel.Engin;
import data.materiel.Outil;
import data.structure.Structure;
import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.Engins;
import gui.gestionnaire.keys.Graine;
import gui.gestionnaire.keys.Outils;
import gui.gestionnaire.keys.Structures;
import process.GestionnaireFactory;

public class AddKeyVisitor implements KeyVisitor<Void>{
	
	private GestionnaireFactory gestionnaireFactory = new GestionnaireFactory();
	private Map map = Map.getInstance();
	private AddVisitor addVisitor = new AddVisitor();

	@Override
	public Void visit(Animals animal, int quantity) {
		Animal newAnimal;
		for (int i=0; i<quantity; i++) {
			newAnimal = gestionnaireFactory.createElement(animal, map);
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
			newStructure = gestionnaireFactory.createElement(structure, map);
			newStructure.accept(addVisitor);
		}
		return null;
	}

	@Override
	public Void visit(Outils outil, int quantity) {
		Outil newOutil;
		for (int i=0; i<quantity; i++) {
			newOutil = gestionnaireFactory.createElement(outil, map);
			newOutil.accept(addVisitor);
		}
		return null;
	}

	@Override
	public Void visit(Engins engin, int quantity) {
		Engin newEngin;
		for (int i=0; i<quantity; i++) {
			newEngin = gestionnaireFactory.createElement(engin, map);
			newEngin.accept(addVisitor);
		}		return null;
	}
	
	
	
}
