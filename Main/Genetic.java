package Main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Dla kazdej metody populacje musza miec identyczne wymiary. Brak obslugi bledow
 * dla tego typu problemow (w tym momencie...)
 * @author Mariusz
 */
public class Genetic {
    public static int MIN_EMP_PER_DAYS  = 10;  // minimalna liczba pracownikow obecnych danego dnia   
    public static int MIN_WEEK_DAYS     = 3;   // minimalna liczba dni w pracy
    public static int DAYS_SERIES       = 6;   // premiowana liczba dni w pracy pod rzad
    public static int WEEKEND_AT_WORK   = 3;   //co 3 tydzien w pracy
    public static int MUTATION_PROB     = 2;   //percent
    public static int C_PARAM           = 1;
    
    /**
     * Wyznaczenie podstawowej wyceny kazdego osobnika populacji
     * @param population 
     */
    public static void rank(ArrayList<Schedule> population){
        for(Schedule s : population){
            s.rate = 0;
            //wystarczajaca liczba pracownikow jednego dnia (
            for(int i = 0; i < s.days; ++i){
                int num = s.countEmployees(i);
                if     (num == MIN_EMP_PER_DAYS)     s.rate += 15;
                else if(num == MIN_EMP_PER_DAYS + 1) s.rate +=  8;
                else if(num == MIN_EMP_PER_DAYS + 2) s.rate +=  3;  
                else if(num <  MIN_EMP_PER_DAYS)     s.rate -= 10;
            }
            
            //3 dni tygodniowo - nadgodziny to nic dobrego dla pracodawcy ;)
            int days;
            for(int i = 0; i < s.employees; ++i){
                for(int j = 0; j < (int)((s.days/7)+1); ++j){
                    days = s.countDaysWeek(i, j+1);
                    if     (days == MIN_WEEK_DAYS)   s.rate += 15;
                    else if(days == MIN_WEEK_DAYS-1) s.rate +=  8;
                    else if(days == MIN_WEEK_DAYS-2) s.rate +=  2; 
                    else if(days >  MIN_WEEK_DAYS)   s.rate -= 10;
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
                if     (series_length <= DAYS_SERIES)     s.rate -= 10;
                else if(series_length == DAYS_SERIES - 1) s.rate -=  8;
                else if(series_length == DAYS_SERIES - 2) s.rate -=  4;
            }
                    
            //work every 3rd weekend            
            //TODO
        }  
    }
        
 
    /**
     * Skalowanie sigma funkcji celu (w celu zapobiegania przedwczesnej zbieznosci algorytmu - podobno ma pomagac...)
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
            s.rate = s.rate + (rate_mean - C_PARAM * standard_deviation);
            if(s.rate < 0) s.rate = 0;
        }   
    }
    
    
    /*===MUTACJA===========================================================================*/
    
    public static void mutation(ArrayList<Schedule> population){
        Random rand = new Random(); 
        for(Schedule s : population){
            for(int i = 0; i < s.employees; ++i)
                for(int j = 0; j < s.days; ++j)
                    if(rand.nextInt(100) < MUTATION_PROB){
                        s.table[i][j] = !s.table[i][j];
                    }
        }
    }
    
    /*===SELEKCJA==========================================================================*/
    
    /**
     * Operacja selekcji turniejowej. Wybierana jest tu populacja poddana operatorom genetycznym.
     * Parametr q (liczba osobnikow poddanych turniejowi) odgornie zostal ustalony na wartosc 2.
	 * Turniej wygrywa osobnik z wyzszym parametrem rate
     * @param population Populacja startowa (w obecnym obiegu)
     * @return 
     */
    public static ArrayList<Schedule> preselection(ArrayList<Schedule> population){
        ArrayList<Schedule> selected = new ArrayList<>();
        Random rand = new Random();
        int index1, index2;
        
        while(selected.size() != population.size()){
            index1 = rand.nextInt(population.size()-1);
            while(true){
                index2 = rand.nextInt(population.size()-1);
                if(index1 != index2) break;
            }
            if(population.get(index1).rate > population.get(index2).rate)
                selected.add(new Schedule(population.get(index1)));
            else
                selected.add(new Schedule(population.get(index2)));
        }
        
        return selected;
    }   
    
    /**
     * Operacja sukcesji elitarnej. Dodanie najlepszego osobnika starej populacji do nowej.
     * Osobnik ten zostaje zamieniony z losowym osobnikiem populacji tymczasowej.
     * @param temp_pop Populacja poddana operatorom genetycznym w obecnym obiegu petli
     * @param old_pop Populacja startowa (w obecnym obiegu)
     * @return Wynikowa populacja powstala po pelnym obiegu petli
     */
    public static ArrayList<Schedule> postselection(ArrayList<Schedule> temp_pop, ArrayList<Schedule> old_pop){  // sukcesja elitarna
        ArrayList<Schedule> new_pop_result = temp_pop;
        Random rand = new Random();
        Schedule old_best = old_pop.get(0);
             
        for(int i = 1; i < old_pop.size(); ++i){    // wyszukanie najlepszego ze starej populacji
            if(old_best.rate < old_pop.get(i).rate)
                old_best = old_pop.get(i);
        }
        
        new_pop_result.set(rand.nextInt(new_pop_result.size()-1), old_best);    // zastapienie losowego elementu najlepszym ze starego zestawu        
        
        return new_pop_result;   
    }
    
    
    /*=== CROSSOVERS:  ==============================================================*/
    
    /**
     * Krzyzowanie jednostajne .
     * Krzyzuje ze soba pary nastepujacych po sobie osobnikow. 
     * @param population Populacja tymczasowa (bazowa po preselekcji)
     */
    public static void uniformCrossover(ArrayList<Schedule> population){
        Iterator<Schedule> iterator = population.iterator();
        
        Schedule temp1, temp2;
        while(iterator.hasNext()){
            temp1 = iterator.next();
            
            if(iterator.hasNext()){
                temp2 = iterator.next();
                
                for(int i = 0; i < temp1.table.length; ++i)
                    singleUniformCross(temp1.table[i], temp2.table[i]);                       
            }
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
        //if(a.length != b.length) throw new ArrayIndexOutOfBoundsException();
        
        for(int i = 0; i < a.length; ++i)
            if(rand.nextInt(100) < cross_probability){
                boolean temp = a[i];
                a[i] = b[i];
                b[i] = temp;
            }
    } 
}
