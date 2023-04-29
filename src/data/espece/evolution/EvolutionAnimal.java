package data.espece.evolution;

import data.myExceptions.MortException;

public enum EvolutionAnimal{
	JEUNE(2), 
	ADULTE(10), 
	VIEUX(5);

	int dureeEvolution;
	
	private EvolutionAnimal(int dureeEvolution) {
		this.dureeEvolution = dureeEvolution;
	}

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

	public int getDureeEvolution() {
		return dureeEvolution;
	}


}
