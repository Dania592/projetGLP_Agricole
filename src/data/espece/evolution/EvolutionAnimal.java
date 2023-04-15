package data.espece.evolution;

import data.myExceptions.MortException;

public enum EvolutionAnimal implements Evolution{
	JEUNE, 
	ADULTE, 
	VIEUX;

	@Override
	public Evolution evolue() throws MortException {
		switch(this){
		case JEUNE:
			return ADULTE;
		case ADULTE:
			return VIEUX;
		case VIEUX:
			throw new MortException();
		default:
			throw new MortException();
		}
	}

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
