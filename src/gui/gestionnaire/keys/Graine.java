package gui.gestionnaire.keys;

import data.espece.Produceur.Type;
import data.production.Produits;
import gui.gestionnaire.GestionnaireKey;
import process.transaction.Buyable;
import process.visitor.GestionVisitor;
import process.visitor.KeyVisitor;

public enum Graine implements Buyable, Keys{
	
	RADISH_SEED(Produits.RADISH),
	RED_CABBAGE_SEED(Produits.RED_CABBAGE),
	TOMATO_SEED(Produits.TOMATO),
	WHEAT_SEED(Produits.WHEAT),
	AMARANTH_SEED(Produits.AMARANTH),
	ARTICHOKE_SEED(Produits.ARTICHOKE),
	BEET_SEED(Produits.BEET),
	BOK_CHOY_SEED(Produits.BOK_CHOY),
	BRUSSEL_SPROUTS_SEED(Produits.BRUSSEL_SPROUTS),
	CRAMBERRIES_SEED(Produits.CRANBERRIES),
	EGGPLANT_SEED(Produits.EGGPLANT),
	PUMPKIN_SEED(Produits.PUMPKIN),
	YAM_SEED(Produits.YAM),
	CACTUS_SEED(Produits.CACTUS),
	CAULIFLOWER_SEED(Produits.CAULIFLOWER),
	GARLIC_SEED(Produits.GARLIC),
	ZUCCHINI_SEED(Produits.ZUCCHINI),
	KALE_SEED(Produits.KALE),
	GREEN_BEAN_SEED(Produits.GREEN_BEAN),
	PARSNIP_SEED(Produits.PARSNIP),
	POTATO_SEED(Produits.POTATO),
	RHUBARB_SEED(Produits.RHUBARB),
	STRAWBERRY_SEED(Produits.STRAWBERRY),
	BLUEBERRY_SEED(Produits.BLUEBERRY),
	CORN_SEED(Produits.CORN),
	HOT_PEPPER_SEED(Produits.HOT_PEPPER), 
	MELON_SEED(Produits.MELON),
	PINEAPPLE_SEED(Produits.PINEAPPLE),
	GRAPE_SEED(Produits.GRAPE),
	SWISS_CHARD_SEED(Produits.SWISS_CHARD),
	CABBAGE_SEED(Produits.CABBAGE),
	BROCCOLI_SEED(Produits.BROCCOLI),
	PEAS_SEED(Produits.PEAS),
	JALEPENO_SEED(Produits.JALEPENO),
	GREEN_PEPPER_SEED(Produits.GREEN_PEPPER),
	RED_PEPPER_SEED(Produits.RED_PEPPER),
	YELLOW_PEPPER_SEED(Produits.YELLOW_PEPPER),
	WATERMELON_SEED(Produits.WATERMELON), 
	YELLOW_ONION_SEED(Produits.YELLOW_ONION),
	RED_ONION_SEED(Produits.RED_ONION),
	CARROT_SEED(Produits.CARROT);

	private float prixAchat = 0;
	private Produits product;
	
	private Graine(Produits product) {
		this.product = product;
	}
	
	@Override
	public float getPrixAchat() {
		return 5;
	}
	
	public <T> T accept(GestionVisitor<T> visitor) {
		visitor.visit(this);
		return null;
	}

	public Produits getProduits(){
		return product;
	}
	
	@Override
	public Keys getKey() {
		return this;
	}
	
	@Override
	public GestionnaireKey getGestionnaireKey() {
		return GestionnaireKey.SEEDS;
	}

	@Override
	public <T> T accept(KeyVisitor<T> visitor, int quantity) {
		visitor.visit(this, quantity);
		return null;
	}
}