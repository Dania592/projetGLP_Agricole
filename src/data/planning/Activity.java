package data.planning;

public enum Activity {
    TO_MILK(2, "Traite"),
    TO_SHAVE(2,"Tonte"),
    TO_HARVEST(4, "Récolte"),
    TO_REST(1, "Repos");
    private int numberOfHourNeeded; 
    private String label;
    private Activity(int numberOfHourNeeded, String label){
        this.numberOfHourNeeded = numberOfHourNeeded;
        this.label = label;
    }
    public int getDuration() {
        return numberOfHourNeeded;
    }   
    public String getLabel(){
        return label;
    }
}
