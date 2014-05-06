/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Main.Genetic;
import Main.Main;

/**
 *
 * @author Mariusz
 */
public class MainFrame extends javax.swing.JFrame {

    protected Thread program;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        GPanel = new javax.swing.JPanel();
        MutProbLabel = new javax.swing.JLabel();
        CrossLimLabel = new javax.swing.JLabel();
        MutLimLabel = new javax.swing.JLabel();
        PopNumLabel = new javax.swing.JLabel();
        PopNumSpinner = new javax.swing.JSpinner();
        IterLimitSpinner = new javax.swing.JSpinner();
        IterLimitCheckbox = new javax.swing.JCheckBox();
        MutProbSpinner = new javax.swing.JSpinner();
        CrossLimSpinner = new javax.swing.JSpinner();
        MutLimSpinner = new javax.swing.JSpinner();
        ScaleCheckBox = new javax.swing.JCheckBox();
        SPanel = new javax.swing.JPanel();
        EmpLabel = new javax.swing.JLabel();
        EmployeesNumberSpinner = new javax.swing.JSpinner();
        DaysNumberLabel = new javax.swing.JLabel();
        DaysNumberSpinner = new javax.swing.JSpinner();
        MinEmpPerDayLabel = new javax.swing.JLabel();
        MinEmpPerDaySpinner = new javax.swing.JSpinner();
        WorkingDaysLabel = new javax.swing.JLabel();
        WorkingDaysSpinner = new javax.swing.JSpinner();
        DaySeriesLabel = new javax.swing.JLabel();
        DaySeriesSpinner = new javax.swing.JSpinner();
        StartButton = new javax.swing.JButton();
        StopButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Schedule");
        setResizable(false);

        MutProbLabel.setText("Mutation probability:");

        CrossLimLabel.setText("Crossover limit:");

        MutLimLabel.setText("Mutation limit:");

        PopNumLabel.setText("Population number:");

        PopNumSpinner.setValue(Main.POP_NUMBER);
        PopNumSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                PopNumSpinnerStateChanged(evt);
            }
        });

        IterLimitSpinner.setValue(Main.ITERATION_BLOCKADE);
        IterLimitSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                IterLimitSpinnerStateChanged(evt);
            }
        });

        IterLimitCheckbox.setSelected(true);
        IterLimitCheckbox.setText("Iteration limit:");
        IterLimitCheckbox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                IterLimitCheckboxStateChanged(evt);
            }
        });
        IterLimitCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IterLimitCheckboxActionPerformed(evt);
            }
        });

        MutProbSpinner.setValue(Genetic.MUTATION_PROB);
        MutProbSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                MutProbSpinnerStateChanged(evt);
            }
        });

        CrossLimSpinner.setValue(Genetic.CROSSOVER_PERCENT);
        CrossLimSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                CrossLimSpinnerStateChanged(evt);
            }
        });

        MutLimSpinner.setValue(Genetic.MUT_UNABLE_PERCENT);
        MutLimSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                MutLimSpinnerStateChanged(evt);
            }
        });

        ScaleCheckBox.setText("Enable Sigma-Scale");
        ScaleCheckBox.setEnabled(false);
        ScaleCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ScaleCheckBoxStateChanged(evt);
            }
        });
        ScaleCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScaleCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout GPanelLayout = new javax.swing.GroupLayout(GPanel);
        GPanel.setLayout(GPanelLayout);
        GPanelLayout.setHorizontalGroup(
            GPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(GPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GPanelLayout.createSequentialGroup()
                        .addComponent(ScaleCheckBox)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(GPanelLayout.createSequentialGroup()
                        .addGroup(GPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(PopNumLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(MutLimLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IterLimitCheckbox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(GPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PopNumSpinner, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(MutLimSpinner)
                            .addComponent(IterLimitSpinner)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GPanelLayout.createSequentialGroup()
                        .addGroup(GPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(MutProbLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CrossLimLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(GPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CrossLimSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                            .addComponent(MutProbSpinner))))
                .addContainerGap())
        );
        GPanelLayout.setVerticalGroup(
            GPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(GPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MutProbLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MutProbSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(GPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CrossLimLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CrossLimSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(GPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MutLimLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MutLimSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(GPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PopNumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PopNumSpinner))
                .addGap(18, 18, 18)
                .addGroup(GPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IterLimitCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IterLimitSpinner))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScaleCheckBox)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Genetic paremeters", GPanel);

        EmpLabel.setText("Employees:");

        EmployeesNumberSpinner.setValue(Main.EMPLOYEES);
        EmployeesNumberSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                EmployeesNumberSpinnerStateChanged(evt);
            }
        });

        DaysNumberLabel.setText("Days:");

        DaysNumberSpinner.setValue(Main.DAYS);
        DaysNumberSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                DaysNumberSpinnerStateChanged(evt);
            }
        });

        MinEmpPerDayLabel.setText("Minimal employees number per day:");

        MinEmpPerDaySpinner.setValue(Genetic.MIN_EMP_PER_DAYS);
        MinEmpPerDaySpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                MinEmpPerDaySpinnerStateChanged(evt);
            }
        });

        WorkingDaysLabel.setText("Working days limit (per week):");

        WorkingDaysSpinner.setValue(Genetic.MIN_WEEK_DAYS);
        WorkingDaysSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                WorkingDaysSpinnerStateChanged(evt);
            }
        });

        DaySeriesLabel.setText("Maximal acceptable days series:");

        DaySeriesSpinner.setValue(Genetic.DAYS_SERIES);
        DaySeriesSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                DaySeriesSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout SPanelLayout = new javax.swing.GroupLayout(SPanel);
        SPanel.setLayout(SPanelLayout);
        SPanelLayout.setHorizontalGroup(
            SPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EmpLabel)
                    .addComponent(DaysNumberLabel)
                    .addComponent(MinEmpPerDayLabel)
                    .addComponent(WorkingDaysLabel)
                    .addComponent(DaySeriesLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(SPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DaySeriesSpinner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(WorkingDaysSpinner, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(MinEmpPerDaySpinner, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(DaysNumberSpinner, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(EmployeesNumberSpinner, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        SPanelLayout.setVerticalGroup(
            SPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(EmployeesNumberSpinner)
                    .addComponent(EmpLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DaysNumberSpinner)
                    .addComponent(DaysNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MinEmpPerDaySpinner)
                    .addComponent(MinEmpPerDayLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(WorkingDaysSpinner)
                    .addComponent(WorkingDaysLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DaySeriesSpinner)
                    .addComponent(DaySeriesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Schedules parameters", SPanel);

        StartButton.setText("Start");
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });

        StopButton.setText("Stop");
        StopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(StopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StartButton)
                    .addComponent(StopButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EmployeesNumberSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_EmployeesNumberSpinnerStateChanged
     int value = (int) EmployeesNumberSpinner.getValue();
        if(value <= 0){
            Main.EMPLOYEES = 1;
            EmployeesNumberSpinner.setValue(1);
        }
        else
            Main.EMPLOYEES = value;    
    }//GEN-LAST:event_EmployeesNumberSpinnerStateChanged

    private void DaysNumberSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_DaysNumberSpinnerStateChanged
       int value = (int) DaysNumberSpinner.getValue();
        if(value <= 0){
            Main.DAYS = 1;
            DaysNumberSpinner.setValue(1);
        }
        else
            Main.DAYS = value;    
    }//GEN-LAST:event_DaysNumberSpinnerStateChanged

    private void MinEmpPerDaySpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_MinEmpPerDaySpinnerStateChanged
        int value = (int) MinEmpPerDaySpinner.getValue();
        if(value < 0){
            Genetic.MIN_EMP_PER_DAYS = 0;
            MinEmpPerDaySpinner.setValue(0);
        }
        else
            Genetic.MIN_EMP_PER_DAYS = value;  
    }//GEN-LAST:event_MinEmpPerDaySpinnerStateChanged

    private void WorkingDaysSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_WorkingDaysSpinnerStateChanged
        int value = (int) WorkingDaysSpinner.getValue();
        if(value < 0){
            Genetic.MIN_WEEK_DAYS = 0;
            WorkingDaysSpinner.setValue(0);
        }
        else if(value > 7){
            Genetic.MIN_WEEK_DAYS = 7;
            WorkingDaysSpinner.setValue(7);
        }
        else
            Genetic.MIN_WEEK_DAYS = value;  
    }//GEN-LAST:event_WorkingDaysSpinnerStateChanged

    private void DaySeriesSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_DaySeriesSpinnerStateChanged
        int value = (int) DaySeriesSpinner.getValue();
        if(value < 0){
            Genetic.DAYS_SERIES = 0;
            DaySeriesSpinner.setValue(0);
        }
        else
            Genetic.DAYS_SERIES = value;  
    }//GEN-LAST:event_DaySeriesSpinnerStateChanged

    private void MutLimSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_MutLimSpinnerStateChanged
        int value = (int)MutLimSpinner.getValue();
        if(value < 0){
            Genetic.MUT_UNABLE_PERCENT = 0;
            MutLimSpinner.setValue(0);
        }
        else if(value > 100){
            Genetic.MUT_UNABLE_PERCENT = 100;
            MutLimSpinner.setValue(100);
        }
        else
        Genetic.MUT_UNABLE_PERCENT = value;
    }//GEN-LAST:event_MutLimSpinnerStateChanged

    private void CrossLimSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_CrossLimSpinnerStateChanged
        int value = (int)CrossLimSpinner.getValue();
        if(value < 0){
            Genetic.CROSSOVER_PERCENT = 0;
            CrossLimSpinner.setValue(0);
        }
        else if(value > 100){
            Genetic.CROSSOVER_PERCENT = 100;
            CrossLimSpinner.setValue(100);
        }
        else
        Genetic.CROSSOVER_PERCENT = value;
    }//GEN-LAST:event_CrossLimSpinnerStateChanged

    private void MutProbSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_MutProbSpinnerStateChanged
        int value = (int)MutProbSpinner.getValue();
        if(value < 0){
            Genetic.MUTATION_PROB = 0;
            MutProbSpinner.setValue(0);
        }
        else if(value > 100){
            Genetic.MUTATION_PROB = 100;
            MutProbSpinner.setValue(100);
        }
        else
        Genetic.MUTATION_PROB = value;
    }//GEN-LAST:event_MutProbSpinnerStateChanged

    private void IterLimitCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IterLimitCheckboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IterLimitCheckboxActionPerformed

    private void IterLimitCheckboxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_IterLimitCheckboxStateChanged
        if(IterLimitCheckbox.isSelected()){
            IterLimitSpinner.setEnabled(true);
            Main.NO_ITER_FLAG = false;
        }
        else{
            IterLimitSpinner.setEnabled(false);
            Main.NO_ITER_FLAG = true;
        }
    }//GEN-LAST:event_IterLimitCheckboxStateChanged

    private void IterLimitSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_IterLimitSpinnerStateChanged
        int value = (int) IterLimitSpinner.getValue();
        if(value <= 0){
            Main.ITERATION_BLOCKADE = 1;
            IterLimitSpinner.setValue(1);
        }
        else
        Main.ITERATION_BLOCKADE = value;
    }//GEN-LAST:event_IterLimitSpinnerStateChanged

    private void PopNumSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_PopNumSpinnerStateChanged
        int value = (int) PopNumSpinner.getValue();
        if(value <= 0){
            Main.POP_NUMBER = 1;
            PopNumSpinner.setValue(1);
        }
        else
        Main.POP_NUMBER = value;
    }//GEN-LAST:event_PopNumSpinnerStateChanged

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed
        Main.BREAK_FLAG = false;
        Main.PAUSE_FLAG = false;
        program = new Thread(new Main());
        program.start();
    }//GEN-LAST:event_StartButtonActionPerformed

    private void StopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopButtonActionPerformed
        Main.BREAK_FLAG = true;
    }//GEN-LAST:event_StopButtonActionPerformed

    private void ScaleCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ScaleCheckBoxActionPerformed
        if(ScaleCheckBox.isSelected())    
            Main.ENABLED_SCALING = true;
        else
            Main.ENABLED_SCALING = false;
    }//GEN-LAST:event_ScaleCheckBoxActionPerformed

    private void ScaleCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ScaleCheckBoxStateChanged

            
    }//GEN-LAST:event_ScaleCheckBoxStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CrossLimLabel;
    private javax.swing.JSpinner CrossLimSpinner;
    private javax.swing.JLabel DaySeriesLabel;
    private javax.swing.JSpinner DaySeriesSpinner;
    private javax.swing.JLabel DaysNumberLabel;
    private javax.swing.JSpinner DaysNumberSpinner;
    private javax.swing.JLabel EmpLabel;
    private javax.swing.JSpinner EmployeesNumberSpinner;
    private javax.swing.JPanel GPanel;
    private javax.swing.JCheckBox IterLimitCheckbox;
    private javax.swing.JSpinner IterLimitSpinner;
    private javax.swing.JLabel MinEmpPerDayLabel;
    private javax.swing.JSpinner MinEmpPerDaySpinner;
    private javax.swing.JLabel MutLimLabel;
    private javax.swing.JSpinner MutLimSpinner;
    private javax.swing.JLabel MutProbLabel;
    private javax.swing.JSpinner MutProbSpinner;
    private javax.swing.JLabel PopNumLabel;
    private javax.swing.JSpinner PopNumSpinner;
    private javax.swing.JPanel SPanel;
    private javax.swing.JCheckBox ScaleCheckBox;
    private javax.swing.JButton StartButton;
    private javax.swing.JButton StopButton;
    private javax.swing.JLabel WorkingDaysLabel;
    private javax.swing.JSpinner WorkingDaysSpinner;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
