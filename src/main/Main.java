package main;

import main.model.MainModel;
import main.view.InitializationWindowView;

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

        InitializationWindowView view = new InitializationWindowView();
        int numberOfTaxis = 10;
        int numberOfGroups = 10;
        int numberOfWindows = 3;
        //int numberOfTaxis = view.getNumberOfTaxis();
        //int numberOfGroups = view.getMaxNumberOfGroups();
        //int numberOfWindows = view.getNumberOfWindows();
        MainModel mainModel = new MainModel(numberOfTaxis,numberOfGroups,numberOfWindows);
        //view.initializeView();
        mainModel.run();


        //TaxiAppController taxiAppController = new TaxiAppController(mainModel);
    }
}
