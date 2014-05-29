package Main;

import java.util.Random;


public class Schedule implements Comparable<Schedule> {
    boolean[][] table;
    int employees;
    int days;
    double rate = 0;
    
    public Schedule(int employees, int days){
        table = new boolean[employees][days];
        this.employees = employees;
        this.days      = days;
        
        Random rand = new Random();     // generowanie poczatkowego harmonogramu
        for(int i = 0; i < employees; ++i)
            for(int j = 0; j < days; ++j)
                table[i][j] = rand.nextBoolean();        
    }
    
    public Schedule(Schedule s){
        employees = s.employees;
        days = s.days;
        table = new boolean[employees][days];
        rate = s.rate;
        
        for(int i = 0; i < employees; ++i)
            for(int j = 0; j < days; ++j)
                table[i][j] = s.table[i][j];
    }
    
    /**
     * Zlicz wszystkie dni robocze danego pracownika
     * @param employeeID
     * @return 
     */
    public int countDays(int employeeID){
        if(employeeID >= employees || employeeID < 0) return -1;
        
        int counter = 0;
        for(int i = 0; i < days; ++i)
            if(table[employeeID][i]) counter++;
        
        return counter;
    }
    
    /**
     * Zlicz dni robocze konkretnym tygodniu danego pracownika
     * @param employeeID
     * @param week numer tygodnia (liczony od 1 - zeby lepiej wygladalo, mozna zmienic...)
     * @return 
     */
    public int countDaysWeek(int employeeID, int week){
        if(employeeID >= employees || employeeID < 0 || (week-1)*7 >=days || (week-1) < 0) 
            return -1; //zeby nie robic wyjatkow bo to klopotliwe...
        
        int counter = 0;
        for(int i = (week-1)*7; i < ((week*7 < days)?week*7:days); ++i)
            if(table[employeeID][i]) counter++;
        
        return counter;
    }
    
    /**
     * Zliczenie pracownikow w konkretnym dniu
     * @param dayID
     * @return 
     */
    public int countEmployees(int dayID){
        if(dayID >= days || dayID < 0) return -1; 
        
        int counter = 0;
        for(int i = 0; i < employees; ++i)
            if(table[i][dayID]) counter++;
        
        return counter;
    }
    
    @Override
    public String toString(){
        String str = "";
        if(table != null){
            int counter;
            for(int i = 0; i < table.length; ++i){
                counter = 0;
                for(int j = 0; j < table[0].length; ++j){
                    str += (table[i][j]  ? "X" : "-")  + " ";  // X - w pracy, - wolne
                    str += ((j+1)%7 == 0) ? " | " : " ";       // separator dla tygodni
                }
                str += "\t# Emp"+(i+1)+": " + countDays(i) + " times\n";
            }
            str += "Employees for each day:\n";
            for(int i = 0; i < table[0].length; ++i)
                str += ""+countEmployees(i)+(((i+1)%7==0)?" | ":" ");
        }
        return str+"\n";
    }
     /**
     * Potrzebne do sortowania
	 * @param o
	 * @return 
     */
    @Override
    public int compareTo(Schedule o) {
        if     (rate == o.rate) return  0;
        else if(rate < o.rate)  return  1;
        else                    return -1;
    }
}
