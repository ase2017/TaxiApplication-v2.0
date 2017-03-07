package main;

import main.controller.TaxiAppController;
import main.model.TaxiData;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        int numberOfTaxis = 10;
        int numberOfGroups = 15;
        int numberOfWindows = 10;
        TaxiData taxiData = new TaxiData(numberOfTaxis,numberOfGroups);
        TaxiAppController taxiAppController = new TaxiAppController(taxiData);
    }
}
