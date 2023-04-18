package gui.planning.component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import data.planning.Activity;
import data.acteur.Personne;
import data.myExceptions.AskingToWorkAtIllegalHourException;
import data.planning.DailyPlanner;
import data.planning.Hour;
import data.planning.TimeSlot;
import data.planning.WeeklyPlanner;
import data.planning.WeeklyPlanner.DayOfWeek;
import process.time.PlanningManager;



public class TimeTable extends JTable{
    private Personne person;
    private static int numberOfHourToRepresent = DailyPlanner.getNumberOfPossibleHour();
    private static Object[] tableHeader;
    private String DEFAULT_REPRESENTATION_FOR_FREE_HOUR = ""; 

    static {
        tableHeader = new Object[8];
        tableHeader[0] = "Heure/Jour";
        for(int indexHeader=1; indexHeader<8; indexHeader++){
            tableHeader[indexHeader] = WeeklyPlanner.days[indexHeader-1];
        }
    }

    public TimeTable(Personne person){
        super(new DefaultTableModel(tableHeader, numberOfHourToRepresent)); 
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        getTableHeader().setReorderingAllowed(false);
        this.person = person;
        fillUpTable();
    }

    public void refreshTable(){
        fillUpTable();
    }

    private void fillUpTable(){
        DailyPlanner dailyPlanner; 
        int lineToFillUp; 
        Activity selectedActivity;
        String valueToAdd;
        for(int day = 0; day<=WeeklyPlanner.days.length; day++){
            lineToFillUp = 0;
            if(day >0){
                dailyPlanner = person.getPlanning().getWeek().get(WeeklyPlanner.days[day-1]);
                for(Hour workingHour : dailyPlanner.getPlanner()){
                    selectedActivity = workingHour.getActivity();
                    if(selectedActivity == Activity.TO_REST){
                        valueToAdd = DEFAULT_REPRESENTATION_FOR_FREE_HOUR; 
                    }else{
                        valueToAdd = selectedActivity.toString();
                    }
                    setValueAt(valueToAdd, lineToFillUp, day);
                    lineToFillUp++;
                }
            }else{
                for(int index=0; index <DailyPlanner.getNumberOfPossibleHour(); index++){
                    setValueAt((DailyPlanner.FIRST_HOUR_OF_WORK+lineToFillUp)+"H - "+ (DailyPlanner.FIRST_HOUR_OF_WORK+lineToFillUp+1)+"H", lineToFillUp, day);
                    lineToFillUp++;
                }
            }
        }    
    }

    public DayOfWeek getSelectedDay() throws Exception{
        int selectedColumnIndex = getSelectedColumn();
        if(selectedColumnIndex <=0){
            throw new Exception("No day selected");
        }
        return WeeklyPlanner.days[selectedColumnIndex-1];
    }

    public int getSelectedHour() throws Exception{
        int selectedRowIndex = getSelectedRow();
        if(selectedRowIndex<=-1){
            new AskingToWorkAtIllegalHourException(selectedRowIndex);
        } 
        return DailyPlanner.FIRST_HOUR_OF_WORK+selectedRowIndex; 

    }

    public TimeSlot getSelectedTimeSlot() throws Exception{
        int selectedHour = getSelectedHour();
        DayOfWeek selectedDay = getSelectedDay();
        return PlanningManager.getInstance().getAssociateTimeSlot(person.getPlanning().getWeek().get(selectedDay), selectedHour);

    }

    public Personne getPerson(){
        return person;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }



}
