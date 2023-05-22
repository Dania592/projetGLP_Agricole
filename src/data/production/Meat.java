package data.production;
/**
 * Est un {@link Produit} que l'on récupère depuis {@link Abattoire} après avoir tué un {@link Slaughtable}  
 */
public class Meat extends Produit {
    private final static float PRIX_VENTE = 100;

    public Meat(){
        super(Produits.VIANDE, PRIX_VENTE);
    }
    
}
