package data.espece.evolution;

import data.myExceptions.MortException;
/**
 * 
 *Enumeration des état d'évolution d'age des animaux 
 *
 */
public enum EvolutionAnimal{
	JEUNE, 
	ADULTE, 
	VIEUX;
	/**
	 * passage d'un état d'évolution à un autre 
	 * @return : nouvel état d'évolution
	 * @throws MortException : en cas de mort de l'animal 
	 */
	public EvolutionAnimal evolue() throws MortException {
		switch(this){
		case JEUNE:
			return EvolutionAnimal.ADULTE;
		case ADULTE:
			return EvolutionAnimal.VIEUX;
		case VIEUX:
			throw new MortException();
		default:
			throw new MortException();
		}
	}
	/**
	 * revoie la durée de passage entre un état et un autre 
	 * @return
	 */
	public int getDureeEvolution() {
		switch(this) {
		case JEUNE :
			return 2;
		case ADULTE : 
			return 10 ;
		case VIEUX : 
			return 5 ;
		default:
			return 1 ;
		}
	}


}
