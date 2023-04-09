package data.myExceptions;

public class UnknownActivityException extends Exception {
    public UnknownActivityException(String unknownActivity){
        super(unknownActivity + " is an unknown activity"); 
    }
}
