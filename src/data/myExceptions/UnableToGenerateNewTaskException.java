package data.myExceptions;

import data.planning.Activity;
import data.structure.hability.Actionnable;

public class UnableToGenerateNewTaskException extends Exception {
    
    public UnableToGenerateNewTaskException(){
        super("Unable To Generate new Task");
    }

    public UnableToGenerateNewTaskException(String cause){
        super("Unable To Generate new Task:: CAUSE : "+ cause);
    }


    public UnableToGenerateNewTaskException(Activity activity, Actionnable actionnable){
        super("Cannot perform "+activity+ " from "+ actionnable);
    }

    public UnableToGenerateNewTaskException(Activity activity, Actionnable actionnable, String cause){
        super("Cannot perform "+activity+ " from "+ actionnable+ " cause : "+ cause);
    }

}
