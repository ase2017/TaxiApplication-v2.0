package main.model;

import java.util.ArrayList;

public class TaxiGenerator {

    public static Taxi generateTaxi() {

        String taxiRegistrationNumber = generateRandomRegistrationNumber();
        int maximumNumberOfPassengers = generateMaximumNumberOfPassengers();

        return new Taxi(taxiRegistrationNumber,maximumNumberOfPassengers);

    }

    public static ArrayList<Taxi> generateTaxi(int n) {
        ArrayList<Taxi> taxis = new ArrayList<>();

        // TODO : check that registrationNumber does not exist before adding

        return taxis;
    }

    public static String generateRandomRegistrationNumber(){
        return "";
    }

    public static int generateMaximumNumberOfPassengers(){
        return 0;
    }
}
