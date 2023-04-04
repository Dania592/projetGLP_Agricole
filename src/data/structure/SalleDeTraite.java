package data.structure;

import java.util.ArrayList;

import data.map.Map;
import process.action.place.PlaceVisitor;
import process.action.place.UnableToPerformSuchActionWithCurrentActionnable;

public class SalleDeTraite extends StructureAction {

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
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable {
		return visitor.action(this);
	}

}
