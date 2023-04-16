package process.time;

import data.myExceptions.AskingToWorkAtIllegalHourException;
import data.myExceptions.HaveABreakWithException;
import data.myExceptions.IsOverWorkedException;
import data.myExceptions.UnAvalableTimeSlot;
import data.planning.Activity;
import data.planning.DailyPlanner;
import data.planning.TimeSlot;
import data.planning.WeeklyPlanner;
import data.planning.WeeklyPlanner.DayOfWeek;

public class PlanningManager {
    private static PlanningManager planningManager;

    private PlanningManager() {
    }

    public static PlanningManager getInstance() {
        if (planningManager == null) {
            planningManager = new PlanningManager();
        }
        return planningManager;
    }

    public void addActivityInPlanner(WeeklyPlanner weeklyPlanner, DayOfWeek day, int hourToStar, Activity activity)
            throws UnAvalableTimeSlot, HaveABreakWithException, AskingToWorkAtIllegalHourException {
        DailyPlanner plannerToComplete = weeklyPlanner.getWeek().get(day);
        addActivityToDay(plannerToComplete, hourToStar, activity);
    }

    /**
     * Add a task to a planner on a given day.
     * 
     * @param day  DayOfWeek
     * @param task Task
     * @throws IsOverWorkedException
     */
    public void addActivityInPlanner(WeeklyPlanner weeklyPlanner, DayOfWeek day, Activity activityToPerform)
            throws UnAvalableTimeSlot, HaveABreakWithException, AskingToWorkAtIllegalHourException,
            IsOverWorkedException {
        DailyPlanner plannerToComplete = weeklyPlanner.getWeek().get(day);
        addActivityToDay(plannerToComplete, activityToPerform);
    }

    /**
     * Add a task to a planner, starting at the first hour of work.
     * 
     * @param planner DailyPlanner
     * @param task    the task to be added to the planner
     */
    private void addActivityToDay(DailyPlanner planner, Activity activityToPerform) throws UnAvalableTimeSlot,
            HaveABreakWithException, AskingToWorkAtIllegalHourException, IsOverWorkedException {
        if (planner.haveEnoughFreeTimeToPerformTask(activityToPerform)) {
            TimeSlot freeTimeSlot = getFreeTimeSlot(planner, activityToPerform);
            reserveTimeSlotForTask(planner, freeTimeSlot);
        } else {
            throw new IsOverWorkedException();
        }
    }

    /**
     * If it's possible to add the task to the planner, then reserve the time slot
     * for the task.
     * 
     * @param hourToStar the hour at which the task will start
     * @param task       Task
     */
    private void addActivityToDay(DailyPlanner planner, int hourToStar, Activity activityToPerform)
            throws UnAvalableTimeSlot, HaveABreakWithException, AskingToWorkAtIllegalHourException {
        if (activityToPerform != Activity.TO_REST) {
            if (isPossibleToAddTheTaskToThePlanner(planner, hourToStar, activityToPerform)) {
                TimeSlot timeSlot = new TimeSlot(hourToStar, activityToPerform);
                if (isCurrentTimeSlotPossibleToReserve(planner, timeSlot)) {
                    reserveTimeSlotForTask(planner, timeSlot);
                } else {
                    throw new UnAvalableTimeSlot(activityToPerform);
                }
            } else {
                throw new UnAvalableTimeSlot(activityToPerform);
            }
        }
    }

    /**
     * This function checks if the activity is already scheduled in the previous
     * hour or the next
     * hour
     * 
     * @param planner    DailyPlanner
     * @param hourToStar the hour at which the activity will start
     * @param task       the task to be added to the planner
     */
    private boolean isAskingToPerformTheSameActivityTwiceInARow(DailyPlanner planner, int hourToStar, Activity activity)
            throws AskingToWorkAtIllegalHourException {
        int endHourOfTheActivity = hourToStar + activity.getDuration();
        return isPreviousHourIsAssociateToTheSameActivity(planner, hourToStar, activity) ||
                isNextHourIsAssociateToTheSameActivity(planner, endHourOfTheActivity, activity);
    }

    /**
     * This function checks if the previous hour is associated to the same activity
     * 
     * @param planner  the daily planner
     * @param hour     the hour of the day (0-23)
     * @param activity the activity to be added to the planner
     */
    private boolean isPreviousHourIsAssociateToTheSameActivity(DailyPlanner planner, int hour, Activity activity)
            throws AskingToWorkAtIllegalHourException {
        if (hour > DailyPlanner.FIRST_HOUR_OF_WORK) {
            return planner.getMatchingHourInPlanner(hour - 1).getActivity() == activity;
        }
        return false;
    }

    /**
     * This function checks if the next hour is associated to the same activity
     * 
     * @param planner  DailyPlanner object
     * @param hour     the hour of the day (0-23)
     * @param activity the activity that is being added to the planner
     * @return A boolean value.
     */
    private boolean isNextHourIsAssociateToTheSameActivity(DailyPlanner planner, int hour, Activity activity)
            throws AskingToWorkAtIllegalHourException {
        if (hour < DailyPlanner.LAST_HOUR_OF_WORK) {
            return planner.getMatchingHourInPlanner(hour + 1).getActivity() == activity;
        }
        return false;
    }

    /**
     * This function takes a daily planner and an hour and returns the time slot
     * associated to that hour
     * 
     * @param planner DailyPlanner
     * @param hour    the hour to be freed
     */
    public void suppressTimeSlotInPlanner(WeeklyPlanner weeklyPlanner, DayOfWeek day, int hour)
            throws AskingToWorkAtIllegalHourException, UnAvalableTimeSlot, HaveABreakWithException {
        DailyPlanner dailyPlanner = weeklyPlanner.getWeek().get(day);
        suppressTimeSlotInDay(dailyPlanner, hour);
    }

    /**
     * It takes a daily planner and an hour and frees the time slot that contains
     * the hour
     * 
     * @param planner DailyPlanner
     * @param hour    the hour to be freed
     */
    private void suppressTimeSlotInDay(DailyPlanner planner, int hour)
            throws AskingToWorkAtIllegalHourException, UnAvalableTimeSlot, HaveABreakWithException {
        TimeSlot timeSlotToFree = getAssociateTimeSlot(planner, hour);
        if (timeSlotToFree.getActivity() != Activity.TO_REST) {
            int currentHourToFree = timeSlotToFree.getStartHour(), lastHourOfToBeFreeSlot = timeSlotToFree.getEndHour();
            while (currentHourToFree < lastHourOfToBeFreeSlot) {
                planner.getMatchingHourInPlanner(currentHourToFree).setFree();
                currentHourToFree++;
            }
            int newFreeHour = planner.getFreeHours() + timeSlotToFree.getDuration();
            planner.setFreeHours(newFreeHour);
        }
    }

    /**
     * It takes a daily planner and a time slot, and reserves the time slot in the
     * planner
     * 
     * @param planner  DailyPlanner
     * @param timeSlot
     */
    private void reserveTimeSlotForTask(DailyPlanner planner, TimeSlot timeSlot)
            throws UnAvalableTimeSlot, HaveABreakWithException, AskingToWorkAtIllegalHourException {
        int freeHourToReserve = timeSlot.getStartHour();
        Activity activityToPerform = timeSlot.getActivity();
        while (!timeSlot.isSchedule()) {
            planner.getMatchingHourInPlanner(freeHourToReserve).setActivity(activityToPerform);
            timeSlot = updateTimeSlotStatus(planner, freeHourToReserve, timeSlot);
            freeHourToReserve++;
        }
    }

    /**
     * If the free hour in the planner is the last hour of the time slot, then set
     * the time slot to
     * scheduled and update the number of free hours in the planner
     * 
     * @param planner           DailyPlanner object
     * @param freeHourInPlanner the free hour in the planner
     * @param timeSlot          the time slot that we want to schedule
     * @return The timeSlot object is being returned.
     */
    private TimeSlot updateTimeSlotStatus(DailyPlanner planner, int freeHourInPlanner, TimeSlot timeSlot) {
        if (freeHourInPlanner == timeSlot.getEndHour() - 1) {
            int newFreeHours = planner.getFreeHours() - timeSlot.getDuration();
            timeSlot.setIsSchedule(true);
            planner.setFreeHours(newFreeHours);
        }
        return timeSlot;
    }

    /**
     * It returns a TimeSlot object that represents the time slot of the activity
     * that is associated with
     * the selected hour
     * 
     * @param planner      DailyPlanner
     * @param selectedHour the hour the user wants to work at
     * @return A TimeSlot object.
     */
    public TimeSlot getAssociateTimeSlot(DailyPlanner planner, int selectedHour)
            throws AskingToWorkAtIllegalHourException {
        Activity activityPerformDuringHour = planner.getMatchingHourInPlanner(selectedHour).getActivity();
        int startTimeOfAssociateActivity = (activityPerformDuringHour == Activity.TO_REST) ? selectedHour
                : getStartTimeOfActivity(planner, selectedHour, activityPerformDuringHour);
        return new TimeSlot(startTimeOfAssociateActivity, activityPerformDuringHour, true);
    }

    // public TimeSlot getAssociateTimeSlot(DailyPlanner planner, int selectedHour){
    // Activity activityPerformDuringHour =
    // planner.getMatchingHourInPlanner(selectedHour).getActivity();
    // int startTimeOfAssociateActivity = (activityPerformDuringHour ==
    // Activity.TO_REST) ? selectedHour
    // : getStartTimeOfActivity(planner, selectedHour, activityPerformDuringHour);
    // return new TimeSlot(startTimeOfAssociateActivity, activityPerformDuringHour,
    // true);

    // }

    /**
     * This function returns the start time of an activity, given the selected hour
     * and the activity
     * itself.
     * 
     * @param planner      DailyPlanner object
     * @param selectedHour the hour the user selected to start the activity
     * @param activity     the activity that the user wants to add to the planner
     * @return The start time of the activity.
     */
    private int getStartTimeOfActivity(DailyPlanner planner, int selectedHour, Activity activity)
            throws AskingToWorkAtIllegalHourException {
        int supposedStartTime = selectedHour;
        while (isPreviousHourIsAssociateToTheSameActivity(planner, supposedStartTime, activity)) {
            supposedStartTime--;
        }
        return supposedStartTime;
    }

    /**
     * While the time slot is not available, shift it by one hour and check again.
     * 
     * @param planner  the daily planner
     * @param timeSlot a TimeSlot object that represents the time slot that the user
     *                 wants to reserve.
     * @return The method returns the start hour of the time slot.
     */
    private TimeSlot getFreeTimeSlot(DailyPlanner planner, TimeSlot timeSlot)
            throws UnAvalableTimeSlot, AskingToWorkAtIllegalHourException {
        while (!isCurrentTimeSlotPossibleToReserve(planner, timeSlot)) {
            timeSlot.shiftByHour(timeSlot.getDuration() + 1);
        }
        if (timeSlot.getEndHour() > DailyPlanner.LAST_HOUR_OF_WORK) {
            throw new UnAvalableTimeSlot(timeSlot);
        }
        return timeSlot;
    }

    private TimeSlot getFreeTimeSlot(DailyPlanner planner, Activity activity)
            throws UnAvalableTimeSlot, AskingToWorkAtIllegalHourException {
        return getFreeTimeSlot(planner, new TimeSlot(DailyPlanner.FIRST_HOUR_OF_WORK, activity));

    }

    /**
     * If the start the time slot is free (not already reserve of another activity),
     * and the previous and next
     * hours are not associated with the same activity, and the end hour of the
     * asked to be reserve time slot
     * is less than or equal to the last hour of work, then the time slot is
     * possible to reserve
     * 
     * @param planner  DailyPlanner
     * @param timeSlot the time slot to be reserved
     */
    private boolean isCurrentTimeSlotPossibleToReserve(DailyPlanner planner, TimeSlot timeSlot)
            throws AskingToWorkAtIllegalHourException {
        if (planner.isHourAvalable(timeSlot.getStartHour()) && planner.isHourAvalable(timeSlot.getEndHour() - 1)) {
            return isHoleTimeSlotFree(planner, timeSlot)
                    && !isPreviousHourIsAssociateToTheSameActivity(planner, timeSlot.getStartHour(),
                            timeSlot.getActivity())
                    && !isNextHourIsAssociateToTheSameActivity(planner, timeSlot.getEndHour(), timeSlot.getActivity())
                    && timeSlot.getEndHour() <= DailyPlanner.LAST_HOUR_OF_WORK;
        }
        return false;
    }

    /**
     * If the activity is to rest, or if the activity is not the same as the
     * previous activity, and if
     * there is enough time to perform the task, and if the hours are legal, the
     * task is addable to the daily planner.
     * 
     * @param planner     DailyPlanner
     * @param hourToStart the hour at which the task is to be started
     * @param task        the task to be added to the planner
     */
    private boolean isPossibleToAddTheTaskToThePlanner(DailyPlanner planner, int hourToStart, Activity activity)
            throws AskingToWorkAtIllegalHourException {
        return !isAskingToPerformTheSameActivityTwiceInARow(planner, hourToStart, activity) &&
                planner.haveEnoughFreeTimeToPerformTask(activity) && planner.areLegalHourToWork(hourToStart, activity)
                && planner.isHourAvalable(hourToStart) && planner.isHourAvalable(hourToStart + activity.getDuration());
    }

    /**
     * Check if each hour of the time slot is free, (except for the start and end
     * hours that have already been checked).
     * 
     * @param planner  DailyPlanner
     * @param timeSlot the time slot that we want to check if it's free
     */
    private boolean isHoleTimeSlotFree(DailyPlanner planner, TimeSlot timeSlot)
            throws AskingToWorkAtIllegalHourException {
        int currentHourToCheck = timeSlot.getStartHour();
        while (currentHourToCheck < timeSlot.getEndHour() - 1 && planner.isHourAvalable(currentHourToCheck)) {
            currentHourToCheck++;
        }
        return currentHourToCheck == timeSlot.getEndHour() - 1;
    }

}
