package process.game;


import data.espece.Milieu;
import data.espece.faune.Animal;
import data.espece.faune.Mouton;
import data.espece.faune.Vache;
import data.finance.Banque;
import data.flore.Culture;
import data.flore.Pommier;
import data.flore.terrains.Terrain;
import data.flore.terrains.TypeGraine;
import data.gestion.RessourcesManager;
import data.map.Map;
import data.structure.Maison;
import data.structure.Structure;
import process.transaction.Achat;
import process.transaction.Vente;

public class Game {
	private RessourcesManager ressourcesManager = new RessourcesManager();
	private Achat achat = new Achat();
	private Vente vente = new Vente();
	private Banque banque = Banque.getInstance();

	
	
	public RessourcesManager getRessourcesManager() {
		return ressourcesManager;
	}

	public Achat getAchat() {
		return achat;
	}

	public Vente getVente() {
		return vente;
	}

	public Banque getBanque() {
		return banque;
	}

	public RessourcesManager getStockManager() {
		return ressourcesManager;
	}
	
	public void acheter(Map map ) {
		
	
		//Animal vache3 = new Vache(0, 0, null, null, null, null, "v3");
		Animal vache = new Vache(0, 0, 0, null, null, null, "v11" , map);
		Animal vache2 = new Vache(0, 0, 0, null, null, null, "v2", map);
		Animal mouton = new Mouton(0, 0, 0, null, null, null, "m11",map);
		Terrain plante1 = new Terrain("p15", false, 0,0, map, TypeGraine.TOMATO);
		//TODO changer la structure de traitement des plantes et arbres (classes de données)
		Culture arbre = new Pommier(0, 0, Milieu.PLAINE, "pm1", map);
		Structure maison = new Maison(0, 0, "ld" , map);
		Structure maison2 = new Maison(0, 0, "bn", map);
		
		achat.addToCart(vache);
		achat.addToCart(vache2);
		achat.addToCart(mouton);
		//achat.addToCart(vache3);
	
		achat.addToCart(plante1); 
		achat.addToCart(maison);
		achat.addToCart(maison2);
		//achat.addToCart(arbre);
		 
		System.out.println("************************************************");
		System.out.println(ressourcesManager);
		System.out.println(achat.isValidated());
		achat.validateOrder(this);
		System.out.println(achat.isValidated());
		System.out.println(ressourcesManager);
		System.out.println(achat);
		
		System.out.println("************************************************");
		vente.addToCart(vache);
		vente.addToCart(vache2);
		System.out.println(ressourcesManager);
		System.out.println(vente.isValidated());
		System.out.println(vente);
		vente.validateSale(this);
		System.out.println(vente.isValidated());
		System.out.println(ressourcesManager);
		System.out.println(vente);
		
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		MapManager manager = GameBuilder.MapBuilder();
		game.acheter(manager.getMap());
	}
}
