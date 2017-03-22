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

public class SimulationController {

    private MainModel mm;
    private SimulationView sv;

    public SimulationController(MainModel mm, SimulationView sv){

        this.mm = mm;
        this.sv = sv;

        sv.addListeners(new SimulationButtonsListener());

        System.out.println(mm.getWindows().length);
        System.out.println(mm.getTaxiData().getTaxiQueue().getTaxisQueue().size());

    }



    class SimulationButtonsListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            JComponent temp = (JComponent) e.getSource();

            System.out.println("Action Performed");
            if(temp == sv.getStartButton()){

                LoggerSingleton.getInstance().add("Starting");
                sv.getStartButton().setEnabled(false);
                mm.addObserver(sv);
                for (int i = 0; i < mm.getWindows().length; i++){
                    mm.getWindows()[i].addObserver(sv);
                }
                mm.getTaxiData().getPassengerQueue().addObserver(sv);
                mm.getTaxiData().getTaxiQueue().addObserver(sv);
                mm.run();
                sv.enableButtonsOnStart();


                // GENERAL PAUSE / RESUME
            } else if(temp == sv.getResumeButton()){
                sv.swapButton();
                //mm.pauseOrResumeAllWindowsOnBreak();

                // GENERAL STOP
            } else if(temp == sv.getStopButton()){
                sv.disableButtonsOnStop();
                mm.stopAllWindows();

                // ADD TAXI
            } else if(temp == sv.getRightPanel().getTaxiPanel().getSubButton()){
                mm.getTaxiData().generateAndAddTaxi();

                // ADD GROUP
            } else if(temp == sv.getRightPanel().getGroupPanel().getSubButton()){
                mm.getTaxiData().generateAndAddGroup();

                // EXPORT
            } else if (temp == sv.getExportButton()) {


                LoggerSingleton.getInstance().exportData();

                SwingUtilities.invokeLater(() -> {
                    TotalAppStatisticsView t = new TotalAppStatisticsView();
                    mm.setStats(new Stats(mm.getTaxiData(),mm.getWindows()));
                    t.setStats(mm.getStats());
                    t.initAndShowGUI();
                });

            } else if(temp.getName().contains("graphButton")){

                SwingUtilities.invokeLater(() -> {
                    IndividualWindowStatisticsView t = new IndividualWindowStatisticsView(Integer.parseInt(temp.getName().replace("graphButton","")));
                    mm.setStats(new Stats(mm.getTaxiData(),mm.getWindows()));
                    t.setStats(mm.getStats());
                    t.initAndShowGUI();
                });

            // specific breakbutton
            }else if(temp.getName().contains("breakButton")){


                int index =Integer.parseInt(temp.getName().replace("breakButton",""));
                mm.goBreakOrGoBack(index);

            // taxi tick box
            } else if(temp == sv.getRightPanel().getTaxiPanel().getSubCheck()){
                if(sv.getRightPanel().getTaxiPanel().getSubCheck().isSelected()) {
                    //md.AUTOMATIC_ADDING_OF_TAXIS = true;
                } else {
                    //md.AUTOMATIC_ADDING_OF_TAXIS = false;
                }
                // group tick box
            } else if(temp == sv.getRightPanel().getGroupPanel().getSubCheck()) {
                if (sv.getRightPanel().getGroupPanel().getSubCheck().isSelected()) {
                    //md.AUTOMATIC_ADDING_OF_GROUPS = true;
                } else {
                    //md.AUTOMATIC_ADDING_OF_GROUPS = false;
                }

            }
        }
    }

}
