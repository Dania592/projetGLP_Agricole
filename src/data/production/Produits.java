package data.production;

import gui.gestionnaire.keys.GestionnaireKey;
import gui.gestionnaire.keys.Keys;
import process.gestion.visitor.KeyVisitor;



/**
 * L'ensemble des produits générés par les {@link data.espece.characteristic.Produceur}
 */
public enum Produits implements Keys{
	LAIT,
	LAINE,
	OEUF,
	RADIS_BLANC,
	RADIS_ROUGE,
	TOMATE,
	AIL,
	ANANAS,
	AUBERGINE,
	BAIE,
	CACTUS,
	CARROTTE,
	CHOUX_BLANC,
	CHOUX_ROUGE,
	CITROUILLE,
	FENOUILLE,
	MAIS,
	MYRTILLE,
	OIGNON,
	POIVRON_ROUGE,
	POIVRON_VERT,
	POMME_DE_TERRE,
	RAISIN,
	TOURNESOL,	
	VIANDE, WATER;

	@Override
	public float getPrixAchat() {
		return 0;
	}

	@Override
	public <T> T accept(KeyVisitor<T> visitor, int quantity) {
		visitor.visit(this, quantity);
		return null;
	}

	@Override
	public GestionnaireKey getGestionnaireKey() {
		return GestionnaireKey.PRODUIT;
	}
	
	public String toString() {
		return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
	}

	@Override
	public float getPrixVente() {
		// TODO Auto-generated method stub
		return 0;
	}
}
