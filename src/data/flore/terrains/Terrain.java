package data.flore.terrains;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JLabel;

import data.map.Map;
import data.stucture_base.Element;
import process.transaction.Buyable;
import process.visitor.GestionVisitor;



//Traitement se rapproche d'un animal !!! Sauf que n'est pas un foodConsumer ! 
public class Terrain extends Element implements Buyable{
	
	private static int SPEED = 10;// vitesse d'évolution
	private static int DIMENSION = 16; // C'est un carré donc une seule dimension
	private static float PRIX_ACHAT = 100;
	// temporaire 
	private HashMap<String, JLabel> actions;

	private int index = 0;
	private int count = 0;
	
	private int quantiteProduction;
	
	private EvolutionTerrain evolution;
	private TypeGraine type;
	
	private HashMap<EvolutionTerrain, BufferedImage> images = new HashMap<>();
	
	public Terrain(String reference, boolean statique, int ligne_init, int colonne_init, Map map,TypeGraine type) {
		super(reference, statique, DIMENSION, ligne_init, colonne_init, map);
		evolution = EvolutionTerrain.VIERGE;
		this.type = type;
		actions = new HashMap<>();
		actions.put("Labourer", new JLabel("Labourer"));
		images = ImagesTerrains.getInstance().getImages().get(type);
		setImage(images.get(evolution));
		randomQuantity();
	}
	
	public void evoluer() {
		index++;
		if (index > SPEED) {
			index = 0;
			nextEvolution();
		}
	}

	public void nextEvolution() {
		switch (count) {
		case 0 :
			evolution = EvolutionTerrain.VIERGE;
			break;
		case 1 :
			evolution = EvolutionTerrain.LABOURE;
			break;
		case 2 :
			evolution = EvolutionTerrain.PLANTE;
			break;
		case 3 :
			evolution = EvolutionTerrain.PLANTE_1;
			break;
		case 4 :
			evolution = EvolutionTerrain.PLANTE_2;
			break;
		case 5 :
			evolution = EvolutionTerrain.PLANTE_3;
			break;
		case 6 :
			evolution = EvolutionTerrain.PLANTE_4;
			break;
		case 7 :
			evolution = EvolutionTerrain.PLANTE_5;
			break;
		default :
			break;
		}
		setActions();
		setImage(images.get(evolution));
		count++;
		
	}
	
	public EvolutionTerrain getEvolution() {
		return evolution;
	}

	public TypeGraine getType() {
		return type;
	}
	
	public void setEvolution(EvolutionTerrain evolution) {
		this.evolution = evolution;
	}
	
	public void randomQuantity() {
		quantiteProduction = ThreadLocalRandom.current().nextInt(10,20);
	}
	
	public BufferedImage getCurrentImage() {
		return getImage();
	}
	
	public HashMap<String, JLabel> getActions() {
		return actions;
	}
	
	@Override
	public float getPrixAchat() {
		return PRIX_ACHAT;
	}
	
	@Override
	public <T> T accept(GestionVisitor<T> visitor) {
		visitor.visit(this);
		return null;
	}

	public void setActions() {
		if (evolution == EvolutionTerrain.VIERGE) {
			actions = new HashMap<>();
			actions.put("Labourer", new JLabel("Labourer"));
		} else if (evolution == EvolutionTerrain.LABOURE) {
			actions = new HashMap<>();
			actions.put("Planter", new JLabel("Planter"));
			actions.put("Arroser", new JLabel("Arroser"));
		}else if (evolution == EvolutionTerrain.PLANTE_5) {
			actions = new HashMap<>();
			actions.put("Recolter", new JLabel("Recolter"));
			actions.put("Arroser", new JLabel("Arroser"));
		} else {
			actions = new HashMap<>();
			actions.put("Arroser", new JLabel("Arroser"));
		}
	}


	public String toString() {
		return "Terrain de " + type;
	}


		
}