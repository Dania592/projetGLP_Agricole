package process.evolution;

public enum HeartLevel {
	LEVEL_100,
	LEVEL_80,
	LEVEL_60,
	LEVEL_40,
	LEVEL_20,
	LEVEL_0;
	
	public HeartLevel getNextState() {
		switch(this) {
		case LEVEL_100 :
			return LEVEL_80;
		case LEVEL_80 :
			return LEVEL_60;
		case LEVEL_60:
			return LEVEL_40 ;
		case LEVEL_40 :
			return LEVEL_20;
		case LEVEL_20 : 
			return LEVEL_0;
		case LEVEL_0 : 
			return LEVEL_0;		
		default : 
			return LEVEL_100 ;
		}
	}

	
}
