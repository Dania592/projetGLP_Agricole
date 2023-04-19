package process.visitor;

import data.gestion.RessourcesManager;
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

public class RemoveKeyVisitor implements KeyVisitor<Void>{

	private RessourcesManager ressourcesManager = RessourcesManager.getInstance();
	private Map map;
	private RemoveVisitor removeVisitor = new RemoveVisitor();

	@Override
	public Void visit(Animals animal, int quantity) {
		for (int i=0; i<quantity; i++) {
			ressourcesManager.getGestionnaireAnimaux().remove(animal, quantity);
		}
		return null;
	}

	@Override
	public Void visit(Graine graine, int quantity) {
		for (int i=0; i<quantity; i++) {
			ressourcesManager.getGestionnaireStocks().remove(graine, quantity);
		}
		return null;
	}

	@Override
	public Void visit(Structures structure, int quantity) {
		for (int i=0; i<quantity; i++) {
			ressourcesManager.getGestionnaireStructure().remove(structure, quantity);
		}
		return null;
	}

	@Override
	public Void visit(Outils outil, int quantity) {
		Outil newOutil;
		for (int i=0; i<quantity; i++) {
			ressourcesManager.getGestionnaireMateriel().remove(outil, quantity);;
		}
		return null;
	}

	@Override
	public Void visit(Engins engin, int quantity) {
		Engin newEngin;
		for (int i=0; i<quantity; i++) {
			ressourcesManager.getGestionnaireMateriel().remove(engin, quantity);;
		}		return null;
	}

}