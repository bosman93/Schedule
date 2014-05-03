package Main;

import java.util.ArrayList;

public class Main {

    public static int POP_NUMBER         = 25;
    public static int EMPLOYEES          = 30;
    public static int DAYS               = 28;
    public static int ITERATION_BLOCKADE = 100000;

    public static void main(String[] args) {
        ArrayList<Schedule> population = ScheduleFactory.createScheduleTable(EMPLOYEES,DAYS,POP_NUMBER); // :D ta fabryka jest napisana dla jaj w sumie :D nie chcialem tu dodatkowego kodu wiec to dopisalem
        ArrayList<Schedule> temporary;

        int iteration_number = 0;
               
        do{
            Genetic.rank(population);
            Genetic.scaleRank(population); 
            
            if(iteration_number >= ITERATION_BLOCKADE)
                break;
            
            temporary = Genetic.preselection(population);
                                            
            Genetic.uniformCrossover(temporary);
            Genetic.mutation(temporary);
            
            population = Genetic.postselection(temporary, population);
            
            /***DO*WYDRUKU*WYNIKU******************/
            Schedule local_best = population.get(0);
            for(Schedule s : population)
                if(local_best.rate < s.rate)
                    local_best = s;
            System.out.print("" + iteration_number + ": " + local_best.rate + "\n");
            /**************************************/
            
            iteration_number++;
        }while(true);
        
        Schedule local_best = population.get(0);
        for(Schedule s : population)
            if(local_best.rate < s.rate)
               local_best = s;
        
        System.out.print(local_best.toString() + local_best.rate + "\n");
    }            
    
}
