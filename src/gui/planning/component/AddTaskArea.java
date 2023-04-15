package gui.planning.component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data.planning.Activity;
import data.myExceptions.AskingToWorkAtIllegalHourException;
import data.planning.DailyPlanner;
import data.myExceptions.HaveABreakWithException;
import data.myExceptions.IsOverWorkedException;
import data.myExceptions.UnAvalableTimeSlot;
import data.planning.WeeklyPlanner;
import data.planning.WeeklyPlanner.DayOfWeek;
import process.time.PlanningManager;

public class AddTaskArea extends JPanel {
    private TimeTable timeTable;
    private JButton addTaskButton = new JButton("AddTask");
    private JLabel taskLabel = new JLabel("Tâche : ");
    private JLabel dayLabel = new JLabel("Jour : ");
    private JLabel startHourLabel = new JLabel("Heure de commencement : ");
    private JComboBox<Activity> activityChoser;
    private JComboBox<DayOfWeek> dayChoser = new JComboBox<>(WeeklyPlanner.days);
    private JComboBox<String> startHourChoser;
    private static String DEFAULT_HOUR_SELECTED = "----";

    public AddTaskArea(TimeTable timeTable) {
        this.timeTable = timeTable;
        initPanel();
    }

    private void initPanel() {
        initHourChoser();
        initActivityChose();
        add(taskLabel);
        add(dayLabel);
        add(startHourLabel);
        add(activityChoser);
        add(dayChoser);
        add(startHourChoser);
        setLayout(new GridLayout(3, 3));
        addTaskButton.addActionListener(new AddTaskAction());
        add(addTaskButton);
    }

    private void initActivityChose() {
        activityChoser = new JComboBox<>();
        Activity[] activities = Activity.values();
        for (Activity activity : activities) {
            if (activity != Activity.TO_REST) {
                activityChoser.addItem(activity);
            }
        }
    }

    private void initHourChoser() {
        int numberHourToRepresent = DailyPlanner.getNumberOfPossibleHour();
        startHourChoser = new JComboBox<>();
        startHourChoser.addItem(DEFAULT_HOUR_SELECTED);
        for (int indexHours = 0,
                hour = DailyPlanner.FIRST_HOUR_OF_WORK; indexHours < numberHourToRepresent; indexHours++) {
            startHourChoser.addItem(hour + "H - " + (hour + 1) + "H");
            hour++;
        }

    }

    private void addTask(int selectedHourIndex, DayOfWeek selectedDay, Activity selectedActivity)
            throws UnAvalableTimeSlot, HaveABreakWithException, AskingToWorkAtIllegalHourException,
            IsOverWorkedException {
        if (selectedHourIndex == 0) {
            PlanningManager.getInstance().addActivityInPlanner(timeTable.getPerson().getPlanning(), selectedDay,
                    selectedActivity);
        } else {
            int chosenHour = DailyPlanner.FIRST_HOUR_OF_WORK + selectedHourIndex - 1;
            PlanningManager.getInstance().addActivityInPlanner(timeTable.getPerson().getPlanning(), selectedDay, chosenHour,
                    selectedActivity);
        }
    }

    private class AddTaskAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            int selectedString = startHourChoser.getSelectedIndex();
            DayOfWeek selecteDay = (DayOfWeek) dayChoser.getSelectedItem();
            Activity selectedActivity = (Activity) activityChoser.getSelectedItem();
            try {
                addTask(selectedString, selecteDay, selectedActivity);
                timeTable.refreshTable();
            } catch (Exception exception) {
                JOptionPane.showInternalMessageDialog(null, exception.getMessage(), "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
