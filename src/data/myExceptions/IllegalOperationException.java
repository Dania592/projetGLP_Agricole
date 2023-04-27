package data.myExceptions;
public class IllegalOperationException extends Exception {
    public IllegalOperationException(String message){
        super("ILLEGAL OPERATION : "+ message);
    }
}
