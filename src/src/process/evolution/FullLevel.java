package process.evolution;

public enum FullLevel {

	FULL,
	HALF_FULL,
	QUARTER_FULL,
	EMPTY; 


	public FullLevel getNextState() {
		switch(this) {
		case FULL :
			return HALF_FULL;
		case HALF_FULL :
			return QUARTER_FULL;
		case QUARTER_FULL :
			return EMPTY ;
		case EMPTY :
			return EMPTY;
		default : 
			return FULL ;
		}
	}


}