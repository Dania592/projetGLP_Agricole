package data.planning;

import data.myExceptions.AskingToWorkAtIllegalHourException;

public class DailyPlanner {
    public static int FIRST_HOUR_OF_WORK = 6;
    public static int LAST_HOUR_OF_WORK = 22;

    private Hour[] planner;
    private int freeHours;

    
    public int getFreeHours() {
        return freeHours;
    }

    public void setFreeHours(int freeHours) {
        this.freeHours = freeHours;
    }

    public DailyPlanner(int maxHourOfWork) {
        setFreeHours(maxHourOfWork);
        planner = new Hour[getNumberOfPossibleHour()];
        int indexPlanner = 0;
        for(int hour=FIRST_HOUR_OF_WORK; hour <=(LAST_HOUR_OF_WORK); hour++){
            planner[indexPlanner]= new Hour(hour);
            indexPlanner++;
        }

    }

    public boolean haveEnoughFreeTimeToPerformTask(Activity taskToAdd) {
        return freeHours >= taskToAdd.getDuration();
    }

    public int getMatchingHourIndexInPlanner(int hour){
        return hour - FIRST_HOUR_OF_WORK;
    }

    public static int getNumberOfPossibleHour() {
        return (LAST_HOUR_OF_WORK - FIRST_HOUR_OF_WORK)+1;
    }

    public Hour[] getPlanner() {
        return planner;
    }

    public void setSpecificHourFree(int hourToFree){
        planner[getMatchingHourIndexInPlanner(hourToFree)].setFree();
    }

    public Hour getMatchingHourInPlanner(int hour) throws AskingToWorkAtIllegalHourException {
        Hour hourToReturn;
        int indexHour;
        if (hour >= FIRST_HOUR_OF_WORK && hour <= LAST_HOUR_OF_WORK) {
            indexHour = getMatchingHourIndexInPlanner(hour);
            hourToReturn = planner[indexHour];
            return hourToReturn;
        }
        throw new AskingToWorkAtIllegalHourException(hour);
    }

    public boolean areLegalHourToWork(int hourToStart, Activity task) {
        return hourToStart>= FIRST_HOUR_OF_WORK && hourToStart <=LAST_HOUR_OF_WORK;
    }

    public boolean isHourAvalable(int hour) throws AskingToWorkAtIllegalHourException {
        return getMatchingHourInPlanner(hour).isFreeOfWork();
    }


    @Override
    public String toString() {
        StringBuilder plannerText = new StringBuilder("/////\tDailyPlanner\t/////");
        for (int timeSlotIndexInPlanner = 0; timeSlotIndexInPlanner < planner.length; timeSlotIndexInPlanner++) {
            plannerText.append("\n" + planner[timeSlotIndexInPlanner]);
        }
        plannerText.append("\nHours free of work left : " + freeHours);
        return plannerText.toString();
    }


}
