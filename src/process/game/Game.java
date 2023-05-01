package process.game;


import data.espece.Milieu;
import data.espece.faune.Animal;
import data.espece.faune.Mouton;
import data.espece.faune.Vache;
import data.finance.Banque;
import data.flore.Culture;

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
		
		Banque.getInstance().getCompte().setSolde(10000);
		//Animal vache3 = new Vache(0, 0, null, null, null, null, "v3");
		Animal vache = new Vache(0, null, null, null, "v11");
		Animal vache1 = new Vache(0, null, null, null, "v2");
		Animal vache2 = new Vache(0, null, null, null, "v2");
		Animal vache3 = new Vache(0, null, null, null, "v2");
		Animal vache4 = new Vache(0, null, null, null, "v2");
		Animal vache5 = new Vache(0, null, null, null, "v2");
		Animal vache6 = new Vache(0, null, null, null, "v2");
		Animal vache7 = new Vache(0, null, null, null, "v2");
		Animal vache8 = new Vache(0, null, null, null, "v2");
		Animal vache9 = new Vache(0, null, null, null, "v2");
		Animal mouton = new Mouton(0, null, null, null, "m11");
		Animal mouton0 = new Mouton(0, null, null, null, "m11");
		Animal mouton1 = new Mouton(0, null, null, null, "m11");
		Animal mouton2 = new Mouton(0, null, null, null, "m11");
		Animal mouton3 = new Mouton(0, null, null, null, "m11");
		Animal mouton4 = new Mouton(0, null, null, null, "m11");
		Terrain plante1 = new Terrain("p15", false, Graine.TOMATO_SEED);
		Terrain plante2 = new Terrain("p15", false, Graine.AMARANTH_SEED);
		Terrain plante3 = new Terrain("p15", false, Graine.ARTICHOKE_SEED);
		Terrain plante4 = new Terrain("p15", false, Graine.BEET_SEED);
		Terrain plante5 = new Terrain("p15", false, Graine.BLUEBERRY_SEED);
		Terrain plante6 = new Terrain("p15", false, Graine.BOK_CHOY_SEED);
		Terrain plante7 = new Terrain("p15", false, Graine.BROCCOLI_SEED);
		Terrain plante8 = new Terrain("p15", false, Graine.BRUSSEL_SPROUTS_SEED);
		Terrain plante9 = new Terrain("p15", false, Graine.CABBAGE_SEED);
		Terrain plante10 = new Terrain("p15", false, Graine.CACTUS_SEED);
		Terrain plante11 = new Terrain("p15", false, Graine.CARROT_SEED);
		//TODO changer la structure de traitement des plantes et arbres (classes de données)
		//Culture arbre = new Pommier(0, 0, Milieu.PLAINE, "pm1", map);
		Structure maison = new Maison("ld");
		Structure maison2 = new Maison("bn");
		Structure maison3 = new Maison("bn");
		Structure maison4 = new Maison("bn");
		Structure maison5 = new Maison("bn");
		Structure maison6 = new Maison("bn");
		Structure maison7 = new Maison("bn");
		
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
		achat.addToCart(plante2.getKey());
		achat.addToCart(plante3.getKey());
		achat.addToCart(plante4.getKey());
		achat.addToCart(plante5.getKey());
		achat.addToCart(plante6.getKey());
		achat.addToCart(plante7.getKey());
		achat.addToCart(plante8.getKey());
		
		achat.addToCart(plante9.getKey());
		achat.addToCart(plante10.getKey());
		achat.addToCart(plante11.getKey());
		//achat.addToCart(arbre.getKey());
		
		System.out.println("************************************************");
		System.out.println(ressourcesManager);
		System.out.println(achat.isValidated());
		System.out.println("Animaux " + ressourcesManager.getGestionnaireAnimaux().getAnimaux().size());
		achat.validate();
		System.out.println(achat.isValidated());
		System.out.println(ressourcesManager);
		System.out.println(achat);
		System.out.println("Animaux " + ressourcesManager.getGestionnaireAnimaux().getAnimaux().size());
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		MapManager manager = GameBuilder.MapBuilder();
		game.acheter(manager.getMap());
	}
}
