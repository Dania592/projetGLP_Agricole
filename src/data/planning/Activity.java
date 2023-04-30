package data.planning;

import java.io.Serializable;
import java.util.ArrayList;

import data.myExceptions.UnknownActivityException;
import data.structure.Enclos;
import data.structure.hability.Actionnable.ActionnableKey;

public enum Activity  implements Serializable{
    // ---------------------- ABATOIRE ---------------------- //
    COLLECT_MEAT(2, "Récupérer la viande", Type.GET_PRODUCTION, ActionnableKey.ABATOIRE, 2),
    SLAUGHTER(1, "Abattre", Type.SPECIAL, ActionnableKey.ABATOIRE, 30),
    // ------------------------------------------------------------------- //


    // ---------------------- ETABLE ---------------------- //
    SEND_TO_MILKING_PARLOUR(1, "Traire", Type.SENT_SPECIAL_ACTION, ActionnableKey.ETABLE, 2),
    // ------------------------------------------------------------------- //
    
   
    // ---------------------- POULAILLER ---------------------- //
    COLLECT_EGG(2, "Récolter les oeuf", Type.GET_PRODUCTION, ActionnableKey.POULAILLER, 5),
    // ------------------------------------------------------------------- //
    
    
    // ---------------------- SALLE DE TRAITE ---------------------- //
    COLLECT_MILK(3, "Récupérer le lait", Type.GET_PRODUCTION, ActionnableKey.SALLE_TRAITE, 5), 
    MILK(3, "Traire", Type.SPECIAL, ActionnableKey.SALLE_TRAITE, 10),
    // ------------------------------------------------------------------- //
    
    
    // ---------------------- MAISON ---------------------- //
    TO_REST(1, "Repos", Type.CARE, ActionnableKey.MAISON, 1),
    // ------------------------------------------------------------------- //
    
    
    // ---------------------- ENCLOS ---------------------- //
    FIX_ENCLOSURE(2, "Réparer", Type.FIX, ActionnableKey.ENCLOS, 1),
    SEND_BACK_HOME_ANIMALS(3, "Rentrer les animaux", Type.GO_BACK_HOME, ActionnableKey.ENCLOS, 10),
    FEED_ANIMAL_FROM_ENCLOSURE(1, "Nourrir", Type.FEED, ActionnableKey.ENCLOS, 5),
    COLLECT_EGG_FROM_ENCLOSURE(3, "Récolter les oeufs", Type.GET_PRODUCTION, ActionnableKey.ENCLOS, 4),
    GIVE_WATER_TO_ANIMAL(1, "Remplir l'abreuvoir", Type.GIVE_WATER, ActionnableKey.ENCLOS, 1),
    TRANSFERT_TO_PRODUCTION_ROOM(1, "Remplir l'abreuvoir", Type.SENT_SPECIAL_ACTION, ActionnableKey.ENCLOS, 1),
    SHAVE_SHEEP(3, "Tondre Mouton", Type.SPECIAL, ActionnableKey.ENCLOS, 3),
    // ------------------------------------------------------------------- //
    
    
    // ---------------------- ENTREPOT ---------------------- //
    // ------------------------------------------------------------------- //
    
    // ---------------------- PUIT ---------------------- //
    // `DRAW_WATER` is an activity that allows the player to draw water from a well.
    DRAW_WATER(2, "Puiser", Type.SPECIAL, ActionnableKey.PUIT, 2),
    COLLECT_WATER(2, "Récupere Eau", Type.GET_PRODUCTION, ActionnableKey.PUIT, 2),
    // ------------------------------------------------------------------- //


    
    // ---------------------- REFUGE ---------------------- //
    SEND_TO_ENCLOSURE(1, "Envoyer à l'enclos", Type.SEND_TO_ENCLOSURE, ActionnableKey.REFUGE, 5),
    SEND_TO_SEND_TO_SLAUGHTERHOUSE(1, "Envoyer à l'abatoire", Type.SEND_TO_SLAUGHTERHOUSE, ActionnableKey.REFUGE, 5),  
    // ------------------------------------------------------------------- //
    
    
    // ---------------------- STRUCTURE ---------------------- //
    FIX_STRUCTURE(2, "Réparer", Type.FIX, ActionnableKey.STRUCTURE, 2),
    // ------------------------------------------------------------------- //
    
    
    // ---------------------- TERRAIN ---------------------- //
    DIG_OVER(2, "Labourer",Type.SPECIAL, ActionnableKey.TERRAIN, 3),
    PLANT(3, "Planter", Type.SPECIAL, ActionnableKey.TERRAIN, 3),
    TO_WATER(2, "Arroser", Type.GIVE_WATER, ActionnableKey.TERRAIN, 2),
    FERTILIZE_GROUND(2, "Mettre de l'engrais", Type.CARE, ActionnableKey.TERRAIN, 2),
    HARVEST(3, "Récolter", Type.GET_PRODUCTION, ActionnableKey.TERRAIN, 2),
    REMOVE_ROTTEN_PLANT(3, "Retirer plantes pourries", Type.SPECIAL, ActionnableKey.TERRAIN, 1),
    // ------------------------------------------------------------------- //
    


    
    ;

    public enum Type{
        SEND_TO_ENCLOSURE, 
        SEND_TO_SLAUGHTERHOUSE,
        GO_BACK_HOME,
        SENT_SPECIAL_ACTION,    
        GET_PRODUCTION,
        FEED,
        GIVE_WATER,
        CARE,
        FIX,
        SPECIAL,
    }

    public enum GetNeededTool{
        NONE,
    }

    private int numberOfHourIfPlanned;
    private String label;
    private Type type;
    private ActionnableKey target;
    private int timeItTakes;
    

    private Activity(int numberOfHourNeeded, String label, Type type, ActionnableKey target, int timeItTakes){
        this.numberOfHourIfPlanned = numberOfHourNeeded;
        this.label = label;
        this.type = type;
        this.target = target;
        this.timeItTakes = timeItTakes;
    }   



    public int getTimeItTakesProduceAUnitInMinutes() {
        return timeItTakes;
    }



    public int getDuration() {
        return timeItTakes;
    }

    public ActionnableKey getTarget() {
        return target;
    }

    public String getLabel(){
        return label;
    }

    public int getNumberOfHourIfPlanned() {
        return numberOfHourIfPlanned;
    }

    public Type getType() {
        return type;
    }

    public int getNumberOfUnitDoneDuringRecommendedTime(){
        return numberOfHourIfPlanned*60/timeItTakes;
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

    public synchronized static ArrayList<Activity> getPossibleActivity(ArrayList<ActionnableKey> actionnableKeys){
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

