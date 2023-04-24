package data.production;

public class Oeuf extends Produit{
	private final static float  PRIX_VENTE = 5 ;


	public Oeuf() {
		super(Produits.OEUF, PRIX_VENTE);
	}

	@Override
	public String toString() {
		return "Oeuf []";
	}
}