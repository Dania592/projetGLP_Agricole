package process.evolution;

import data.notification.Message;
import data.notification.Messagerie;
import data.time.Clock;

public enum FullLevel {

	FULL,
	HALF_FULL,
	QUARTER_FULL,
	EMPTY; 


	public FullLevel getNextState(Clock clock , String type) {
		switch(this) {
		case FULL :
			return HALF_FULL;
		case HALF_FULL :
			return QUARTER_FULL;
		case QUARTER_FULL :
			Message message = new Message("Plus de "+type+" dans \nun enclos",clock.getHour(), clock.getMinute());
			Messagerie.getInstance().addMessage(message);
			return EMPTY ;
		case EMPTY :
			return EMPTY;
		default : 
			return FULL ;
		}
	}


}