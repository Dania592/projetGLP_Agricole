package data.flore.terrains;

public enum EvolutionTerrain {
	VIERGE(1),
	LABOURE(1),
	PLANTE(3),
	PLANTE_1(3),
	PLANTE_2(3),
	PLANTE_3(3),
	PLANTE_4(3),
	PLANTE_5(10),
	POURRI(1)
	;
	
	int durationMultiplier;

	private EvolutionTerrain(int durationMultiplier) {
		this.durationMultiplier = durationMultiplier;
	}




	public int getDurationMultiplier() {
		return durationMultiplier;
	}

	public static int getCompleteCycleDurationMultiplier(){
		return PLANTE.durationMultiplier
		+ PLANTE_1.durationMultiplier
		+ PLANTE_2.durationMultiplier
		+ PLANTE_3.durationMultiplier
		+ PLANTE_4.durationMultiplier
		+ PLANTE_5.durationMultiplier;
	}



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

