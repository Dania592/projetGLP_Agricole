package process.action.visitor.being.exception;

public class UnableToMakeTheTransfertException extends Exception{
    public UnableToMakeTheTransfertException(String msg){
        super("Unable to make the transfert cause : "+ msg);
    }
}
