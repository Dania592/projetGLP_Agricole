package data.planning;

import java.util.ArrayList;

import data.myExceptions.UnknownActivityException;
import data.structure.hability.Actionnable.ActionnableKey;

public enum Activity {

    SEND_TO_MILKING_PARLOUR(1, "Envoyer en salle de traite", Type.TRANSFERT, ActionnableKey.ABATOIRE, 2),
    /*
     *  --- COLLECTION 
     */
    MILK(3, "Traire", Type.GET_PRODUCTION, ActionnableKey.ABATOIRE, 10),
    COLLECT_EGG(2, "Récolter les oeuf", Type.GET_PRODUCTION, ActionnableKey.POULAILLER, 5),
    COLLECT_EGG_FROM_ENCLOSURE(1, "Récolter les oeufs", Type.GET_PRODUCTION, ActionnableKey.ENCLOS, 2),

    /*
     * --- TRANSFERT 
     */
    SEND_TO_SLAUGHTER_HOUSE(1, "Envoyer à l'abatoire", Type.TRANSFERT, ActionnableKey.REFUGE, 5),  
    SEND_TO_ENCLOSURE(1, "Envoyer à l'enclos", Type.TRANSFERT, ActionnableKey.REFUGE, 5),
    SEND_BACK_HOME_ANIMALS(3, "Rentrer les animaux", Type.TRANSFERT, ActionnableKey.ENCLOS, 10),
    
    /*
     *  --- FIX
     */
    FIX_STRUCTURE(6, "Réparer", Type.FIX, ActionnableKey.STRUCTURE, 2),
    FIX_ENCLOSURE(2, "Réparer", Type.FIX, ActionnableKey.ENCLOS, 1),

    /*
    *   --- SPECIAL 
    */    
    TO_REST(1, "Repos", Type.CARE, ActionnableKey.MAISON, 30),
    ;

    public enum Type{
        TRANSFERT, 
        GET_PRODUCTION,
        FEED,
        CARE,
        FIX,
    }


    public enum GetNeededTool{
        NONE,
    }

    private int numberOfHourIfPlanned;
    private String label;
    private Type type;
    private ActionnableKey target;
    private int timeItTakesProduceAUnitInMinutes;
    

    private Activity(int numberOfHourNeeded, String label, Type type, ActionnableKey target, int timeItTakesProduceAUnitInMinutes){
        this.numberOfHourIfPlanned = numberOfHourNeeded;
        this.label = label;
        this.type = type;
        this.target = target;
        this.timeItTakesProduceAUnitInMinutes = timeItTakesProduceAUnitInMinutes;
    }   



    public int getTimeItTakesProduceAUnitInMinutes() {
        return timeItTakesProduceAUnitInMinutes;
    }



    public int getDuration() {
        return numberOfHourIfPlanned;
    }

    public ActionnableKey getTarget() {
        return target;
    }

    public String getLabel(){
        return label;
    }

    public String toString(){
        return getLabel();
    }

    public Type getType() {
        return type;
    }

    public int getNumberOfUnitDoneDuringRecommendedTime(){
        return numberOfHourIfPlanned*60/timeItTakesProduceAUnitInMinutes;
    }

    public Activity getActivityAssociateToLabel(String label) throws UnknownActivityException{
        int indexActivity = 0, maxIndexActivity=  Activity.values().length;
        Activity[] activities = Activity.values();
        while( indexActivity < maxIndexActivity && activities[indexActivity].getLabel() !=label){
            indexActivity++;
        }
        if(indexActivity == maxIndexActivity){
            throw new UnknownActivityException(label);
        }
        return activities[indexActivity];
    }


    public static ArrayList<Activity> getPossibleActivity(ArrayList<ActionnableKey> actionnableKeys){
        ArrayList<Activity> possibleActionsToPerform = new ArrayList<>();
        Activity[] possibleActivities = Activity.values();
        ActionnableKey currentActionnableKey;
        for (Activity currentActivity : possibleActivities) {
            currentActionnableKey = currentActivity.getTarget();
            if(actionnableKeys.contains(currentActionnableKey)){
                possibleActionsToPerform.add(currentActivity);
            }
        }
        return possibleActionsToPerform;
    }

}

