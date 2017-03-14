package main;

import main.controller.TaxiAppController;
import main.model.MainModel;
import main.model.TaxiData;

public class Main {

    // if true : we can click to add taxis / groups
    // if false : we cannot click to add taxis / groups
    public static boolean MANUAL_MODE;

    public static void main(String[] args) {

        // TODO : tick box for
        // - generating automatically taxis
        // - generating automatically groups
        // - activating / desactivating automatic break

        // TODO : save number of groups who got a taxi
        // TODO : save number of taxis
        // TODO : save number of passengers

        // TODO : add time
        // TODO : number of groups / passengers served per window
        // TODO : maybe graph

        // TODO : average / median / min / max / variance wait

        System.out.println("Hello World!");
        MANUAL_MODE = false;
        int numberOfTaxis = 6;
        int numberOfGroups = 6;
        int numberOfWindows = 3;
        MainModel mainModel = new MainModel(numberOfTaxis,numberOfGroups,numberOfWindows);
        mainModel.run();
        //TaxiAppController taxiAppController = new TaxiAppController(mainModel);
    }
}
