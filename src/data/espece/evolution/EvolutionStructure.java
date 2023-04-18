package data.espece.evolution;

public enum EvolutionStructure{
	ETAT_INITIAL, 
	ABIME_PAR_CATASTROPHE;

	public EvolutionStructure evolue() {
		switch(this){
			case ETAT_INITIAL:
				return ABIME_PAR_CATASTROPHE;
			default:
				return ABIME_PAR_CATASTROPHE;
		}
	}


}
