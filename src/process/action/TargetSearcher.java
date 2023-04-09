package process.action;
import data.planning.Activity;

import data.structure.hability.list.FeedableList;
import data.structure.hability.list.FixableList;
import data.structure.hability.list.CollectableList;

public class TargetSearcher {
    private Activity activity;
    private static TargetSearcher instance;

    private TargetSearcher(){}

    public static TargetSearcher getInstance(){
        if(instance == null){
            instance = new TargetSearcher();
        }
        return instance;
    }

    public CollectableList getTargetsForProductifTask(){
        return new CollectableList();
    }

    public FeedableList getTargetsForFeedingTask(){
        return new FeedableList(); 
    }

    public FixableList getTargetForFixingTask(){
        return new FixableList();
    }
    



}
