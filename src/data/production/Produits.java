package data.production;

import gui.gestionnaire.GestionnaireKey;
import gui.gestionnaire.keys.Keys;
import process.visitor.KeyVisitor;

public enum Produits implements Keys{
	LAIT, LAINE, OEUF,
	RADISH, RED_CABBAGE, TOMATO,
	WHEAT, AMARANTH, ARTICHOKE,
	BEET, BOK_CHOY, BRUSSEL_SPROUTS,
	CRANBERRIES, EGGPLANT, PUMPKIN, 
	YAM, CACTUS, CAULIFLOWER, 
	GARLIC, ZUCCHINI, POTATO,
	KALE, GREEN_BEAN, PARSNIP,
	RHUBARB, STRAWBERRY, BLUEBERRY,
	CORN, HOT_PEPPER, MELON,
	PINEAPPLE, GRAPE, SWISS_CHARD,
	CABBAGE, BROCCOLI, PEAS,
	JALEPENO, GREEN_PEPPER, RED_PEPPER,
	YELLOW_PEPPER, WATERMELON, YELLOW_ONION,
	RED_ONION, CARROT, MEAT, WATER;

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
