package Main;

import java.util.ArrayList;
import java.util.Random;

/**
 * Dla kazdej metody populacje musza miec identyczne wymiary. Brak obslugi bledow
 * dla tego typu problemow (w tym momencie...)
 * @author Mariusz
 */
public class Genetic {
    public static int MIN_EMP_PER_DAYS  = 8;  // minimalna liczba pracownikow obecnych danego dnia   
    public static int MIN_WEEK_DAYS     = 3;   // minimalna liczba dni w pracy
    public static int DAYS_SERIES       = 6;   // premiowana liczba dni w pracy pod rzad
    public static int WEEKEND_AT_WORK   = 3;   //co 3 tydzien w pracy
       
    public static int MUTATION_PROB      = 2;   //procent
    public static int CROSSOVER_PERCENT  = 35;  //procent elementow podlegajacych krzyzowaniu
    public static int MUT_UNABLE_PERCENT = 20;  //procent elementow nie podlegajacych mutacji
    
    /**
     * Wyznaczenie podstawowej wyceny kazdego osobnika populacji
     * @param population 
     */
    public static void rank(ArrayList<Schedule> population){
        for(Schedule s : population){
            s.rate = 0;
            //wystarczajaca liczba pracownikow jednego dnia
            for(int i = 0; i < s.days; ++i){
                int num = s.countEmployees(i);
                if     (num == MIN_EMP_PER_DAYS)     s.rate += 10;
                else if(num == MIN_EMP_PER_DAYS + 1) s.rate +=  8;
                else if(num == MIN_EMP_PER_DAYS + 2) s.rate +=  5;  
                else if(num <  MIN_EMP_PER_DAYS)     s.rate -= 10;
            }
            
            //3 dni tygodniowo )
            int days;
            for(int i = 0; i < s.employees; ++i){
                for(int j = 0; j < (s.days/7)+1; ++j){
                    days = s.countDaysWeek(i, j+1);
                    if     (days == MIN_WEEK_DAYS)   s.rate += 10;
                    else if(days == MIN_WEEK_DAYS-1) s.rate +=  8;
                    else if(days == MIN_WEEK_DAYS-2) s.rate +=  5; 
                    else if(days <  MIN_WEEK_DAYS)   s.rate -= 10;
                }
            }
            
            //obciecie punktow za serie kolejnych dni w pracy
            int series_length = 0;
            for(int i = 0; i < s.employees; ++i){
                for(int j = 0; j < s.days; ++j){
                    if(s.table[i][j] == true)
                        series_length++;
                    else
                        series_length = 0;                    
                }
                if     (series_length >= DAYS_SERIES)      s.rate -= 10;
                else if(series_length == DAYS_SERIES - 1)  s.rate -= 8;
                else if(series_length == DAYS_SERIES - 2)  s.rate -= 4;
            }
                    
            //work every 3rd weekend            
            //TODO
        }  
    }
        
 
    /**
     * --wylaczone
     * Skalowanie sigma funkcji celu (w celu zapobiegania przedwczesnej zbieznosci algorytmu)
     * @param population 
     */
    public static void scaleRank(ArrayList<Schedule> population){
        double rate_mean = 0;
        for(Schedule s : population)
            rate_mean += s.rate;
        rate_mean /= population.size(); // wyznaczenie sredniej
        
        double standard_deviation = 0;
        
        /*WYZNACZENIE*ODCHYLENIA*STANDARDOWEGO***********************************/
        for(Schedule s : population)
            standard_deviation += (Math.pow(s.rate - rate_mean,2));
        
        standard_deviation = Math.sqrt(standard_deviation/population.size());
        
        for(Schedule s : population){
            s.rate = s.rate + (rate_mean - standard_deviation);
            if(s.rate < 0) s.rate = 0;
        }   
    }
    
    
    /*===MUTATION===========================================================================*/
    
    /**
     * Ograniczenie mutacji - mutowane jest tylko (100-MUT_UNABLE_PERCENT)% osobników od końca (najgorszych)
     * @param population 
     */
    public static void mutation(ArrayList<Schedule> population){

        Random rand = new Random(); 
        
        int start = (MUT_UNABLE_PERCENT * Main.POP_NUMBER) / 100;
        Schedule current;
        
        for(int index = start; index < population.size(); ++index){
            current = population.get(index);
            
             for(int i = 0; i < current.employees; ++i)
                for(int j = 0; j < current.days; ++j)
                    if(rand.nextInt(100) < MUTATION_PROB){
                        current.table[i][j] = !current.table[i][j];
                    }
        }
    }    
    
    /*=== CROSSOVERS:  ==============================================================*/
    
    /**
     * Krzyzowanie jednostajne .
     * Krzyzuje ze soba pary nastepujacych po sobie (100-CROSSOVER_PERCENT)% osobnikow
     * @param population
     */
    public static void uniformCrossover(ArrayList<Schedule> population){
        int limit = (CROSSOVER_PERCENT * Main.POP_NUMBER)/100;
        
        Schedule temp1, temp2;
        for(int index = 0; index < population.size()-2; ++index){   
            if(index == limit)  //krzyzujemy pewna liczbe najlepszych osobnikow (limit)
                break;
            
            temp1 = new Schedule(population.get(index));
            temp2 = new Schedule(population.get(index+1));
            
            for(int i = 0; i < temp1.table.length; ++i)
                singleUniformCross(temp1.table[i], temp2.table[i]); 
            
            population.set(population.size() - index - 1, temp1);   // podmieniamy je za najgorsze osobniki
            population.set(population.size() - index - 2, temp2);       
        }
    }
    
    
    /**
     * Krzyzowanie jednostajne 2 tablic logicznych z prawdopodobienstwem krzyzowania 50%.
     * Czyli 50% szansy na zamiane kazdego indeksu tablic.
     * @param a
     * @param b 
     */
    private static void singleUniformCross(boolean[] a, boolean[] b){
        int cross_probability = 50; //procentowo
        Random rand = new Random();
        
        for(int i = 0; i < a.length; ++i)
            if(rand.nextInt(100) < cross_probability){
                boolean temp = a[i];
                a[i] = b[i];
                b[i] = temp;
            }
    } 
}
