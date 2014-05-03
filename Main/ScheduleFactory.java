package Main;

import java.util.ArrayList;

final public class ScheduleFactory {
    private ScheduleFactory(){}
    
    public static ArrayList<Schedule> createScheduleTable(int employees, int days, int length){
        ArrayList<Schedule> schedules = new ArrayList<>();
        for(int i = 0; i < length; ++i)
            schedules.add(new Schedule(employees,days));
        
        return schedules;
    }
    
    public static ArrayList<Schedule> cloneScheduleTable(ArrayList<Schedule> s){
        ArrayList<Schedule> schedules = new ArrayList<>();
        
        for(int i = 0; i < s.size(); ++i)
            schedules.add(new Schedule(s.get(i)));
        
        return schedules;
    }
}
