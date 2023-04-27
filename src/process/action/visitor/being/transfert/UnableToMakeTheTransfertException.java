package process.action.visitor.being.transfert;

public class UnableToMakeTheTransfertException extends Exception{
    public UnableToMakeTheTransfertException(String msg){
        super("Unable to make the transfert cause : "+ msg);
    }
}
