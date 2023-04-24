package process.evolution;

import data.notification.Message;
import data.notification.Messagerie;
import data.time.Clock;

public enum FullLevel {

	FULL(3),
	HALF_FULL(2),
	QUARTER_FULL(1),
	EMPTY(0); 

	int associateNumber;

	private FullLevel(int associateNumber){
		this.associateNumber = associateNumber;
	}

	public FullLevel getNextState(Clock clock , String type) {
		switch(this) {
		case FULL :
			return HALF_FULL;
		case HALF_FULL :
			return QUARTER_FULL;
		case QUARTER_FULL :
			Message message = new Message("Plus de "+type+" dans \nun enclos", Clock.getInstance().getHour().getValue() , Clock.getInstance().getMinute().getValue());
			Messagerie.getInstance().addMessage(message);
			return EMPTY ;
		case EMPTY :
			return EMPTY;
		default : 
			return FULL ;
		}
	}


	public boolean isLessThan(FullLevel other){
		return this.associateNumber < other.associateNumber;
	}

	public boolean isLessOrEqualTo(FullLevel other){
		return this.associateNumber < other.associateNumber;
	}

	public boolean isMoreThan(FullLevel other){
		return this.associateNumber < other.associateNumber;
	}

	public boolean isMoreOrEqualTo(FullLevel other){
		return this.associateNumber < other.associateNumber;
	}



}