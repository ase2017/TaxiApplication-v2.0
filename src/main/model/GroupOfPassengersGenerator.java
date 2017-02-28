package main.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Generates one more more groups of passengers
 * @author George C. and Jules
 */
public class GroupOfPassengersGenerator {

    private static Random rd = new Random();
    private static int MAX_NUMBER_OF_PEOPLE_IN_GROUP = 30;

    /**
     *
     * @return
     */
    public static GroupOfPassengers generateGroupOfPassengers() {
        return new GroupOfPassengers(rd.nextInt(MAX_NUMBER_OF_PEOPLE_IN_GROUP+1),DestinationList.getRandomDestinationName());
    }

    /**
     *
     * @param n
     * @return
     */
    public ArrayList<GroupOfPassengers> generateGroupOfPassengers(int n) {

        ArrayList<GroupOfPassengers> groups = new ArrayList<>();

        if (n > 0) {
            for(int i = 0; i < n; i++) {
                groups.add(generateGroupOfPassengers());
            }
        }


        return groups;
    }
}
