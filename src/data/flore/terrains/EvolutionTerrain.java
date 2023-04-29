package data.flore.terrains;

public enum EvolutionTerrain {
	VIERGE,
	LABOURE,
	PLANTE,
	PLANTE_1,
	PLANTE_2,
	PLANTE_3,
	PLANTE_4,
	PLANTE_5,
	POURRI//TODO trouver img terrain pourri
	;
	
	public EvolutionTerrain evolue(){
		switch (this) {
			case VIERGE : 
				return LABOURE;
			case LABOURE : 
				return PLANTE;
			case PLANTE : 
				return PLANTE_1;
			case PLANTE_1 : 
				return PLANTE_2;
			case PLANTE_2 : 
				return PLANTE_3;
			case PLANTE_3 : 
				return PLANTE_4;
			case PLANTE_4 : 
				return PLANTE_5;
			case PLANTE_5 : 
			case POURRI :
			default:
				return POURRI;
		}

	}
	
	
	
}

