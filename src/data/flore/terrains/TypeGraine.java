package data.flore.terrains;

import process.transaction.Buyable;
import process.visitor.GestionVisitor;

public enum TypeGraine implements Buyable{
	
	RADISH,
	RED_CABBAGE,
	TOMATO,
	WHEAT,
	AMARANTH,
	ARTICHOKE,
	BEET,
	BOK_CHOY,
	BRUSSEL_SPROUTS,
	CRANBERRIES,
	EGGPLANT,
	PUMPKIN,
	YAM,
	CACTUS,
	CAULIFLOWER,
	GARLIC,
	ZUCCHINI,
	KALE,
	GREEN_BEAN,
	PARSNIP,
	POTATO,
	RHUBARB,
	STRAWBERRY,
	BLUEBERRY,
	CORN,
	HOT_PEPPER, 
	MELON,
	PINEAPPLE,
	GRAPE,
	SWISS_CHARD,
	CABBAGE,
	BROCCOLI,
	PEAS,
	JALEPENO,
	GREEN_PEPPER,
	RED_PEPPER,
	YELLOW_PEPPER,
	WATERMELON, 
	YELLOW_ONION,
	RED_ONION,
	CARROT;
	
	private int quantity = 0;
	private int prixAchat = 0;
	
	private TypeGraine() {}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void incrementQuantity() {
		quantity++;
	}
	
	public void decrementQuantity() {
		quantity--;
	}
	
	public <T> T accept(GestionVisitor<T> visitor) {
		visitor.visit(this);
		return null;
	}
	
	// Tomate, Aubergine, mirty, ananas, cactus, ail, green pepper, 
	// choux fleur, radis, red pepper, potato, iceberg violet, pumpkin, strawberry, carotte 
//	RADISH, RED_CABBAGE, TOMATO, WHEAT,	AMARANTH, ARTICHOKE, BEET, BOK_CHOY,
//	BRUSSEL_SPROUTS, CRANBERRIES, EGGPLANT, PUMPKIN, YAM, CACTUS, CAULIFLOWER,
//	GARLIC,	ZUCCHINI, KALE, GREEN_BEAN, PARSNIP, POTATO, RHUBARB, STRAWBERRY,
//	BLUEBERRY, CORN, HOT_PEPPER, MELON, PINEAPPLE, GRAPE, SWISS_CHARD, CABBAGE,
//	BROCCOLI, PEAS, JALEPENO, GREEN_PEPPER, RED_PEPPER, YELLOW_PEPPER,
//	WATERMELON,	YELLOW_ONION, RED_ONION, CARROT;

	@Override
	public String getReference() {
		return null;
	}

	@Override
	public float getPrixAchat() {
		return prixAchat;
	}
}