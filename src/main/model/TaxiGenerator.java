package main.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class that generates one or more Taxi
 * @author George C. and Jules
 */
public class TaxiGenerator {

    public final static int MINIMUM_NUMBER_OF_PASSENGERS = 3;
    public final static int MAXIMUM_NUMBER_OF_PASSENGERS = 7;

    /**
     * Generates a Taxi
     * The maximum number of passengers is randomly set between MINIMUM_NUMBER_OF_PASSENGERS and MAXIMUM_NUMBER_OF_PASSENGERS
     * The registration number is randomly generated. It is in the French vehicle plate format.
     * @return a Taxi
     */
    public static Taxi generateTaxi() {
        String taxiRegistrationNumber = generateRandomRegistrationNumber();
        int maximumNumberOfPassengers = generateMaximumNumberOfPassengers();

        return new Taxi(taxiRegistrationNumber,maximumNumberOfPassengers);
    }

    /**
     * Generates n taxis
     * The maximum number of passengers is randomly set between MINIMUM_NUMBER_OF_PASSENGERS and MAXIMUM_NUMBER_OF_PASSENGERS
     * The registration number is randomly generated. It is in the French vehicle plate format.
     * @param n the number of taxis
     * @return an arraylist of taxis of size n if n positive, else it's empty
     */
    public static ArrayList<Taxi> generateTaxi(int n) {

        ArrayList<Taxi> taxis = new ArrayList<>();

        if (n > 0) {
            for (int i = 0; i < n; i++){
                taxis.add(generateTaxi());
            }
        }

        return taxis;
    }

    /**
     * returns a random taxi registration number, in the French vehicle plate format
     * It is composed of 2 upper case letters, 3 numbers, 2 upper case letters
     * E.G. ZX262AZ
     * @return a String which is the registration number
     */
    public static String generateRandomRegistrationNumber(){

        Random r = new Random();
        String result = "" + (char)(r.nextInt(26) + 'A')
                            + (char)(r.nextInt(26) + 'A')
                            + r.nextInt(10)
                            + r.nextInt(10)
                            + r.nextInt(10)
                            + (char)(r.nextInt(26) + 'A')
                            + (char)(r.nextInt(26) + 'A');
        return result;
    }

    /**
     * Returns a random number between MINIMUM_NUMBER_OF_PASSENGERS and MAXIMUM_NUMBER_OF_PASSENGERS
     * @return the maximum number of passengers
     */
    public static int generateMaximumNumberOfPassengers(){
        return ThreadLocalRandom.current().nextInt(MINIMUM_NUMBER_OF_PASSENGERS, MAXIMUM_NUMBER_OF_PASSENGERS + 1);
    }
}
