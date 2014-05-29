package Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.*;
import javax.swing.table.TableColumn;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Main extends Thread {

    public static int POP_NUMBER          = 50;
    public static int EMPLOYEES           = 20;
    public static int DAYS                = 28;
    public static int ITERATION_BLOCKADE  = 400;
    public static int PLOT_STEP           = 50;
    public static boolean BREAK_FLAG      = false;
    public static boolean NO_ITER_FLAG    = false;
    public static boolean ENABLED_SCALING = false;
    public static boolean COMPARE_LIDERS  = false;
	
    public static boolean VISUAL  = false;
    public static int sim_step  = 7;
    public static double sim_step_m  = 1.4;
    public static int sim_max  = 100;
    public static int iter_of_problem  = 15;
	
	private static Schedule last_local_best;
	private static Schedule local_best;
    
	private void printTimeToEnd(long start_time, double percent){
		Date date = new Date();
		long diff = date.getTime()-start_time;
		long toEnd = (long)((1/percent)*diff)-diff;
		long sec = (long)(toEnd/1000)%60;
		long min = (long)(((long)(toEnd/1000)-sec)/60)%60;
		long hour = (long)((long)(((long)(toEnd/1000)-sec)/60)-min)/60;
		System.out.println(((int)(percent*10000)/(double)100) +"% | " +hour+" h, "+min+" m, "+sec+" s");
	}
	private int how_m(double step){
		double val = 1;
		int i = 0;
		while(val<=sim_max){
			i++;
			val*=step;
		}
		return i;
	}
	private int how_s_st(int step, int max){
		while(max>step) max -= step;
		return max;
	}
	
	private int how_s(int step){
		int val = 1;
		int i = 0;
		while(val<sim_max){
			i++;
			val+=step;
		}
		return i;
	}
	
	private void nonVisualSimulation(){
		Random rand = new Random(); 
		File FileOut;
		PrintWriter pout = null;
		Date date = new Date();
		long time = date.getTime();
				
		ArrayList<Schedule> population_source = ScheduleFactory.createScheduleTable(EMPLOYEES,DAYS,POP_NUMBER);
		double best = 0;
		double it_of_best = 0;
		double sum = 0;
		double best_s = 0;
		double it_of_best_s = 0;
		double sum_s = 0;
		double rating;
		int iter_ofall = 0;
		int iter_all = how_m(sim_step_m)*how_m(sim_step_m)*how_s(sim_step)*how_s(sim_step)*iter_of_problem;
//(sim_max/sim_step)*(sim_max/sim_step)*(sim_max/sim_step)*(sim_max/sim_step)*iter_of_problem*iter_of_data;
		
			//EMPLOYEES  = rand.nextInt(30)+10; 
			//DAYS  = (rand.nextInt(4)+1)*7; 
			//Genetic.MIN_EMP_PER_DAYS  = rand.nextInt(EMPLOYEES/2-1)+3;  
			//Genetic.MIN_WEEK_DAYS     = rand.nextInt(4)+1;
		
		//System.out.println(iter_all+" | "+how_s_st(sim_step, sim_max)+" | "+how_m(sim_step_m)+" | "+how_s(sim_step));
	
		FileOut = new File("plot"+rand.nextInt(100000)+".txt");
		try {
			pout = new PrintWriter(FileOut);
		} catch (FileNotFoundException ex) {}
				
		for(Genetic.MUTATION_PROB = 1 ; Genetic.MUTATION_PROB <= sim_max ; Genetic.MUTATION_PROB *= sim_step_m){	
		for(Genetic.MUTATION_PERCENT = 1 ; Genetic.MUTATION_PERCENT <= sim_max ; Genetic.MUTATION_PERCENT *= sim_step_m){
		for(Genetic.CROSSOVER_PERCENT = how_s_st(sim_step, sim_max) ; Genetic.CROSSOVER_PERCENT <= sim_max ; Genetic.CROSSOVER_PERCENT += sim_step){	
		for(Genetic.MUT_UNABLE_PERCENT = how_s_st(sim_step, sim_max) ; Genetic.MUT_UNABLE_PERCENT <= sim_max ; Genetic.MUT_UNABLE_PERCENT += sim_step){
		best_s = 0;
		it_of_best_s = 0;
		sum_s = 0;	
		for(int j=0 ; j<iter_of_problem ; ++j){			
			ArrayList<Schedule> population = new ArrayList<>(population_source);
			best = 0;
			it_of_best = 0;
			sum = 0;
			for(int i=0 ; i<ITERATION_BLOCKADE ; ++i){
				Genetic.rank(population);
				Collections.sort(population);    
				Genetic.uniformCrossover(population);   
				Genetic.mutation(population);        

				rating = (population.get(0)).rate;
				for(Schedule s : population){
					if(rating < s.rate)
						rating = s.rate;
				}
				if(rating>best){
					best = rating;
					it_of_best = i;
				}
				sum += best;
			}
			best_s += best;
			sum_s += sum;
			it_of_best_s += it_of_best;
			iter_ofall++;
		}
		best = best_s/iter_of_problem;
		sum = sum_s/iter_of_problem;
		it_of_best = it_of_best_s/iter_of_problem;
		pout.println(Genetic.CROSSOVER_PERCENT+" "+Genetic.MUT_UNABLE_PERCENT+" "+Genetic.MUTATION_PROB+" "+Genetic.MUTATION_PERCENT+" "+best+" "+it_of_best+" "+sum);
		printTimeToEnd(time,(double)(iter_ofall)/iter_all);
		}}}}
		pout.close();
		System.out.println("END");
	}

	
    @Override
    public void run() {
		if(!VISUAL){
			nonVisualSimulation();			
			return;
		}
			
        ArrayList<Schedule> population = ScheduleFactory.createScheduleTable(EMPLOYEES,DAYS,POP_NUMBER);
        int iteration_number = 0;
		
        XYSeries series1 = new XYSeries("High");
        XYSeries series2 = new XYSeries("Medium");
        XYSeries series3 = new XYSeries("Low");

        JFrame f = new JFrame();
        f.setSize(640,480);
        f.setVisible(true);

        ChartPanel chPanel = new ChartPanel(null); //creating the chart panel, which extends JPanel
        chPanel.setPreferredSize(new Dimension(640, 480)); //size according to my window
        
        while(true){
            Genetic.rank(population);
            
            if(ENABLED_SCALING)
                Genetic.scaleRank(population); 
            
            if((!NO_ITER_FLAG && iteration_number >= ITERATION_BLOCKADE) || BREAK_FLAG )
                break;
            
                       
            Collections.sort(population);
                                            
            Genetic.uniformCrossover(population);   //crossover for the best schedules in population
            Genetic.mutation(population);           //mutation for the worst schedules in population
            
            
            /****Visualisation******************************/			
			
            local_best = population.get(0);
            for(Schedule s : population){
                if(local_best.rate < s.rate)
                    local_best = s;
			}
			
			// Jeśli lider się zmienił to druk z zmianami
			if(COMPARE_LIDERS 
					&& last_local_best!=local_best && iteration_number!=0){
				printResult();
			}
            last_local_best = local_best;
            series1.add(iteration_number,local_best.rate);
            series2.add(iteration_number,getMediumRating(population));
            series3.add(iteration_number,getLowRating(population));
			
            drawPlot(series1,series2, series3, f, chPanel,iteration_number);
            /**********************************************/
            
            iteration_number++;
        }
		getMediumRating(population);

        Schedule local_best = population.get(0);
        for(Schedule s : population)
            if(local_best.rate < s.rate)
               local_best = s;
        
        if(!COMPARE_LIDERS) printResult();
    }
	/*****************************************************/ 
	
	static int getMediumRating(ArrayList<Schedule> population){
		double[] ratings = new double[POP_NUMBER];
		int i=0;
		for(Schedule s : population){
			ratings[i++] = s.rate;
		}
		Arrays.sort(ratings);
		return (int)ratings[(int)(POP_NUMBER/2)];
	}
	static int getLowRating(ArrayList<Schedule> population){
		double[] ratings = new double[POP_NUMBER];
		int i=0;
		for(Schedule s : population){
			ratings[i++] = s.rate;
		}
		Arrays.sort(ratings);
		return (int)ratings[0];
	}
	static int getHighRating(ArrayList<Schedule> population){
		double[] ratings = new double[POP_NUMBER];
		int i=0;
		for(Schedule s : population){
			ratings[i++] = s.rate;
		}
		Arrays.sort(ratings);
		return (int)ratings[POP_NUMBER-1];
	}

	//zamieniaja '14 working days' -> '12+2 working days'
	static String normalizeWorkingDaysPrint(int best){
		int diffrence = best - Genetic.MIN_WEEK_DAYS*DAYS/7;
		String diffrence_str = "";
		diffrence_str += "" + Genetic.MIN_WEEK_DAYS*DAYS/7;
		if(diffrence>0) diffrence_str += "+";
		if(diffrence!=0) diffrence_str += "" + diffrence;
		return "" + diffrence_str + " working days";	
	}
	static String normalizeEmployersAtDayPrint(int best){
		int diffrence = best - Genetic.MIN_EMP_PER_DAYS;
		String diffrence_str = "";
		diffrence_str += "" + Genetic.MIN_EMP_PER_DAYS;
		if(diffrence>0) diffrence_str += "+";
		if(diffrence!=0) diffrence_str += "" + diffrence;
		return diffrence_str;
	}
	
	static boolean newInBest(int i, int j){
		if(i<0 || j<0) return false;
		if(i>=local_best.employees || j>=local_best.days) return false;
		return local_best.table[i][j] && !last_local_best.table[i][j];
	}
	static boolean lostInBest(int i, int j){
		if(i<0 || j<0) return false;
		if(i>=local_best.employees || j>=local_best.days) return false;
		return !local_best.table[i][j] && last_local_best.table[i][j];
	}
	
    //static void printResult(Schedule best, Schedule last_best){
    static void printResult(){
		Schedule best = local_best;
		Schedule last_best = last_local_best;
        JFrame f = new JFrame();
        int w, h;
        w = (best.days) * 35 + 250;
        h = (best.employees+2) * 20; 
        f.setSize(w,h);
        f.setVisible(true);

        String[] columnNames = new String[best.days+2];
        
        // Filling cells in table
        columnNames[0] = "EMP";     // column titles
        for(int i = 1; i <= best.days; ++i){
            columnNames[i] = "" + i;
        }
        columnNames[columnNames.length-1] = "SUMMARY";

		//Tablica [ile pracowników + SUMMARY][EMP+ile dni+SUMMARY]
        Object[][] data = new Object[best.employees+1][best.days+2];
        
        // working days
        for(int i = 0; i < best.employees; ++i){    
		data[i][0] = "Employee " + (i+1);
			for(int j = 0; j < best.days; ++j){
				if(COMPARE_LIDERS){
					if(best.table[i][j] && last_best.table[i][j])
						data[i][j+1] = "X";
					else if(lostInBest(i,j))
						data[i][j+1] = "-";
					else if(newInBest(i,j))
						data[i][j+1] = "+";
					else
						data[i][j+1] = " ";
				}else{
					if(best.table[i][j])
						data[i][j+1] = "X";
					else
						data[i][j+1] = " ";
				}
			}
			data[i][best.days+1] = normalizeWorkingDaysPrint(best.countDays(i));
		}
        //statistics
        data[best.employees][0] = "SUMMARY";
        for(int i = 0; i < best.days; ++i)
            data[best.employees][i+1] = normalizeEmployersAtDayPrint(best.countEmployees(i));
            
        // Creating and configuration of new table
        final JTable table = new JTable(data, columnNames);
        
        table.setPreferredScrollableViewportSize(new Dimension(520, 150));
        table.setFillsViewportHeight(true);       
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setPreferredScrollableViewportSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		// To powinno malować kolorowe plusy i minusy w połączeniu z klasą na dole
		//table.setDefaultRenderer(Object.class, new MyRenderer());
		
        
        int[] columnsWidth = new int[best.days+2];  //table column width 
        columnsWidth[0] = 100;
        for(int i = 0; i < best.days; ++i)
            columnsWidth[i+1] = 35;
        
        columnsWidth[best.days+1] = 120;

        int i = 0;
        for (int width : columnsWidth) {            
            TableColumn column = table.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        //Add the scroll pane to this panel.
        f.add(scrollPane);
        f.validate();    
    }
    
    static void drawPlot(XYSeries series1,XYSeries series2,XYSeries series3, JFrame f, ChartPanel chPanel, int iteration_number ){
        if(iteration_number % PLOT_STEP == 0) {
                XYSeriesCollection dataset = new XYSeriesCollection();
                dataset.addSeries(series3);
                dataset.addSeries(series2);
                dataset.addSeries(series1);
                // Generate the graph
                JFreeChart chart = ChartFactory.createXYLineChart(
                        "Adaptation graph",         // Title
                        "Generation",               // x-axis Label
                        "Rating",                   // y-axis Label
                        dataset,                    // Dataset
                        PlotOrientation.VERTICAL,   // Plot Orientation
                        true,                       // Show Legend
                        true,                       // Use tooltips
                        false                       // Configure chart to generate URLs?
                );

                chPanel.setChart(chart);
                chPanel.setMouseWheelEnabled(true);
                f.add(chPanel);
                f.validate();
            }
    }

}
/*
//Renderer użyty jeśli chce się kolorować plusy i minusy
class MyRenderer extends DefaultTableCellRenderer {
  @Override
  public Component getTableCellRendererComponent(JTable table, Object value,
                                                 boolean isSelected,
                                                 boolean hasFocus,
                                                 int row, int col) {
    JLabel l = (JLabel)super.getTableCellRendererComponent(table, value,
        isSelected, hasFocus, row, col);
	
		if(Main.newInBest(row,col-1)) l.setForeground(Color.green);
		else if(Main.lostInBest(row,col-1)) l.setForeground(Color.red);
		else l.setForeground(Color.black);
	
    return l;
  }
}*/
