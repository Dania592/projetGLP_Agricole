package gui.planning;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.ColorUIResource;

import java.awt.BorderLayout;

import data.acteur.Personne;
import data.planning.TimeSlot;
import data.planning.WeeklyPlanner.DayOfWeek;
import gui.planning.component.AddTaskArea;
import gui.planning.component.ExtraInformationPanel;
import gui.planning.component.TimeTable;

public class Planning extends JFrame implements Runnable {
    private static final int FRAME_HEIGTH = 800;
    private static final int FRAME_WIDTH = 1000;

    // TimeTablePanel timeTablePanel;
    private TimeTable timeTable;
    private JPanel addTaskPanel;
    private JPanel timeTablePanel = new JPanel(new BorderLayout());
    private ExtraInformationPanel extraInformationPanel;

    // TODO Trop moche : adapter à celui d'Alicia
    //TODO Changer les messages des labels
    public Planning(Personne person) {
        super(person.getName()+"'s Planning");
        timeTable = new TimeTable(person);
        addTaskPanel = new AddTaskArea(timeTable);
        extraInformationPanel = new ExtraInformationPanel(timeTable);
        initFrame();
    }

    public void initFrame() {
        setLayout(new BorderLayout());
        JScrollPane scrollpane = new JScrollPane(timeTable);
        timeTablePanel.add(scrollpane, BorderLayout.CENTER);
        add(BorderLayout.CENTER, timeTablePanel);
        add(BorderLayout.EAST, extraInformationPanel);
        add(BorderLayout.SOUTH, addTaskPanel);
        setSize(FRAME_WIDTH, FRAME_HEIGTH);
        setBackground(new ColorUIResource(255, 231, 171));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void run() {
        TimeSlot selectedTimeSlot;
        DayOfWeek selectedDay;
        while (true) {
            if (timeTable.getSelectedColumn() > 0) {
                try {
                    selectedDay = timeTable.getSelectedDay();
                    selectedTimeSlot = timeTable.getSelectedTimeSlot();
                    extraInformationPanel.setSelectedActivity(selectedTimeSlot.getActivity().getLabel());
                    extraInformationPanel.setSelectedDay(selectedDay.toString());
                    extraInformationPanel.setPossibleWorkingHoursLeft(
                            "" + timeTable.getPerson().getPlanning().getWeek().get(selectedDay).getFreeHours());
                    extraInformationPanel.setSelectedPeriode(
                            selectedTimeSlot.getStartHour() + "H - " + selectedTimeSlot.getEndHour() + "H");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.err.println(e.getStackTrace());
            }
        }

    }

}
