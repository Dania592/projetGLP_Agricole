package gui.gestionnaire.keys;

import process.visitor.KeyVisitor;

public enum Structures implements Keys{

	MAISON(150),
	POULAILLER(300),
	ETABLE(150),
	GRANGE(150),
	ENTREPOT(150),
	SALLE_DE_TRAITE(400),
	ABATTOIRE(400),
	GARAGE(150), 
	BERGERIE_CHEVRE(130),
	BERGERIE_MOUTON(160),
	PUIT(100);
	
	private float prixAchat;
	
	Structures(float prixAchat) {
		this.prixAchat = prixAchat;
	}
	
	@Override
	public float getPrixAchat() {
		return prixAchat;
	}
	
	@Override
	public float getPrixVente() {
		return 0;
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