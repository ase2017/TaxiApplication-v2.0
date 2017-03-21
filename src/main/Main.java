package main;

import main.controller.SimulationController;
import main.model.MainModel;
import main.view.InitializationWindowView;
import main.view.SimulationView;

public class Main {



    public static void main(String[] args) {

        //  jules : testing git on new comp

        // TODO : tick box for
        // - generating automatically taxis
        // - generating automatically groups
        // - activating / deactivating automatic break

        // TODO : save number of groups who got a taxi
        // TODO : save number of taxis
        // TODO : save number of passengers

        // TODO : number of groups / passengers served per window
        // TODO : maybe graph

        // TODO : average / median / min / max / variance wait

        System.out.println("Hello World!");

        MainModel mainModel = new MainModel();
        InitializationWindowView view = new InitializationWindowView(mainModel);
        SimulationView sv = new SimulationView(mainModel);

        SimulationController sc = new SimulationController(mainModel, sv);

        int numberOfTaxis = 10;
        int numberOfGroups = 10;
        int numberOfWindows = 3;
        //int numberOfTaxis = view.getNumberOfTaxis();
        //int numberOfGroups = view.getMaxPassengersPerGroup();
        //int numberOfWindows = view.getNumberOfWindows();

        //view.initializeView();
        //mainModel.run();



    }
}
