package data.espece.faune;


import data.espece.EtreVivant;
import data.espece.FoodConsumer;
import data.espece.Milieu;
import data.espece.evolution.EvolutionAnimal;
import data.gestion.Stockage;
import data.map.Map;
import data.myExceptions.MortException;
import data.structure.Structure;
import process.transaction.Buyable;
import process.transaction.Saleable;
import process.visitor.GestionVisitor;




public abstract class Animal extends EtreVivant implements Stockage, Saleable, Buyable, FoodConsumer{

	private int naissance ;
	private float poids ;
	private String nom ;
	private Alimentation alimentation ;
	private String sexe ;
	private Structure habitat ;
	private EvolutionAnimal evolution ;
	private int lastEvolutionHour;
	private HungerLevel hungerLevel;
	
	public Animal( int ligne_init, int colonne_init, Milieu milieu, int dureeVie, float prixAchat , int naissance, float poids, String nom, 
			Alimentation alimentation, String sexe, Structure habitat, String reference , Map map ) {
		super(1, ligne_init, colonne_init, milieu, dureeVie, prixAchat,1 , reference , map );
		this.naissance = naissance;
		this.poids = poids;
		this.nom = nom;
		this.alimentation = alimentation;
		this.sexe = sexe;
		this.habitat = habitat;
		this.evolution = EvolutionAnimal.JEUNE;
		hungerLevel = HungerLevel.FULL; 
		lastEvolutionHour = naissance;
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

	@Override
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

	public void feed(){
		hungerLevel = HungerLevel.FULL;
	}
	
}
