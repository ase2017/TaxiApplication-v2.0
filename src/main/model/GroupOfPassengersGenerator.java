package main.model;

import main.utils.Utils;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class that generates one more more groups of passengers
 * @author George C. and Jules
 */
public class GroupOfPassengersGenerator {

    private static Random random = new Random();
    public final static int MIN_NUMBER_OF_PEOPLE_IN_GROUP = 1;
    public static int MAX_NUMBER_OF_PEOPLE_IN_GROUP = 10;

    /**
     * Generates one group of passengers
     * The number of passengers is a random number between 0 and MAX_NUMBER_OF_PEOPLE_IN_GROUP
     * The destination name is randomly picked inside DestinationList
     * @return a group of passenger
     */
    public static GroupOfPassengers generateGroupOfPassengers() {
        return new GroupOfPassengers(Utils.getIntBetween(MIN_NUMBER_OF_PEOPLE_IN_GROUP,MAX_NUMBER_OF_PEOPLE_IN_GROUP),
                                    DestinationList.getRandomDestinationName());
    }

    /**
     * Generates n groups of passengers
     * The number of passengers is a random number between MIN_NUMBER_OF_PEOPLE_IN_GROUP and MAX_NUMBER_OF_PEOPLE_IN_GROUP
     * The destination name is randomly picked inside DestinationList
     * @param n : the number of group of passengers
     * @return an arraylist of groups of passengers of size n if n positive, else it's empty
     */
    public static ArrayList<GroupOfPassengers> generateGroupOfPassengers(int n) {

        ArrayList<GroupOfPassengers> groups = new ArrayList<>();

        if (n > 0) {
            for(int i = 0; i < n; i++) {
                groups.add(generateGroupOfPassengers());
            }
        }

        return groups;
    }



}
