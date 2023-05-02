package gui.gestionnaire.keys;

import data.espece.Produceur.Type;
import data.production.Produits;
import process.transaction.Buyable;
import process.visitor.GestionVisitor;
import process.visitor.KeyVisitor;

public enum Graine implements Buyable, Keys{
	
	GRAINE_RADIS_BLANC(Produits.RADISH),
	GRAINE_RADIS_ROUGE(Produits.RED_CABBAGE),
	GRAINE_TOMATE(Produits.TOMATO),
	GRAINE_AUBERGINE(Produits.EGGPLANT),
	GRAINE_CITROUILLE(Produits.PUMPKIN),
	GRAINE_CACTUS(Produits.CACTUS),
	GRAINE_CHOUX_BLANC(Produits.CAULIFLOWER),
	GRAINE_AIL(Produits.GARLIC),
	GRAINE_POMME_DE_TERRE(Produits.POTATO),
	GRAINE_MYRTILLE(Produits.BLUEBERRY),
	GRAINE_MAIS(Produits.CORN),
	GRAINE_POIVRON_ROUGE(Produits.HOT_PEPPER), 
	GRAINE_ANANAS(Produits.PINEAPPLE),
	GRAINE_RAISIN(Produits.GRAPE),
	GRAINE_CHOUX_ROUGE(Produits.CABBAGE),
	GRAINE_POIVRON_VERT(Produits.JALEPENO),
	GRAINE_OIGNON(Produits.YELLOW_ONION),
	GRAINE_CARROTTE(Produits.CARROT);

	private Produits product;
	@Override
	public float getPrixVente() {
		return 6;
	}
	
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