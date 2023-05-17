package gui.gestionnaire.keys;

import data.espece.characteristic.Produceur.Type;
import data.production.Produits;
import process.gestion.transaction.Buyable;
import process.gestion.visitor.GestionVisitor;
import process.gestion.visitor.KeyVisitor;

public enum Graine implements Buyable, Keys{
	
	GRAINE_RADIS_BLANC(Produits.RADIS_BLANC),
	GRAINE_RADIS_ROUGE(Produits.RADIS_ROUGE),
	GRAINE_TOMATE(Produits.TOMATE),
	GRAINE_AUBERGINE(Produits.AUBERGINE),
	GRAINE_CITROUILLE(Produits.CITROUILLE),
	GRAINE_CACTUS(Produits.CACTUS),
	GRAINE_CHOUX_BLANC(Produits.CHOUX_BLANC),
	GRAINE_AIL(Produits.AIL),
	GRAINE_POMME_DE_TERRE(Produits.POMME_DE_TERRE),
	GRAINE_MYRTILLE(Produits.MYRTILLE),
	GRAINE_MAIS(Produits.MAIS),
	GRAINE_POIVRON_ROUGE(Produits.POIVRON_ROUGE), 
	GRAINE_ANANAS(Produits.ANANAS),
	GRAINE_RAISIN(Produits.RAISIN),
	GRAINE_CHOUX_ROUGE(Produits.CHOUX_ROUGE),
	GRAINE_POIVRON_VERT(Produits.POIVRON_VERT),
	GRAINE_OIGNON(Produits.OIGNON),
	GRAINE_CARROTTE(Produits.CARROTTE);

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