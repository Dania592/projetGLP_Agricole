package process.action.exception.structure;

import data.flore.terrains.Terrain;
import data.planning.Activity;
import data.structure.hability.Actionnable;

public class UnableToPerformSuchActionWithCurrentActionnable extends Exception {
    public UnableToPerformSuchActionWithCurrentActionnable(Terrain terrain){
        super("Cannot perform "+ terrain.getType()+ " with current Actionnable");
    }

    public UnableToPerformSuchActionWithCurrentActionnable(){
        super("Cannot perform such activity with current Actionnable");
    }

    public UnableToPerformSuchActionWithCurrentActionnable(Actionnable actionnable){
        super("Cannot perform current activity with "+actionnable);
    }
    public UnableToPerformSuchActionWithCurrentActionnable(Activity  activity, Actionnable actionnable){
        super("Cannot perform "+ activity.getType()+ " with "+ actionnable);
    }

    
    

}
