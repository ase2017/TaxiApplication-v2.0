package main.model;

/**
 * Classes that represents the groups of passengers waiting for the Taxi
 * @author George C. and Jules
 */
public class GroupOfPassengers {

    private int numberOfPassengers;
    private String destinationName;

    public GroupOfPassengers(int numberOfPassengers, String destinationName) {
        this.numberOfPassengers = numberOfPassengers;
        this.destinationName = destinationName;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }
}
