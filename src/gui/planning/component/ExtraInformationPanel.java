package gui.planning.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.planning.WeeklyPlanner.DayOfWeek;
import process.time.PlanningManager;

public class ExtraInformationPanel extends JPanel {
    
    private TimeTable timeTable;
    private JLabel planningTitle;
    private JPanel informationPanel = new JPanel(new GridLayout(3,1));
    private JLabel selectedDay;
    private JLabel selectedPeriode;
    private JLabel selectedActivity;
    private JLabel possibleWorkingHoursLeft;
    private JButton removeButton = new JButton("Remove Task");

    private static String DEFAULT_PLANNING_TITLE = "Planning";
    private static String DEFAULT_SELECTED_DAY = "DAY"; 
    private static String DEFAULT_SELECTED_PERIODE = "PERIODE"; 
    private static String DEFAULT_SELECTED_ACTIVITY = "ACTIVITY"; 
    private static String DEFAULT_POSSIBLE_WORKING_HOURS_LEFT = "POSSIBLE WORKING HOUR LEFT"; 
//TODO get person name so pass person as a parameter of the constructor
    public ExtraInformationPanel(TimeTable timeTable){
        super();
        this.timeTable = timeTable;
        planningTitle = new JLabel(timeTable.getPerson().getName()+"'s"+DEFAULT_PLANNING_TITLE);
        selectedDay = new JLabel(DEFAULT_SELECTED_DAY);
        selectedPeriode = new JLabel(DEFAULT_SELECTED_PERIODE);
        selectedActivity = new JLabel(DEFAULT_SELECTED_ACTIVITY);
        possibleWorkingHoursLeft = new JLabel(DEFAULT_POSSIBLE_WORKING_HOURS_LEFT);
        removeButton.addActionListener(new DeleteAction());
        addElementToExtraInformationPanel();
    }

    public void addElementToExtraInformationPanel(){
        setLayout(new BorderLayout());
        setBackground(Color.BLUE);
        setSize(200,500);
        setCursor(getCursor());
        add(BorderLayout.NORTH, planningTitle);
        informationPanel.setLayout(new GridLayout(7,1));
        informationPanel.add(selectedActivity);
        informationPanel.add(selectedPeriode);
        informationPanel.add(selectedDay);
        informationPanel.add(possibleWorkingHoursLeft);
        informationPanel.setBackground(Color.GREEN);
        add(informationPanel);
        informationPanel.add(removeButton);
    }


    public void setSelectedDay(String selectedDay) {
        this.selectedDay.setText(DEFAULT_SELECTED_DAY+" : "+ selectedDay);
    }

    public void setSelectedPeriode(String selectedPeriode) {
        this.selectedPeriode.setText(DEFAULT_SELECTED_PERIODE +" : " + selectedPeriode);

    }

    public void setSelectedActivity(String selectedActivity) {
        this.selectedActivity.setText(DEFAULT_SELECTED_ACTIVITY +" : "+selectedActivity);
    }

    public void setPossibleWorkingHoursLeft(String possibleWorkingHoursLeft) {
        this.possibleWorkingHoursLeft.setText(DEFAULT_POSSIBLE_WORKING_HOURS_LEFT +" : "+possibleWorkingHoursLeft);
    }


    
    public void setSelectedDay() {
        this.selectedDay.setText(DEFAULT_SELECTED_DAY);
    }

    public void setSelectedPeriode() {
        this.selectedPeriode.setText(DEFAULT_SELECTED_PERIODE);

    }

    public void setSelectedActivity() {
        this.selectedActivity.setText(DEFAULT_SELECTED_ACTIVITY);
    }

    public void setPossibleWorkingHoursLeft() {
        this.possibleWorkingHoursLeft.setText(DEFAULT_POSSIBLE_WORKING_HOURS_LEFT);
    }

    private class DeleteAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DayOfWeek selectedDay = timeTable.getSelectedDay();
                int selectedHour = timeTable.getSelectedHour();
                PlanningManager.getInstance().suppressTimeSlotInPlanner(timeTable.getPerson().getPlanning(), selectedDay, selectedHour);
                timeTable.refreshTable();
            } catch (Exception e1) {
                e1.printStackTrace();
            } 
            
        }




    }

    


}
