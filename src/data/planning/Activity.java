package data.planning;

import java.util.ArrayList;

import data.myExceptions.UnknownActivityException;
import data.structure.hability.Actionnable.ActionnableKey;

public enum Activity {
    // ---------------------- ABATOIRE ---------------------- //
    COLLECT_MEAT(2, "Récupérer la viande", Type.GET_PRODUCTION, ActionnableKey.ABATOIRE, 2),
    SLAUGHTER(4, "Abattre les animaux", Type.SPECIAL, ActionnableKey.ABATOIRE, 30),
    // ------------------------------------------------------------------- //


    // ---------------------- ETABLE ---------------------- //
    SEND_TO_MILKING_PARLOUR(1, "Envoyer en salle de traite", Type.SENT_SPECIAL_ACTION, ActionnableKey.ETABLE, 2),
    // ------------------------------------------------------------------- //
    
   
    // ---------------------- POULAILLER ---------------------- //
    COLLECT_EGG(2, "Récolter les oeuf", Type.GET_PRODUCTION, ActionnableKey.POULAILLER, 5),
    // ------------------------------------------------------------------- //
    
    
    // ---------------------- SALLE DE TRAITE ---------------------- //
    COLLECT_MILK(3, "Récupérer le lait", Type.GET_PRODUCTION, ActionnableKey.SALLE_TRAITE, 5), 
    MILK(3, "Traire", Type.SPECIAL, ActionnableKey.SALLE_TRAITE, 10),
    // ------------------------------------------------------------------- //
    
    
    // ---------------------- MAISON ---------------------- //
    TO_REST(1, "Repos", Type.CARE, ActionnableKey.MAISON, 30),
    // ------------------------------------------------------------------- //


    // ---------------------- ENCLOS ---------------------- //
    FIX_ENCLOSURE(2, "Réparer", Type.FIX, ActionnableKey.ENCLOS, 1),
    SEND_BACK_HOME_ANIMALS(3, "Rentrer les animaux", Type.GO_BACK_HOME, ActionnableKey.ENCLOS, 10),
    FEED_ANIMAL_FROM_ENCLOSURE(3, "Nourrir les animaux", Type.FEED, ActionnableKey.ENCLOS, 5),
    COLLECT_EGG_FROM_ENCLOSURE(1, "Récolter les oeufs", Type.GET_PRODUCTION, ActionnableKey.ENCLOS, 2),
    // ------------------------------------------------------------------- //
    
    
    // ---------------------- ENTREPOT ---------------------- //
    // ------------------------------------------------------------------- //
    
    
    // ---------------------- REFUGE ---------------------- //
    SEND_TO_ENCLOSURE(1, "Envoyer à l'enclos", Type.SEND_TO_ENCLOSURE, ActionnableKey.REFUGE, 5),
    SEND_TO_SEND_TO_SLAUGHTERHOUSE(1, "Envoyer à l'abatoire", Type.SEND_TO_SLAUGHTERHOUSE, ActionnableKey.REFUGE, 5),  
    // ------------------------------------------------------------------- //
    
    
    // ---------------------- STRUCTURE ---------------------- //
    FIX_STRUCTURE(6, "Réparer", Type.FIX, ActionnableKey.STRUCTURE, 2),
    // ------------------------------------------------------------------- //
    
    
    // ---------------------- TERRAIN ---------------------- //
    DIG_OVER(2, "Labourer",Type.SPECIAL, ActionnableKey.TERRAIN, 45),
    PLANT(3, "Planter", Type.SPECIAL, ActionnableKey.TERRAIN, 30),
    TO_WATER(2, "Arroser", Type.FEED, ActionnableKey.TERRAIN, 5),
    FERTILIZE_GROUND(2, "Mettre de l'engrais", Type.CARE, ActionnableKey.TERRAIN, 20),
    HARVEST(4, "Récolter", Type.GET_PRODUCTION, ActionnableKey.TERRAIN, 20),
    REMOVE_ROTTEN_PLANT(3, "Retirer plantes pourries", Type.FIX, ActionnableKey.TERRAIN, 10),
    // ------------------------------------------------------------------- //
    


    // COLLECT_WOOL(2, "Récupérer la laine", Type.GET_PRODUCTION, ActionnableKey.SALLE_DE_TONTE, 3),
    // SEND_TO_SHEARING_ROOM(4, "Envoyer à la salle de tonte", Type.SENT_SPECIAL_ACTION, ActionnableKey.SHEEP_HOUSE, 4),
    
    ;

    public enum Type{
        SEND_TO_ENCLOSURE, 
        SEND_TO_SLAUGHTERHOUSE,
        GO_BACK_HOME,
        SENT_SPECIAL_ACTION,    
        GET_PRODUCTION,
        FEED,
        CARE,
        FIX,
        SPECIAL,
    }


    //TODO on conditionne l'action du joueur en fonction des outils qu'il possède !
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

    public int getNumberOfHourIfPlanned() {
        return numberOfHourIfPlanned;
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

