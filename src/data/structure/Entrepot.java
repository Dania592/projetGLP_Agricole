package data.structure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import data.map.Map;
import data.production.Produit;
import process.action.exception.structure.UnableToPerformSuchActionWithCurrentActionnable;
import process.action.visitor.place.PlaceVisitor;


public class Entrepot extends Structure{

	private ArrayList<Produit> produits ;
	private int capacite =100  ; 
	private final static float PRIX_ACHAT = 50000 ;
	
	public Entrepot(int ligne_init, int colonne_init, String reference , Map map ) {
		super(ligne_init, colonne_init, PRIX_ACHAT , reference , map);
		this.produits = new ArrayList<>();
		try {
			setImage(ImageIO.read(new File("src"+File.separator+"ressources"+File.separator+"minigrange.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Produit> getProduits() {
		return produits;
	}

	public void addProduit(Produit produit) {
		produits.add(produit);
	}
	public void removeProduit(Produit produit) {
		produits.remove(produit);
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	@Override
	public <T> T launchAction(PlaceVisitor<T> visitor) throws UnableToPerformSuchActionWithCurrentActionnable {
		return visitor.action(this);
	}

	



}
