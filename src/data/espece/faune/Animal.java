package data.espece.faune;


import data.espece.EtreVivant;
import data.espece.FoodConsumer;
import data.espece.Milieu;
import data.espece.WaterConsumer;
import data.espece.evolution.EvolutionAnimal;
import data.gestion.Stockage;
import data.map.Map;
import data.myExceptions.MortException;
import data.structure.Structure;
import data.time.BoundedCounter;
import gui.gestionnaire.GestionnaireKey;
import gui.gestionnaire.keys.Animals;
import process.evolution.Direction;
import process.transaction.Buyable;
import process.visitor.GestionVisitor;

public abstract class Animal extends EtreVivant implements Stockage, Buyable, FoodConsumer, WaterConsumer{

	private static final long serialVersionUID = 1L;
	
	private int naissance ;
	private float poids ;
	private String nom ;
	private Alimentation alimentation ;
	private String sexe ;
	private Structure habitat ;
	private EvolutionAnimal evolution ;
	private Direction direction ;
	private HungerLevel hungerLevel;
	private HydrationLevel hydrationLevel;
	
	private int lastEvolutionHour ;
	private int speedGrowth ; 
	
	public Animal( int ligne_init, int colonne_init, Milieu milieu, int dureeVie, float prixAchat , int naissance, float poids, String nom, 
			Alimentation alimentation, String sexe, Structure habitat, String reference , Map map  , int speedGrowth) {
				super(1, ligne_init, colonne_init, milieu, dureeVie, prixAchat,reference , map );
		this.naissance = naissance;
		this.poids = poids;
		this.nom = nom;
		this.alimentation = alimentation;
		this.sexe = sexe;
		this.habitat = habitat;
		this.evolution = EvolutionAnimal.JEUNE;
		hungerLevel = HungerLevel.FULL; 
		hydrationLevel = HydrationLevel.FULLY_HYDRATED; 
		lastEvolutionHour = naissance;
		direction=Direction.STAND;
		this.speedGrowth = speedGrowth;
		
	}
	public int getGrowthSpeed() {
		return speedGrowth; 
	}

	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction new_direction) {
		direction = new_direction;
	}

	@Override
    public boolean isEnoughHydrated(){
		return hydrationLevel == HydrationLevel.NORMAL || hydrationLevel == HydrationLevel.FULLY_HYDRATED;
	}
	
	public int getLastEvolutionHour() {
		return lastEvolutionHour;
	}
	public void setLastEvolutionHour(int hour) {
		lastEvolutionHour = hour ; 
	}

	public int getNaissance() {
		return naissance;
	}

	public void setNaissance(int date) {
		naissance=date;
		lastEvolutionHour = date ; 
	}
	
	public HydrationLevel getHydrationLevel() {
		return hydrationLevel;
	}
	public void setHydrationLevel(HydrationLevel hydrationLevel) {
		this.hydrationLevel = hydrationLevel;
	}
	public float getPoids() {
		return poids;
	}

	public String getNom() {
		return nom;
	}


	public Alimentation getAlimentation() {
		return alimentation;
	}


	public String getSexe() {
		return sexe;
	}


	public Structure getHabitat() {
		return habitat;
	}


	public EvolutionAnimal getEvolution() {
		return evolution;
	}

	public void vieillir() throws MortException{
		this.evolution = (EvolutionAnimal) evolution.evolue();
	}


	@Override
	public <T> T accept(GestionVisitor<T> visitor) {
		visitor.visit(this);
		return null;
	}

	public HungerLevel getHungerLevel(){
		return hungerLevel;
	}
    public boolean isHungry(){
		return hungerLevel == HungerLevel.HUNGRY || hungerLevel == HungerLevel.VERY_HUNGRY;
	}

    public void setHungerLevel(HungerLevel hungerLevel) {
        this.hungerLevel = hungerLevel;
    }
    
    public GestionnaireKey getGestionnaireKey() {
    	return GestionnaireKey.ANIMALS;
    }
    
    public abstract Animals getKey();


	
}
