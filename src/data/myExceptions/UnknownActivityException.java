package data.myExceptions;

import data.planning.Activity;

public class UnknownActivityException extends Exception {
    public UnknownActivityException(Activity activity){
        super(activity + " is an unknown activity"); 
    }


    public UnknownActivityException(String unknownActivity){
        super(unknownActivity + " is an unknown activity"); 
    } 
}
