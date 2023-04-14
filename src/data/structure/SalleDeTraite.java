package data.structure;

import java.util.ArrayList;

import data.map.Map;
import data.structure.hability.SpecialActionPerformer;
import process.action.exception.NotImplementYetException;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.place.PlaceVisitor;

public class SalleDeTraite extends StructureAction implements SpecialActionPerformer{

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

}
