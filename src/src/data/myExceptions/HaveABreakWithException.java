package data.myExceptions;

import data.planning.Activity;

public class HaveABreakWithException extends Exception {
    
    public HaveABreakWithException(Activity activity){
        super("Have a break with "+ activity);
    }
}
