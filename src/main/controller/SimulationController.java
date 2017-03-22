package main.controller;

import main.log.LoggerSingleton;
import main.model.MainModel;
import main.view.SimulationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationController {

    private MainModel mm;
    private SimulationView sv;

    public SimulationController(MainModel mm, SimulationView sv){

        this.mm = mm;
        this.sv = sv;

        //addListeners();

    }

    public void addListeners(){
        sv.addListner(sv.getGroupButton(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == sv.getStartButton()){

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
                }
            }
        });
    }

}
