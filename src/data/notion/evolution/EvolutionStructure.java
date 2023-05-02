package data.notion.evolution;
/**
 * Enumeration des états d'évolution des {@link data.structure.Structure}
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
