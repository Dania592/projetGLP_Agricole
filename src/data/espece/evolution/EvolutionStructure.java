package data.espece.evolution;
/**
 * Enumerationd des états d'évolution des structures 
 * 
 *
 */
public enum EvolutionStructure{
	ETAT_INITIAL, 
	ABIME_PAR_CATASTROPHE;

	/**
	 * passage d'un état initial à un état abimé 
	 * @return
	 */
	public EvolutionStructure evolue() {
		switch(this){
			case ETAT_INITIAL:
				return ABIME_PAR_CATASTROPHE;
			default:
				return ABIME_PAR_CATASTROPHE;
		}
	}


}
