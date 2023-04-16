package process.action.factory; 

import process.action.place.action.CollectVisitor;
import process.action.place.action.FeedVisitor;
import process.action.place.transfert.SendToEnclosure;
import process.action.place.transfert.SendToSlaughterHouser;

public class TaskFactory{
    private static CollectVisitor collector = new CollectVisitor();
    private static FeedVisitor feeder = new FeedVisitor();
    private static SendToSlaughterHouser slaughterHouseSender = new SendToSlaughterHouser();
    private static SendToEnclosure enclosureSender = new SendToEnclosure();
    private static TaskFactory instance;

    private TaskFactory() {}

    public TaskFactory getInstance(){
        if(instance == null){
            instance = new TaskFactory();
        }
        return instance;
    }

    

    

}
