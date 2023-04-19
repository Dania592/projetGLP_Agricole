package data.structure;

import java.util.ArrayList;

import data.espece.faune.Chevre;
import data.espece.faune.Vache;
import data.map.Map;
import data.structure.hability.SpecialActionPerformer;
import gui.gestionnaire.keys.Structures;
import process.action.exception.NotImplementYetException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.place.PlaceVisitor;

public class SalleDeTraite extends StructureAction implements SpecialActionPerformer{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Vache> vaches = new ArrayList<>();
	private ArrayList<Chevre> chevres = new ArrayList<>();
	private final static float PRIX_ACHAT = 50000;

	public SalleDeTraite(int ligne_init, int colonne_init, String reference, Map map) {
		super(ligne_init, colonne_init, PRIX_ACHAT, reference, map);
	}

	public ArrayList<ActionnableKey> getActionnableKey() {
		ArrayList<ActionnableKey> actionnableKey = super.getActionnableKey();
		actionnableKey.add(ActionnableKey.SALLE_TRAITE);
		return actionnableKey;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable, NotImplementYetException {
		return visitor.action(this);
	}

	public Structures getKey() {
		return Structures.SALLE_DE_TRAITE;
	}
	
}
