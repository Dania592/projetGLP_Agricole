package process.action.exception;

import data.planning.Activity;

public class NotImplementYetException extends Exception  {

    public NotImplementYetException() {
        super("Les autres tâches n'ont pas encore étés implémentées");    
    }
    
    public NotImplementYetException(String msg) {
        super(msg);    
    }

    public NotImplementYetException(Activity activity) {
        super(activity + " n'a pas encore été implémentée!");    }
}
