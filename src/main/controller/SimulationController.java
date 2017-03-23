package main.controller;

import main.log.LoggerSingleton;
import main.model.MainModel;
import main.model.Stats;
import main.view.IndividualWindowStatisticsView;
import main.view.SimulationView;
import main.view.TotalAppStatisticsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;

/**
 * Class Name: SimulationController.java
 *
 * Description: This class is the controller of the MVC Pattern for the Simulation
 *              mode. This controller is connected with the {@link SimulationView} and the
 *              {@link MainModel}.
 *
 * @author George Goniotakis
 * @since Mar 18, 2017
 */
public class SimulationController {

    private MainModel mm; //The main model
    private SimulationView sv; //The simulation view

    /**
     * This is the constructor of this class. It receives the main model
     * and the simulation view and creates a new Object.
     *
     * @param mm The main model
     * @param sv The simulation view
     */
    public SimulationController(MainModel mm, SimulationView sv){

        this.mm = mm;
        this.sv = sv;

        sv.addListeners(new SimulationButtonsListener()); //Call the method that adds the listeners to the buttons

    }

    /**
     * This class is used to track the events of the buttons. It used because
     * in MVC pattern, the controller includes all the methods that are used to
     * track the events of the buttons.
     */
    class SimulationButtonsListener implements ActionListener {

        private final int INTERVAL_SECONDS = 5; //Add a new taxi/group in the queue every 5 seconds
        private Timer taxiTimer, groupTimer; //The two timers for automatically adding the taxis and the groups

        /**
         * This method includes the action events for all the buttons
         * in the {@link SimulationView}.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            JComponent triggeredComponent = (JComponent) e.getSource(); //The component that was triggered

            /* If the start button is pressed */
            if(triggeredComponent == sv.getStartButton()){

                LoggerSingleton.getInstance().add("Starting"); //Add a record in the log file
                sv.getStartButton().setEnabled(false); //Disable that start button

                /* Add observers for each textarea */
                mm.addObserver(sv);
                for (int i = 0; i < mm.getWindows().length; i++){
                    mm.getWindows()[i].addObserver(sv);
                }
                mm.getTaxiData().getPassengerQueue().addObserver(sv);
                mm.getTaxiData().getTaxiQueue().addObserver(sv);
                mm.run();
                sv.enableButtonsOnStart();

            }

            /* If the stop button is pressed */
            else if(triggeredComponent == sv.getStopButton()){

                sv.disableButtonsOnStop();
                mm.stopAllWindows(); //Stop the execution for all windows that serve

            }

            /* If the "Add Taxi" button is pressed */
            else if(triggeredComponent == sv.getRightPanel().getTaxiPanel().getSubButton()){

                mm.getTaxiData().generateAndAddTaxi(); //Add a new taxi to the queue

            }

            /* If the "Add Group" button is pressed */
            else if(triggeredComponent == sv.getRightPanel().getGroupPanel().getSubButton()){

                mm.getTaxiData().generateAndAddGroup();

            }

            /* If the "Export" button is pushed */
            else if (triggeredComponent == sv.getExportButton()) {

                LoggerSingleton.getInstance().exportData(); //Export data to file

                /* Show graph with overall statistics */
                SwingUtilities.invokeLater(() -> {
                    TotalAppStatisticsView t = new TotalAppStatisticsView();
                    mm.setStats(new Stats(mm.getTaxiData(),mm.getWindows()));
                    t.setStats(mm.getStats());
                    t.initAndShowGUI();
                });

            }

            /* If individual "Show graph" button is pressed*/
            else if(triggeredComponent.getName().contains("graphButton")){

                /* Produce and show individual statistics graph */
                SwingUtilities.invokeLater(() -> {
                    IndividualWindowStatisticsView t = new IndividualWindowStatisticsView(Integer.parseInt(triggeredComponent.getName().replace("graphButton","")));
                    mm.setStats(new Stats(mm.getTaxiData(),mm.getWindows()));
                    t.setStats(mm.getStats());
                    t.initAndShowGUI();
                });

            }

            /* If individual "End Of Day" button is pressed */
            else if(triggeredComponent.getName().contains("endOfDayButton")){

                mm.endOfDay(Integer.parseInt(triggeredComponent.getName().replace("endOfDayButton",""))); //Stop execution in this window

            }

            /* If "Automatically Add taxis" checkbox is triggered */
            else if(triggeredComponent == sv.getRightPanel().getTaxiPanel().getSubCheck()){

                /* If it is checked, add new taxi to the queue every some interval */
                if(sv.getRightPanel().getTaxiPanel().getSubCheck().isSelected()) {

                    taxiTimer = new Timer();
                    taxiTimer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            mm.getTaxiData().generateAndAddTaxi();
                        }
                    }, 0,INTERVAL_SECONDS*1000);

                }

                /* If it is unchecked, stop adding taxis automatically to the queue*/
                else {
                    taxiTimer.cancel();
                }

            }

            /* If "Automatically Add groups" checkbox is triggered */
            else if(triggeredComponent == sv.getRightPanel().getGroupPanel().getSubCheck()) {

                /* If it is checked, add new group to the queue every some interval */
                if (sv.getRightPanel().getGroupPanel().getSubCheck().isSelected()) {

                    groupTimer = new Timer();
                    groupTimer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            mm.getTaxiData().generateAndAddGroup();
                        }
                    }, 0,INTERVAL_SECONDS*1000);

                }

                /* If it is unchecked, stop adding groups automatically to the queue*/
                else {
                    groupTimer.cancel();
                }

            }
        }
    }
}
