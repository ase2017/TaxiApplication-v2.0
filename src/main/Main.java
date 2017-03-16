package main;

import main.controller.TaxiAppController;
import main.model.MainModel;
import main.model.Stats;
import main.model.TaxiData;

public class Main {



    public static void main(String[] args) {

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

        int numberOfTaxis = 10;
        int numberOfGroups = 6;
        int numberOfWindows = 3;
        MainModel mainModel = new MainModel(numberOfTaxis,numberOfGroups,numberOfWindows);
        mainModel.run();


        //TaxiAppController taxiAppController = new TaxiAppController(mainModel);
    }
}
