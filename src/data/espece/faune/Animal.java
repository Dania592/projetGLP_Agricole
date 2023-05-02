package data.espece.faune;


import data.espece.characteristic.EtreVivant;
import data.espece.characteristic.FoodConsumer;
import data.espece.characteristic.WaterConsumer;
import data.gestion.Stockage;
import data.myExceptions.MortException;
import data.notion.evolution.EvolutionAnimal;
import data.structure.Structure;
import gui.gestionnaire.keys.Animals;
import gui.gestionnaire.keys.GestionnaireKey;
import process.evolution.Direction;
import process.gestion.transaction.Buyable;
import process.gestion.visitor.GestionVisitor;

/**
 * class abstraite regroupant le conmportement de tout les animaux 
 *
 */
public abstract class Animal extends EtreVivant implements Stockage, Buyable, FoodConsumer, WaterConsumer{
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * heure de naissance de l'animal
	 */
	private int naissance ;
	/**
	 * poids de l'animal pour pouvoir recuperer sa viande 
	 */
	private float poids ;
	/**
	 * nom de l'animal 
	 */
	private String nom ;
	/**
	 * sexe de l'animal 
	 */
	private String sexe ;
	/**
	 * structure qui correspond à l'habitat de l'animal 
	 */
	private Structure habitat ;
	/**
	 * état d'évolution d'age des animaux 
	 */
	private EvolutionAnimal evolution ;
	/**
	 * direction de deplacement de l'animal 
	 */
	private Direction direction ;
	/**
	 * état de faim de l'animal 
	 */
	private HungerLevel hungerLevel;
	/**
	 * état d'hydaration de l'animal 
	 */
	private HydrationLevel hydrationLevel;
	/**
	 * dernier moment d'évolution de l'animal
	 */
	private int lastEvolutionHour ;
	/**
	 * vitesse d'évolution de l'age de l'animal
	 */
	private int speedGrowth ; 
	/**
	 * constructeur nécéssaire pour le polymorphisme 
	 * @param dureeVie : durée de vie de l'animal 
	 * @param prixAchat : prix d'achat de l'animal
	 * @param naissance : heure de naissance de l'animal
	 * @param poids : poids de l'animal
	 * @param nom : nom de l'animal
	 * @param sexe : sexe de l'animal
	 * @param habitat : structure habitat de l'animal 
	 * @param reference : reference par rapport à la map 
	 * @param speedGrowth : vitesse d'évolution de l'animal
	 */
	public Animal(  int dureeVie, float prixAchat , int naissance, float poids, String nom,  String sexe, Structure habitat, String reference   , int speedGrowth) {
				super(1, dureeVie, prixAchat,reference );
		this.naissance = naissance;
		this.poids = poids;
		this.nom = nom;
		this.sexe = sexe;
		this.habitat = habitat;
		this.evolution = EvolutionAnimal.JEUNE;
		hungerLevel = HungerLevel.FULL; 
		hydrationLevel = HydrationLevel.FULLY_HYDRATED; 
		lastEvolutionHour = naissance;
		direction=Direction.STAND;
		this.speedGrowth = speedGrowth;
		
	}
	/**
	 * retourne la vitesse d'evolution de l'animal
	 * @return
	 */
	public int getGrowthSpeed() {
		return speedGrowth; 
	}

	/**
	 * retourne la direction vers lequel avance l'animal
	 * @return
	 */
	public Direction getDirection() {
		return direction;
	}
	/**
	 * modifie la direction de l'animal 
	 * @param new_direction
	 */
	public void setDirection(Direction new_direction) {
		direction = new_direction;
	}

	/**
	 * vérifie si l'animal est bien hydraté 
	 */
	@Override
    public boolean isEnoughHydrated(){
		return hydrationLevel == HydrationLevel.NORMAL || hydrationLevel == HydrationLevel.FULLY_HYDRATED;
	}
	/**
	 * retourne le dernier moment d'évolution de l'animal
	 * @return
	 */
	public int getLastEvolutionHour() {
		return lastEvolutionHour;
	}
	/**
	 * modifie le dernier moment d'évolution de l'animal
	 * @param hour
	 */
	public void setLastEvolutionHour(int hour) {
		lastEvolutionHour = hour ; 
	}

	/**
	 * retourne la date de naissance de l'animal 
	 * @return
	 */
	public int getNaissance() {
		return naissance;
	}
	/**
	 * modifie la date de naissance de l'animal lors du positionnement sur la map 
	 * @param date
	 */
	public void setNaissance(int date) {
		naissance=date;
		lastEvolutionHour = date ; 
	}
	/**
	 * retourne le degré d'hydratation de l'animal 
	 * @return
	 */
	public HydrationLevel getHydrationLevel() {
		return hydrationLevel;
	}
	/**
	 * modifie le degré d'hydratation de l'animal 
	 * @param hydrationLevel
	 */
	public void setHydrationLevel(HydrationLevel hydrationLevel) {
		this.hydrationLevel = hydrationLevel;
	}
	/**
	 * retourne le poids de l'animal 
	 * @return
	 */
	public float getPoids() {
		return poids;
	}
	/**
	 * retourne le nom de l'animal
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * retrourne le sexe de l'animal 
	 * @return
	 */
	public String getSexe() {
		return sexe;
	}

	/**
	 * retourne l'habitat de l'animal 
	 * @return
	 */
	public Structure getHabitat() {
		return habitat;
	}

	/**
	 * retourne l'état d'évolution de l'age des animaux 
	 * @return
	 */
	public EvolutionAnimal getEvolution() {
		return evolution;
	}

	/**
	 * passe d'un état d'évolution d'age au suivant 
	 * @throws MortException
	 */
	public void vieillir() throws MortException{
		this.evolution = (EvolutionAnimal) evolution.evolue();
	}

	/**
	 * Visitor pour l'ajout dans le gestionnaire 
	 */
	@Override
	public <T> T accept(GestionVisitor<T> visitor) {
		visitor.visit(this);
		return null;
	}

	/**
	 * retourne l'état de faim de l'animal 
	 */
	public HungerLevel getHungerLevel(){
		return hungerLevel;
	}
	/**
	 * vérifie si l'animal a faim 
	 */
    public boolean isHungry(){
		return hungerLevel == HungerLevel.HUNGRY || hungerLevel == HungerLevel.VERY_HUNGRY;
	}

    /**
     * modifie l'état de faim de l'animal 
     * @param hungerLevel
     */
    public void setHungerLevel(HungerLevel hungerLevel) {
        this.hungerLevel = hungerLevel;
    }
    
    /**
     * retourne la clé du gestionnaire 
     */
    public GestionnaireKey getGestionnaireKey() {
    	return GestionnaireKey.ANIMALS;
    }
    
    /**
     *retourne la clé à utiliser dans le gestionnaire  
     */
    public abstract Animals getKey();


	
}
