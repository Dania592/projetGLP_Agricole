package data.myExceptions;

import data.planning.Hour;

public class AskingToWorkAtIllegalHourException extends Exception {
    public AskingToWorkAtIllegalHourException(Hour hour){
        super("ILLEGAL HOUR : is asking to work at "+ hour);
    }

    public AskingToWorkAtIllegalHourException(int hour){
        super("ILLEGAL HOUR : is asking to work at "+ hour);
    }
}
