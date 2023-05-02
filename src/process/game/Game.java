package process.game;


import data.espece.faune.Animal;
import data.espece.faune.Mouton;
import data.espece.faune.Vache;
import data.espece.flore.terrains.Terrain;
import data.finance.Banque;
import data.gestion.RessourcesManager;
import data.map.Map;
import data.materiel.Engin;
import data.materiel.Outil;
import data.structure.Maison;
import data.structure.Structure;
import gui.gestionnaire.keys.Engins;
import gui.gestionnaire.keys.Graine;
import gui.gestionnaire.keys.Outils;
import process.gestion.transaction.Achat;
import process.gestion.transaction.Vente;

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
		Animal mouton = new Mouton(0, null, null, null, "m11");
		Animal mouton0 = new Mouton(0, null, null, null, "m11");
		Animal mouton1 = new Mouton(0, null, null, null, "m11");
		Animal mouton2 = new Mouton(0, null, null, null, "m11");
		Animal mouton3 = new Mouton(0, null, null, null, "m11");
		Animal mouton4 = new Mouton(0, null, null, null, "m11");
		Terrain plante1 = new Terrain("p15", false, Graine.GRAINE_TOMATE);
		Terrain plante5 = new Terrain("p15", false, Graine.GRAINE_MYRTILLE);
		Terrain plante9 = new Terrain("p15", false, Graine.GRAINE_CHOUX_ROUGE);
		Terrain plante10 = new Terrain("p15", false, Graine.GRAINE_CACTUS);
		Terrain plante11 = new Terrain("p15", false, Graine.GRAINE_CARROTTE);

		
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
		achat.addToCart(plante5.getKey());
		
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
		
		

		vente.addToCart(plante5.getKey());
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
