package data.myExceptions;

public class FullCapaciteException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public FullCapaciteException(String message ) {
		super(message);
	}

}
