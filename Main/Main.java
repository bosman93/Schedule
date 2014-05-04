package Main;

import java.awt.*;
import java.util.ArrayList;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class Main {

    public static int POP_NUMBER         = 25;
    public static int EMPLOYEES          = 30;
    public static int DAYS               = 28;
    public static int ITERATION_BLOCKADE = 100000;

    public static void main(String[] args) {
        ArrayList<Schedule> population = ScheduleFactory.createScheduleTable(EMPLOYEES,DAYS,POP_NUMBER); // :D ta fabryka jest napisana dla jaj w sumie :D nie chcialem tu dodatkowego kodu wiec to dopisalem
        ArrayList<Schedule> temporary;

        int iteration_number = 0;
        XYSeries series = new XYSeries("XYGraph");


        JFrame f = new JFrame();
        f.setSize(640,480);
        f.setVisible(true);

        ChartPanel chPanel = new ChartPanel(null); //creating the chart panel, which extends JPanel
        chPanel.setPreferredSize(new Dimension(640, 480)); //size according to my window
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
            //System.out.print("" + iteration_number + ": " + local_best.rate + "\n");
            /**************************************/


            series.add(iteration_number,local_best.rate);

            if(iteration_number % 50 == 0) {
                XYSeriesCollection dataset = new XYSeriesCollection();
                dataset.addSeries(series);
                // Generate the graph
                JFreeChart chart = ChartFactory.createXYLineChart(
                        "Adaptation graph", // Title
                        "Generation", // x-axis Label
                        "Rating", // y-axis Label
                        dataset, // Dataset
                        PlotOrientation.VERTICAL, // Plot Orientation
                        true, // Show Legend
                        true, // Use tooltips
                        false // Configure chart to generate URLs?
                );

                chPanel.setChart(chart);
                chPanel.setMouseWheelEnabled(true);
                f.add(chPanel);
                f.validate();
            }
            iteration_number++;
        }while(true);

        Schedule local_best = population.get(0);
        for(Schedule s : population)
            if(local_best.rate < s.rate)
               local_best = s;
        
        System.out.print(local_best.toString() + local_best.rate + "\n");
    }

}
