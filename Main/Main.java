package Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


import javax.swing.*;
import javax.swing.table.TableColumn;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Main extends Thread{

    public static int POP_NUMBER         = 50;
    public static int EMPLOYEES          = 20;
    public static int DAYS               = 28;
    public static int ITERATION_BLOCKADE = 5000;
    public static int PLOT_STEP          = 50;
    public static boolean PAUSE_FLAG     = false;
    public static boolean BREAK_FLAG     = false;
    public static boolean NO_ITER_FLAG   = false;
    public static boolean ENABLED_SCALING = false;
    


    @Override
    public void run() {
        ArrayList<Schedule> population = ScheduleFactory.createScheduleTable(EMPLOYEES,DAYS,POP_NUMBER);
        int iteration_number = 0;
        XYSeries series = new XYSeries("XYGraph");

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
            Schedule local_best = population.get(0);
            for(Schedule s : population)
                if(local_best.rate < s.rate)
                    local_best = s;

            series.add(iteration_number,local_best.rate);
            drawPlot(series, f, chPanel,iteration_number);
            /**********************************************/
            
            iteration_number++;
        }

        Schedule local_best = population.get(0);
        for(Schedule s : population)
            if(local_best.rate < s.rate)
               local_best = s;
        
        printResult(local_best);
    }
   /*****************************************************/ 
    
    static void printResult(Schedule best){
        JFrame f = new JFrame();
        int w, h;
        w = (best.days) * 35 + 230;
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


        Object[][] data = new Object[best.employees+1][best.days+2];
        
        // working days
        for(int i = 0; i < best.employees; ++i){    
         data[i][0] = "Employee " + (i+1);
            for(int j = 0; j < best.days; ++j){
               if(best.table[i][j])
                   data[i][j+1] = "X";
               else
                   data[i][j+1] = " ";
           }
           data[i][best.days+1] = "" + best.countDays(i) + " working days";
        }
        //statistics
        data[best.employees][0] = "SUMMARY";
        for(int i = 0; i < best.days; ++i)
            data[best.employees][i+1] = "" +best.countEmployees(i);
            
        // Creating and configuration of new table
        final JTable table = new JTable(data, columnNames);
        
        table.setPreferredScrollableViewportSize(new Dimension(500, 150));
        table.setFillsViewportHeight(true);       
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setPreferredScrollableViewportSize(Toolkit.getDefaultToolkit().getScreenSize());
        
        int[] columnsWidth = new int[best.days+2];  //table column width 
        columnsWidth[0] = 100;
        for(int i = 0; i < best.days; ++i)
            columnsWidth[i+1] = 35;
        
        columnsWidth[best.days+1] = 100;

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
    
    static void drawPlot(XYSeries series, JFrame f, ChartPanel chPanel, int iteration_number ){
        if(iteration_number % PLOT_STEP == 0) {
                XYSeriesCollection dataset = new XYSeriesCollection();
                dataset.addSeries(series);
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
