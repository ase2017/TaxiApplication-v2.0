package main.controller;

import main.model.MainModel;
import main.view.SimulationView;

public class SimulationController {

    private MainModel mm;
    private SimulationView sv;

    public SimulationController(MainModel mm, SimulationView sv){

        this.mm = mm;
        this.sv = sv;

    }

}
