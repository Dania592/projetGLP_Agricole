package process.action.exception.structure;

import data.planning.Activity;
import data.structure.hability.Actionnable;

public class TaskNotNeededToBePerform extends Exception {
    public <T extends Actionnable> TaskNotNeededToBePerform(Activity activity, T actionnable){
        super(activity +" doesn't need to be performed on "+ actionnable.getActionnableKey());
    }
    
}
