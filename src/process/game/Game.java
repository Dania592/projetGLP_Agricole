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
import data.materiel.Engin;
import data.materiel.Outil;
import data.structure.Maison;
import data.structure.Structure;
import gui.gestionnaire.keys.Engins;
import gui.gestionnaire.keys.Graine;
import gui.gestionnaire.keys.Outils;
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
		Terrain plante2 = new Terrain("p15", false, 0,0, map, Graine.AMARANTH_SEED);
		Terrain plante3 = new Terrain("p15", false, 0,0, map, Graine.ARTICHOKE_SEED);
		Terrain plante4 = new Terrain("p15", false, 0,0, map, Graine.BEET_SEED);
		Terrain plante5 = new Terrain("p15", false, 0,0, map, Graine.BLUEBERRY_SEED);
		Terrain plante6 = new Terrain("p15", false, 0,0, map, Graine.BOK_CHOY_SEED);
		Terrain plante7 = new Terrain("p15", false, 0,0, map, Graine.BROCCOLI_SEED);
		Terrain plante8 = new Terrain("p15", false, 0,0, map, Graine.BRUSSEL_SPROUTS_SEED);
		Terrain plante9 = new Terrain("p15", false, 0,0, map, Graine.CABBAGE_SEED);
		Terrain plante10 = new Terrain("p15", false, 0,0, map, Graine.CACTUS_SEED);
		Terrain plante11 = new Terrain("p15", false, 0,0, map, Graine.CARROT_SEED);
		
		Outil outil = new Outil(null, false, 0, 0, 0, map, Outils.ARROSOIR);
		Outil outil1 = new Outil(null, false, 0, 0, 0, map, Outils.PELE);
		
		Engin engin = new Engin(null, false, 0, 0, 0, map, Engins.TONDEUSE);
		Engin engin1 = new Engin(null, false, 0, 0, 0, map, Engins.TRACTEUR);
		
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
		
		achat.addToCart(outil.getType());
		achat.addToCart(outil1.getType());
		achat.addToCart(engin.getType());
		achat.addToCart(engin1.getType());
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
		
		
		vente.addToCart(plante4.getKey());
		vente.addToCart(plante5.getKey());
		vente.addToCart(plante6.getKey());
		vente.addToCart(plante7.getKey());
		vente.addToCart(plante8.getKey());
		
		vente.addToCart(plante9.getKey());
		vente.addToCart(plante10.getKey());
		vente.addToCart(plante11.getKey());
		
		System.out.println("************************************************");
		System.out.println(ressourcesManager);
		System.out.println(vente.isValidated());
		System.out.println("Animaux " + ressourcesManager.getGestionnaireAnimaux().getAnimaux().size());
		vente.validate();
		System.out.println(vente.isValidated());
		System.out.println(ressourcesManager);
		System.out.println(vente);
		System.out.println("Animaux " + ressourcesManager.getGestionnaireAnimaux().getAnimaux().size());
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		MapManager manager = GameBuilder.MapBuilder();
		game.acheter(manager.getMap());
	}
}
