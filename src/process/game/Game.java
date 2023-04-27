package process.game;


import data.espece.Milieu;
import data.espece.faune.Animal;
import data.espece.faune.Mouton;
import data.espece.faune.Vache;
import data.finance.Banque;
import data.flore.Culture;
import data.flore.Pommier;
import data.flore.terrains.Terrain;
import data.gestion.RessourcesManager;
import data.map.Map;
import data.structure.Maison;
import data.structure.Structure;
import gui.gestionnaire.keys.Graine;
import process.transaction.Achat;
import process.transaction.Vente;

public class Game {
	private RessourcesManager ressourcesManager = RessourcesManager.getInstance();
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
		Animal vache1 = new Vache(0, 0, 0, null, null, null, "v2", map);
		Animal vache2 = new Vache(0, 0, 0, null, null, null, "v2", map);
		Animal vache3 = new Vache(0, 0, 0, null, null, null, "v2", map);
		Animal vache4 = new Vache(0, 0, 0, null, null, null, "v2", map);
		Animal vache5 = new Vache(0, 0, 0, null, null, null, "v2", map);
		Animal vache6 = new Vache(0, 0, 0, null, null, null, "v2", map);
		Animal vache7 = new Vache(0, 0, 0, null, null, null, "v2", map);
		Animal vache8 = new Vache(0, 0, 0, null, null, null, "v2", map);
		Animal vache9 = new Vache(0, 0, 0, null, null, null, "v2", map);
		Animal mouton = new Mouton(0, 0, 0, null, null, null, "m11",map);
		Animal mouton0 = new Mouton(0, 0, 0, null, null, null, "m11",map);
		Animal mouton1 = new Mouton(0, 0, 0, null, null, null, "m11",map);
		Animal mouton2 = new Mouton(0, 0, 0, null, null, null, "m11",map);
		Animal mouton3 = new Mouton(0, 0, 0, null, null, null, "m11",map);
		Animal mouton4 = new Mouton(0, 0, 0, null, null, null, "m11",map);
		Terrain plante1 = new Terrain("p15", false, 0,0, map, Graine.TOMATO_SEED);
		//TODO changer la structure de traitement des plantes et arbres (classes de données)
		Culture arbre = new Pommier(0, 0, Milieu.PLAINE, "pm1", map);
		Structure maison = new Maison(0, 0, "ld" , map);
		Structure maison2 = new Maison(0, 0, "bn", map);
		Structure maison3 = new Maison(0, 0, "bn", map);
		Structure maison4 = new Maison(0, 0, "bn", map);
		Structure maison5 = new Maison(0, 0, "bn", map);
		Structure maison6 = new Maison(0, 0, "bn", map);
		Structure maison7 = new Maison(0, 0, "bn", map);
		
		achat.addToCart(vache.getKey());
		achat.addToCart(vache1.getKey());
		achat.addToCart(vache2.getKey());
		achat.addToCart(vache3.getKey());
		achat.addToCart(vache4.getKey());
		achat.addToCart(vache5.getKey());
		achat.addToCart(vache6.getKey());
		achat.addToCart(mouton.getKey());
		achat.addToCart(mouton0.getKey());
		achat.addToCart(mouton1.getKey());
		achat.addToCart(mouton2.getKey());
		achat.addToCart(mouton3.getKey());
		achat.addToCart(mouton4.getKey());
		//achat.addToCart(vache3.getKey());
	
		achat.addToCart(plante1.getKey()); 
		achat.addToCart(maison.getKey());
		achat.addToCart(maison2.getKey());
		achat.addToCart(maison3.getKey());
		achat.addToCart(maison4.getKey());
		achat.addToCart(maison5.getKey());
		achat.addToCart(maison6.getKey());
		achat.addToCart(maison7.getKey());
		
		achat.addToCart(vache7.getKey());
		achat.addToCart(vache8.getKey());
		achat.addToCart(vache9.getKey());
		//achat.addToCart(arbre.getKey());
		
		System.out.println("************************************************");
		System.out.println(ressourcesManager);
		System.out.println(achat.isValidated());
		System.out.println("Animaux " + ressourcesManager.getGestionnaireAnimaux().getAnimaux().size());
		achat.validateOrder();
		System.out.println(achat.isValidated());
		System.out.println(ressourcesManager);
		System.out.println(achat);
		System.out.println("Animaux " + ressourcesManager.getGestionnaireAnimaux().getAnimaux().size());
		
//		System.out.println("************************************************");
//		vente.addToCart(vache.getKey());
//		vente.addToCart(maison2.getKey());
//		System.out.println(ressourcesManager);
//		System.out.println(vente.isValidated());
//		System.out.println(vente);
//		System.out.println("Animaux " + ressourcesManager.getGestionnaireAnimaux().getAnimaux().size());
//		vente.validateSale(this);
//		System.out.println(vente.isValidated());
//		System.out.println(ressourcesManager);
//		System.out.println(vente);
//		System.out.println("Animaux " + ressourcesManager.getGestionnaireAnimaux().getAnimaux().size());
		
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		MapManager manager = GameBuilder.MapBuilder();
		game.acheter(manager.getMap());
	}
}
