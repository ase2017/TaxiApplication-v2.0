package main.model;

import main.utils.Utils;
import main.view.InitializationWindowView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that generates one more more groups of passengers
 * @author George C. and Jules
 */
public class GroupOfPassengersGenerator {

    private static Random random = new Random();
    private final int minNumberOfPeopleInGroup = 1;
    private int maxNumberOfPeopleInGroup = 10;

    public GroupOfPassengersGenerator(int maxNumberOfPeopleInGroup){
        this.maxNumberOfPeopleInGroup = maxNumberOfPeopleInGroup;
    }

    /**
     * Generates one group of passengers
     * The number of passengers is a random number between 0 and MAX_NUMBER_OF_PEOPLE_IN_GROUP
     * The destination name is randomly picked inside DestinationList
     * @return a group of passenger
     */
    public GroupOfPassengers generateGroupOfPassengers() {
        return new GroupOfPassengers(Utils.getIntBetween(minNumberOfPeopleInGroup,maxNumberOfPeopleInGroup),
                                    DestinationList.getRandomDestinationName());
    }

    /**
     * Generates n groups of passengers
     * The number of passengers is a random number between MIN_NUMBER_OF_PEOPLE_IN_GROUP and MAX_NUMBER_OF_PEOPLE_IN_GROUP
     * The destination name is randomly picked inside DestinationList
     * @param n : the number of group of passengers
     * @return an arraylist of groups of passengers of size n if n positive, else it's empty
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



    public static void setRandom(Random random) {
        GroupOfPassengersGenerator.random = random;
    }

    public int getMinNumberOfPeopleInGroup() {
        return minNumberOfPeopleInGroup;
    }

    public int getMaxNumberOfPeopleInGroup() {
        return maxNumberOfPeopleInGroup;
    }

    public void setMaxNumberOfPeopleInGroup(int maxNumberOfPeopleInGroup) {
        this.maxNumberOfPeopleInGroup = maxNumberOfPeopleInGroup;
    }
}
