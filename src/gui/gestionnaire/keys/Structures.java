package gui.gestionnaire.keys;

import gui.gestionnaire.GestionnaireKey;
import process.visitor.KeyVisitor;

public enum Structures implements Keys{

	MAISON,
	POULAILLER,
	ETABLE,
	GRANGE,
	ENTREPOT,
	SALLE_DE_TRAITE,
	ABATTOIRE,
	CAGE_POISSON,
	REFUGE_CHAMEAU,
	REFUGE_CANARDLUNE,
	GARAGE;
	
	private float prixAchat;
	
	Structures() {}
	
	@Override
	public float getPrixAchat() {
		return prixAchat;
	}
	
	@Override
	public <T> T accept(KeyVisitor<T> visitor, int quantity) {
		visitor.visit(this, quantity);
		return null;
	}

	@Override
	public GestionnaireKey getGestionnaireKey() {
		return GestionnaireKey.STRUCTURES;
	}

}